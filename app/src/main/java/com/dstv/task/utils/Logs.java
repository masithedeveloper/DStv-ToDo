package com.dstv.task.utils;

import android.util.Log;

import com.dstv.task.BuildConfig;


class Logs {
    private static final boolean ENABLE_LOGS = BuildConfig.DEBUG;

    private Logs() {}

    public static void v(String tag, String msg) {
        if (ENABLE_LOGS) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Exception e) {
        if (ENABLE_LOGS) {
            Log.v(tag, msg, e);
        }
    }

    public static void v(String tag, String msg, OutOfMemoryError e) {
        if (ENABLE_LOGS) {
            Log.v(tag, msg, e);
        }
    }

    public static boolean getIsLogsEnabled() {
        return ENABLE_LOGS;
    }

    public static void reportException(Exception e) {
        if (ENABLE_LOGS) {
            Log.e("Exception", e.toString(), e);
        }
    }
}
