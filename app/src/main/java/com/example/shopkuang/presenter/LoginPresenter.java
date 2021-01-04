package com.example.shopkuang.presenter;


import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.bean.bean.login.LoginBean;
import com.example.shopkuang.bean.bean.login.RegisterBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.ILoginHome;
import com.example.shopkuang.model.LoginModel;
import com.example.shopkuang.ui.login.LoginoutBean;

import java.util.Map;

public class LoginPresenter extends BasePresenter<ILoginHome.View> implements ILoginHome.Presenter {

    ILoginHome.Model model;
    ILoginHome.View mView;

    public LoginPresenter(ILoginHome.View mView) {
        this.mView = mView;
        model = new LoginModel();
    }

    @Override
    public void getLogin(String username, String pwd) {
        model.getLoginCallback(username, pwd, new Callback() {
            @Override
            public void success(Object o) {
                mView.getLoginReturn((LoginBean) o);
            }
        });
    }

    @Override
    public void getRegister(String username, String pwd) {
        model.getRegisterCallback(username, pwd, new Callback() {
            @Override
            public void success(Object o) {
                mView.getRegisterReturn((RegisterBean) o);
            }
        });
    }

    @Override
    public void getLoginOut() {
        model.getLoginoutCallback(new Callback() {
            @Override
            public void success(Object o) {
                mView.getLoginoutReturn((LoginoutBean) o);
            }
        });
    }

    @Override
    public void PostLoginUserInfo(Map<String, String> map) {

    }
}
