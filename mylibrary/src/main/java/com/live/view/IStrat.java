package com.live.view;

import com.live.base.Callback;
import com.live.base.IBaseModel;
import com.live.base.IBasePersenter;
import com.live.base.IBaseView;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.RoomBean;
import com.live.model.bean.StartLiveBean;

import java.util.Map;

public interface IStrat {

    interface View extends IBaseView {
        void StartLivereturn(StartLiveBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void startlive(Map<String,String> map);
    }


    interface Model extends IBaseModel {
        void startlive(Map<String,String> map,Callback callback);
    }

}
