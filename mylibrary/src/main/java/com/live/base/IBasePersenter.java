package com.live.base;

public interface IBasePersenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();

}
