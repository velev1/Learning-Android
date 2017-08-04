package com.example.velev.dexterlab.views.weaponDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.velev.dexterlab.R;
import com.example.velev.dexterlab.data.models.Weapon;

public class WeaponDetailsActivity extends AppCompatActivity {
    private static final String WEAPON_OBJECT = "WEAPON_OBJECT";

    private RecyclerView recyclerView;
    private WeaponDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_details);

        Intent intent = getIntent();

        Weapon weapon = (Weapon) intent.getSerializableExtra(WEAPON_OBJECT);

        ImageView imgWeapon = (ImageView) findViewById(R.id.img_weapon);
        imgWeapon.setImageResource(weapon.getImg());

        TextView tvWeaponName = (TextView) findViewById(R.id.tv_weapon_name);
        tvWeaponName.setText(weapon.getName());

        recyclerView = (RecyclerView) findViewById(R.id.rv_weapons_details);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WeaponDetailsAdapter(weapon);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}