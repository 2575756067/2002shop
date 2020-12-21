package com.example.shopkuang.adapter.renqituijian;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.shop.HomeBean;

import java.util.List;

public class HotGoodsAdapter extends BaseAdapter {

    public HotGoodsAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.hotgoods_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.HotGoodsListBean bean= (HomeBean.DataBean.HotGoodsListBean) data;
        ImageView iv= (ImageView) vh.getViewById(R.id.hotgoods_item_iv);
        TextView name= (TextView) vh.getViewById(R.id.hotgoods_item_tv_name);
        TextView brif= (TextView) vh.getViewById(R.id.hotgoods_item_tv_brif);
        TextView price= (TextView) vh.getViewById(R.id.hotgoods_item_tv_price);

        Glide.with(context).load(bean.getList_pic_url()).into(iv);
        name.setText(bean.getName());
        brif.setText(bean.getGoods_brief());
        price.setText("￥"+bean.getRetail_price());
    }
}
