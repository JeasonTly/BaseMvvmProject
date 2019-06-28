package com.aorise.baseproject.model;

import android.content.Context;
import android.databinding.ViewDataBinding;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public class BaseViewModel implements BaseLoaderListener {
    private ViewDataBinding viewDataBinding;
    protected Context mContext;
    private ModelCallBack modelCallBack;

    public BaseViewModel(Context mContext, ViewDataBinding viewDataBinding, int from) {
        this.mContext = mContext;
        modelCallBack = new ModelCallbackImpl(mContext, this, from);

    }
    public void refreshData(){
        modelCallBack.refreshData();
    }

    public void loadMoreData(){
        modelCallBack.loadMoreData();
    }

    /**
     * 网络请求回执成功
     */
    @Override
    public void succuess() {

    }
    /**
     * 网络请求回执成功 失败
     * @param e
     */
    @Override
    public void fail(String e) {

    }
}
