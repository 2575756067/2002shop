package com.example.shopkuang.adapter.newproduct;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.shop.NewsShujuBean;

import java.util.List;

public class NewsFirstAdapter extends BaseAdapter {

    public NewsFirstAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.newsfirst_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        NewsShujuBean.DataBeanX.GoodsListBean bean = (NewsShujuBean.DataBeanX.GoodsListBean) data;

        ImageView iv = (ImageView) vh.getViewById(R.id.newsfirst_item_iv);
        TextView name = (TextView) vh.getViewById(R.id.newsfirst_item_tv_name);
        TextView price = (TextView) vh.getViewById(R.id.newsfirst_item_tv_price);

        Glide.with(context).load(bean.getList_pic_url()).into(iv);
        name.setText(bean.getName());
        price.setText("￥"+bean.getRetail_price());
    }
}
