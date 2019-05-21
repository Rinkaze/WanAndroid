package com.rinkaze.wanandroid.net;

import com.rinkaze.wanandroid.bean.LoginInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
