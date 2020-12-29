package com.example.shopkuang.shoppingcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkuang.R;
import com.example.shopkuang.app.MyApp;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.shoppingcar.bean.CarBean;
import com.example.shopkuang.ui.shopaddress.ShopAddressesActivity;

import java.util.List;

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

    //todo  优惠卷向右箭头
    @BindView(R.id.tv_coupon_details)
    ImageView tvCouponDetails;

    //todo 运费价钱
    @BindView(R.id.tv_freight_price)
    TextView tvFreightPrice;

    //todo  优惠卷减免
    @BindView(R.id.tv_coupon_price)
    TextView tvCouponPrice;


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
        TextView getTvShopAll = findViewById(R.id.tv_shop_all_price);        //价钱
        TextView allprice = findViewById(R.id.tv_all_price);    //总价格
        RecyclerView paymentRecycle = findViewById(R.id.payment_recycle);   //todo  购物车列表
        TextView gopay = findViewById(R.id.tv_gopay);    //todo  去付款
        List<CarBean.DataBean.CartListBean> carlist = (List<CarBean.DataBean.CartListBean>) MyApp.getMap().get("carlist");

        String str = null;
        for (int i = 0; i < carlist.size(); i++) {
            str += ("" + Integer.valueOf(carlist.get(i).getRetail_price() * carlist.get(i).getNumber()));
        }
        getTvShopAll.setText("￥" + str);
        allprice.setText(str);
        ImageView name_details = findViewById(R.id.tv_name_details);


        paymentRecycle.setLayoutManager(new LinearLayoutManager(this));
        AddressAdapter addressAdapter = new AddressAdapter(this, carlist);
        paymentRecycle.setAdapter(addressAdapter);

        //详情
        name_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayMentActivity.this, ShopAddressesActivity.class));
            }
        });
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
