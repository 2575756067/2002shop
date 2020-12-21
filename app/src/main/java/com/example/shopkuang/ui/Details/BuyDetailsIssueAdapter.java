package com.example.shopkuang.ui.Details;

import android.content.Context;
import android.widget.TextView;


import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.utils.TxtUtils;

import java.util.List;

public class BuyDetailsIssueAdapter extends BaseAdapter {

    public BuyDetailsIssueAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_issue_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BuyDetailsBean.DataBeanX.IssueBean bean = (BuyDetailsBean.DataBeanX.IssueBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_issue_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_issue_value);

        TxtUtils.setTextView(key,bean.getQuestion());
        TxtUtils.setTextView(value,bean.getAnswer());
    }
}
