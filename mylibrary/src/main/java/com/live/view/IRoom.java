package com.live.view;

import com.live.base.Callback;
import com.live.base.IBaseModel;
import com.live.base.IBasePersenter;
import com.live.base.IBaseView;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.StartLiveBean;
import com.live.model.bean.RoomBean;

import java.util.Map;

public interface IRoom {

    interface View extends IBaseView {
        void roomreturn(RoomBean result);
        void MeRoomreturn(MeRoomBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void room();
        void meroom();
    }


    interface Model extends IBaseModel {
        void room(Callback callback);
        void meroom(Callback callback);
    }

}
