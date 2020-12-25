package com.example.shopkuang.api;

import com.example.shopkuang.bean.bean.type.TypeDataBean;
import com.example.shopkuang.bean.bean.type.TypeTabBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TypeApi {

    String BASE_URL = "https://cdplay.cn/";

    @GET("api/catalog/index")
    Flowable<TypeTabBean> getTabData();

    @GET("api/catalog/current")
    Flowable<TypeDataBean> getTypeData(@Query("id") int id);
}
