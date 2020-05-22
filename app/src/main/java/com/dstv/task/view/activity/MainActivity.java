package com.dstv.task.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.dstv.task.R;
import com.dstv.task.databinding.ActivityMainBinding;
import com.dstv.task.utils.FragmentUtils;
import com.dstv.task.view.base.BaseActivity;
import com.dstv.task.view.fragment.ToDoListFragment;


import static com.dstv.task.utils.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, ToDoListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
