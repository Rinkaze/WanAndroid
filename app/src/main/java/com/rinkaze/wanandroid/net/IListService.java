package com.rinkaze.wanandroid.net;

import com.rinkaze.wanandroid.bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.bean.ProjectClassBean;
import com.rinkaze.wanandroid.bean.ProjectListBean;

import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IListService {
    //项目   project
    //tablayout栏
    public static final int SUCCESS_CODE = 0;
    //    https://www.wanandroid.com/project/tree/json
    String BASE_URL = "https://www.wanandroid.com/";
    @GET("project/tree/json")
    Observable<ProjectClassBean> getProjectClassifyData();


    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid second page id
     * @return 项目类别数据
     */
    //列表数据
    public static String DataUrl="https://www.wanandroid.com/";
    @GET("project/list/{page}/json?")
    Observable<ProjectListBean> getProjectListData(@Path("page") int page,@Query("cid") int cid);
    //收藏

//    https://www.wanandroid.com/lg/collect/1165/json
    @POST("lg/collect/{id}/json")
    Observable<String> getCollect(@Header("Cookie")String name, @Header("Cookie")String psw, @Path("id") int id);

    @POST("lg/uncollect_originId/{disid}/json")
    Observable<String> getDisCollect(@Header("Cookie")String name,@Header("Cookie")String psw,@Path("disid") int disid);
}
