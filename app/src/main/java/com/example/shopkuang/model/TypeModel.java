package com.example.shopkuang.model;

import com.example.shopkuang.base.BaseModel;
import com.example.shopkuang.bean.bean.type.TypeDataBean;
import com.example.shopkuang.bean.bean.type.TypeTabBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.ITypeHome;
import com.example.shopkuang.net.CommonSubscriber;
import com.example.shopkuang.net.HttpManager;
import com.example.shopkuang.utils.RxUtils;

public class TypeModel extends BaseModel  implements ITypeHome.model {


    @Override
    public void getTypeTabData(Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getTypeApi()
        .getTabData()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TypeTabBean>(callback) {
            @Override
            public void onNext(TypeTabBean typeTabBean) {
                callback.success(typeTabBean);
            }
        }));
    }

    @Override
    public void getTypebData(int id, Callback callback) {
        addDisposiable(HttpManager
        .getInstance()
        .getTypeApi()
        .getTypeData(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TypeDataBean>(callback) {
            @Override
            public void onNext(TypeDataBean typeDataBean) {
                callback.success(typeDataBean);
            }
        }));
    }
}
