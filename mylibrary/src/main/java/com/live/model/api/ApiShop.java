package com.live.model.api;
import com.live.model.bean.CreateRoomBean;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.StartLiveBean;
import com.live.model.bean.RoomBean;
import com.live.model.bean.RoomLiveBean;
import com.live.model.bean.UpdateRoomBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;;

public interface ApiShop {

    String BASE_URL = "https://cdplay.cn/";

    //获取房间列表
    @GET("api/live/getRoomList")
    Flowable<RoomBean> getRoom();

    //创建直播间
    @POST("api/live/createRoom") //room_name 小白的直播间  房间名      room_icon https://shop-app1.oss-cn-beijing.aliyuncs.com/live/1/room.jpg    房间列表默认的背景图
    @FormUrlEncoded
    Flowable<CreateRoomBean> CreateRoom(@FieldMap Map<String, String> map);     //isopen    1   1公开  2密码

    //修改房间信息
    @POST("api/live/editorRoom")    //name  房间名     roomid  房间id    icon    房间背景图地址    title 直播标题
    @FormUrlEncoded
    Flowable<UpdateRoomBean> UpdateRoom(@FieldMap Map<String, String> map);

    //获取自己房间信息
    @POST("api/live/myroom")
    Flowable<MeRoomBean> MeRoom();

    //开启直播  (推流 开始自己的直播 push)
    @POST("api/live/startLive")
    @FormUrlEncoded
    Flowable<StartLiveBean> StartLive(@FieldMap Map<String, String> map);

    //房间播放地址获取  (拉流 看别人的 play)
    @POST("api/live/roomLiveUrl")
    @FormUrlEncoded
    Flowable<RoomLiveBean> RoomLive(@Field("roomid")int id);

}
