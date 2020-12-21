package com.example.shopkuang.interfaces.home;

import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;
import com.example.shopkuang.ui.Details.BuyDetailsBean;
import com.example.shopkuang.ui.Details.BuyDetailsBottomInfoBean;

public interface IBuyhome {

    interface View extends IBaseView {
        void getBuyDetails(BuyDetailsBean buyDetailsBean);
        void getCategoryBottomInfoReturn(BuyDetailsBottomInfoBean result);

    }

    interface Presenter extends IBasePresenter<View> {
        void getBuyDetailsData(int id);

        void getCategoryBottomInfo(String id);

    }

    interface Model extends IBaseModel {
        void getBuyDetailsCallback(int id, Callback callback);

        void getCategoryBottomInfo(String id,Callback callback);

    }
}
