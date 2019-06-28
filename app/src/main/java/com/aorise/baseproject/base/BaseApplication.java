package com.aorise.baseproject.base;

import android.app.Application;

import com.hjq.toast.ToastUtils;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
    }
}
