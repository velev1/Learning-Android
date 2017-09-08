package com.example.velev.dragselection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 5.9.2017 г..
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder> {

    private OnLongPressSelectedItem listener;
    private List<String> data;
    private boolean[] isSelected;

    public GridAdapter(List<String> data, OnLongPressSelectedItem listener) {
        if (data == null) {
            this.data = new ArrayList<>();
            isSelected = new boolean[0];
        } else {
            this.data = data;
            isSelected = new boolean[data.size()];
        }

        this.listener = listener;
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
        } else {
            holder.itemView.setBackgroundResource(R.drawable.cell_background);
        }

        String hour = data.get(position);
        holder.tvHour.setText(hour);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    interface OnLongPressSelectedItem {
        void onLongPressed();
    }

    public void select(int position) {
        if(position > -1 && position < isSelected.length) {
            isSelected[position] = true;
        }
    }

    public void unSelect(int position) {
        if (position > -1 && position < isSelected.length){
            isSelected[position] = false;
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

        private TextView tvHour;

        public GridHolder(View view) {
            super(view);

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
            } else {
                isSelected[position] = false;
                v.setBackgroundResource(R.drawable.cell_background);
            }
        }
    }
}
