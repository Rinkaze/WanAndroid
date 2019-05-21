package com.rinkaze.wanandroid.net;

import com.rinkaze.wanandroid.bean.ProjectClassBean;
import com.rinkaze.wanandroid.bean.ProjectListBean;

import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IListService {
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
    public static String DataUrl="https://www.wanandroid.com/";
    @GET("project/list/{page}/json?")
    Observable<ProjectListBean> getProjectListData(@Path("page") int page,@Query("cid") int cid);


}
