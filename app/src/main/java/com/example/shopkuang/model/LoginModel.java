package com.example.shopkuang.model;

import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.bean.bean.login.LoginBean;
import com.example.shopkuang.bean.bean.login.RegisterBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.ILoginHome;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.ui.login.LoginoutBean;
import com.example.shopkuang.utils.RxUtils;

import java.util.Map;

public class LoginModel extends BaseModel implements ILoginHome.Model {
    @Override
    public void getLoginCallback(String username, String pwd, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getLoginApi()
                .login(username, pwd)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }

    @Override
    public void getRegisterCallback(String username, String pwd, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getLoginApi()
                .Register(username, pwd)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(callback) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        callback.success(registerBean);
                    }
                }));
    }

    @Override
    public void getLoginoutCallback(Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getLoginApi()
        .loginout()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<LoginoutBean>(callback) {
            @Override
            public void onNext(LoginoutBean loginoutBean) {
                callback.success(loginoutBean);
            }
        }));
    }

    @Override
    public void PostLoginUserInfoCallback(Map<String, String> map, Callback callback) {

    }

}
