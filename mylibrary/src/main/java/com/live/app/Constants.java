package com.live.app;

import com.live.MyApplication;

import java.io.File;

public class Constants {
    //网络缓存的地址
    public static final String PATH_DATA = MyApplication.application.getCacheDir().getAbsolutePath() + File.separator + "data";
    //拼接一个地址
    public static final String PATH_IMGS = PATH_DATA + "/tp/imgs";
}
