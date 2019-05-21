package com.rinkaze.wanandroid.servicelist;

import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceList {
    public static String url="https://www.wanandroid.com/";
    @GET("navi/json")
    Observable<Navi_Tab_Bean> getNaviTab();
}
