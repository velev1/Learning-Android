package com.example.velev.fragmentsresponsiveui.views.items;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.fragmentsresponsiveui.R;
import com.example.velev.fragmentsresponsiveui.data.models.Item;
import com.example.velev.fragmentsresponsiveui.views.itemDetails.ItemDetailsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 7.8.2017 г..
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{
    private static final String ITEM_KEY = "ITEM_KEY";
    private static final int SCREEN_WIDTH = 600;

    private List<Item> items;

    public ItemsAdapter(List<Item> items) {

        if(items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
    }

    @Override
    public ItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_row, parent, false);

        return new ItemsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemsHolder holder, int position) {
        final Item item = items.get(position);

        holder.tvItemName.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) holder.itemView.getContext();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

                Bundle args = new Bundle();
                args.putSerializable(ITEM_KEY, item);

                ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
                itemDetailsFragment.setArguments(args);

                Configuration config = activity.getResources().getConfiguration();

                if(config.smallestScreenWidthDp >= SCREEN_WIDTH) {
                    transaction.replace(R.id.details_container, itemDetailsFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                } else {
                    transaction.replace(R.id.container, itemDetailsFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemsHolder extends RecyclerView.ViewHolder{

        private TextView tvItemName;

        public ItemsHolder(View itemView) {
            super(itemView);

            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
        }
    }
}