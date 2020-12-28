package com.example.shopkuang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.shopkuang.adapter.MainVpAdapter;
import com.example.shopkuang.ui.home.HomeFragment;
import com.example.shopkuang.ui.me.MeFragment;
import com.example.shopkuang.ui.shop.ShopFragment;
import com.example.shopkuang.ui.sort.SortFragment;
import com.example.shopkuang.ui.topic.TopicFragment;
import com.example.shopkuang.utils.CustomViewPager;
import com.example.shopkuang.welcome.VpAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private CustomViewPager vp;
    private BottomNavigationView navView;
    private ArrayList<Fragment> fragments;
    private PopupWindow window;
    private TextView tv_dao;
    private ViewPager mVp;
    private Disposable disposable;
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        vp = findViewById(R.id.main_vp);
        vp.setScanScroll(false);

    }

    private void initwss() {
        MainVpAdapter adapter = new MainVpAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        item.setIcon(R.mipmap.ic_menu_choice_pressed);
                        vp.setCurrentItem(0);
                        return true;
                    case R.id.navigation_topic:
                        item.setIcon(R.mipmap.ic_menu_topic_pressed);
                        vp.setCurrentItem(1);
                        return true;
                    case R.id.navigation_sort:
                        item.setIcon(R.mipmap.ic_menu_sort_pressed);
                        vp.setCurrentItem(2);
                        return true;
                    case R.id.navigation_shop:
                        item.setIcon(R.mipmap.ic_menu_shoping_pressed);
                        vp.setCurrentItem(3);
                        return true;
                    case R.id.navigation_me:
                        item.setIcon(R.mipmap.ic_menu_me_pressed);
                        vp.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //fragment 集合
    private void initFragment() {
        fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        TopicFragment topicFragment = new TopicFragment();
        SortFragment sortFragment = new SortFragment();
        ShopFragment shopFragment = new ShopFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(topicFragment);
        fragments.add(sortFragment);
        fragments.add(shopFragment);
        fragments.add(meFragment);
    }

    @Override
    protected void onResume() {
        int pos = getIntent().getIntExtra("poss", 0);
        vp.setCurrentItem(pos);
        super.onResume();
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
        //倒计时
        popupVpCli();
        window.showAsDropDown(this.findViewById(R.id.main_vp));
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
                                    long time = 3 - aLong;
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
        if (aBoolean) {
            initPop();
            initFragment();
            initwss();
            aBoolean = false;
        }

    }

    //取消订阅的方法
    private void cancelCallback() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}