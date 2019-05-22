package com.rinkaze.wanandroid.net;

import com.rinkaze.wanandroid.Bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EveryWhereApi {
        String baseUrl="http://www.wanandroid.com/";
        @GET("article/list/{num}/json")
    Observable<HomeBean>getHomeInit(@Path("num") int num);
        @GET("banner/json")
    Observable<HomeBanner>getBanninit();

}
