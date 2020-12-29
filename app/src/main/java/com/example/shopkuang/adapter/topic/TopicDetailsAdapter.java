package com.example.shopkuang.adapter.topic;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.utils.ImageLoader;


import java.util.List;

public class TopicDetailsAdapter extends BaseAdapter {

    public TopicDetailsAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_special_details_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String str = (String) data;
        ImageView img = (ImageView) vh.getViewById(R.id.iv_special_details_item_img);
        ImageLoader.loadImage(str,img);
    }
}
