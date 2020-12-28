package com.example.shopkuang.interfaces.home;

import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;
import com.example.shopkuang.bean.bean.address.AddressAddProvinceBean;
import com.example.shopkuang.bean.bean.address.AddressBean;

public interface IAddressHome {


    interface View extends IBaseView {
        //todo 收获地址列表
        void getAddressData(AddressBean result);

        //todo 获得省市接口数据
        void getAddressProvince(AddressAddProvinceBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        //todo 收获地址列表
        void getAddress();

        //todo 获得省市接口数据

        void getAddressProvince(int parentId);
    }

    interface Model extends IBaseModel {
        //todo 收获地址列表
        void AddressCllback(Callback callback);

        //todo 获得省市接口数据
        void AddressProvinceCallback(int parentId, Callback callback);
    }
}
