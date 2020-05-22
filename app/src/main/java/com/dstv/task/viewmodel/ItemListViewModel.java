package com.dstv.task.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dstv.task.data.entity.ItemEntity;
import com.dstv.task.data.repository.ItemRepository;
import com.dstv.task.utils.StatisticsUtils;

import java.util.List;

import javax.inject.Inject;

public class ItemListViewModel extends ViewModel {

    ItemRepository itemRepository;
    LiveData<List<ItemEntity>> items;
    MutableLiveData<StatisticsUtils.StatsResult> statsResultLiveData = new MutableLiveData<>();

    @Inject
    public ItemListViewModel(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        items = itemRepository.loadItems();
    }

    public LiveData<List<ItemEntity>> getItems() {
        return items;
    }

    public void saveItem(ItemEntity itemEntity) {
        itemRepository.saveItem(itemEntity);
    }

    public void updateItem(ItemEntity itemEntity) {
        itemEntity.setIsComplete(itemEntity.getIsBooleanComplete()? 0 : 1);
        itemRepository.updateItem(itemEntity);
    }

    public void deleteItem(ItemEntity itemEntity) {
        itemRepository.deleteItem(itemEntity);
    }

    public void refreshItems() {
        statsResultLiveData.setValue(StatisticsUtils.getActiveAndCompletedStats(items.getValue()));
    }

    public LiveData<StatisticsUtils.StatsResult> getStats() {
        return statsResultLiveData;
    }
}
