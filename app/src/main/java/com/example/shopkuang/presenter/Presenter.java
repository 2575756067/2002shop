package com.example.shopkuang.presenter;


import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.bean.bean.topic.TopicBean;
import com.example.shopkuang.bean.bean.pinpai.BrandBean;
import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.bean.bean.ChanneShujuBean;
import com.example.shopkuang.bean.bean.NewsBean;
import com.example.shopkuang.bean.bean.NewsShujuBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistRecyBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.IHome;
import com.example.shopkuang.model.Model;
import com.example.shopkuang.bean.bean.HomeBean;

import java.util.HashMap;

public class Presenter extends BasePresenter<IHome.View> implements IHome.Presenter {

    IHome.Model model;
    IHome.View mView;

    public Presenter(IHome.View mView) {
        this.mView = mView;
        model = new Model();
    }

    @Override
    public void getHomeData() {
        model.getHomeCallback(new Callback() {
            @Override
            public void success(Object o) {
                mView.getHome((HomeBean) o);
            }
        });
    }

    @Override
    public void getChanneData(int id) {
        model.getCHanneCallback(id, new Callback() {
            @Override
            public void success(Object o) {
                mView.getChanne((ChanneBean) o);
            }
        });
    }

    @Override
    public void getChanneShujuData(int categoryId) {
        model.getChanneShujuCallback(categoryId, new Callback() {
            @Override
            public void success(Object o) {
                mView.getChanneShuju((ChanneShujuBean) o);
            }
        });
    }

    @Override
    public void getBrandData(int page, int size) {
        model.getBrandCallback(page, size, new Callback() {
            @Override
            public void success(Object o) {
                mView.getBrand((BrandBean) o);
            }
        });
    }

    @Override
    public void getNewsData() {
        model.getNewCallback(new Callback() {
            @Override
            public void success(Object o) {
                mView.getNews((NewsBean) o);
            }
        });
    }

    @Override
    public void getNewsShujuData(HashMap<String, String> map) {
        model.getNewsShujuCallback(map, new Callback() {
            @Override
            public void success(Object o) {
                mView.getNewsShuju((NewsShujuBean) o);
            }
        });
    }

    @Override
    public void getBrandlistData(int id) {
        model.getBrandlistCallback(id, new Callback() {
            @Override
            public void success(Object o) {
                mView.getBrandlist((BrandlistBean) o);
            }
        });
    }

    @Override
    public void getBrandlistRecyData(int id) {
        model.getBrandlistRecyCallback(id, new Callback() {
            @Override
            public void success(Object o) {
                mView.getBrandlistRecy((BrandlistRecyBean) o);
            }
        });
    }

    @Override
    public void getTopicData(int page) {
        model.getTopicCallback(page, new Callback() {
            @Override
            public void success(Object o) {
                mView.getTopic((TopicBean) o);
            }
        });
    }


}
