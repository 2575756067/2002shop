package com.live.presenter;

import com.live.base.BasePresenter;
import com.live.base.Callback;
import com.live.model.LiveModel;
import com.live.model.StartModel;
import com.live.model.bean.RoomLiveBean;
import com.live.view.ILive;
import com.live.view.IStrat;

public class LivePresenter extends BasePresenter<ILive.View> implements ILive.Presenter  {

    ILive.Model model;

    public LivePresenter() {
        model = new LiveModel();
    }

    @Override
    public void roomlive(int id) {
        model.roomlive(id, new Callback() {
            @Override
            public void success(Object data) {
                if(mView != null){
                    mView.RoomLivereturn((RoomLiveBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
