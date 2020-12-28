package com.example.shopkuang.interfaces.home;

import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;

public interface ITopicHome {

    interface View extends IBaseView {

        //todo  专题详情
        void getTopicDetailsReturn(TopicDetailsBean result);
    }

    interface Presenter extends IBasePresenter<View> {

        //todo 专题详情
        void getTopicDetailsData(int id);
    }

    interface Model extends IBaseModel {

        //todo  专题详情
        void getTopicDetalisCallback(int id, Callback callback);
    }
}
