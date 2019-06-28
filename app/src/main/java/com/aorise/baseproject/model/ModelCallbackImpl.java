package com.aorise.baseproject.model;

import android.content.Context;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public class ModelCallbackImpl implements ModelCallBack {
    private BaseLoaderListener mBaseLoaderListener;
    private Context mContext;
    private int comefrom;
    public ModelCallbackImpl(Context context,BaseLoaderListener mBaseLoaderListener,int sourceSurface) {
        this.mBaseLoaderListener = mBaseLoaderListener;
        mContext = context;
        comefrom = sourceSurface;
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void loadMoreData() {

    }
}
