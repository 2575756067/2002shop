package com.example.shopkuang.interfaces.home;

import com.example.shopkuang.bean.bean.topic.TopicDetailisCommentBean;
import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;
import com.example.shopkuang.bean.bean.topic.TopicRelevantBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;
import com.example.shopkuang.presenter.TopicDetailsPresenter;

import java.util.HashMap;
import java.util.Map;

public interface ITopicHome {

    interface View extends IBaseView {

        //todo  专题详情
        void getTopicDetailsReturn(TopicDetailsBean result);

        //评论
        void getTopicCommentReturn(TopicDetailisCommentBean result);

        //底部数据
        void getTopicBottomReturn(TopicRelevantBean result);
    }

    interface Presenter extends IBasePresenter<View> {

        //todo 专题详情
        void getTopicDetailsData(int id);

        //todo  评论
        void getTopicCommentData(Map<String, String> map);

        //todo  底部数据
        void getTopicBottomData(int id);
    }

    interface Model extends IBaseModel {

        //todo  专题详情
        void getTopicDetalisCallback(int id, Callback callback);

        //todo 评论
        void getTopicCommentCallback(Map<String, String> map, Callback callback);

        //todo  底部数据
        void getTopicBottomCallback(int id, Callback callback);
    }
}
