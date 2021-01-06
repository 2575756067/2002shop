package com.live.base;

public interface Callback<T> {

    void success(T data);

    void fail(String err);

}
