package com.live.presenter;

import com.live.base.BasePresenter;
import com.live.base.Callback;
import com.live.model.bean.CreateRoomBean;
import com.live.model.CreateRoomModel;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.UpdateRoomBean;
import com.live.view.ICreateRoom;

import java.util.Map;

public class CreateRoomPresenter extends BasePresenter<ICreateRoom.View> implements ICreateRoom.Presenter {

    ICreateRoom.Model model;

    public CreateRoomPresenter() {
        model = new CreateRoomModel();
    }

    @Override
    public void create(Map<String, String> map) {
        if(mView != null){
            model.create(map, new Callback() {
                @Override
                public void success(Object data) {
                    mView.Createreturn((CreateRoomBean) data);
                }

                @Override
                public void fail(String err) {

                }
            });
        }
    }

    @Override
    public void meroom() {
        if(mView != null){
            model.meroom(new Callback() {
                @Override
                public void success(Object data) {
                    mView.MeRoomturn((MeRoomBean) data);
                }

                @Override
                public void fail(String err) {

                }
            });
        }
    }

    @Override
    public void update(Map<String, String> map) {
        if(mView != null){
            model.update(map, new Callback() {
                @Override
                public void success(Object data) {
                    mView.Updatereturn((UpdateRoomBean) data);
                }

                @Override
                public void fail(String err) {

                }
            });
        }
    }

}
