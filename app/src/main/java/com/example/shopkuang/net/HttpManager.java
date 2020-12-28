package com.example.shopkuang.net;

import android.util.Log;


import com.example.shopkuang.api.AddressApi;
import com.example.shopkuang.api.ApiService;
import com.example.shopkuang.api.BuyDetailApi;
import com.example.shopkuang.api.LoginApi;
import com.example.shopkuang.api.PInpaiApi;
import com.example.shopkuang.api.TopicApi;
import com.example.shopkuang.api.TypeApi;
import com.example.shopkuang.shoppingcar.ICarApi;
import com.example.shopkuang.utils.SpUtils;

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
                    .addHeader("X-Nideshop-Token", SpUtils.getInstance().getString("token"))
                    //注册
                    .addHeader("Client-Type", SpUtils.getInstance().getString("token"))
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * ServiceApi
     *
     * @return
     */
    private ApiService apiService;
    private PInpaiApi apipinpai;
    private BuyDetailApi apibuyDetails;
    private TypeApi typeApi;
    private LoginApi loginApi;
    private ICarApi iCarApi;
    private AddressApi addressApi;
    private TopicApi topicApi;

    public TopicApi getTopicApi() {
        if (topicApi == null) {
            topicApi = getRetrofit(TopicApi.BASE_URL).create(TopicApi.class);
        }
        return topicApi;
    }


    public AddressApi getAddressApi() {
        if (addressApi == null) {
            addressApi = getRetrofit(AddressApi.BASE_URL).create(AddressApi.class);
        }
        return addressApi;
    }

    public ICarApi getiCarApi() {
        if (iCarApi == null) {
            iCarApi = getRetrofit(ICarApi.BASE_URL).create(ICarApi.class);
        }
        return iCarApi;
    }

    public ApiService getService() {
        if (apiService == null) {
            apiService = getRetrofit(ApiService.BASE_URL).create(ApiService.class);
        }
        return apiService;
    }

    public PInpaiApi getPinpaiApi() {
        if (apipinpai == null) {
            apipinpai = getRetrofit(PInpaiApi.BASE_URL).create(PInpaiApi.class);
        }
        return apipinpai;
    }

    public BuyDetailApi getApibuyDetails() {
        if (apibuyDetails == null) {
            apibuyDetails = getRetrofit(BuyDetailApi.BASE_URL).create(BuyDetailApi.class);
        }

        return apibuyDetails;
    }

    public TypeApi getTypeApi() {
        if (typeApi == null) {
            typeApi = getRetrofit(TypeApi.BASE_URL).create(TypeApi.class);
        }
        return typeApi;
    }

    public LoginApi getLoginApi() {
        if (loginApi == null) {
            loginApi = getRetrofit(loginApi.BASE_URL).create(LoginApi.class);
        }

        return loginApi;
    }

}
