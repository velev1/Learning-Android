package com.example.velev.fragmentsresponsiveui.views.itemDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.velev.fragmentsresponsiveui.R;
import com.example.velev.fragmentsresponsiveui.data.models.Item;

/**
 * Created by velev on 7.8.2017 г..
 */

public class ItemDetailsFragment extends Fragment {
    private static final String ITEM_KEY = "ITEM_KEY";

    private RecyclerView rvItemDetails;
    private ItemDetailsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_details_view, container, false);

        rvItemDetails = (RecyclerView) view.findViewById(R.id.rv_items_details);

        // shows blank when first time loaded.
        Item item = new Item("", "");

        Bundle args = getArguments();
        if(args != null) {
            item = (Item) getArguments().getSerializable(ITEM_KEY);
        }


        this.adapter = new ItemDetailsAdapter(item);
        rvItemDetails.setAdapter(this.adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvItemDetails.setLayoutManager(layoutManager);

        rvItemDetails.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}