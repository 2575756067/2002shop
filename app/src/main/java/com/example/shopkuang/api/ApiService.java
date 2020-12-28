package com.example.shopkuang.api;


import com.example.shopkuang.bean.bean.topic.TopicBean;
import com.example.shopkuang.bean.bean.pinpai.BrandBean;
import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.bean.bean.ChanneShujuBean;
import com.example.shopkuang.bean.bean.HomeBean;
import com.example.shopkuang.bean.bean.NewsBean;
import com.example.shopkuang.bean.bean.NewsShujuBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    String BASE_URL = "https://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeBean> getHome();

    //分类tab标题
    @GET("api/catalog/index/")
    Flowable<ChanneBean> getChanneData(@Query("id") int id);

    //分类具体数据
    @GET("api/goods/list")
    Flowable<ChanneShujuBean> getChanneShujuData(@Query("categoryId") int categoryId);

    //品牌详情
    @GET("api/brand/list")
    Flowable<BrandBean> getBrandData(@Query("page") int page, @Query("size") int size);

    //新品
    @GET("api/goods/hot")
    Flowable<NewsBean> getNewsData();

    //新品发布的条件筛选数据接口
    @GET("api/goods/list")
    Flowable<NewsShujuBean>  getNewsShujuData(@QueryMap HashMap<String,String> map);

    //专题
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page")int page);
}
