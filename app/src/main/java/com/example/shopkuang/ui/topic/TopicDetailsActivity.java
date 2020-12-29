package com.example.shopkuang.ui.topic;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkuang.R;
import com.example.shopkuang.adapter.topic.TopicDetailsButtomAdapter;
import com.example.shopkuang.adapter.topic.TopicDetailsCommentAdapter;
import com.example.shopkuang.adapter.topic.TopicDetailsAdapter;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseActivity;

import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.topic.TopicDetailisCommentBean;
import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;
import com.example.shopkuang.bean.bean.topic.TopicRelevantBean;
import com.example.shopkuang.interfaces.home.ITopicHome;
import com.example.shopkuang.presenter.TopicDetailsPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopicDetailsActivity extends BaseActivity<ITopicHome.Presenter> implements ITopicHome.View {

    private int topicid;
    private RecyclerView topic_recycle;
    private ImageView topic_write;
    private RecyclerView topic_leave;
    private RecyclerView topic_recycle_bottom;
    private Button btn_topic_more;

    @Override
    protected int getLayout() {
        return R.layout.activity_topicdetails_layout;
    }

    @Override
    protected ITopicHome.Presenter createPrenter() {
        return new TopicDetailsPresenter(this);
    }


    @Override
    protected void initView() {
        //todo  评论上边recycle
        topic_recycle = findViewById(R.id.recycle_topic);
        //todo 点击评论
        topic_write = findViewById(R.id.iv_topic_write);
        //todo 评论recycleview
        topic_leave = findViewById(R.id.recycle_topic_leave);
        //todo 查看更多
        btn_topic_more = findViewById(R.id.btn_topic_more);

        //todo 详情底部数据
        topic_recycle_bottom = findViewById(R.id.recycle_topic_bottom);


        //接受传过来的id  pageid
        topicid = (int) MyApp.getMap().get("topicid");
    }

    @Override
    protected void initData() {
        presenter.getTopicDetailsData(topicid);  //todo  详情数据
        presenter.getTopicCommentData(getMap());  //todo 详情评论数据
        presenter.getTopicBottomData(topicid);  //todo  详情底部数据
    }

    private HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("valueId",String.valueOf(topicid));
        map.put("typeId",String.valueOf(1));
        map.put("size",String.valueOf(5));
        return map;
    }

    //todo  专题详情数据
    @Override
    public void getTopicDetailsReturn(TopicDetailsBean result) {
        TopicDetailsBean.DataBean data = result.getData();
        initgetImage(data.getContent());   //todo  h5分割图片
    }


    @Override
    public void getTopicCommentReturn(TopicDetailisCommentBean result) {
        //todo 评论数据
        List<TopicDetailisCommentBean.DataBeanX.DataBean> data = result.getData().getData();

        if (result.getData().getData() != null && result.getData().getData().size() > 0) {
            topic_leave.setLayoutManager(new LinearLayoutManager(this));
            topic_leave.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            TopicDetailsCommentAdapter specialDetailsCommentAdapter = new TopicDetailsCommentAdapter(this, data);
            topic_leave.setAdapter(specialDetailsCommentAdapter);
            specialDetailsCommentAdapter.notifyDataSetChanged();


            //todo  查看更多
            btn_topic_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TopicDetailsActivity.this, TopicDetailsActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            btn_topic_more.setVisibility(View.GONE);
        }
    }

    @Override
    public void getTopicBottomReturn(TopicRelevantBean result) {
        //todo  详情底部数据
        List<TopicRelevantBean.DataBean> data = result.getData();

        topic_recycle_bottom.setLayoutManager(new LinearLayoutManager(this));
        TopicDetailsButtomAdapter topicDetailsButtomAdapter = new TopicDetailsButtomAdapter(this, data);
        topic_recycle_bottom.setAdapter(topicDetailsButtomAdapter);
        topicDetailsButtomAdapter.notifyDataSetChanged();


        //点击底部数据详情
        topicDetailsButtomAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = data.get(pos).getId();
                Intent intent = new Intent(TopicDetailsActivity.this,TopicDetailsActivity.class);
                presenter.getTopicDetailsData(id);
                presenter.getTopicBottomData(id);
                HashMap<String, String> map = new HashMap<>();
                map.put("valueId",String.valueOf(id));
                map.put("typeId",String.valueOf(1));
                map.put("size",String.valueOf(5));
                presenter.getTopicCommentData(map);
                startActivity(intent);
            }
        });
    }

    //TODO H5分割图片
    private void initgetImage(String content) {
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            //判断图片的格式
            if (end > 0) {
                String url = word.substring(start, end);
                if (url != null) {
                    url = "http:" + url + ".jpg";
                    list.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    list.add(url);
                } else {
                    return;
                }
            }
        }
        topic_recycle.setLayoutManager(new LinearLayoutManager(this));
        TopicDetailsAdapter topicDetailsAdapter = new TopicDetailsAdapter(this, list);
        topic_recycle.setAdapter(topicDetailsAdapter);
        topicDetailsAdapter.notifyDataSetChanged();

    }
}
