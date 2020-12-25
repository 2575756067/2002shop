package com.example.shopkuang.ui.ChaneActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.shopkuang.R;
import com.example.shopkuang.adapter.channe.ChanneTabAdapter;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.bean.bean.NewsShujuBean;
import com.example.shopkuang.bean.bean.TopicBean;
import com.example.shopkuang.bean.bean.pinpai.BrandBean;
import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.bean.bean.ChanneShujuBean;
import com.example.shopkuang.bean.bean.HomeBean;
import com.example.shopkuang.bean.bean.NewsBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistRecyBean;
import com.example.shopkuang.interfaces.home.IHome;
import com.example.shopkuang.presenter.Presenter;
import com.example.shopkuang.utils.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ChaneActivity extends BaseActivity<IHome.Presenter> implements IHome.View {


    private TabLayout tab;
    private CustomViewPager vp;
    private List<ChanneBean.DataBean.CategoryListBean> categoryList;
    private int idid;

    @Override
    protected int getLayout() {
        return R.layout.chane_activity_layout;
    }

    @Override
    protected Presenter createPrenter() {
        return new Presenter(this);
    }

    @Override
    protected void initView() {
        idid = (int) MyApp.getMap().get("idid");
        tab = findViewById(R.id.channe_tab);
        vp = findViewById(R.id.channe_vp);
        vp.setScanScroll(false);
    }

    @Override
    protected void initData() {
        presenter.getChanneData(idid);
    }

    @Override
    public void getHome(HomeBean homeBean) {

    }

    @Override
    public void getChanne(ChanneBean channeBean) {
        categoryList = channeBean.getData().getCategoryList();
        ArrayList<ChannelFragment> fragments = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            int id = categoryList.get(i).getId();
            Log.e("id", "getChanne: " + id);
            ChannelFragment channelFragment = new ChannelFragment();
//            channelFragment.getId(id);

            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            channelFragment.setArguments(bundle);

            fragments.add(channelFragment);
        }

        ChanneTabAdapter adapter = new ChanneTabAdapter(getSupportFragmentManager(), fragments, categoryList);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        adapter.notifyDataSetChanged();


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

    }
}
