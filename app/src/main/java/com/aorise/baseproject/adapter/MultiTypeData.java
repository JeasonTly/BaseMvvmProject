package com.aorise.baseproject.adapter;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public class MultiTypeData<T> {
    private T data;
    private int type;
    private String description;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
