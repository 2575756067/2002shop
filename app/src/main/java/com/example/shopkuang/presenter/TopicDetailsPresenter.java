package com.example.shopkuang.presenter;

import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.bean.bean.topic.TopicDetailisCommentBean;
import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;
import com.example.shopkuang.bean.bean.topic.TopicRelevantBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.ITopicHome;
import com.example.shopkuang.model.TopicModel;

import java.util.Map;

public class TopicDetailsPresenter extends BasePresenter<ITopicHome.View> implements ITopicHome.Presenter {

    ITopicHome.View mView;
    ITopicHome.Model model;

    public TopicDetailsPresenter(ITopicHome.View mView) {
        this.mView = mView;
        model = new TopicModel();
    }

    @Override
    public void getTopicDetailsData(int id) {
        model.getTopicDetalisCallback(id, new Callback() {
            @Override
            public void success(Object o) {
                mView.getTopicDetailsReturn((TopicDetailsBean) o);
            }
        });
    }


    //todo 评论数据
    @Override
    public void getTopicCommentData(Map<String, String> map) {
        model.getTopicCommentCallback(map, new Callback() {
            @Override
            public void success(Object o) {
                mView.getTopicCommentReturn((TopicDetailisCommentBean) o);
            }
        });
    }

    @Override
    public void getTopicBottomData(int id) {
        model.getTopicBottomCallback(id, new Callback() {
            @Override
            public void success(Object o) {
                mView.getTopicBottomReturn((TopicRelevantBean) o);
            }
        });
    }
}
