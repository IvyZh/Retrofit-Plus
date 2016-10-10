package com.ivy.retrofitplus.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by Ivy on 2016/10/7.
 * 日志工具类
 */

public class L {
    private static boolean isOpen = true;//日志开关

    public static void d(String msg) {
        if (isOpen)
            Logger.d(msg);
    }

    public static void e(String msg) {
        if (isOpen)
            Logger.e(msg);
    }

    public static void w(String msg) {
        if (isOpen)
            Logger.w(msg);
    }

    public static void v(String msg) {
        if (isOpen)
            Logger.v(msg);
    }

    public static void j(String msg) {
        if (isOpen)
            Logger.json(msg);
    }
}
