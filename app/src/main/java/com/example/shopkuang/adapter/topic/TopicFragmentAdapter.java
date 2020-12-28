package com.example.shopkuang.adapter.topic;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.topic.TopicBean;
import com.example.shopkuang.utils.TxtUtils;

import java.util.List;

public class TopicFragmentAdapter extends BaseAdapter {

    public TopicFragmentAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_frag_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicBean.DataBeanX.DataBean dataBean = (TopicBean.DataBeanX.DataBean) data;

        ImageView topic_rlv_item_iv = (ImageView) vh.getViewById(R.id.topic_rlv_item_iv);
        TextView topic_rlv_item_tv_name = (TextView) vh.getViewById(R.id.topic_rlv_item_tv_name);
        TextView topic_rlv_item_brif = (TextView) vh.getViewById(R.id.topic_rlv_item_brif);
        TextView topic_rlv_item_price = (TextView) vh.getViewById(R.id.topic_rlv_item_price);

        TxtUtils.setTextView(topic_rlv_item_tv_name, dataBean.getTitle());
        TxtUtils.setTextView(topic_rlv_item_brif, dataBean.getSubtitle());
        TxtUtils.setTextView(topic_rlv_item_price, dataBean.getPrice_info()+"元起");
        Glide.with(context).load(dataBean.getScene_pic_url()).into(topic_rlv_item_iv);
    }
}
