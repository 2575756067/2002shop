package com.example.shopkuang.interfaces.home;

import com.example.shopkuang.bean.shop.type.TypeDataBean;
import com.example.shopkuang.bean.shop.type.TypeTabBean;
import com.example.shopkuang.interfaces.Callback;
import com.example.shopkuang.interfaces.IBaseModel;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.interfaces.IBaseView;

public interface ITypeHome {


    interface View extends IBaseView {

        //垂直分类
        void getTypeTabReturn(TypeTabBean result);

        //对应的数据
        void getTypeDataReturn(TypeDataBean result);
    }

    interface presenter extends IBasePresenter<View> {
        void getTypeTabData();

        void getTypeData(int id);
    }

    interface model extends IBaseModel {

        void getTypeTabData(Callback callback);

        void getTypebData(int id, Callback callback);
    }
}
