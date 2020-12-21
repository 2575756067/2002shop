package com.example.shopkuang.model;

import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.IBuyhome;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.ui.Details.BuyDetailsBean;
import com.example.shopkuang.ui.Details.BuyDetailsBottomInfoBean;
import com.example.shopkuang.utils.RxUtils;

public class BuyModel extends BaseModel implements IBuyhome.Model {
    @Override
    public void getBuyDetailsCallback(int id, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getApibuyDetails()
                .getBuyDatailsData(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BuyDetailsBean>(callback) {
                    @Override
                    public void onNext(BuyDetailsBean buyDetailsBean) {
                        callback.success(buyDetailsBean);
                    }
                })
        );
    }

    @Override
    public void getCategoryBottomInfo(String id, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getApibuyDetails()
        .getCategoryBottomInfo(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<BuyDetailsBottomInfoBean>(callback) {
            @Override
            public void onNext(BuyDetailsBottomInfoBean buyDetailsBottomInfoBean) {
                callback.success(buyDetailsBottomInfoBean);
            }
        }));
    }
}
