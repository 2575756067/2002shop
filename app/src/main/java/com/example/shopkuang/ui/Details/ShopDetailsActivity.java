package com.example.shopkuang.ui.Details;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.shopkuang.MainActivity;
import com.example.shopkuang.R;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.shop.ShoppingCarBean;
import com.example.shopkuang.interfaces.home.IBuyhome;
import com.example.shopkuang.presenter.IBuyPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailsActivity extends BaseActivity<IBuyhome.Presenter> implements IBuyhome.View {

    //    @BindView(R.id.webView_category)
//    WebView webView;
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
    @BindView(R.id.bigimgRecycle)
    RecyclerView bigImg_recycle;

    @BindView(R.id.tv_addcar)
    TextView addCar;
    @BindView(R.id.iv_category_car)
    ImageView iv_shopCar;
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
    private boolean isSelect = false;

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
    private PopupWindow popupWindow;
    private TextView tv_shu;
    private int shu;
    private Intent intent;


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
//        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))) {
//
//
//        } else {
        //查看购物车
        iv_shopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopDetailsActivity.this, MainActivity.class);
                intent.putExtra("pos", 3);
                startActivity(intent);
            }
        });
//        }

        //广播
        intent = new Intent();
        intent.setAction("shu");
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
        Log.e("TAG", "initData: "+categoryId );
        presenter.getBuyDetailsData(categoryId);
        String s = String.valueOf(categoryId);
        presenter.getCategoryBottomInfo(s);

    }

    //TODO 评论
    private void initComment(BuyDetailsBean.DataBeanX.CommentBean data) {
        BuyDetailsBean.DataBeanX.CommentBean bean = data;
        int count = bean.getCount();
        BuyDetailsBean.DataBeanX.CommentBean.DataBean data1 = bean.getData();
        tv_assess.setText("评价" + "(" + String.valueOf(count) + ")");
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

//    //TODO h5 商品详情数据
//    private void initGoodDetail(String webData) {
//        String content = h5.replace("word", webData);
//        Log.i("TAG", content);
//        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
//    }


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
//            initGoodDetail(result.getData().getInfo().getGoods_desc());
            //商品参数
            initParameter(result.getData().getAttribute());
            //加入购物车
            initAddCar(result.getData().getInfo(), result.getData().getProductList());
            //展示goods_desc
            showImage(result.getData().getInfo().getGoods_desc());

        }
    }

    private void initAddCar(BuyDetailsBean.DataBeanX.InfoBean info, List<BuyDetailsBean.DataBeanX.ProductListBean> productList) {
        //判断选中状态
        if (isSelect == false) {
            isSelect = true;
        } else {
            isSelect = false;
        }
        //添加购物车
        //显示隐藏
        //        //添加购物车
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isSelect) {
                    View view = LayoutInflater.from(ShopDetailsActivity.this).inflate(R.layout.shopcar_pop_layout, null);
                    popupWindow = new PopupWindow(view, GridLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    popupWindow.showAtLocation(addCar, Gravity.BOTTOM, 0, 150);

                    ImageView image_pop = view.findViewById(R.id.iv_pop_head);
                    TextView price_pop = view.findViewById(R.id.tv_price);
                    Button btn_jia = view.findViewById(R.id.detail_btn_jia);
                    Button btn_jian = view.findViewById(R.id.detail_btn_jian);
                    tv_shu = view.findViewById(R.id.detail_btn_shu);
                    TextView tv_back = view.findViewById(R.id.detail_tv_back);

                    Glide.with(ShopDetailsActivity.this).load(info.getList_pic_url()).into(image_pop);
                    price_pop.setText("价格:  ￥" + info.getRetail_price() + "");
                    shu = 1;

                    ClickListener clickListener = new ClickListener();
                    btn_jia.setOnClickListener(clickListener);
                    btn_jian.setOnClickListener(clickListener);

                    tv_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                        }
                    });
                    isSelect = false;
                } else {
                    popupWindow.dismiss();

                    //添加购物车 goodsid
                    int goodsId = info.getId();
                    intent.putExtra("goodsId", goodsId);
                    sendBroadcast(intent);
                    //添加购物车number
                    String number = tv_shu.getText().toString();
                    intent.putExtra("number", number);
                    sendBroadcast(intent);
                    //添加购物车productId
                    int productId = productList.get(0).getId();
                    intent.putExtra("productId", productId);
                    sendBroadcast(intent);


                    //创建 popwindow
                    View join_view = LayoutInflater.from(ShopDetailsActivity.this).inflate(R.layout.layout_detail_pop_ok, null);
                    PopupWindow popupWindow1 = new PopupWindow(join_view, 200, 200);

                    WindowManager.LayoutParams attributes = getWindow().getAttributes();
                    attributes.alpha = 0.5f;
                    getWindow().setAttributes(attributes);

                    popupWindow1.showAtLocation(addCar, Gravity.CENTER, 0, 0);

                    //两秒自动关闭
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    popupWindow1.dismiss();
                                    WindowManager.LayoutParams attributes = getWindow().getAttributes();
                                    attributes.alpha = 1f;
                                    getWindow().setAttributes(attributes);
                                }
                            });
                        }
                    }, 1000, 3000);

                    isSelect = true;
                }
            }
        });
    }

    //TODO 购物车的点击
    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.detail_btn_jia:
                    shu++;
                    if (shu > 0) {
                        tv_shu.setText(shu + "");
                    }
                    break;
                case R.id.detail_btn_jian:
                    shu--;
                    if (shu > 0) {
                        tv_shu.setText(shu + "");
                    }
                    break;
            }
        }
    }


    private void showImage(String goods_desc) {
        ArrayList<String> listUrl = new ArrayList<>();
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(goods_desc);

        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            if (end > 0) {//如果是jpg格式的就截取jpg
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    listUrl.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");//如果是png格式的就截取png
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    listUrl.add(url);
                } else {
                    return;
                }
            }
        }

        bigImg_recycle.setLayoutManager(new LinearLayoutManager(this));
        CategoryBigImageAdapter categoryBigImageAdapter = new CategoryBigImageAdapter(this, listUrl);
        bigImg_recycle.setAdapter(categoryBigImageAdapter);

        //点击条目跳转
        categoryBigImageAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("image", listUrl);
                Intent intent = new Intent(ShopDetailsActivity.this, BigImageActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getCategoryBottomInfoReturn(BuyDetailsBottomInfoBean result) {
        List<BuyDetailsBottomInfoBean.DataBean.GoodsListBean> data = result.getData().getGoodsList();
        goodsList.addAll(data);
        categoryButtomInfoAdapter.notifyDataSetChanged();
    }

    @Override
    public void getShopCarReturn(ShoppingCarBean result) {

    }
}