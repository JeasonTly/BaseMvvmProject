package com.aorise.baseproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aorise.baseproject.common.LogT;

import java.util.List;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public abstract class BaseListAdapter<T extends MultiTypeData,VH extends BaseVH> extends RecyclerView.Adapter<VH> {
    private List<T> mDataList;
    private Context mContext;
    protected LayoutInflater inflater;
    protected BaseRecyclerViewItemClick viewItemClick;

    public BaseListAdapter(Context context ,BaseRecyclerViewItemClick baseRecyclerViewItemClick) {
        this.mContext = context;
        this.viewItemClick = baseRecyclerViewItemClick;
        inflater = LayoutInflater.from(context);
    }
    public enum MULITYTYPE{
        TYPE_TITLE,
        TYPE_CONTENT,
    }
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if(mDataList.get(position).getType() == MULITYTYPE.TYPE_TITLE.ordinal()){
            return onCreateTitleVH(viewGroup,position);
        }else {
            return onCreateContentVH(viewGroup,position);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull VH viewHolder, final int position) {
        if(mDataList.get(position).getType() == MULITYTYPE.TYPE_TITLE.ordinal()){
           onBindTitleVH(viewHolder,position);
        }else{
            onBindContentVH(viewHolder,position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogT.d("item click " + position);
                    viewItemClick.onClick(position,false);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    viewItemClick.onClick(position,true);
                    LogT.d("item long click "+ position);
                    return true;
                }
            });
        }
    }

    public abstract VH onCreateTitleVH(ViewGroup viewGroup, int i);
    public abstract VH onCreateContentVH(ViewGroup viewGroup, int i);
    public abstract void onBindTitleVH(VH viewHolder, int i);
    public abstract void onBindContentVH(VH viewHolder, int i);

    /**
     * 插入数据
     * @param newList
     */
    public void refreshData(List<T> newList){
        this.mDataList.clear();
        this.mDataList.addAll(newList);
        notifyDataSetChanged();
    }

    /**
     * 插入单条数据
     * @param data
     */
    public void insertNewData(T data){
        mDataList.add(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     * @param morelist
     */
    public void loadMoreData(List<T> morelist){
        this.mDataList.addAll(morelist);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getType();
    }
}
