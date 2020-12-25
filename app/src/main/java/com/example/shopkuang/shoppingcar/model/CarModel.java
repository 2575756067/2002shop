package com.example.shopkuang.shoppingcar.model;

import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.shoppingcar.ICarHome;
import com.example.shopkuang.shoppingcar.bean.AddCarBean;
import com.example.shopkuang.shoppingcar.bean.CarBean;
import com.example.shopkuang.shoppingcar.bean.DeleteCarBean;
import com.example.shopkuang.shoppingcar.bean.UpdateCarBean;
import com.example.shopkuang.utils.RxUtils;

import java.util.Map;

public class CarModel extends BaseModel implements ICarHome.Model {

    @Override
    public void getCarList(Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getiCarApi()
        .getCarList()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<CarBean>(callback) {
            @Override
            public void onNext(CarBean carBean) {
                callback.success(carBean);
            }
        }));
    }

    @Override
    public void addGoodCar(Map<String, String> map, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getiCarApi()
        .addCar(map)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
            @Override
            public void onNext(AddCarBean addCarBean) {
                callback.success(addCarBean);
            }
        }));
    }

    @Override
    public void updateCar(Map<String, String> map, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getiCarApi()
        .updateCar(map)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<UpdateCarBean>(callback) {
            @Override
            public void onNext(UpdateCarBean updateCarBean) {
                callback.success(updateCarBean);
            }
        }));
    }

    @Override
    public void deleteCar(String pIds, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getiCarApi()
        .deleteCar(pIds)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
            @Override
            public void onNext(DeleteCarBean deleteCarBean) {
                callback.success(deleteCarBean);
            }
        }));
    }
}
