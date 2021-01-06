package com.example.shopkuang.bean.bean.live.interfaces.home;

import com.example.shopkuang.bean.bean.live.OpenLiveBean;
import com.example.shopkuang.bean.bean.live.SetRoomBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiLive {

    String BASE_URL = "https://cdplay.cn/";


    //todo  创建房间
    @POST("api/live/createRoom")
    @FormUrlEncoded
    Flowable<SetRoomBean> CreateRoom(@FieldMap Map<String, String> map);

    //todo 开启直播
    @POST("api/live/startLive")
    @FormUrlEncoded
    Flowable<OpenLiveBean> OPenLive(@FieldMap Map<String, String> map);

}
