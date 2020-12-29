package com.example.shopkuang.api;

import com.example.shopkuang.bean.bean.topic.TopicDetailisCommentBean;
import com.example.shopkuang.bean.bean.topic.TopicRelevantBean;
import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface TopicApi {

    String BASE_URL = "https://cdplay.cn/";


    //todo  专题详情
    @GET("api/topic/detail")
    Flowable<TopicDetailsBean> getTopicDetails(@Query("id") int id);


    //todo 专题详情页评论
    @GET("api/comment/list")
    Flowable<TopicDetailisCommentBean> getTopicComment(@QueryMap Map<String,String> map);


    //todo 专题详情页底部的数据
    @GET("api/topic/related")
    Flowable<TopicRelevantBean> getTopicDetailsRelevant(@Query("id") int id);
}
