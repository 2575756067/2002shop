package com.example.shopkuang.shoppingcar.presenter;

import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.shoppingcar.ICarApi;
import com.example.shopkuang.shoppingcar.ICarHome;
import com.example.shopkuang.shoppingcar.bean.AddCarBean;
import com.example.shopkuang.shoppingcar.bean.CarBean;
import com.example.shopkuang.shoppingcar.bean.DeleteCarBean;
import com.example.shopkuang.shoppingcar.bean.UpdateCarBean;
import com.example.shopkuang.shoppingcar.model.CarModel;

import java.util.Map;

public class CarPresenter extends BasePresenter<ICarHome.View> implements ICarHome.Presenter {

    ICarHome.Model model;
    ICarHome.View mView;

    public CarPresenter(ICarHome.View mView) {
        model = new CarModel();
        this.mView = mView;
    }

    @Override
    public void getCarList() {
        model.getCarList(new Callback() {
            @Override
            public void success(Object o) {
                mView.getCarListReturn((CarBean) o);
            }
        });
    }

    //todo  添加购物车
    @Override
    public void addGoodCar(Map<String, String> map) {
        model.addGoodCar(map, new Callback() {
            @Override
            public void success(Object o) {
                mView.addGoodCarReturn((AddCarBean) o);
            }
        });
    }


    //todo 更新购物车
    @Override
    public void updateCar(Map<String, String> map) {

        model.updateCar(map, new Callback() {
            @Override
            public void success(Object o) {
                mView.updateCarReturn((UpdateCarBean) o);
            }
        });
    }

    //todo  删除购物车
    @Override
    public void deleteCar(String pIds) {
        model.deleteCar(pIds, new Callback() {
            @Override
            public void success(Object o) {
                mView.deleteCarReturn((DeleteCarBean) o);
            }
        });
    }
}
