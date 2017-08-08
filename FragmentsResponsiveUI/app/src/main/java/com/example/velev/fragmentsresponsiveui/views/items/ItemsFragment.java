package com.example.velev.fragmentsresponsiveui.views.items;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.velev.fragmentsresponsiveui.R;

/**
 * Created by velev on 7.8.2017 г..
 */

public class ItemsFragment extends Fragment {

    private RecyclerView rvItems;
    private ItemsAdapter adapter;

    private ItemsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items_view, container, false);

        this.viewModel = new ItemsViewModel();
        this.adapter = new ItemsAdapter(this.viewModel.getAllItems());

        rvItems = (RecyclerView) view.findViewById(R.id.rv_items);
        rvItems.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvItems.setLayoutManager(layoutManager);

        rvItems.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}