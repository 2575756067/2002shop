package com.example.shopkuang.app;

import android.app.Application;

import com.live.MyApplication;

import java.util.HashMap;
import java.util.Map;

public class MyApp extends Application {


    private static Map<String, Object> map;
    public static MyApp app;

    public static Map<String, Object> getMap() {
        return map;
    }
    private static String[] modules = {"com.live.MyApplication"};

    @Override
    public void onCreate() {
        super.onCreate();
        map = new HashMap<>();
        app = this;

        initMoudles();
    }
    private void initMoudles() {
        for (String moduleImpl : modules){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof MyApplication){
                    ((MyApplication) obj).initApp(app);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
