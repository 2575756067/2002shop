package com.live.view;

import com.live.base.Callback;
import com.live.base.IBaseModel;
import com.live.base.IBasePersenter;
import com.live.base.IBaseView;
import com.live.model.bean.CreateRoomBean;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.UpdateRoomBean;

import java.util.Map;

public interface ICreateRoom {

    interface View extends IBaseView {
        void Createreturn(CreateRoomBean result);
        void MeRoomturn(MeRoomBean result);
        void Updatereturn(UpdateRoomBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void create(Map<String, String> map);
        void meroom();
        void update(Map<String, String> map);
    }


    interface Model extends IBaseModel {
        void create(Map<String, String> map,Callback callback);
        void meroom(Callback callback);
        void update(Map<String, String> map,Callback callback);
    }

}
