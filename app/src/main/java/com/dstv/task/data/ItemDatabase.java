package com.dstv.task.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.dstv.task.data.dao.ItemDao;
import com.dstv.task.data.entity.ItemEntity;

@Database(entities = {ItemEntity.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();
}