package com.example.shopkuang.ui.Details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;
import com.example.shopkuang.utils.ImageLoader;
import com.example.shopkuang.utils.TxtUtils;

import java.util.List;

public class BuyDetailsButtomInfoAdapter extends BaseAdapter {

    public BuyDetailsButtomInfoAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_buydetails_item;
    }

    @Override
    protected void bindData(Object data, com.example.shopkuang.base.BaseAdapter.VH vh) {
        BuyDetailsBottomInfoBean.DataBean.GoodsListBean bean = (BuyDetailsBottomInfoBean.DataBean.GoodsListBean) data;

        ImageView image = (ImageView) vh.getViewById(R.id.iv_category_img);
        TextView name = (TextView) vh.getViewById(R.id.tv_category_name);
        TextView floor_price = (TextView) vh.getViewById(R.id.tv_category_price);

        ImageLoader.loadImage(bean.getList_pic_url(), image);
        TxtUtils.setTextView(name, bean.getName());
        floor_price.setText("￥" + bean.getRetail_price());
    }
}


