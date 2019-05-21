package com.rinkaze.wanandroid.icallback;

public interface ICallBack<T> {
    void Success(T bean);
    void Fain(String msg);
}
