package com.rinkaze.wanandroid.net;

import com.rinkaze.wanandroid.bean.KnowledgeHierarchyData;
import com.rinkaze.wanandroid.bean.LoginInfo;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.bean.official.WxAuthor;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 灵风 on 2019/5/21.
 */

public interface WanAndroidApi {
    int SUCCESS_CODE = 0;   //请求成功的状态码

    String baseUrl = "https://www.wanandroid.com/";

    /**
     * 登录接口
     * @param username  用户名
     * @param psw       密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginInfo> login(@Field("username")String username, @Field("password")String psw);

    /**
     * 注册接口
     * @param username  用户名
     * @param psw       密码
     * @param rePsw     重复密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<LoginInfo> register(@Field("username")String username, @Field("password")String psw,@Field("repassword")String rePsw);

    /**
     * 退出登录接口
     * @return
     */
    @GET("user/logout/json")
    Observable<LoginInfo> logout();

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

    @GET("article/list/{page}/json")
    Observable<FeedArticleListData> getKnowledgeHierarchyDetailData(@Path("page") int page,@Query("cid") int cid);

    @GET("tree/json")
    Observable<KnowledgeHierarchyData> getKnowledgeHierarchyData();


    //Navigation_收藏
    @FormUrlEncoded
    @POST("lg/collect/addtool/json")
    Observable<String> getNaviLike(@Field("name")String name,@Field("link") String link);


}
