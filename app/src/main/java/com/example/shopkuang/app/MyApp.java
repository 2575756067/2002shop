package com.example.shopkuang.app;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

public class MyApp extends Application {


    private static Map<String, Object> map;
    public static MyApp app;

    public static Map<String, Object> getMap() {
        return map;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        map = new HashMap<>();
        app = this;
    }
}
