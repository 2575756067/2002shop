package com.example.shopkuang.bean.bean.live;

import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;

import java.util.Map;

import retrofit2.http.FieldMap;

public interface ILiveHome {

    interface View extends IBaseView {
        //todo  创建房间
        void CreateLiveRoomReturn(SetRoomBean result);

        //todo 开启直播
        void OpenLiveReturn(OpenLiveBean result);
    }

    interface presenter extends IBasePresenter<View> {
        //todo  创建房间
        void CreateRoomData(@FieldMap Map<String, String> map);

        //todo 开启直播
        void OpenLiveData(@FieldMap Map<String, String> map);
    }

    interface model extends IBaseModel {
        //todo  创建房间
        void CreateRoomCallback(@FieldMap Map<String, String> map, Callback callback);

        //todo 开启直播
        void OpenLiveCallback(@FieldMap Map<String, String> map, Callback callback);
    }
}
