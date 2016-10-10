package com.ivy.retrofitplus.base;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import com.ivy.retrofitplus.net.OkHttp3Utils;
import com.orhanobut.logger.Logger;

import okhttp3.OkHttpClient;

/**
 * Created by Ivy on 2016/10/9.
 *
 * @description:
 */

public class MyApplication extends Application {
    private static MyApplication mContext;
    private static Handler mHandler;//全局Handler
    private static int mMainTid;//主线程的线程Id

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("way", "MyApplication onCreate");
        mContext = this;
        mHandler = new Handler();
        mMainTid = android.os.Process.myTid();

        Logger.init("way");//初始化Logger
        initOkHttp();//初始化OkHttp
        initGlide();//初始化Glide

    }

    /**
     * 初始化单例Glide对象
     */
    private void initGlide() {

//        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientUtils.getOkHttpSingletonInstance(mContext)));
    }

    /**
     * 初始化单例OkHttpClient对象
     */
    private void initOkHttp() {
        OkHttpClient client = OkHttp3Utils.getOkHttpClient();
        Log.v("way", client.toString());
    }

    public static MyApplication getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static int getMainTid() {
        return mMainTid;
    }
}
