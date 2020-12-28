package com.example.shopkuang.api;

import com.example.shopkuang.bean.bean.address.AddressAddProvinceBean;
import com.example.shopkuang.bean.bean.address.AddressBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddressApi {
    //todo  地址
    String BASE_URL = "http://cdplay.cn/api/";


    //收获地址列表
    @GET("address/list")
    Flowable<AddressBean> getAddress();

    //获得省市接口数据
    @GET("region/list")
    //parentId  1
    Flowable<AddressAddProvinceBean> getAddressAddProvince(@Query("parentId") int parentId);
}
