package com.example.velev.dexterlab.views.lab;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.velev.dexterlab.R;
import com.example.velev.dexterlab.data.models.Weapon;
import com.example.velev.dexterlab.views.weaponDetails.WeaponDetailsActivity;

import java.util.List;

/**
 * Created by velev on 3.8.2017 г..
 */

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.WeaponHolder> {

    private List<Weapon> weapons;

    public WeaponAdapter(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public WeaponAdapter.WeaponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_weapons_row, parent, false);

        return new WeaponHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WeaponAdapter.WeaponHolder holder, int position) {
        Weapon weapon = weapons.get(position);
        holder.imgWeapon.setImageResource(weapon.getImg());
        holder.tvName.setText(weapon.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), WeaponDetailsActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weapons.size();
    }

    public class WeaponHolder extends RecyclerView.ViewHolder{
        private ImageView imgWeapon;
        private TextView tvName;

        public WeaponHolder(View view) {
            super(view);
            imgWeapon = (ImageView) view.findViewById(R.id.img_weapon);
            tvName = (TextView) view.findViewById(R.id.tv_weapon_name);
        }
    }
}
