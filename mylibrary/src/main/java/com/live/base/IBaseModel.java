package com.live.base;

import io.reactivex.disposables.Disposable;

public interface IBaseModel {
    void addDisposable(Disposable disposable);//把当前的网络请求添加到缓存
    void clear();//取消还未进行的网络请求
}
