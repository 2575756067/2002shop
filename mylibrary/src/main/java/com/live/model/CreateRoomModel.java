package com.live.model;

import com.live.base.BaseModel;
import com.live.base.Callback;
import com.live.model.bean.CreateRoomBean;
import com.live.model.bean.MeRoomBean;
import com.live.model.bean.UpdateRoomBean;
import com.live.net.CommonSubscriber;
import com.live.net.HttpManager;
import com.live.utils.RxUtils;
import com.live.view.ICreateRoom;

import java.util.Map;

public class CreateRoomModel extends BaseModel implements ICreateRoom.Model {
    @Override
    public void create(Map<String, String> map, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().CreateRoom(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CreateRoomBean>(callback) {
                    @Override
                    public void onNext(CreateRoomBean createRoomBean) {
                        callback.success(createRoomBean);
                    }
                }));
    }

    @Override
    public void meroom(Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().MeRoom()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<MeRoomBean>(callback) {
                    @Override
                    public void onNext(MeRoomBean meRoomBean) {
                        callback.success(meRoomBean);
                    }
                }));
    }

    @Override
    public void update(Map<String, String> map, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().UpdateRoom(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateRoomBean>(callback) {
                    @Override
                    public void onNext(UpdateRoomBean updateRoomBean) {
                        callback.success(updateRoomBean);
                    }
                }));
    }
}
