package com.live.model;

import com.live.base.BaseModel;
import com.live.base.Callback;
import com.live.model.bean.StartLiveBean;
import com.live.net.CommonSubscriber;
import com.live.net.HttpManager;
import com.live.utils.RxUtils;
import com.live.view.ICreateRoom;
import com.live.view.IStrat;

import java.util.Map;

public class StartModel extends BaseModel implements IStrat.Model{
    @Override
    public void startlive(Map<String, String> map, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().StartLive(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<StartLiveBean>(callback) {
                    @Override
                    public void onNext(StartLiveBean startLiveBean) {
                        callback.success(startLiveBean);
                    }
                }));
    }
}
