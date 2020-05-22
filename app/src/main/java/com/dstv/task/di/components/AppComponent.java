package com.dstv.task.di.components;

import android.app.Application;

import com.dstv.task.DStvToDoApp;
import com.dstv.task.di.builder.ActivityBuilderModule;
import com.dstv.task.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(DStvToDoApp DStvToDoApp);
}