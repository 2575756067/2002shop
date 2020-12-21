package com.example.shopkuang.ui.topic;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkuang.R;
import com.example.shopkuang.adapter.topic.TopicFragmentAdapter;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.base.BaseFragment;
import com.example.shopkuang.bean.shop.ChanneBean;
import com.example.shopkuang.bean.shop.ChanneShujuBean;
import com.example.shopkuang.bean.shop.HomeBean;
import com.example.shopkuang.bean.shop.NewsBean;
import com.example.shopkuang.bean.shop.NewsShujuBean;
import com.example.shopkuang.bean.shop.TopicBean;
import com.example.shopkuang.bean.shop.pinpai.BrandBean;
import com.example.shopkuang.bean.shop.pinpai.BrandlistBean;
import com.example.shopkuang.bean.shop.pinpai.BrandlistRecyBean;
import com.example.shopkuang.interfaces.home.IHome;
import com.example.shopkuang.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TopicFragment extends BaseFragment<IHome.Presenter> implements IHome.View {


    @BindView(R.id.topic_recycle)
    RecyclerView topicRecycle;

    @BindView(R.id.topic_scroll)
    NestedScrollView topicScroll;
    @BindView(R.id.iv_special_all)
    ImageView ivSpecialAll;
    @BindView(R.id.tv_special_loading)
    TextView tvSpecialLoading;
    @BindView(R.id.topic_btn_top)
    RadioButton topicBtnTop;
    @BindView(R.id.topic_btn_next)
    RadioButton topicBtnNext;

    private ArrayList<TopicBean.DataBeanX.DataBean> list;
    private TopicFragmentAdapter adapter;
    private int ONE = 1;
    private int TWO = 2;

    private int page = ONE;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected Presenter createPrenter() {
        return new Presenter(this);
    }

    @Override
    protected void initView() {
        topicRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new TopicFragmentAdapter(getActivity(), list);
        topicRecycle.setAdapter(adapter);


        //跳转到专题详情
        adapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = list.get(pos).getId();
                MyApp.getMap().put("topicid",id);
                startActivity(new Intent(getActivity(),TopicDetailsActivity.class));
            }
        });

    }

    @Override
    protected void initData() {
        presenter.getTopicData(page);
    }

    @Override
    public void getHome(HomeBean homeBean) {

    }

    @Override
    public void getChanne(ChanneBean channeBean) {

    }

    @Override
    public void getChanneShuju(ChanneShujuBean channeShujuBean) {

    }

    @Override
    public void getBrand(BrandBean brandBean) {

    }

    @Override
    public void getNews(NewsBean newsBean) {

    }

    @Override
    public void getNewsShuju(NewsShujuBean shujuBean) {

    }

    @Override
    public void getBrandlist(BrandlistBean brandlistBean) {

    }

    @Override
    public void getBrandlistRecy(BrandlistRecyBean brandlistRecyBean) {

    }

    @Override
    public void getTopic(TopicBean topicBean) {
        //清空集合
        list.clear();
        List<TopicBean.DataBeanX.DataBean> data = topicBean.getData().getData();
        list.addAll(data);
        adapter.notifyDataSetChanged();

        //隐藏加载中
        ivSpecialAll.setVisibility(View.GONE);
        tvSpecialLoading.setVisibility(View.GONE);
    }

    @OnClick({R.id.topic_btn_top, R.id.topic_btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topic_btn_top:
                //点击上一页  更换page
                page = ONE;
                //隐藏加载中
                ivSpecialAll.setVisibility(View.GONE);
                tvSpecialLoading.setVisibility(View.GONE);
                presenter.getTopicData(page);
                //返回顶部
                topicScroll.fullScroll(ScrollView.FOCUS_UP);
                break;
            case R.id.topic_btn_next:
                //点击下一页  page++
                page = TWO;
                //隐藏加载中
                ivSpecialAll.setVisibility(View.GONE);
                tvSpecialLoading.setVisibility(View.GONE);
                //请求数据
                presenter.getTopicData(page);
                //返回顶部
                topicScroll.fullScroll(ScrollView.FOCUS_UP);
                break;
        }
    }
}
