package com.live.view;

import com.live.base.Callback;
import com.live.base.IBaseModel;
import com.live.base.IBasePersenter;
import com.live.base.IBaseView;
import com.live.model.bean.RoomLiveBean;
import com.live.model.bean.StartLiveBean;

import java.util.Map;

public interface ILive {

    interface View extends IBaseView {
        void RoomLivereturn(RoomLiveBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void roomlive(int id);
    }


    interface Model extends IBaseModel {
        void roomlive(int id, Callback callback);
    }

}
