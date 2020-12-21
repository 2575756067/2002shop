package com.example.shopkuang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.shop.HomeBean;

import java.util.List;

public class LivingAdapter extends BaseAdapter {


    public LivingAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.living_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.CategoryListBean.GoodsListBean bean = (HomeBean.DataBean.CategoryListBean.GoodsListBean) data;
        ImageView iv = (ImageView) vh.getViewById(R.id.living_item_iv);
        TextView name = (TextView) vh.getViewById(R.id.living_item_tv_name);
        TextView price = (TextView) vh.getViewById(R.id.living_item_tv_price);


        name.setText(bean.getName());
        Glide.with(context).load(bean.getList_pic_url()).into(iv);
        price.setText("￥" + bean.getRetail_price());

    }
}
