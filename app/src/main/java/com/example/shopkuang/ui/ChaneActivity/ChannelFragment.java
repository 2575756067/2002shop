package com.example.shopkuang.ui.ChaneActivity;

import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkuang.R;
import com.example.shopkuang.adapter.channe.ChanneShujuAdapter;
import com.example.shopkuang.base.BaseFragment;
import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.bean.bean.ChanneShujuBean;
import com.example.shopkuang.bean.bean.HomeBean;
import com.example.shopkuang.bean.bean.NewsBean;
import com.example.shopkuang.bean.bean.NewsShujuBean;
import com.example.shopkuang.bean.bean.topic.TopicBean;
import com.example.shopkuang.bean.bean.pinpai.BrandBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistRecyBean;
import com.example.shopkuang.interfaces.home.IHome;
import com.example.shopkuang.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelFragment extends BaseFragment<IHome.Presenter> implements IHome.View {

    @BindView(R.id.channe_frg_recycle)
    RecyclerView channeFrgRecycle;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    private int id;
    //    private RecyclerView recycle;
    private ArrayList<ChanneShujuBean.DataBeanX.GoodsListBean> list;
    private ChanneShujuAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.channe_fragment_layout;
    }

    @Override
    protected Presenter createPrenter() {
        return new Presenter(this);
    }

    @Override
    protected void initView() {
        id = getArguments().getInt("id");
        Log.i("TAG", "传过来的id:" + this.id);
        // recycle = getActivity().findViewById(R.id.channe_frg_recycle);
        channeFrgRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        list = new ArrayList<>();
        adapter = new ChanneShujuAdapter(getActivity(), list);
        channeFrgRecycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void initData() {
        presenter.getChanneShujuData(id);
        presenter.getChanneData(id);

    }

    @Override
    public void getHome(HomeBean homeBean) {

    }

    @Override
    public void getChanne(ChanneBean channeBean) {
        ChanneBean.DataBean.CurrentCategoryBean currentCategory = channeBean.getData().getCurrentCategory();
        tvTitle1.setText(currentCategory.getName());
        tvTitle2.setText(currentCategory.getFront_desc());
    }

    @Override
    public void getChanneShuju(ChanneShujuBean channeShujuBean) {
        List<ChanneShujuBean.DataBeanX.GoodsListBean> goodsList = channeShujuBean.getData().getGoodsList();
        Log.e("数据", "getChanneShuju: " + goodsList.size());
        list.addAll(goodsList);
        adapter.notifyDataSetChanged();
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

    }
//    public void getId(int id) {
//        this.id = id;
//    }
}
