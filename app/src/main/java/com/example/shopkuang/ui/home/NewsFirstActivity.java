package com.example.shopkuang.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.adapter.newproduct.NewFirstTabAdapter;
import com.example.shopkuang.adapter.newproduct.NewsFirstAdapter;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.base.BaseAdapter;
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
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFirstActivity extends BaseActivity<IHome.Presenter> implements IHome.View {

    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";

    @BindView(R.id.img_hotgood)
    ImageView imgHotGood;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.layout_price)
    LinearLayout layoutPrice;
    @BindView(R.id.img_arrow_up)
    ImageView imgArrowUp;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrowDown;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_sort)
    TextView txtSort;
    @BindView(R.id.recy_goodList)
    RecyclerView recyHotGood;
    @BindView(R.id.layout_info)
    ConstraintLayout layoutInfo;
    @BindView(R.id.layout_sort)
    ConstraintLayout layoutSort;
    @BindView(R.id.con)
    ConstraintLayout con;
    @BindView(R.id.newsfirst_item_view)
    View newsfirstItemView;

    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order;
    private String sort;
    private int categoryId;


    private NewsFirstAdapter adapter;
    private List<NewsShujuBean.DataBeanX.FilterCategoryBean> fiftetlist = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_newsfirst;
    }

    @Override
    protected Presenter createPrenter() {
        return new Presenter(this);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        order = ASC;
        sort = DEFAULT;
        categoryId = 0;
        layoutPrice.setTag(0);
        txtAll.setTextColor(Color.parseColor(NewsFirstActivity.this.getString(R.color.red)));

        NewsBean.DataBean.BannerInfoBean info = (NewsBean.DataBean.BannerInfoBean) MyApp.getMap().get("info");
        Glide.with(this).load(info.getImg_url()).into(imgHotGood);
        txtInfo.setText(info.getName());

    }

    @Override
    protected void initData() {
        presenter.getNewsShujuData(getParam());
    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.layout_price, R.id.txt_all, R.id.txt_sort})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.layout_price:
                int tag = (int) layoutPrice.getTag();
                if (tag == 0) {
                    resetPriceState();
                    priceStateUp();
                    layoutPrice.setTag(1);
                    order = ASC;
                } else if (tag == 1) {
                    resetPriceState();
                    priceStateDown();
                    layoutPrice.setTag(0);
                    order = DESC;
                }

                sort = PRICE;
                presenter.getNewsShujuData(getParam());
                break;
            case R.id.txt_all:
                resetPriceState();
                txtAll.setTextColor(Color.parseColor(NewsFirstActivity.this.getString(R.color.red)));
                sort = DEFAULT;
                categoryId = 0;
                presenter.getNewsShujuData(getParam());
                break;
            case R.id.txt_sort:
                initPopwindow();
                resetPriceState();
                txtSort.setTextColor(Color.parseColor(NewsFirstActivity.this.getString(R.color.red)));
                sort = CATEGORY;
//                presenter.getNewsShujuData(getParam());
                break;
        }
    }


    //分类弹框
    private void initPopwindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);
        PopupWindow pw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setBackgroundDrawable(new ColorDrawable());
        pw.setFocusable(true);
        pw.setTouchable(true);
        pw.showAsDropDown(layoutSort, 0, 0);
        RecyclerView pop_recycle = view.findViewById(R.id.pop_recycle);
        pop_recycle.setLayoutManager(new GridLayoutManager(this, 5));
        NewFirstTabAdapter newFirstTabAdapter = new NewFirstTabAdapter(this, fiftetlist);
        pop_recycle.setAdapter(newFirstTabAdapter);
        newFirstTabAdapter.notifyDataSetChanged();


        newFirstTabAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = fiftetlist.get(pos).getId();
                Toast.makeText(NewsFirstActivity.this, "点击了"+id, Toast.LENGTH_SHORT).show();
                Log.e("TAG", "itemClick: " + id);
                String id1 = String.valueOf(id);
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("(isNew", String.valueOf(isNew));
                map1.put("categoryId", id1);
                presenter.getNewsShujuData(map1);
            }
        });
    }

    /**
     * 组装当前的接口参数
     *
     * @return
     */
    private HashMap<String, String> getParam() {
        HashMap<String, String> map = new HashMap<>();
        map.put("isNew", String.valueOf(isNew));
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        map.put("order", order);
        map.put("sort", sort);
        map.put("category", String.valueOf(categoryId));
        return map;
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private void priceStateUp() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private void priceStateDown() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private void resetPriceState() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.black)));
        txtAll.setTextColor(Color.parseColor(getString(R.color.black)));
        txtSort.setTextColor(Color.parseColor(getString(R.color.black)));
        layoutPrice.setTag(0);
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
        List<NewsShujuBean.DataBeanX.GoodsListBean> goodsList = shujuBean.getData().getGoodsList();
        fiftetlist.clear();
        fiftetlist = shujuBean.getData().getFilterCategory();
        recyHotGood.setLayoutManager(new GridLayoutManager(this, 2));
//        recyHotGood.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
//        recyHotGood.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new NewsFirstAdapter(this, goodsList);
        recyHotGood.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
