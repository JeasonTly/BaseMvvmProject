package com.aorise.baseproject.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public class BaseVH<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private B dataBinding;
    public BaseVH(B viewDataBinding){
        super(viewDataBinding.getRoot());
        dataBinding = viewDataBinding;
    }

    public B getDataBinding() {
        return dataBinding;
    }
}
