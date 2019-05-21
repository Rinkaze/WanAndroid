package com.rinkaze.wanandroid.net;

import com.rinkaze.wanandroid.bean.FeedArticleListData;
import com.rinkaze.wanandroid.bean.KnowledgeHierarchyData;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WanAndroidAPi {
    String url="http://www.wanandroid.com/";
    @GET("tree/json")
    Observable<KnowledgeHierarchyData> getKnowledgeHierarchyData();

    @GET("article/list/{page}/json")
    Observable<FeedArticleListData> getKnowledgeHierarchyDetailData(@Path("page") int page, @Query("cid") int cid);
}
