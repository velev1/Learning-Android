package com.example.velev.dexterlab.views.weaponDetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.dexterlab.R;
import com.example.velev.dexterlab.data.models.Weapon;

/**
 * Created by velev on 4.8.2017 г..
 */

public class WeaponDetailsAdapter extends RecyclerView.Adapter<WeaponDetailsAdapter.DetailsHolder> {

    private Weapon weapon;

    public WeaponDetailsAdapter(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weapon_details_row, parent, false);

        return new DetailsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailsHolder holder, int position) {

        holder.tvUsage.setText(weapon.getUsage());
        holder.tvSchema.setText(weapon.getSchema());
        holder.tvVersus.setText(weapon.getEffectiveVersus());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class DetailsHolder extends RecyclerView.ViewHolder{

        private TextView tvUsage;
        private TextView tvSchema;
        private TextView tvVersus;

        public DetailsHolder(View view) {
            super(view);

            tvUsage = (TextView) view.findViewById(R.id.tv_usage);
            tvSchema = (TextView) view.findViewById(R.id.tv_schema);
            tvVersus = (TextView) view.findViewById(R.id.tv_effective_vs);
        }
    }
}
