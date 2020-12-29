package com.example.shopkuang.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.adapter.hotgoods.HotGoodsAdapter;
import com.example.shopkuang.adapter.type.LivingAdapter;
import com.example.shopkuang.adapter.newproduct.NewGoodsAdapter;
import com.example.shopkuang.adapter.brand.BrandAdapter;
import com.example.shopkuang.adapter.topic.TopicAdapter;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.base.BaseFragment;
import com.example.shopkuang.bean.bean.NewsShujuBean;
import com.example.shopkuang.bean.bean.topic.TopicBean;
import com.example.shopkuang.bean.bean.pinpai.BrandBean;
import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.bean.bean.ChanneShujuBean;
import com.example.shopkuang.bean.bean.HomeBean;
import com.example.shopkuang.bean.bean.NewsBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistRecyBean;
import com.example.shopkuang.interfaces.home.IHome;
import com.example.shopkuang.presenter.Presenter;
import com.example.shopkuang.ui.ChaneActivity.ChaneActivity;
import com.example.shopkuang.ui.Details.ShopDetailsActivity;
import com.example.shopkuang.ui.brand.BrandShopActivity;
import com.example.shopkuang.ui.pinpai.BrandActivity;
import com.example.shopkuang.utils.TxtUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View {


    private Banner mybanner;
    private RecyclerView pinpairecycle;
    private RecyclerView newgoods_recycle;
    private RecyclerView hotgoodsrecycle;
    private RecyclerView specail_recycle;
    private LinearLayout layoutTab;
    private ArrayList<HomeBean.DataBean.BrandListBean> pinpailist;
    private BrandAdapter pinpaiadapter;
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> newGoodslist;
    private NewGoodsAdapter newGoodsAdapter;
    private ArrayList<HomeBean.DataBean.HotGoodsListBean> hotgoodslist;
    private HotGoodsAdapter hotGoodsAdapter;
    private ArrayList<HomeBean.DataBean.TopicListBean> topiclist;
    private TopicAdapter topicAdapter;
    private LivingAdapter livingAdapter;
    private TextView brandname;
    private TextView newname;
    private List<BrandBean.DataBeanX.DataBean> data;
    private NewsBean.DataBean.BannerInfoBean bannerInfo;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected Presenter createPrenter() {
        return new Presenter(this);
    }

    @Override
    protected void initView() {
        initBanner(); //Banner
        initPinPai();   //品牌
        initNewGoods(); //新品
        initHotGoods(); //人气推荐
        initTopic();    //专题精选
    }

    //banner
    private void initBanner() {
        mybanner = getActivity().findViewById(R.id.mybanner);

    }


    //品牌
    private void initPinPai() {
        pinpairecycle = getActivity().findViewById(R.id.pinpai_recycle);
        brandname = getActivity().findViewById(R.id.home_tv_brand_name);
        pinpailist = new ArrayList<>();
        pinpairecycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        pinpairecycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        pinpaiadapter = new BrandAdapter(getActivity(), pinpailist);
        pinpairecycle.setAdapter(pinpaiadapter);

        //点击条目进入详情页面
        pinpaiadapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getActivity(), BrandActivity.class);
                intent.putExtra("name", pinpailist.get(pos).getName());
                intent.putExtra("simple", pinpailist.get(pos).getSimple_desc());
                intent.putExtra("pic", pinpailist.get(pos).getList_pic_url());
                startActivity(intent);
            }
        });
        brandname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到品牌制造商直供
                MyApp.getMap().put("list", data);
                startActivity(new Intent(getActivity(), BrandShopActivity.class));
            }
        });
    }

    private void initNewGoods() {
        newgoods_recycle = getActivity().findViewById(R.id.newgoods_recycle);
        newname = getActivity().findViewById(R.id.home_tv_new_name);
        newGoodslist = new ArrayList<>();
        newgoods_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        newGoodsAdapter = new NewGoodsAdapter(getActivity(), newGoodslist);
        newgoods_recycle.setAdapter(newGoodsAdapter);
        newname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到新品 分类 价格升降序
                MyApp.getMap().put("info", bannerInfo);
                startActivity(new Intent(getActivity(), NewsFirstActivity.class));
            }
        });

    }

    private void initHotGoods() {
        hotgoodsrecycle = getActivity().findViewById(R.id.hotgoods_recycle);
        hotgoodslist = new ArrayList<>();
        hotgoodsrecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        hotgoodsrecycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        hotGoodsAdapter = new HotGoodsAdapter(getActivity(), hotgoodslist);
        hotgoodsrecycle.setAdapter(hotGoodsAdapter);

    }

    private void initTopic() {
        specail_recycle = getActivity().findViewById(R.id.specail_recycle);
        topiclist = new ArrayList<>();
        specail_recycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        topicAdapter = new TopicAdapter(getActivity(), topiclist);
        specail_recycle.setAdapter(topicAdapter);
    }


    @Override
    protected void initData() {
        presenter.getHomeData();
        presenter.getBrandData(1, 1000);
        presenter.getNewsData();
    }

    @Override
    public void getHome(HomeBean homeBean) {
        //banner
        List<HomeBean.DataBean.BannerBean> bannerbean = homeBean.getData().getBanner();

        mybanner.setImages(bannerbean).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean bean = (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(bean.getImage_url()).into(imageView);
            }
        }).start();

        //风格
        List<HomeBean.DataBean.ChannelBean> channel = homeBean.getData().getChannel();
        initType(channel);

        //品牌
        List<HomeBean.DataBean.BrandListBean> brandList = homeBean.getData().getBrandList();
        pinpailist.addAll(brandList);
        pinpaiadapter.notifyDataSetChanged();


        //新品
        List<HomeBean.DataBean.NewGoodsListBean> newGoodsList = homeBean.getData().getNewGoodsList();
        newGoodslist.addAll(newGoodsList);
        newGoodsAdapter.notifyDataSetChanged();

        //推荐
        List<HomeBean.DataBean.HotGoodsListBean> hotGoodsList = homeBean.getData().getHotGoodsList();
        hotgoodslist.addAll(hotGoodsList);
        hotGoodsAdapter.notifyDataSetChanged();

        //专题
        List<HomeBean.DataBean.TopicListBean> topicList = homeBean.getData().getTopicList();
        topiclist.addAll(topicList);
        topicAdapter.notifyDataSetChanged();

        //居家
        List<HomeBean.DataBean.CategoryListBean> categoryList = homeBean.getData().getCategoryList();
        initCategory(categoryList);
       /* livinghomelist.addAll(categoryList);
        livingAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void getChanne(ChanneBean channeBean) {

    }

    @Override
    public void getChanneShuju(ChanneShujuBean channeShujuBean) {

    }

    @Override
    public void getBrand(BrandBean brandBean) {
        data = brandBean.getData().getData();
    }

    @Override
    public void getNews(NewsBean newsBean) {
        bannerInfo = newsBean.getData().getBannerInfo();

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

    //居家
    private void initCategory(List<HomeBean.DataBean.CategoryListBean> categoryList) {

        LinearLayout ll = mActivity.findViewById(R.id.ll);
        for (int i = 0; i < categoryList.size(); i++) {
            View view = View.inflate(mActivity, R.layout.layout_pop_cat, null);

            List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList = categoryList.get(i).getGoodsList();
            TextView title = view.findViewById(R.id.tv_title);
            title.setText(categoryList.get(i).getName());

            RecyclerView mRec = view.findViewById(R.id.mRec);
            mRec.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            livingAdapter = new LivingAdapter(getActivity(), categoryList.get(i).getGoodsList());
            mRec.setAdapter(livingAdapter);
            ll.addView(view);


            livingAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    int id = goodsList.get(pos).getId();
                    MyApp.getMap().put("id1", id);
                    startActivity(new Intent(getActivity(), ShopDetailsActivity.class));
                }
            });
        }
    }

    //todo  动态栏
    private void initType(List<HomeBean.DataBean.ChannelBean> list) {
        layoutTab = getActivity().findViewById(R.id.layout_tab);
        layoutTab.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        for (HomeBean.DataBean.ChannelBean item : list) {
            View channel = LayoutInflater.from(getContext()).inflate(R.layout.layout_channel_item, layoutTab, false);
            ImageView img = channel.findViewById(R.id.img_channel);
            TextView txtChannel = channel.findViewById(R.id.txt_channel);
            Glide.with(mActivity).load(item.getIcon_url()).into(img);
            TxtUtils.setTextView(txtChannel, item.getName());
            txtChannel.setGravity(Gravity.CENTER);
            channel.setLayoutParams(params);
            layoutTab.addView(channel);

            int id = item.getId();

            channel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApp.getMap().put("idid", id);
                    startActivity(new Intent(getActivity(), ChaneActivity.class));
                }
            });

        }
    }
}