package com.live.presenter;

import com.live.base.BasePresenter;
import com.live.base.Callback;
import com.live.model.RoomModel;
import com.live.model.StartModel;
import com.live.model.bean.StartLiveBean;
import com.live.view.IRoom;
import com.live.view.IStrat;
import com.live.view.IStrat.Presenter;

import java.util.Map;

public class StratPresenter extends BasePresenter<IStrat.View> implements IStrat.Presenter {

    IStrat.Model model;

    public StratPresenter() {
        model = new StartModel();
    }

    @Override
    public void startlive(Map<String, String> map) {
        model.startlive(map, new Callback() {
            @Override
            public void success(Object data) {
                if(mView != null){
                    mView.StartLivereturn((StartLiveBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
