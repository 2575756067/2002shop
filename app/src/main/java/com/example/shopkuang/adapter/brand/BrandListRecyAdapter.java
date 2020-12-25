package com.example.shopkuang.adapter.brand;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.pinpai.BrandlistRecyBean;

import java.util.List;

public class BrandListRecyAdapter extends BaseAdapter {

    public BrandListRecyAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brandlist_recy_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandlistRecyBean.DataBeanX.DataBean beanX = (BrandlistRecyBean.DataBeanX.DataBean) data;
        ImageView iv = (ImageView) vh.getViewById(R.id.brandlist_recy_iv);
        TextView name = (TextView) vh.getViewById(R.id.brandlist_recy_tv_name);
        TextView price = (TextView) vh.getViewById(R.id.brandlist_recy_tv_price);

        Glide.with(context).load(beanX.getList_pic_url()).into(iv);
        name.setText(beanX.getName());
        price.setText("￥"+beanX.getRetail_price());
    }
}
