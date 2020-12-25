package com.example.shopkuang.shoppingcar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.interfaces.IBasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayMentActivity extends BaseActivity {

    //todo 名字
    @BindView(R.id.tv_name)
    TextView tvName;

    //todo  手机号
    @BindView(R.id.tv_number)
    TextView tvNumber;

    //todo  默认框
    @BindView(R.id.tv_moren)
    TextView tvMoren;

    //todo  地址
    @BindView(R.id.tv_place)
    TextView tvPlace;

    //todo 资料右箭头
    @BindView(R.id.tv_name_details)
    ImageView tvNameDetails;

    //todo  优惠卷数量
    @BindView(R.id.tv_coupon_num)
    TextView tvCouponNum;

    //todo  优惠苑向右箭头
    @BindView(R.id.tv_coupon_details)
    ImageView tvCouponDetails;

    //todo  商品合计数量
    @BindView(R.id.tv_shop_all_price)
    TextView getTvShopAll;

    //todo 运费价钱
    @BindView(R.id.tv_freight_price)
    TextView tvFreightPrice;

    //todo  优惠卷减免
    @BindView(R.id.tv_coupon_price)
    TextView tvCouponPrice;

    //todo   购物车列表
    @BindView(R.id.payment_recycle)
    RecyclerView paymentRecycle;


    @Override
    protected int getLayout() {
        return R.layout.payment_activity_layout;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
