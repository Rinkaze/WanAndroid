package com.rinkaze.wanandroid.utils;
import android.util.Log;

import com.rinkaze.wanandroid.base.Constants;

public class Logger {
    public static void logD(String tag, String msg){
        if (Constants.isDebug){
            Log.d(tag, "logD: "+msg);
        }
    }
}
