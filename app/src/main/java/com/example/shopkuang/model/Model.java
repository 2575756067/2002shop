package com.example.shopkuang.model;


import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.bean.shop.TopicBean;
import com.example.shopkuang.bean.shop.pinpai.BrandBean;
import com.example.shopkuang.bean.shop.ChanneBean;
import com.example.shopkuang.bean.shop.ChanneShujuBean;
import com.example.shopkuang.bean.shop.NewsBean;
import com.example.shopkuang.bean.shop.NewsShujuBean;
import com.example.shopkuang.bean.shop.pinpai.BrandlistBean;
import com.example.shopkuang.bean.shop.pinpai.BrandlistRecyBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.IHome;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.bean.shop.HomeBean;
import com.example.shopkuang.utils.RxUtils;

import java.util.HashMap;

public class Model extends BaseModel implements IHome.Model {


    @Override
    public void getHomeCallback(Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getService()
                .getHome()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeBean>(callback) {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        callback.success(homeBean);
                    }
                }));
    }

    @Override
    public void getCHanneCallback(int  id, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getService()
                .getChanneData(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ChanneBean>(callback) {
                    @Override
                    public void onNext(ChanneBean channeBean) {
                        callback.success(channeBean);
                    }
                }));
    }

    @Override
    public void getChanneShujuCallback(int categoryId, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getService()
                .getChanneShujuData(categoryId)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ChanneShujuBean>(callback) {
                    @Override
                    public void onNext(ChanneShujuBean channeShujuBean) {
                        callback.success(channeShujuBean);
                    }
                }));
    }

    @Override
    public void getBrandCallback(int page, int size, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getService()
                .getBrandData(page, size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandBean>(callback) {
                    @Override
                    public void onNext(BrandBean brandBean) {
                        callback.success(brandBean);
                    }
                }));
    }

    @Override
    public void getNewCallback(Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getService()
                .getNewsData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<NewsBean>(callback) {
                    @Override
                    public void onNext(NewsBean newsBean) {
                        callback.success(newsBean);
                    }
                }));
    }

    @Override
    public void getNewsShujuCallback(HashMap<String, String> map, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getService()
                .getNewsShujuData(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<NewsShujuBean>(callback) {
                    @Override
                    public void onNext(NewsShujuBean shujuBean) {
                        callback.success(shujuBean);
                    }
                }));
    }

    @Override
    public void getBrandlistCallback(int id, Callback callback) {
        addDisposiable(HttpManager
                .getInstance()
                .getPinpaiApi()
                .getBrandlistData(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandlistBean>(callback) {
                    @Override
                    public void onNext(BrandlistBean brandlistBean) {
                        callback.success(brandlistBean);
                    }
                }));
    }

    @Override
    public void getBrandlistRecyCallback(int id, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getPinpaiApi()
        .getBrandlistREcyData(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<BrandlistRecyBean>(callback) {
            @Override
            public void onNext(BrandlistRecyBean brandlistRecyBean) {
                callback.success(brandlistRecyBean);
            }
        }));
    }

    @Override
    public void getTopicCallback(int page, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getService()
        .getTopic(page)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TopicBean>(callback) {
            @Override
            public void onNext(TopicBean topicBean) {
                callback.success(topicBean);
            }
        }));
    }


}
