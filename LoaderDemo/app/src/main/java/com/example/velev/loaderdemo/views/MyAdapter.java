package com.example.velev.loaderdemo.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.loaderdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 17.8.2017 г..
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    private List<String> data;

    public MyAdapter(List<String> data) {
        if(data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    public void swapData(List<String> data) {
        if(data == null) {
            data = new ArrayList<>();
        }

        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvItem.setText(this.data.get(position));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public MyHolder(View view) {
            super(view);
            tvItem = (TextView) view.findViewById(R.id.tv_item);
        }
    }
}
