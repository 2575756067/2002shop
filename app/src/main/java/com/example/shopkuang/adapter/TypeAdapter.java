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

public class TypeAdapter extends BaseAdapter {

    public TypeAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.type_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.ChannelBean bean= (HomeBean.DataBean.ChannelBean) data;
        ImageView iv = (ImageView) vh.getViewById(R.id.type_item_iv);
        TextView tv = (TextView) vh.getViewById(R.id.type_item_tv);

        Glide.with(context).load(bean.getIcon_url()).into(iv);
        tv.setText(bean.getName());
    }
}
