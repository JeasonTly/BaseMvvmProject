package com.aorise.baseproject.common;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */

public class HandlerUtil {
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public HandlerUtil() {
    }

    public static void runOnUiThread(Runnable runnable) {
        HANDLER.post(runnable);
    }

    public static void runOnUiThreadDelay(Runnable runnable, long delayMillis) {
        HANDLER.postDelayed(runnable, delayMillis);
    }

    public static void removeRunnable(Runnable runnable) {
        HANDLER.removeCallbacks(runnable);
    }
}