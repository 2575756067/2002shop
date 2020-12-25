package com.example.shopkuang.ui.sort;

import android.os.Bundle;
import android.util.Log;

import com.example.shopkuang.R;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.bean.bean.ChanneShujuBean;
import com.example.shopkuang.bean.bean.HomeBean;
import com.example.shopkuang.bean.bean.NewsBean;
import com.example.shopkuang.bean.bean.NewsShujuBean;
import com.example.shopkuang.bean.bean.TopicBean;
import com.example.shopkuang.bean.bean.pinpai.BrandBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistRecyBean;
import com.example.shopkuang.bean.bean.type.TypeDataBean;
import com.example.shopkuang.interfaces.home.IHome;
import com.example.shopkuang.presenter.Presenter;
import com.example.shopkuang.ui.ChaneActivity.ChannelFragment;
import com.example.shopkuang.utils.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SortActivity extends BaseActivity<IHome.Presenter> implements IHome.View {
    @BindView(R.id.channe_tab)
    TabLayout tab;
    @BindView(R.id.channe_vp)
    CustomViewPager vp;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.sort_activity_layout;
    }

    @Override
    protected Presenter createPrenter() {
        return new Presenter(this);
    }

    @Override
    protected void initView() {
        //id = getIntent().getIntExtra("idtype", 0);

    }

    @Override
    protected void initData() {
        presenter.getChanneData(id);
    }

    @Override
    public void getHome(HomeBean homeBean) {

    }

    @Override
    public void getChanne(ChanneBean channeBean) {

        List<ChanneBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = channeBean.getData().getCurrentCategory().getSubCategoryList();
        ArrayList<ChannelFragment> fragments = new ArrayList<>();

        List<TypeDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list = (ArrayList<TypeDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean>) MyApp.getMap().get("typelist");

        for (int i = 0; i < list.size(); i++) {
            id = subCategoryList.get(i).getId();
            Log.e("id", "getChanne: " + id);
            ChannelFragment channelFragment = new ChannelFragment();
//            channelFragment.getId(id);

            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            channelFragment.setArguments(bundle);

            fragments.add(channelFragment);
        }

        SortDetailsTabAdapter adapter = new SortDetailsTabAdapter(getSupportFragmentManager(), fragments, subCategoryList);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
