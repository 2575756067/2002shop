package com.example.shopkuang;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.shopkuang.base.BaseActivity;
import com.example.shopkuang.interfaces.IBasePresenter;
import com.example.shopkuang.welcome.VpAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class SplaceActivity extends BaseActivity {


    Disposable disposable;
    private ViewPager mVp;
    private TextView tv_dao;
    private PopupWindow window;

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

    }


    private void initPop() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(R.drawable.page1);
        integerList.add(R.drawable.page2);
        integerList.add(R.drawable.page3);

        View view = View.inflate(this, R.layout.layout_pop, null);
        window = new PopupWindow(view, -1, -1);
        tv_dao = view.findViewById(R.id.tv_dao);
        mVp = view.findViewById(R.id.mVp);
        VpAdapter vpAdapter = new VpAdapter(this, integerList, window);
        mVp.setAdapter(vpAdapter);
        popupVpCli();
        window.showAsDropDown(this.findViewById(R.id.vp_splace));
    }

    private void popupVpCli() {
        //页码的点击监听
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {//在最后一页执行倒计时
                    tv_dao.setVisibility(View.VISIBLE);
                    //TODO       Interval操作符(有范围)：创建一个按照固定时间发射整数序列的Observable
                    disposable = Observable.intervalRange(0, 6, 0, 1, TimeUnit.SECONDS) //起始值，发送总数量，初始延迟，固定延迟
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    long time = 5 - aLong;
                                    tv_dao.setText(time + "s");
                                    if (time == 0) {
                                        window.dismiss();
                                    }
                                }
                            });
                } else {
                    tv_dao.setVisibility(View.GONE);
                    cancelCallback();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initPop();
        initView();
    }


    @Override
    protected void initData() {

    }

    //取消订阅的方法
    private void cancelCallback() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
