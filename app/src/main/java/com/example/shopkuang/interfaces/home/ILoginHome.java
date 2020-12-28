package com.example.shopkuang.interfaces.home;

import com.example.shopkuang.bean.bean.login.LoginBean;
import com.example.shopkuang.bean.bean.login.RegisterBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;

public interface ILoginHome {

    //todo  登录

    interface View extends IBaseView {
        void getLoginReturn(LoginBean result);

        void getRegisterReturn(RegisterBean result);
    }

    interface Presenter extends IBasePresenter<ILoginHome.View> {
        void getLogin(String username, String pwd);

        void getRegister(String username, String pwd);
    }

    interface Model extends IBaseModel {
        void getLoginCallback(String username, String pwd, Callback callback);

        void getRegisterCallback(String username, String pwd, Callback callback);
    }
}
