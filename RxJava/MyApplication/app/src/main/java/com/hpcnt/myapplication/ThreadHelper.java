package com.hpcnt.myapplication;

import android.os.Handler;
import android.os.Looper;

import rx.Scheduler;

public final class ThreadHelper {
    private static Handler sMainThreadHandler;
    private static HandlerScheduler sMainThreadScheduler;

    private ThreadHelper() {
    }

    public static Handler mainHandler() {
        if (sMainThreadHandler == null) {
            synchronized (ThreadHelper.class) {
                if (sMainThreadHandler == null) {
                    sMainThreadHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return sMainThreadHandler;
    }

    public static Scheduler mainScheduler() {
        if (sMainThreadScheduler == null) {
            synchronized (ThreadHelper.class) {
                if (sMainThreadScheduler == null) {
                    sMainThreadScheduler = new HandlerScheduler(mainHandler());
                }
            }
        }
        return sMainThreadScheduler;
    }
}
