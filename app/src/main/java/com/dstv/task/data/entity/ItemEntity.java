package com.dstv.task.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "item")
public class ItemEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "isComplete")
    private int isComplete;

    @Ignore
    private boolean isBooleanComplete;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public boolean getIsBooleanComplete() {
        if(isComplete == 0)
            isBooleanComplete = false;
        else{
            isBooleanComplete = true;
        }

        return isBooleanComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
