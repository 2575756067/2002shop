package com.example.shopkuang.adapter.newproduct;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.shop.NewsShujuBean;

import java.util.List;

public class NewFirstTabAdapter extends BaseAdapter {

    public NewFirstTabAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.pop_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        NewsShujuBean.DataBeanX.FilterCategoryBean bean = (NewsShujuBean.DataBeanX.FilterCategoryBean) data;
        Button type = (Button) vh.getViewById(R.id.pop_item_tv_type);
        type.setText(bean.getName());



    }
}
