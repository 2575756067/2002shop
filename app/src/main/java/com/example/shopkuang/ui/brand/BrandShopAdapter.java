package com.example.shopkuang.ui.brand;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.shop.pinpai.BrandBean;

import java.util.List;

public class BrandShopAdapter extends BaseAdapter {

    public BrandShopAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_shop_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandBean.DataBeanX.DataBean bean= (BrandBean.DataBeanX.DataBean) data;

        Log.e("TAG", "bindData: "+bean);
       ImageView iv= (ImageView) vh.getViewById(R.id.shop_item_iv);
       TextView name= (TextView) vh.getViewById(R.id.shop_item_tv_name);
       TextView price= (TextView) vh.getViewById(R.id.shop_item_tv_price);

        Glide.with(context).load(bean.getApp_list_pic_url()).into(iv);
        name.setText(bean.getName());
        price.setText(bean.getFloor_price()+"元起");

    }
}
