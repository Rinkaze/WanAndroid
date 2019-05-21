package com.rinkaze.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;


/**
 * Created by 灵风 on 2019/5/6.
 */

public class GlideUtil {
    /**
     * 加载圆形资源图片
     * @param placeholderImg    图片加载时的占位图
     * @param image         要加载的图片
     * @param iv
     * @param context
     */
    public static void loadCircleImage(int placeholderImg, Object image, ImageView iv, Context context){
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderImg)
                .circleCrop();
        Glide.with(context)
                .load(image)
                .apply(options)
                .into(iv);
    }

    //加载资源图片
    public static void loadImage(int placeholderImg, Object image, ImageView iv, Context context){
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderImg);
        Glide.with(context)
                .load(image)
                .apply(options)
                .into(iv);
    }

    /**
     * 加载圆角图片
     * @param roundRadius   圆角大小
     * @param placeholderImg 占位图
     * @param image
     * @param iv
     * @param context
     */
    public static void loadUrlRoundImg(int roundRadius,int placeholderImg, Object image, ImageView iv, Context context) {
        RoundedCorners corners = new RoundedCorners(SystemUtil.dp2px(roundRadius));
        RequestOptions options = RequestOptions.bitmapTransform(corners)
                .placeholder(placeholderImg);
        Glide.with(context)
                .load(image)
                .apply(options)
                .into(iv);
    }

    /**
     * 加载图片但不缓存
     * @param context
     * @param placeholder
     * @param object
     * @param imageView
     */
    public static void loadNoCatchImg(Context context, int placeholder, Object object, ImageView imageView){
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(placeholder)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);//不做缓存
        Glide.with(context)
                .load(object)
                .apply(requestOptions)
                .into(imageView);
    }
}
