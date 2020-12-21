package com.example.shopkuang;

import android.content.Intent;

import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.interfaces.IBasePresenter;


public class SplaceActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_splace;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

        Intent intent = new Intent(SplaceActivity.this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void initData() {

    }
}
