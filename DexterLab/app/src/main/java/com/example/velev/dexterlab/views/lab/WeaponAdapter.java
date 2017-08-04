package com.example.velev.dexterlab.views.lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
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

    private static final String WEAPON_OBJECT = "WEAPON_OBJECT";

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
        final Weapon weapon = weapons.get(position);
        holder.imgWeapon.setImageResource(weapon.getImg());
        holder.tvName.setText(weapon.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), WeaponDetailsActivity.class);
                intent.putExtra(WEAPON_OBJECT, weapon);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    ImageView img = (ImageView) holder.itemView.findViewById(R.id.img_weapon);
                    TextView tvName = (TextView) holder.itemView.findViewById(R.id.tv_weapon_name);

                    Activity activity = (Activity) holder.itemView.getContext();

                    Pair<View, String> pairFirst = Pair.create((View)img, "weaponImg");
                    Pair<View, String> pairSecond = Pair.create((View)tvName, "weaponName");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(activity, pairFirst, pairSecond);

                    activity.startActivity(intent, options.toBundle());

                } else {
                    holder.itemView.getContext().startActivity(intent);
                }
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
