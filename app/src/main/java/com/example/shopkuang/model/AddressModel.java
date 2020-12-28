package com.example.shopkuang.model;

import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.IAddressHome;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.bean.bean.address.AddressAddProvinceBean;
import com.example.shopkuang.bean.bean.address.AddressBean;
import com.example.shopkuang.utils.RxUtils;

public class AddressModel extends BaseModel implements IAddressHome.Model {


    //todo  城市
    @Override
    public void AddressCllback(Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getAddressApi()
                .getAddress()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddressBean>(callback) {
                    @Override
                    public void onNext(AddressBean addressBean) {
                        callback.success(addressBean);
                    }
                }));
    }

    @Override
    public void AddressProvinceCallback(int parentId, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getAddressApi()
                .getAddressAddProvince(parentId)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddressAddProvinceBean>(callback) {
                    @Override
                    public void onNext(AddressAddProvinceBean addressAddProvinceBean) {
                        callback.success(addressAddProvinceBean);
                    }
                }));
    }
}
