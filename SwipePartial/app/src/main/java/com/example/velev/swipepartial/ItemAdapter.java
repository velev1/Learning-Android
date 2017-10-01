package com.example.velev.swipepartial;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.velev.swipepartial.data.ItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 30.9.2017 г..
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private Context mContext;
    private List<ItemModel> items;

    public ItemAdapter(Context context, List<ItemModel> items) {
        if (items != null) {
            this.items = items;
        } else {
            this.items = new ArrayList<>();
        }

        mContext = context;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_row, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if (position % 2 == 0) {
            holder.container.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.blue));
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        } else {
            holder.container.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }

        holder.tvName.setText(this.items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public ItemModel getItem(int position) {
        return this.items.get(position);
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private View container;
        private TextView tvName;

        public ItemHolder(View view) {
            super(view);

            container = view.findViewById(R.id.container);
            tvName = (TextView) view.findViewById(R.id.tv_name);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
