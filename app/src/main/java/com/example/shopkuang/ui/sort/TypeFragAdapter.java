package com.example.shopkuang.ui.sort;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.bean.bean.type.TypeDataBean;
import com.example.shopkuang.utils.ImageLoader;

import java.util.List;

public class TypeFragAdapter extends BaseAdapter {


    public TypeFragAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.type_frag_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView text= (TextView) vh.getViewById(R.id.type_item_tv);
        ImageView iv= (ImageView) vh.getViewById(R.id.type_item_iv);

        TypeDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean= (TypeDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        text.setText(bean.getName());
        ImageLoader.loadImage(bean.getWap_banner_url(),iv);
    }
}
