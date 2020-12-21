package com.example.shopkuang.ui.Details;

import android.content.Context;
import android.widget.TextView;


import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.utils.TxtUtils;

import java.util.List;

public class BuyDetailsParameterAdapter extends BaseAdapter {

    public BuyDetailsParameterAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_parameter_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BuyDetailsBean.DataBeanX.AttributeBean bean = (BuyDetailsBean.DataBeanX.AttributeBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_parameter_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_parameter_value);

        TxtUtils.setTextView(key,bean.getName());
        TxtUtils.setTextView(value,bean.getValue());
    }
}
