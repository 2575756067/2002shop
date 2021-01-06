package com.live.base;


import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePersenter<V> {

    WeakReference<V> weakReference;
    protected V mView;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<V>(view);
        mView = weakReference.get();
    }

    @Override
    public void unAttachView() {
        weakReference.clear();
        mView = null;
    }

}
