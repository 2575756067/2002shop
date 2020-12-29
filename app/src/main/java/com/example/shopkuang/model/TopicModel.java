package com.example.shopkuang.model;

import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.bean.bean.topic.TopicDetailisCommentBean;
import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;
import com.example.shopkuang.bean.bean.topic.TopicRelevantBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.ITopicHome;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.utils.RxUtils;

import java.util.Map;

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

    //评论数据
    @Override
    public void getTopicCommentCallback(Map<String, String> map, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getTopicApi()
        .getTopicComment(map)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TopicDetailisCommentBean>(callback) {
            @Override
            public void onNext(TopicDetailisCommentBean topicDetailisCommentBean) {
                callback.success(topicDetailisCommentBean);
            }
        }));
    }

    //底部数据
    @Override
    public void getTopicBottomCallback(int id, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getTopicApi()
        .getTopicDetailsRelevant(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TopicRelevantBean>(callback) {
            @Override
            public void onNext(TopicRelevantBean topicRelevantBean) {
                callback.success(topicRelevantBean);
            }
        }));
    }
}
