package com.example.shopkuang.adapter.brand;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.HomeBean;

import java.util.List;

public class BrandAdapter extends BaseAdapter {

    public BrandAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.pinpai_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.BrandListBean bean= (HomeBean.DataBean.BrandListBean) data;
        ImageView iv= (ImageView) vh.getViewById(R.id.pinpai_item_iv);
        TextView name= (TextView) vh.getViewById(R.id.pinpai_item_tv_name);
        TextView price= (TextView) vh.getViewById(R.id.pinpai_item_tv_price);
        Glide.with(context).load(bean.getNew_pic_url()).into(iv);
        name.setText(bean.getName());
        price.setText(bean.getFloor_price()+"元起");




    }
}
