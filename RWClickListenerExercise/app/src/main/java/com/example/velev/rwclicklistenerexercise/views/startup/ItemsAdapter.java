package com.example.velev.rwclicklistenerexercise.views.startup;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.rwclicklistenerexercise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 16.8.2017 г..
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder> {

    private List<String> items;
    private ItemClickListener listener;

    // pass the listener in the constructor of the adapter
    public ItemsAdapter(List<String> items, ItemClickListener listener) {
        if(items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }

        this.listener = listener;
    }

    // define interface listener
    interface ItemClickListener {
        void onItemClicked(int positionClicked);
    }

    @Override
    public ItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_row, parent, false);

        return new ItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsHolder holder, int position) {
        holder.tvItemNumber.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // implement OnclickListener
    public class ItemsHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        TextView tvItemNumber;

        public ItemsHolder(View view) {
            super(view);

            this.tvItemNumber = (TextView) view.findViewById(R.id.tv_item_number);
            // attach the listener
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listener.onItemClicked(clickedPosition);
        }
    }
}
