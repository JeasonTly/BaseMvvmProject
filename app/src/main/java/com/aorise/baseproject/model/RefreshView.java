package com.aorise.baseproject.model;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public interface RefreshView {
    void networkRequstSuccess(boolean loadmore);
    void networkRquestFail(String error);
}
