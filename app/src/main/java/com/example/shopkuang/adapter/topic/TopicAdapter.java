package com.example.shopkuang.adapter.topic;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.shop.HomeBean;
import com.example.shopkuang.bean.shop.TopicBean;
import com.example.shopkuang.utils.ImageLoader;
import com.example.shopkuang.utils.TxtUtils;

import java.util.List;

public class TopicAdapter extends BaseAdapter {

    public TopicAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.TopicListBean bean= (HomeBean.DataBean.TopicListBean) data;

        ImageView iv = (ImageView) vh.getViewById(R.id.topic_item_iv);
        TextView name= (TextView) vh.getViewById(R.id.topic_item_tv_name);
        TextView brif= (TextView) vh.getViewById(R.id.topic_item_brif);
        TextView price= (TextView) vh.getViewById(R.id.topic_item_price);

        ImageLoader.loadImage(bean.getItem_pic_url(),iv);
        TxtUtils.setTextView(name,bean.getTitle());
        TxtUtils.setTextView(brif,bean.getSubtitle());
        TxtUtils.setTextView(price,"￥"+bean.getPrice_info()+"元起");
    }
}
