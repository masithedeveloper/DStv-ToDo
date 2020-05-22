package com.dstv.task.data.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.dstv.task.common.Constants;
import com.dstv.task.data.dao.ItemDao;
import com.dstv.task.data.entity.ItemEntity;

import java.util.List;

import javax.inject.Inject;

public class ItemRepository {

    private final ItemDao itemDao;

    @Inject
    ItemRepository(ItemDao dao) {
        this.itemDao = dao;
    }

    public LiveData<List<ItemEntity>> loadItems() {
        return itemDao.getItems();
    }

    public void saveItem(ItemEntity itemEntity) {
        new performActionAsyncTask().execute(itemEntity, Constants.SAVE);
    }

    public void updateItem(ItemEntity itemEntity) {
        new performActionAsyncTask().execute(itemEntity, Constants.UPDATE);
    }

    public void deleteItem(ItemEntity itemEntity) {
        new performActionAsyncTask().execute(itemEntity, Constants.DELETE);
    }

    private class performActionAsyncTask extends AsyncTask<Object, String, Void> {
        @Override
        protected Void doInBackground(final Object... params) {
            ItemEntity itemEntity = (ItemEntity) params[0];
            String action = (String) params[1];
            switch (action){
                case Constants.SAVE:
                    itemDao.saveItem(itemEntity);
                    break;
                case Constants.UPDATE:
                    itemDao.updateItem(itemEntity);
                    break;
                case Constants.DELETE:
                    itemDao.deleteItem(itemEntity);
                    break;
            }
            return null;
        }
    }
}

