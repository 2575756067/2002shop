package com.example.shopkuang.shoppingcar;

import com.example.shopkuang.shoppingcar.bean.DeleteCarBean;
import com.example.shopkuang.shoppingcar.bean.UpdateCarBean;
import com.example.shopkuang.shoppingcar.bean.AddCarBean;
import com.example.shopkuang.shoppingcar.bean.CarBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ICarApi {

    String BASE_URL = "https://cdplay.cn/";

    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap Map<String,String> map);

    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);

    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);

    //购物车列表
    @GET("api/cart/index")
    Flowable<CarBean> getCarList();
}
