package com.live.model;

import com.live.base.BaseModel;
import com.live.base.Callback;
import com.live.model.bean.RoomLiveBean;
import com.live.model.bean.StartLiveBean;
import com.live.net.CommonSubscriber;
import com.live.net.HttpManager;
import com.live.utils.RxUtils;
import com.live.view.ILive;
import com.live.view.IStrat;

public class LiveModel extends BaseModel implements ILive.Model {
    @Override
    public void roomlive(int id, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().RoomLive(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RoomLiveBean>(callback) {
                    @Override
                    public void onNext(RoomLiveBean roomLiveBean) {
                        callback.success(roomLiveBean);
                    }
                }));
    }
}
