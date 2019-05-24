package com.rinkaze.wanandroid.net;


import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EveryWhereApi {
    String baseUrl = "http://www.wanandroid.com/";
    @GET("article/list/{num}/json")
    Observable<HomeBean> getHomeInit(@Path("num") int num);

    @GET("banner/json")
    Observable<HomeBanner> getBanninit();

    @POST("lg/collect/{id}/json")
    Observable<String> getCollect(@Header("Cookie")String name,@Header("Cookie")String psw,@Path("id") int id);

    @POST("lg/uncollect_originId/{disid}/json")
    Observable<String> getDisCollect(@Header("Cookie")String name,@Header("Cookie")String psw,@Path("disid") int disid);


}
