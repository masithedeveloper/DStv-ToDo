package com.dstv.task.di.builder;

import com.dstv.task.view.fragment.ToDoListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ToDoListFragment contributeToDoListFragment();
}
