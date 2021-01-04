package com.example.shopkuang.interfaces.home;

import com.example.shopkuang.bean.bean.login.LoginBean;
import com.example.shopkuang.bean.bean.login.LoginUserInfoBean;
import com.example.shopkuang.bean.bean.login.RegisterBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;
import com.example.shopkuang.ui.login.LoginoutBean;

import java.util.Map;

import retrofit2.http.FieldMap;

public interface ILoginHome {

    //todo  登录

    interface View extends IBaseView {
        void getLoginReturn(LoginBean result);

        void getRegisterReturn(RegisterBean result);

        //todo  退出登录
        void getLoginoutReturn(LoginoutBean result);

        //todo  用户数据刷新
        void LoginUserInfoReturn(LoginUserInfoBean result);
    }

    interface Presenter extends IBasePresenter<ILoginHome.View> {
        void getLogin(String username, String pwd);

        void getRegister(String username, String pwd);

        void getLoginOut();

        void PostLoginUserInfo(@FieldMap Map<String, String> map);
    }

    interface Model extends IBaseModel {
        void getLoginCallback(String username, String pwd, Callback callback);

        void getRegisterCallback(String username, String pwd, Callback callback);

        void getLoginoutCallback(Callback callback);

        void PostLoginUserInfoCallback(@FieldMap Map<String, String> map, Callback callback);
    }
}
