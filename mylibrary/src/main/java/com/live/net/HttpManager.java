package com.live.net;

import android.util.Log;

import com.live.model.api.ApiShop;
import com.live.utils.SpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 * 整个项目全局使用
 */
public class HttpManager {
    //单例模式
    private static HttpManager instance;

    public static HttpManager getInstance(){
        if(instance == null){
            synchronized(HttpManager.class){//加锁
                if(instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    //日志拦截器
    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody responseBody = response.peekBody(Integer.MAX_VALUE);
            Log.e("responseBody",responseBody.string());
            return response;
        }
    }

    //头部拦截器
    static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization","APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
                    //添加购物车
                    .addHeader("X-Nideshop-Token", SpUtils.getInstance().getString("token"))
                    //注册
                    .addHeader("Client-Type",SpUtils.getInstance().getString("token"))
                    .build();
            return chain.proceed(request);
        }
    }

    private OkHttpClient getOkHttpClient(){
        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return ok;
    }

    //网络请求
    private Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())///添加拦截器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public ApiShop apiShop;
    public ApiShop getApiShop() {
        if(apiShop == null){
            apiShop = getRetrofit(ApiShop.BASE_URL).create(ApiShop.class);
        }
        return apiShop;
    }

}
