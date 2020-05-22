package com.dstv.task.view.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dstv.task.R;
import com.dstv.task.data.entity.ItemEntity;
import com.dstv.task.databinding.FragmentListItemsBinding;
import com.dstv.task.view.adapter.ItemListAdapter;

import com.dstv.task.view.base.BaseFragment;
import com.dstv.task.view.callbacks.ItemListCallback;
import com.dstv.task.viewmodel.ItemListViewModel;

public class ToDoListFragment extends BaseFragment<ItemListViewModel, FragmentListItemsBinding> implements ItemListCallback {

    public static ToDoListFragment newInstance() {
        Bundle args = new Bundle();
        ToDoListFragment fragment = new ToDoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<ItemListViewModel> getViewModel() {
        return ItemListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list_items;
    }

    @Override
    public void onItemClicked(ItemEntity itemEntity) {
        viewModel.updateItem(itemEntity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new ItemListAdapter(this));

        dataBinding.progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        ItemEntity itemEntity = ((ItemListAdapter) dataBinding.recyclerView.getAdapter()).getItemAt(position);
                        Toast.makeText(getActivity(), "Deleted " + " " + itemEntity.getDescription(), Toast.LENGTH_LONG).show();
                        viewModel.deleteItem(itemEntity);
                    }
                });

        helper.attachToRecyclerView(dataBinding.recyclerView);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dataBinding.addItemEditButton.setOnClickListener(view -> {
            String description = dataBinding.addItemEditText.getText().toString();
            if(!description.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setDescription(description);
                viewModel.saveItem(itemEntity);
                dataBinding.addItemEditText.setText("");
                dataBinding.addItemEditText.clearFocus();
            }
            else{
                Toast.makeText(getContext(), "Description can't be empty", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getItems()
                .observe(this, listResource -> {
                    ((ItemListAdapter) dataBinding.recyclerView.getAdapter()).setData(listResource);
                    viewModel.refreshItems();
                    viewModel.getStats().observe(this, statsResultLiveData -> {
                        dataBinding.progressBar.setProgress((int) statsResultLiveData.getCompletedTasksPercent());
                        dataBinding.progressPercentLabel.setText(String.valueOf(statsResultLiveData.getCompletedTasksPercent()));
                    });
                });
    }
}
