package com.dstv.task.utils;

import android.arch.lifecycle.MutableLiveData;

import com.dstv.task.data.entity.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class StatisticsUtils {
    public static StatsResult getActiveAndCompletedStats(List<ItemEntity> allItemEntities){
        List<ItemEntity> completedItemEntities = new ArrayList<>();
        for (ItemEntity itemEntity : allItemEntities) {
            if(!itemEntity.getIsBooleanComplete())
                completedItemEntities.add(itemEntity);
        }

        MutableLiveData<StatsResult> statsResult = new MutableLiveData<>();
        StatsResult statsResults = new StatsResult(0f);

        if(allItemEntities == null
                || allItemEntities.isEmpty()) {
            return statsResults;
        }
        else{
            int allItems = allItemEntities.size();

            int completedItems = completedItemEntities.size();

            statsResults.setCompletedTasksPercent(100f * (allItems - completedItems) / allItemEntities.size());
            statsResult.setValue(statsResults);
        }

        return statsResults;
    }

    public static class StatsResult {
        float completedTasksPercent = 0f;

        public StatsResult(float completedTasksPercent) {
            this.completedTasksPercent = completedTasksPercent;
        }

        public float getCompletedTasksPercent() {
            return completedTasksPercent;
        }

        public void setCompletedTasksPercent(float completedTasksPercent) {
            this.completedTasksPercent = completedTasksPercent;
        }
    }

}
