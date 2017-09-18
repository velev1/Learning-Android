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

    private OnLongPressSelectedItem mListener;
    private List<String> mData;
    private boolean[] mIsSelected;
    private Context mContext;
    private int mCellWidth;

    public GridAdapter(List<String> data, OnLongPressSelectedItem listener, Context context, int cellWidth) {
        if (data == null) {
            mData = new ArrayList<>();
            mIsSelected = new boolean[0];
        } else {
            mData = data;
            mIsSelected = new boolean[data.size()];
        }

        mListener = listener;
        mContext = context;
        mCellWidth = cellWidth;
    }

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_grid_cell, parent, false);

        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(GridHolder holder, int position) {

        if (mIsSelected[position]) {
            holder.itemView.setBackgroundResource(R.drawable.cell_background_selected);
            holder.tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            holder.itemView.setBackgroundResource(R.drawable.cell_background);
            holder.tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
        }

        String[] dayAndHour = mData.get(position).split(" ");
        //  holder.tvDay.setText(dayAndHour[0]);
        holder.tvHour.setText(dayAndHour[1]);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    interface OnLongPressSelectedItem {
        void onLongPressed();
    }

    public boolean[] getIsSelected() {
        return mIsSelected;
    }

    public void select(int position, View view) {
        if (position > -1 && position < mIsSelected.length) {
            mIsSelected[position] = true;

            TextView tvHour = (TextView) view.findViewById(R.id.tv_hour);
            tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
    }

    public void unSelect(int position) {
        if (position > -1 && position < mIsSelected.length) {
            mIsSelected[position] = false;
            notifyItemChanged(position);
        }
    }

    public void unSelect(int position, View view) {
        if (position > -1 && position < mIsSelected.length) {
            mIsSelected[position] = false;

            TextView tvHour = (TextView) view.findViewById(R.id.tv_hour);
            tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
        }
    }

    public boolean checkIfSelected(int position) {
        if (position > -1 && position < mIsSelected.length) {
            if (mIsSelected[position]) {
                return true;
            }
        }

        return false;
    }

    public class GridHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private TextView tvHour;

        public GridHolder(View view) {
            super(view);

            this.tvHour = (TextView) view.findViewById(R.id.tv_hour);
            view.getLayoutParams().width = mCellWidth;

            // attach the long click
            view.setOnLongClickListener(this);

            // attach the click listener
            view.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            mListener.onLongPressed();
            return true;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (!mIsSelected[position]) {
                mIsSelected[position] = true;
                v.setBackgroundResource(R.drawable.cell_background_selected);
                tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.white));

            } else {
                mIsSelected[position] = false;
                v.setBackgroundResource(R.drawable.cell_background);
                tvHour.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            }
        }
    }
}
