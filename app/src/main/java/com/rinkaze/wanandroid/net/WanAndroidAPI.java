package com.rinkaze.wanandroid.net;

import com.rinkaze.wanandroid.Bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.Bean.official.WxAuthor;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroidAPI {
    public String BASE_URL = "https://www.wanandroid.com/";


    /* 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    @GET("wxarticle/chapters/json")
    Observable<WxAuthor> getWxAuthorListData();

    /* 获取当前公众号某页的数据
     * http://wanandroid.com/wxarticle/list/405/1/json
     *
     * @param id
     * @param page
     * @return 获取当前公众号某页的数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<FeedArticleListData> getWxSumData(@Path("id") int id, @Path("page") int page);

}
