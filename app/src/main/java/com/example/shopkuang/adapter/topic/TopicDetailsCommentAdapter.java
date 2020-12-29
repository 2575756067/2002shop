package com.example.shopkuang.adapter.topic;

import android.content.Context;
import android.widget.TextView;


import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.topic.TopicDetailisCommentBean;
import com.example.shopkuang.utils.TxtUtils;

import java.util.List;

public class TopicDetailsCommentAdapter extends BaseAdapter {

    public TopicDetailsCommentAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_special_details_comment_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicDetailisCommentBean.DataBeanX.DataBean bean = (TopicDetailisCommentBean.DataBeanX.DataBean) data;

        TextView name = (TextView) vh.getViewById(R.id.iv_special_details_comment_item_name);
        TextView date = (TextView) vh.getViewById(R.id.iv_special_details_comment_item_date);
        TextView desc = (TextView) vh.getViewById(R.id.iv_special_details_comment_item_desc);

        TxtUtils.setTextView(name,bean.getUser_info().getUsername());
        TxtUtils.setTextView(date,bean.getAdd_time());
        TxtUtils.setTextView(desc,bean.getContent());
    }
}
