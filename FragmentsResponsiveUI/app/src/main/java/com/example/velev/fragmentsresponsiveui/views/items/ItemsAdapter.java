package com.example.velev.fragmentsresponsiveui.views.items;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.fragmentsresponsiveui.R;
import com.example.velev.fragmentsresponsiveui.data.models.Item;
import com.example.velev.fragmentsresponsiveui.startup.models.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 7.8.2017 г..
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{
    private static final String ITEM_KEY = "ITEM_KEY";

    private List<Item> items;
    private Device mDevice;

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

                ItemsFragment itemsFragment = new ItemsFragment();
                AppCompatActivity activity = (AppCompatActivity) holder.itemView.getContext();
                itemsFragment.updateUI(ITEM_KEY, item, activity);
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