package com.example.shopkuang.ui.brand;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkuang.R;
import com.example.shopkuang.adapter.BrandListRecyAdapter;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseActivity;
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
import com.example.shopkuang.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class BrandListActivity extends BaseActivity<IHome.Presenter> implements IHome.View {


    private int id;
    private ImageView brand_list_iv;
    private TextView brand_list_tv_title;
    private TextView brand_list_tv_jianjie;
    private RecyclerView barnd_list_recycle;
    private int id1;
    private ArrayList<BrandlistRecyBean.DataBeanX.DataBean> list;
    private BrandListRecyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.brand_list_layout;
    }

    @Override
    protected Presenter createPrenter() {
        return new Presenter(this);
    }

    @Override
    protected void initView() {
        id = (int) MyApp.getMap().get("id");

        brand_list_iv = (ImageView) findViewById(R.id.brand_list_iv);
        brand_list_tv_title = (TextView) findViewById(R.id.brand_list_tv_title);
        brand_list_tv_jianjie = (TextView) findViewById(R.id.brand_list_tv_jianjie);
        barnd_list_recycle = (RecyclerView) findViewById(R.id.barnd_list_recycle);
        barnd_list_recycle.setLayoutManager(new GridLayoutManager(this, 2));
        list = new ArrayList<>();
        adapter = new BrandListRecyAdapter(this, list);
        barnd_list_recycle.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getBrandlistData(id);
        presenter.getBrandlistRecyData(id);
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
        BrandlistBean.DataBean.BrandBean brand = brandlistBean.getData().getBrand();
        ImageLoader.loadImage(brand.getList_pic_url(), brand_list_iv);
        brand_list_tv_jianjie.setText(brand.getSimple_desc());
        brand_list_tv_title.setText(brand.getName());
        id1 = brand.getId();
    }

    @Override
    public void getBrandlistRecy(BrandlistRecyBean brandlistRecyBean) {
        List<BrandlistRecyBean.DataBeanX.DataBean> data = brandlistRecyBean.getData().getData();
        list.addAll(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void getTopic(TopicBean topicBean) {

    }
}
