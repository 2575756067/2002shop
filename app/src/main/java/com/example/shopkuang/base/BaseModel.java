package com.example.shopkuang.base;


import com.example.shopkuang.interfaces.IBaseModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel implements IBaseModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void addDisposiable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }
}
