package com.example.shopkuang.api;

import com.example.shopkuang.bean.bean.shop.ShoppingCarBean;
import com.example.shopkuang.ui.Details.BuyDetailsBean;
import com.example.shopkuang.ui.Details.BuyDetailsBottomInfoBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BuyDetailApi {
    //todo 提交

    String BASE_URL = "https://cdplay.cn/";

    //商品详情购买页
    @GET("api/goods/detail")
    Flowable<BuyDetailsBean> getBuyDatailsData(@Query("id") int id);


    //商品 详情购买页 底部数据列表 api/goods/related?id=1155000
    @GET("api/goods/related")
    Flowable<BuyDetailsBottomInfoBean> getCategoryBottomInfo(@Query("id") String id);


    //购物车列表
    @GET("api/cart/index")
    Flowable<ShoppingCarBean> getShoppingCar();


}
