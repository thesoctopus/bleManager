package com.strangeoctopus.blehelper.utils;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * 日志打印工具类
 * a.修改 LEVEL 来筛选日志类型
 * b.APP上线 LEVEL 赋值为 NOTHING 屏蔽所有日志
 */
public class LogUtil {

    public static final int VERBOSE = 1;

    public static final int DEBUG = 2;

    public static final int INFO = 3;

    public static final int WARN = 4;

    public static final int ERROR = 5;

    public static final int NOTHING = 6; //关闭所有log

    public static int LEVEL = VERBOSE;//调试版

    public static void v(String tag, @NonNull String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, @NonNull String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, @NonNull String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, @NonNull String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, @NonNull String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }

    /**
     * @param tag //打印超长字符串
     * @param msg //打印超长字符串
     */
    public static void longStr(String tag, String msg) {
        if (LEVEL <= ERROR) {
            int i = 0;
            int step = 3 * 1024;
            for (; i < msg.length(); ) {
                Log.v(tag, msg.substring(i, Math.min(i + step, msg.length())));
                i = i + step;
            }
        }
    }

    public static void lineN(String tag, String msg, int n) {
        int nn = Thread.currentThread().getStackTrace().length - 3;
        for (int i = 0; i < n && i < nn; i++) {
            StackTraceElement traceElement = Thread.currentThread().getStackTrace()[3 + i];
            String fileName = traceElement.getFileName();
            String funName = traceElement.getMethodName();
            int line = traceElement.getLineNumber();
            StringBuilder taskName = new StringBuilder();
            taskName.append("第").append(i).append("级[").append(funName).append("]");
            taskName.append("(").append(fileName).append(":").append(line).append(")");
            Log.d(tag, taskName.toString());
        }
        LogUtil.w(tag, "end");
    }
}
