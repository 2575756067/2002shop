package com.example.shopkuang.interfaces.home;


import com.example.shopkuang.bean.bean.TopicBean;
import com.example.shopkuang.bean.bean.pinpai.BrandBean;
import com.example.shopkuang.bean.bean.ChanneBean;
import com.example.shopkuang.bean.bean.ChanneShujuBean;
import com.example.shopkuang.bean.bean.NewsBean;
import com.example.shopkuang.bean.bean.NewsShujuBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistBean;
import com.example.shopkuang.bean.bean.pinpai.BrandlistRecyBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;
import com.example.shopkuang.bean.bean.HomeBean;

import java.util.HashMap;

public interface IHome {

    interface View extends IBaseView {

        void getHome(HomeBean homeBean);

        void getChanne(ChanneBean channeBean);

        void getChanneShuju(ChanneShujuBean channeShujuBean);

        void getBrand(BrandBean brandBean);

        void getNews(NewsBean newsBean);

        void getNewsShuju(NewsShujuBean shujuBean);

        void getBrandlist(BrandlistBean brandlistBean);

        void getBrandlistRecy(BrandlistRecyBean brandlistRecyBean);

        void getTopic(TopicBean topicBean);
    }

    interface Presenter extends IBasePresenter<View> {

        void getHomeData();

        void getChanneData(int  id);

        void getChanneShujuData(int categoryId);

        void getBrandData(int page, int size);

        void getNewsData();

        void getNewsShujuData(HashMap<String, String> map);

        void getBrandlistData(int id);

        void getBrandlistRecyData(int id);

        void getTopicData(int page);
    }

    interface Model extends IBaseModel {

        void getHomeCallback(Callback callback);

        void getCHanneCallback(int id, Callback callback);

        void getChanneShujuCallback(int categoryId, Callback callback);

        void getBrandCallback(int page, int size, Callback callback);

        void getNewCallback(Callback callback);

        void getNewsShujuCallback(HashMap<String, String> map, Callback callback);

        void getBrandlistCallback(int id, Callback callback);

        void getBrandlistRecyCallback(int id, Callback callback);

        void getTopicCallback(int page, Callback callback);
    }
}
