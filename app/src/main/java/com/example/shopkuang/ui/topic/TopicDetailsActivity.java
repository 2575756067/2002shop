package com.example.shopkuang.ui.topic;

import com.example.shopkuang.R;
import com.example.shopkuang.base.BaseActivity;

import com.example.shopkuang.bean.bean.shop.ShoppingCarBean;
import com.example.shopkuang.interfaces.home.IBuyhome;
import com.example.shopkuang.presenter.IBuyPresenter;
import com.example.shopkuang.ui.Details.BuyDetailsBean;
import com.example.shopkuang.ui.Details.BuyDetailsBottomInfoBean;

public class TopicDetailsActivity extends BaseActivity<IBuyhome.Presenter> implements IBuyhome.View {
    @Override
    protected int getLayout() {
        return R.layout.activity_topicdetails_layout;
    }

    @Override
    protected IBuyPresenter createPrenter() {
        return new IBuyPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void getBuyDetails(BuyDetailsBean buyDetailsBean) {

    }

    @Override
    public void getCategoryBottomInfoReturn(BuyDetailsBottomInfoBean result) {

    }

    @Override
    public void getShopCarReturn(ShoppingCarBean result) {

    }



}
