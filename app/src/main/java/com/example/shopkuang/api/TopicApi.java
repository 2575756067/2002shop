package com.example.shopkuang.api;

import com.example.shopkuang.bean.bean.topic.TopicDetailisCommentBean;
import com.example.shopkuang.bean.bean.topic.TopicRelevantBean;
import com.example.shopkuang.bean.bean.topic.TopicDetailsBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopicApi {

    String BASE_URL = "https://cdplay.cn/";


    //todo  专题详情
    @GET("api/topic/detail")
    Flowable<TopicDetailsBean> getTopicDetails(@Query("id") int id);


    //todo 专题详情页相关的专题推荐数据
    @GET("api/topic/related")
    Flowable<TopicRelevantBean> getTopicDetailsRelevant(@Query("id") int id);

    //todo 专题详情页评论
    @GET("api/comment/list")
    Flowable<TopicDetailisCommentBean> getTopicComment(@Query("valueId") int valueId, @Query("typeId") int typeId, @Query("size") int size);
}
