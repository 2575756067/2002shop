package com.example.shopkuang.bean.bean.live.interfaces.home.presenter;

import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.bean.bean.live.ILiveHome;
import com.example.shopkuang.bean.bean.live.OpenLiveBean;
import com.example.shopkuang.bean.bean.live.SetRoomBean;
import com.example.shopkuang.bean.bean.live.interfaces.home.model.LiveModel;
import com.example.shopkuang.interfaces.Callback;

import java.util.Map;

public class LivePresenter extends BasePresenter<ILiveHome.View> implements ILiveHome.presenter {

    ILiveHome.model model;
    ILiveHome.View mView;

    public LivePresenter(ILiveHome.View mView) {
        this.mView = mView;
        model = new LiveModel();
    }

    @Override
    public void CreateRoomData(Map<String, String> map) {
        model.CreateRoomCallback(map, new Callback() {
            @Override
            public void success(Object o) {
                mView.CreateLiveRoomReturn((SetRoomBean) o);
            }
        });
    }

    @Override
    public void OpenLiveData(Map<String, String> map) {
        model.OpenLiveCallback(map, new Callback() {
            @Override
            public void success(Object o) {
                mView.OpenLiveReturn((OpenLiveBean) o);
            }
        });
    }
}
