package com.example.shopkuang.adapter.channe;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.shop.ChanneShujuBean;

import java.util.List;

public class ChanneShujuAdapter extends BaseAdapter {


    public ChanneShujuAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.channe_shuju_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ChanneShujuBean.DataBeanX.GoodsListBean bean = (ChanneShujuBean.DataBeanX.GoodsListBean) data;
        ImageView iv = (ImageView) vh.getViewById(R.id.channe_shuju_iv);
        TextView name = (TextView) vh.getViewById(R.id.channe_shuju_tv_name);
        TextView price = (TextView) vh.getViewById(R.id.channe_shuju_tv_price);

        Glide.with(context).load(bean.getList_pic_url()).into(iv);
        name.setText(bean.getName());
        price.setText("￥"+bean.getRetail_price());
    }
}
