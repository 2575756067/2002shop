package com.example.shopkuang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.shopkuang.adapter.MainVpAdapter;
import com.example.shopkuang.ui.home.HomeFragment;
import com.example.shopkuang.ui.me.MeFragment;
import com.example.shopkuang.ui.shop.ShopFragment;
import com.example.shopkuang.ui.sort.SortFragment;
import com.example.shopkuang.ui.topic.TopicFragment;
import com.example.shopkuang.utils.CustomViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity {

    private CustomViewPager vp;
    private BottomNavigationView navView;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        vp = findViewById(R.id.main_vp);
        initFragment();
        vp.setScanScroll(false);


        MainVpAdapter adapter = new MainVpAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //底部
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_topic, R.id.navigation_sort, R.id.navigation_shop, R.id.navigation_me)
//                .build();
        //控制器
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        //控制器和底部   捆绑操作
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //绑定
//        NavigationUI.setupWithNavController(navView, navController);


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

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

//        Intent intent = getIntent();
//        if (intent.hasExtra("pos")){
//            int pos = intent.getIntExtra("pos", 0);
//            Log.e("TAG", "onCreate: "+pos);
//            vp.setCurrentItem(pos);
//        }

        int pos = getIntent().getIntExtra("pos", 0);
        vp.setCurrentItem(pos);
    }

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

}