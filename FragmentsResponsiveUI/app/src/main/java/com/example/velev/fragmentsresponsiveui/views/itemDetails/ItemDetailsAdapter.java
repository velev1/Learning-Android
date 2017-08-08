package com.example.velev.fragmentsresponsiveui.views.itemDetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.fragmentsresponsiveui.R;
import com.example.velev.fragmentsresponsiveui.data.models.Item;

/**
 * Created by velev on 7.8.2017 г..
 */

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.ItemDetailsHolder> {

    private Item item;

    public ItemDetailsAdapter(Item item) {
        this.item = item;
    }

    @Override
    public ItemDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_details_row, parent, false);

        return new ItemDetailsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemDetailsHolder holder, int position) {

        holder.tvName.setText(this.item.getName());
        holder.tvDetails.setText(this.item.getDetails());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ItemDetailsHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDetails;

        public ItemDetailsHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.tv_item_name);
            tvDetails = (TextView) view.findViewById(R.id.tv_details);
        }
    }
}