package com.example.shopkuang.model;

import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.ITopicHome;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.utils.RxUtils;

public class TopicModel extends BaseModel implements ITopicHome.Model {


    //todo  专题详情
    @Override
    public void getTopicDetalisCallback(int id, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getTopicApi()
        .getTopicDetails(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TopicDetailsBean>(callback) {
            @Override
            public void onNext(TopicDetailsBean topicDetailsBean) {
                callback.success(topicDetailsBean);
            }
        }));
    }
}
