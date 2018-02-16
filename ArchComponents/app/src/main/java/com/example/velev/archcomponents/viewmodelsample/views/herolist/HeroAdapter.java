package com.example.velev.archcomponents.viewmodelsample.views.herolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.archcomponents.R;
import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 15.1.2018 г..
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ItemHolder> {

    private List<HeroEntity> mHeroes;
    private ItemClickListener mListener;

    public HeroAdapter(List<HeroEntity> heroes, ItemClickListener listener) {
        if (heroes != null) {
            mHeroes = heroes;
        } else {
            mHeroes = new ArrayList<>();
        }

        mListener = listener;
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

    public void updateData(List<HeroEntity> data) {
        mHeroes.clear();
        mHeroes.addAll(data);
        notifyDataSetChanged();
    }

    interface ItemClickListener {
        void onItemClick(HeroEntity heroEntity);
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView tvURL;

        public ItemHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tv_item);
            tvURL = view.findViewById(R.id.tv_url);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int positionClicked = getAdapterPosition();
            mListener.onItemClick(mHeroes.get(positionClicked));
        }
    }
}