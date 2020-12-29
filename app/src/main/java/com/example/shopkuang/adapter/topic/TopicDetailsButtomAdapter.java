package com.example.shopkuang.adapter.topic;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.topic.TopicRelevantBean;
import com.example.shopkuang.utils.ImageLoader;
import com.example.shopkuang.utils.TxtUtils;


import java.util.List;

public class TopicDetailsButtomAdapter extends BaseAdapter {

    public TopicDetailsButtomAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_special_details_buttom_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicRelevantBean.DataBean bean = (TopicRelevantBean.DataBean) data;

        ImageView img = (ImageView) vh.getViewById(R.id.iv_special_details_buttom_item_img);
        TextView title = (TextView) vh.getViewById(R.id.tv_special_details_buttom_item_title);
        ImageLoader.loadImage(bean.getScene_pic_url(),img);
        TxtUtils.setTextView(title,bean.getTitle());
    }
}
