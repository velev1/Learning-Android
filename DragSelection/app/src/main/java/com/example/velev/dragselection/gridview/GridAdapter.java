package com.example.velev.dragselection.gridview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.dragselection.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 5.9.2017 г..
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder> {

    private OnLongPressSelectedItem listener;
    private List<String> data;
    private boolean[] isSelected;
    private Context mContext;

    public GridAdapter(List<String> data, OnLongPressSelectedItem listener, Context context) {
        if (data == null) {
            this.data = new ArrayList<>();
            isSelected = new boolean[0];
        } else {
            this.data = data;
            isSelected = new boolean[data.size()];
        }

        this.listener = listener;
        this.mContext = context;
    }

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_grid_cell, parent, false);

        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(GridHolder holder, int position) {

        if (isSelected[position]) {
            holder.itemView.setBackgroundResource(R.drawable.cell_background_selected);
            holder.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            holder.itemView.setBackgroundResource(R.drawable.cell_background);
            holder.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            holder.tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }

        String[] dayAndHour = data.get(position).split(" ");
        holder.tvDay.setText(dayAndHour[0]);
        holder.tvHour.setText(dayAndHour[1]);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    interface OnLongPressSelectedItem {
        void onLongPressed();
    }

    public void select(int position, View view) {
        if(position > -1 && position < isSelected.length) {
            isSelected[position] = true;
            TextView tvDay = (TextView) view.findViewById(R.id.tv_day);
            tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            TextView tvHour = (TextView) view.findViewById(R.id.tv_hour);
            tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
    }

    public void unSelect(int position, View view) {
        if (position > -1 && position < isSelected.length){
            isSelected[position] = false;
            TextView tvDay = (TextView) view.findViewById(R.id.tv_day);
            tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            TextView tvHour = (TextView) view.findViewById(R.id.tv_hour);
            tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }
    }

    public boolean checkIfSelected(int position) {
        if (position > -1 && position < isSelected.length){
            if (isSelected[position]) {
                return true;
            }
        }

        return false;
    }

    public class GridHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener{

        private TextView tvDay;
        private TextView tvHour;

        public GridHolder(View view) {
            super(view);

            this.tvDay = (TextView) view.findViewById(R.id.tv_day);
            this.tvHour = (TextView) view.findViewById(R.id.tv_hour);

            // attach the long click
            view.setOnLongClickListener(this);

            // attach the click listener
            view.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            listener.onLongPressed();
            return true;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(!isSelected[position]) {
                isSelected[position] = true;

                v.setBackgroundResource(R.drawable.cell_background_selected);
                tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.white));

            } else {
                isSelected[position] = false;
                v.setBackgroundResource(R.drawable.cell_background);
                tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            }
        }
    }
}
