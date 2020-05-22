package com.dstv.task;

import android.app.Activity;
import android.app.Application;

import com.dstv.task.di.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class DStvToDoApp extends Application implements HasActivityInjector {

    private static DStvToDoApp sInstance;

    public static DStvToDoApp getAppContext() {
        return sInstance;
    }

    private static synchronized void setInstance(DStvToDoApp app) {
        sInstance = app;
    }
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
