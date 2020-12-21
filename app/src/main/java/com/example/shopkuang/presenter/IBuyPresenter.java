package com.example.shopkuang.presenter;

import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.IBuyhome;
import com.example.shopkuang.model.BuyModel;
import com.example.shopkuang.ui.Details.BuyDetailsBean;
import com.example.shopkuang.ui.Details.BuyDetailsBottomInfoBean;

public class IBuyPresenter extends BasePresenter<IBuyhome.View> implements IBuyhome.Presenter {

    IBuyhome.Model model;
    IBuyhome.View mView;

    public IBuyPresenter(IBuyhome.View mView) {
        this.mView = mView;
        model = new BuyModel();
    }

    @Override
    public void getBuyDetailsData(int id) {
        model.getBuyDetailsCallback(id, new Callback() {
            @Override
            public void success(Object o) {
                mView.getBuyDetails((BuyDetailsBean) o);
            }
        });
    }

    @Override
    public void getCategoryBottomInfo(String id) {
        model.getCategoryBottomInfo(id, new Callback() {
            @Override
            public void success(Object o) {
                mView.getCategoryBottomInfoReturn((BuyDetailsBottomInfoBean) o);
            }
        });
    }
}
