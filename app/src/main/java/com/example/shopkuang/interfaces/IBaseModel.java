package com.example.shopkuang.interfaces;

import io.reactivex.disposables.Disposable;

public interface IBaseModel {

    void addDisposiable(Disposable disposable);

    void clear();
}
