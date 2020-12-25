package com.example.shopkuang.adapter.newproduct;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.HomeBean;

import java.util.List;

public class NewGoodsAdapter extends BaseAdapter {

    public NewGoodsAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.goods_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.NewGoodsListBean bean= (HomeBean.DataBean.NewGoodsListBean) data;
        ImageView iv= (ImageView) vh.getViewById(R.id.goods_item_iv);
        TextView name= (TextView) vh.getViewById(R.id.goods_item_tv_name);
        TextView price= (TextView) vh.getViewById(R.id.goods_item_tv_price);
        Glide.with(context).load(bean.getList_pic_url()).into(iv);
        name.setText(bean.getName());
        price.setText("￥"+bean.getRetail_price());
    }
}
