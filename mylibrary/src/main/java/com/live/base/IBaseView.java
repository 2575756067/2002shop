package com.live.base;

public interface IBaseView {
    void tips(String tip);//失败的方法
    void loading(int visible);//加载布局

    void showToast(String msg, int time);//吐丝
}
