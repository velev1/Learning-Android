package com.example.velev.archcomponents.viewmodelsample.views.herolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.archcomponents.R;
import com.example.velev.archcomponents.viewmodelsample.data.models.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 15.1.2018 г..
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemHolder> {

    private List<Hero> mHeroes;

    public ItemsAdapter(List<Hero> heroes) {
        if (heroes != null) {
            mHeroes = heroes;
        } else {
            mHeroes = new ArrayList<>();
        }
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_heroes_row, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.tvName.setText(mHeroes.get(position).getName());
        holder.tvURL.setText(mHeroes.get(position).getImgURL());
    }

    @Override
    public int getItemCount() {
        return mHeroes.size();
    }

    // TODO fix this
    public void updateData(List<Hero> data) {
        mHeroes = data;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvURL;

        public ItemHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tv_item);
            tvURL = view.findViewById(R.id.tv_url);
        }
    }
}