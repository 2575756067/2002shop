package com.example.shopkuang.presenter;

import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.IAddressHome;
import com.example.shopkuang.model.AddressModel;
import com.example.shopkuang.bean.bean.address.AddressAddProvinceBean;
import com.example.shopkuang.bean.bean.address.AddressBean;

public class AddressPresenter extends BasePresenter<IAddressHome.View> implements IAddressHome.Presenter {

    IAddressHome.Model model;
    IAddressHome.View mView;

    public AddressPresenter(IAddressHome.View mView) {
        this.mView = mView;
        model = new AddressModel();
    }

    @Override
    public void getAddress() {
        model.AddressCllback(new Callback() {
            @Override
            public void success(Object o) {
                mView.getAddressData((AddressBean) o);
            }
        });
    }

    @Override
    public void getAddressProvince(int parentId) {
        model.AddressProvinceCallback(parentId, new Callback() {
            @Override
            public void success(Object o) {
                mView.getAddressProvince((AddressAddProvinceBean) o);
            }
        });
    }
}
