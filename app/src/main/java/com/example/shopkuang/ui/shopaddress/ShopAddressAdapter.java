package com.example.shopkuang.ui.shopaddress;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopAddressAdapter extends BaseAdapter {

    public ShopAddressAdapter(Context context, List<ShopAddressBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.shopaddress_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView name = (TextView) vh.getViewById(R.id.tv_address_item_name);
        TextView phone = (TextView) vh.getViewById(R.id.tv_address_item_phone);
        TextView address = (TextView) vh.getViewById(R.id.tv_address_item_address);

        ShopAddressBean list = (ShopAddressBean) data;
        name.setText(list.getName());
        phone.setText(list.getPhone());
        address.setText(list.getDetailsAddress());
    }
}
