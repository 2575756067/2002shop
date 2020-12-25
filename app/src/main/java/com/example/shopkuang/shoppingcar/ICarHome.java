package com.example.shopkuang.shoppingcar;

import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;
import com.example.shopkuang.shoppingcar.bean.AddCarBean;
import com.example.shopkuang.shoppingcar.bean.CarBean;
import com.example.shopkuang.shoppingcar.bean.DeleteCarBean;
import com.example.shopkuang.shoppingcar.bean.UpdateCarBean;

import java.util.Map;

public interface ICarHome {

    interface View extends IBaseView {

        void getCarListReturn(CarBean carBean);

        //todo 添加购物车
        void addGoodCarReturn(AddCarBean addCarBean);

        //todo 更新 购物车
        void updateCarReturn(UpdateCarBean result);

        //todo 删除购物车
        void deleteCarReturn(DeleteCarBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCarList();

        //todo 添加购物车
        void addGoodCar(Map<String, String> map);

        //todo 更新购物车的数据
        void updateCar(Map<String, String> map);

        //todo 删除购物车列表
        void deleteCar(String pIds);
    }

    interface Model extends IBaseModel {
        void getCarList(Callback callback);

        //todo  添加购物车
        void addGoodCar(Map<String, String> map, Callback callback);

        //todo 更新购物车的数据
        void updateCar(Map<String, String> map, Callback callback);

        //todo 删除购物车列表
        void deleteCar(String pIds, Callback callback);
    }
}
