package com.dstv.task.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.dstv.task.data.ItemDatabase;
import com.dstv.task.data.dao.ItemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Provides
    @Singleton
    ItemDatabase provideItemDatabase(Application application) {
        return Room.databaseBuilder(application, ItemDatabase.class, "items.db").build();
    }

    @Provides
    @Singleton
    ItemDao provideItemDao(ItemDatabase itemDatabase) {
        return itemDatabase.itemDao();
    }

}
