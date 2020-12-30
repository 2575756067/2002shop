package com.example.shopkuang.ui.shopaddress;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.interfaces.home.IAddressHome;
import com.example.shopkuang.presenter.AddressPresenter;
import com.example.shopkuang.bean.bean.address.AddressAddProvinceBean;
import com.example.shopkuang.bean.bean.address.AddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopAddressesActivity extends BaseActivity<IAddressHome.Presenter> implements IAddressHome.View {


    @BindView(R.id.address_btn_add)
    Button addressBtnAdd;
    private List<ShopAddressBean> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_shop_addresses;
    }

    @Override
    protected AddressPresenter createPrenter() {
        return new AddressPresenter(this);

    }

    @Override
    protected void initView() {
        RecyclerView addressrecycle = findViewById(R.id.address_recycle);
        addressrecycle.setLayoutManager(new LinearLayoutManager(this));
        addressrecycle.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");
        String detailsAddress = intent.getStringExtra("detailsAddress");

        String addressDetails = address + detailsAddress;
        list = new ArrayList<>();
        list.add(new ShopAddressBean(name, phone, addressDetails));


        ShopAddressAdapter adapter = new ShopAddressAdapter(this, list);
        addressrecycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void initData() {
        presenter.getAddress();
    }

    @OnClick(R.id.address_btn_add)
    public void onClick() {
        Intent intent = new Intent(this, ShopAddressesAddActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void getAddressData(AddressBean result) {
        if (result.getData().size() > 0) {
            Log.e("TAG", "getAddressReturn: " + result.getData().get(0).getName());
        }
    }

    @Override
    public void getAddressProvince(AddressAddProvinceBean result) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}