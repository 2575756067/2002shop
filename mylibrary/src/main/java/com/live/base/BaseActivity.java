package com.live.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePersenter> extends AppCompatActivity implements IBaseView {
    //p层关联
    protected P persenter;
    protected Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout=getLayout();//需要界面view
        if(layout<=0){
            throw new RuntimeException("layout id not allow 0 or <0");
        }else {
            setContentView(getLayout());
        }
        bind = ButterKnife.bind(this);

        persenter = createPersenter();
        if(persenter!=null){
            persenter.attachView(this);
        }
        //初始化界面
        initView();
        //初始化界面数据
        initData();
    }

    //定义一个获取当前界面的方法  由子类提供的
    protected abstract int getLayout();
    //初始化p层的方法
    protected abstract P createPersenter();
    //初始化界面
    protected abstract void initView();
    //初始化界面数据
    protected abstract void initData();

    @Override
    public void tips(String tip) {//错误时的view方法
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loading(int visible) {

    }

    @Override
    public void showToast(String msg, int time) {
        Toast.makeText(this, msg,time).show();
    }

    // 界面销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bind != null){//解除
            bind.unbind();
        }
        //释放p关联的v的引用
        if(persenter != null){
            persenter.unAttachView();
        }
    }

}
