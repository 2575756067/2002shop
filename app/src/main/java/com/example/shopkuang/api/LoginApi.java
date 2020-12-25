package com.example.shopkuang.api;

import com.example.shopkuang.bean.bean.login.LoginBean;
import com.example.shopkuang.bean.bean.login.RegisterBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {

    String BASE_URL = "https://cdplay.cn/";

    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String pwd);


    @POST("api/auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> Register(@Field("username") String username, @Field("password") String pwd);

}
