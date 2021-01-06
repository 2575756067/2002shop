package com.live.presenter;

import com.live.base.BasePresenter;
import com.live.base.Callback;
import com.live.model.RoomModel;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.StartLiveBean;
import com.live.model.bean.RoomBean;
import com.live.view.IRoom;

import java.util.Map;

public class RoomPresenter extends BasePresenter<IRoom.View> implements IRoom.Presenter {

    IRoom.Model model;

    public RoomPresenter() {
        model = new RoomModel();
    }

    @Override
    public void room() {
        model.room(new Callback() {
            @Override
            public void success(Object data) {
                if(mView != null){
                    mView.roomreturn((RoomBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void meroom() {
        model.meroom(new Callback() {
            @Override
            public void success(Object data) {
                if(mView != null){
                    mView.MeRoomreturn((MeRoomBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}
