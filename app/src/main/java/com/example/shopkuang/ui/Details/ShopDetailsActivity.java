package com.example.shopkuang.ui.Details;


import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.shopkuang.R;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.interfaces.home.IBuyhome;
import com.example.shopkuang.presenter.IBuyPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailsActivity extends BaseActivity<IBuyhome.Presenter> implements IBuyhome.View {

    @BindView(R.id.webView_category)
    WebView webView;
    @BindView(R.id.mRlv_category_all)
    RecyclerView mRlv_all;//底部列表数据
    @BindView(R.id.mRlv_category_issue)
    RecyclerView mRlv_issue;//常见问题
    @BindView(R.id.mRlv_category_parameter)
    RecyclerView mRlv_parameter;//商品参数
    @BindView(R.id.banner_category)
    Banner banner;//Banner
    @BindView(R.id.tv_category_info_title)
    TextView tv_title;
    @BindView(R.id.tv_category_info_desc)
    TextView tv_desc;
    @BindView(R.id.tv_category_info_price)
    TextView tv_price;
    @BindView(R.id.txt_assess)
    TextView tv_assess;

    @BindView(R.id.tv_category_info_comment_head_name)
    TextView tv_head_name;
    @BindView(R.id.tv_category_info_comment_head_desc)
    TextView tv_head_desc;
    @BindView(R.id.tv_category_info_comment_head_date)
    TextView tv_head_date;
    @BindView(R.id.iv_category_info_comment_head_img)
    ImageView iv_head_img;
    @BindView(R.id.iv_category_info_comment_img)
    ImageView iv_img;


    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";

    private ArrayList<BuyDetailsBottomInfoBean.DataBean.GoodsListBean> goodsList;//底部列表集合
    private ArrayList<BuyDetailsBean.DataBeanX.IssueBean> issuelist;//常见问题集合
    private ArrayList<BuyDetailsBean.DataBeanX.AttributeBean> attributeList;//商品参数集合
    private BuyDetailsButtomInfoAdapter categoryButtomInfoAdapter;
    private BuyDetailsIssueAdapter categoryIssueAdapter;
    private BuyDetailsParameterAdapter categoryParameterAdapter;


    @Override
    protected int getLayout() {
        return R.layout.shop_details_layout;
    }

    @Override
    protected IBuyPresenter createPrenter() {
        return new IBuyPresenter(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initViewIssue();//常见问题布局
        initBottomInfo();//底部列表数据
        initViewParameter();//商品参数
    }

    //TODO 商品参数布局
    private void initViewParameter() {
        attributeList = new ArrayList<>();
        mRlv_parameter.setLayoutManager(new LinearLayoutManager(this));
        categoryParameterAdapter = new BuyDetailsParameterAdapter(this, attributeList);
        mRlv_parameter.setAdapter(categoryParameterAdapter);
    }

    //TODO 常见问题布局
    private void initViewIssue() {
        issuelist = new ArrayList<>();
        mRlv_issue.setLayoutManager(new LinearLayoutManager(this));
        categoryIssueAdapter = new BuyDetailsIssueAdapter(this, issuelist);
        mRlv_issue.setAdapter(categoryIssueAdapter);
    }

    //TODO 底部列表数据
    private void initBottomInfo() {
        goodsList = new ArrayList<>();
        mRlv_all.setLayoutManager(new GridLayoutManager(this, 2));
//        mRlv_all.addItemDecoration(new ItemDecoration(this));
        categoryButtomInfoAdapter = new BuyDetailsButtomInfoAdapter(this, goodsList);
        mRlv_all.setAdapter(categoryButtomInfoAdapter);
    }

    @Override
    protected void initData() {
        int categoryId = (int) MyApp.getMap().get("id1");
        presenter.getBuyDetailsData(categoryId);
        String s = String.valueOf(categoryId);
        presenter.getCategoryBottomInfo(s);

    }

    //TODO 评论
    private void initComment(BuyDetailsBean.DataBeanX.CommentBean data) {
        BuyDetailsBean.DataBeanX.CommentBean bean= data;
        int count = bean.getCount();
        BuyDetailsBean.DataBeanX.CommentBean.DataBean data1 = bean.getData();
        tv_assess.setText("评价"+"("+String.valueOf(count)+")");
        //时间
        tv_head_date.setText(data1.getAdd_time());
        //名字
        tv_head_name.setText(data1.getNickname());
        //评论内容
        tv_head_desc.setText(data1.getContent());
        //底部图片
//        com.example.shopkuang.utils.ImageLoader.loadImage(data.getPic_list().get(0).getPic_url(), iv_img);
    }

    //TODO Banner下面的展示数据
    private void initInfo(BuyDetailsBean.DataBeanX.InfoBean info) {
        tv_title.setText(info.getName());
        tv_desc.setText(info.getGoods_brief());
        tv_price.setText("￥" + info.getRetail_price());
    }

    //TODO Banner数据
    private void initBanner(BuyDetailsBean.DataBeanX data) {
        banner.setImages(data.getGallery()).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BuyDetailsBean.DataBeanX.GalleryBean bean = (BuyDetailsBean.DataBeanX.GalleryBean) path;
                Glide.with(context).load(bean.getImg_url()).into(imageView);
            }
        }).start();
    }

    //TODO 商品参数数据
    private void initParameter(List<BuyDetailsBean.DataBeanX.AttributeBean> attribute) {
        attributeList.addAll(attribute);
        categoryParameterAdapter.notifyDataSetChanged();
    }

    //TODO 常见问题数据
    private void initIssue(List<BuyDetailsBean.DataBeanX.IssueBean> issue) {
        issuelist.addAll(issue);
        categoryIssueAdapter.notifyDataSetChanged();
    }

    //TODO h5 商品详情数据
    private void initGoodDetail(String webData) {
        String content = h5.replace("word", webData);
        Log.i("TAG", content);
        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }


    @Override
    public void getBuyDetails(BuyDetailsBean result) {
        if (result != null) {
            //Banner
            initBanner(result.getData());
            //Banner下面的展示数据
            initInfo(result.getData().getInfo());
            //评论
            initComment(result.getData().getComment());
            //常见问题数据
            initIssue(result.getData().getIssue());
            //h5 商品详情
            initGoodDetail(result.getData().getInfo().getGoods_desc());
            //商品参数
            initParameter(result.getData().getAttribute());
        }
    }

    @Override
    public void getCategoryBottomInfoReturn(BuyDetailsBottomInfoBean result) {
        List<BuyDetailsBottomInfoBean.DataBean.GoodsListBean> data = result.getData().getGoodsList();
        goodsList.addAll(data);
        categoryButtomInfoAdapter.notifyDataSetChanged();
    }
}