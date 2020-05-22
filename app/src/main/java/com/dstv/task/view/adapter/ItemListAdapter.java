package com.dstv.task.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dstv.task.data.entity.ItemEntity;
import com.dstv.task.databinding.ItemListBinding;
import com.dstv.task.view.base.BaseAdapter;
import com.dstv.task.view.callbacks.ItemListCallback;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends  BaseAdapter<ItemListAdapter.ItemViewHolder, ItemEntity> {

    private List<ItemEntity> itemEntities;

    private final ItemListCallback itemListCallback;

    public ItemListAdapter(@NonNull ItemListCallback itemListCallback) {
        itemEntities = new ArrayList<>();
        this.itemListCallback = itemListCallback;
    }

    @Override
    public void setData(List<ItemEntity> entities) {
        this.itemEntities = entities;
        notifyDataSetChanged();
    }

    @Override
    public ItemEntity getItemAt(int position) {
        return itemEntities.get(position);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return ItemViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, itemListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int i) {
        viewHolder.onBind(itemEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return itemEntities.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private static ItemViewHolder create(LayoutInflater inflater, ViewGroup parent, ItemListCallback callback) {
            ItemListBinding itemListBinding = ItemListBinding.inflate(inflater, parent, false);
            return new ItemViewHolder(itemListBinding, callback);
        }

        final ItemListBinding binding;

        private ItemViewHolder(ItemListBinding binding, ItemListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.completedCheckbox.setOnClickListener(v ->
                    callback.onItemClicked(binding.getItem()));
        }

        private void onBind(ItemEntity itemEntity) {
            binding.setItem(itemEntity);
            binding.executePendingBindings();
        }
    }
}
