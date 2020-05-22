package com.dstv.task.view.base;

import android.support.v7.widget.RecyclerView;

import com.dstv.task.data.entity.ItemEntity;

import java.util.List;

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T>{
    public abstract void setData(List<D> data);
    public abstract ItemEntity getItemAt(int position);
}