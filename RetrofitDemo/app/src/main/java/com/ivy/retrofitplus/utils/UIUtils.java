package com.ivy.retrofitplus.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.ivy.retrofitplus.base.MyApplication;

/**
 * Created by Ivy on 2016/10/9.
 *
 * @description:
 */

public class UIUtils {

    public static Toast mToast;

    public static void showToast(final String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
        }

        if (android.os.Process.myTid() == MyApplication.getMainTid()) {
            mToast.setText(msg);
            mToast.show();
        } else {
            MyApplication.getHandler().post(new Runnable() {

                @Override
                public void run() {
                    mToast.setText(msg);
                    mToast.show();
                }
            });
        }
    }

    public static Context getContext() {
        return MyApplication.getContext();
    }

    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0);
    }

    public static void runOnUiThread(Runnable runnable, long delayMillis) {
        MyApplication.getHandler().postDelayed(runnable, delayMillis);
    }


    public static Resources getResource() {
        return getContext().getResources();
    }

    public static int dip2px(float dpValue) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static View inflate(int viewId) {
        return View.inflate(UIUtils.getContext(), viewId, null);
    }

    public static Drawable getDrawable(int id) {
        return getResource().getDrawable(id);
    }


    public static boolean isNetworkConnected() {
        Context context = MyApplication.getContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}
