package com.aorise.baseproject.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import java.util.Iterator;
import java.util.Stack;

public class ActivityManager {
    private static Stack<Activity> sActivityStack;
    private static ActivityManager sInstance;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if(sInstance == null) {
            Class var0 = ActivityManager.class;
            synchronized(ActivityManager.class) {
                if(sInstance == null) {
                    sInstance = new ActivityManager();
                }
            }
        }

        return sInstance;
    }

    public void addActivity(Activity activity) {
        if(sActivityStack == null) {
            sActivityStack = new Stack();
        }

        sActivityStack.add(activity);
    }

    public Activity currentActivity() {
        return sActivityStack != null && !sActivityStack.isEmpty()?(Activity)sActivityStack.lastElement():null;
    }

    public Activity findActivity(Class<?> cls) {
        Activity activity = null;
        Iterator var3 = sActivityStack.iterator();

        while(var3.hasNext()) {
            Activity aty = (Activity)var3.next();
            if(aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }

        return activity;
    }

    public void finishActivity() {
        Activity activity = (Activity)sActivityStack.lastElement();
        this.finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if(activity != null) {
            sActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }

    }

    public void finishActivity(Class<?> cls) {
        Iterator var2 = sActivityStack.iterator();

        while(var2.hasNext()) {
            Activity activity = (Activity)var2.next();
            if(activity.getClass().equals(cls)) {
                this.finishActivity(activity);
            }
        }

    }

    public void finishAllActivity() {
        if(sActivityStack != null) {
            int i = 0;

            for(int size = sActivityStack.size(); i < size; ++i) {
                if(null != sActivityStack.get(i)) {
                    ((Activity)sActivityStack.get(i)).finish();
                }
            }

            sActivityStack.clear();
        }
    }

    public void finishOthersActivity(Class<?> cls) {
        Iterator var2 = sActivityStack.iterator();

        while(var2.hasNext()) {
            Activity activity = (Activity)var2.next();
            if(!activity.getClass().equals(cls)) {
                this.finishActivity(activity);
            }
        }

    }

    public void appExit(Context context) {
        try {
            this.finishAllActivity();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Exception var3) {
            var3.printStackTrace();
            System.exit(0);
        }

    }

    public boolean resumeApp(Context context) {
        boolean isAppLive = false;
        Activity activity = this.currentActivity();
        if(activity != null) {
            isAppLive = true;
            Intent intent = new Intent(context, activity.getClass());
            intent.addFlags(603979776);
            context.startActivity(intent);
        }

        return isAppLive;
    }
}