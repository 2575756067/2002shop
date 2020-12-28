package com.example.shopkuang.presenter;

import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.ITopicHome;
import com.example.shopkuang.model.TopicModel;

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
}
