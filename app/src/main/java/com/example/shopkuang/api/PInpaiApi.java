package com.example.shopkuang.api;

import com.example.shopkuang.bean.bean.pinpai.BrandlistBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistRecyBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PInpaiApi {


    String BASE_URL = "https://cdplay.cn/";

    //todo 品牌制造商列表
    @GET("api/brand/detail")
    Flowable<BrandlistBean>  getBrandlistData(@Query("id") int id);

    @GET("api/goods/list")
    Flowable<BrandlistRecyBean> getBrandlistREcyData(@Query("brandId") int id);

}
