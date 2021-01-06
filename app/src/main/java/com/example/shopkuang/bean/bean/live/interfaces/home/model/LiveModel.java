package com.example.shopkuang.bean.bean.live.interfaces.home.model;

import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.bean.bean.live.ILiveHome;
import com.example.shopkuang.bean.bean.live.OpenLiveBean;
import com.example.shopkuang.bean.bean.live.SetRoomBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.utils.RxUtils;

import java.util.Map;

import io.reactivex.disposables.Disposable;

public class LiveModel extends BaseModel implements ILiveHome.model {

    @Override
    public void CreateRoomCallback(Map<String, String> map, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getApiLive()
                .CreateRoom(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SetRoomBean>(callback) {
                    @Override
                    public void onNext(SetRoomBean setRoomBean) {
                        callback.success(setRoomBean);
                    }
                }));
    }

    @Override
    public void OpenLiveCallback(Map<String, String> map, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getApiLive()
                .OPenLive(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<OpenLiveBean>(callback) {
                    @Override
                    public void onNext(OpenLiveBean openLiveBean) {
                        callback.success(openLiveBean);
                    }
                }));
    }
}
