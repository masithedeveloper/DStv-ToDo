package com.dstv.task.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dstv.task.data.entity.ItemEntity;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM item")
    LiveData<List<ItemEntity>> getItems();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveItem(ItemEntity itemEntity);

    @Update
    void updateItem(ItemEntity itemEntity);

    @Delete
    void deleteItem(ItemEntity itemEntity);
}
