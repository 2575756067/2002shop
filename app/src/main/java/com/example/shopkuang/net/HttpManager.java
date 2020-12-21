package com.example.shopkuang.net;

import android.util.Log;


import com.example.shopkuang.api.ApiService;
import com.example.shopkuang.api.BuyDetailApi;
import com.example.shopkuang.api.PInpaiApi;
import com.example.shopkuang.api.TypeApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private ApiService apiService;


    private Retrofit getRetrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return okHttpClient;
    }

    static class LoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody responseBody = response.peekBody(Integer.MAX_VALUE);
            Log.i("responseBody", responseBody.string());
            return chain.proceed(request);
        }
    }

    /**
     * 拦截的头处理
     */
    static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * ServiceApi
     *
     * @return
     */
    public ApiService getService() {
        if (apiService == null) {
            apiService = getRetrofit(ApiService.BASE_URL).create(ApiService.class);
        }
        return apiService;
    }

    private PInpaiApi apipinpai;

    public PInpaiApi getPinpaiApi() {
        if (apipinpai == null) {
            apipinpai = getRetrofit(PInpaiApi.BASE_URL).create(PInpaiApi.class);
        }
        return apipinpai;
    }

    private BuyDetailApi apibuyDetails;

    public BuyDetailApi getApibuyDetails() {
        if (apibuyDetails == null) {
            apibuyDetails = getRetrofit(BuyDetailApi.BASE_URL).create(BuyDetailApi.class);
        }

        return apibuyDetails;
    }

    private TypeApi typeApi;

    public TypeApi getTypeApi() {
        if (typeApi == null) {
            typeApi = getRetrofit(TypeApi.BASE_URL).create(TypeApi.class);
        }
        return typeApi;
    }

}
