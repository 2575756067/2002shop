package com.example.shopkuang.presenter;

import com.example.shopkuang.base.BasePresenter;
import com.example.shopkuang.bean.bean.type.TypeDataBean;
import com.example.shopkuang.bean.bean.type.TypeTabBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.home.ITypeHome;
import com.example.shopkuang.model.TypeModel;

public class TypePresenter extends BasePresenter<ITypeHome.View> implements ITypeHome.presenter {

    ITypeHome.model model;
    ITypeHome.View mView;

    public TypePresenter(ITypeHome.View mView) {
        this.mView = mView;
        model = new TypeModel();
    }

    @Override
    public void getTypeTabData() {
        model.getTypeTabData(new Callback() {
            @Override
            public void success(Object o) {
                mView.getTypeTabReturn((TypeTabBean) o);
            }
        });
    }

    @Override
    public void getTypeData(int id) {
        model.getTypebData(id, new Callback() {
            @Override
            public void success(Object o) {
                mView.getTypeDataReturn((TypeDataBean) o);
            }
        });
    }
}
