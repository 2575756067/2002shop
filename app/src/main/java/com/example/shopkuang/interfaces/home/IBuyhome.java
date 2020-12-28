package com.example.shopkuang.interfaces.home;

import com.example.shopkuang.bean.bean.shop.ShoppingCarBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;
import com.example.shopkuang.ui.Details.BuyDetailsBean;
import com.example.shopkuang.ui.Details.BuyDetailsBottomInfoBean;

public interface IBuyhome {

    //todo 购买
    interface View extends IBaseView {
        void getBuyDetails(BuyDetailsBean buyDetailsBean);

        void getCategoryBottomInfoReturn(BuyDetailsBottomInfoBean result);

        //购物车列表
        void getShopCarReturn(ShoppingCarBean result);

    }

    interface Presenter extends IBasePresenter<View> {
        void getBuyDetailsData(int id);

        void getCategoryBottomInfo(String id);

        void getShoppingCar();

    }

    interface Model extends IBaseModel {
        void getBuyDetailsCallback(int id, Callback callback);

        void getCategoryBottomInfo(String id, Callback callback);

        void getShoppingCallback(Callback callback);

    }
}
