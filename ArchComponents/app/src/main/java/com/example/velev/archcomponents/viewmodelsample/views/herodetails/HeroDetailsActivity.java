package com.example.velev.archcomponents.viewmodelsample.views.herodetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.velev.archcomponents.R;
import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;
import com.example.velev.archcomponents.viewmodelsample.views.herolist.HeroesListActivity;
import com.squareup.picasso.Picasso;

public class HeroDetailsActivity extends AppCompatActivity {
    private static final String HERO = "HERO";
    private HeroEntity mHero;
    private HeroDetailsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUi();
    }

    private void initUi() {
        setContentView(R.layout.activity_hero_details);

        mViewModel  = ViewModelProviders.of(this).get(HeroDetailsViewModel.class);

        mHero =  (HeroEntity) getIntent().getSerializableExtra(HERO);

        setHeroName();
        loadImg();

        Button btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteDialog();
            }
        });

        Button btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateDialog();
            }
        });
    }

    private void openDeleteDialog() {
        AlertDialog.Builder delDialog = new AlertDialog.Builder(this);
        delDialog.setMessage(R.string.delete_this_hero)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDelete();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = delDialog.create();
        alert.show();
    }

    private void onDelete() {
        mViewModel.deleteHero(mHero, this);
        Intent intent = new Intent(this, HeroesListActivity.class);
        startActivity(intent);
    }

    private void openUpdateDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View mView = getLayoutInflater().inflate(R.layout.update_hero_dialog, null);
        builder.setView(mView);
        final AlertDialog dialog = builder.create();


        final EditText etHeroName = mView.findViewById(R.id.et_heroname);
        etHeroName.setText(mHero.getName());

        final EditText etUrl = mView.findViewById(R.id.et_url);
        etUrl.setText(mHero.getImgURL());

        Button btnUpdate = mView.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdate();
                updateActivityState(etHeroName, etUrl);
                dialog.dismiss();
            }
        });

        Button btnCancel = mView.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void onUpdate(){
        mViewModel.updateHero(mHero, this);
    }

    private void setHeroName() {
        TextView tvHeroName = findViewById(R.id.tv_heroname);
        tvHeroName.setText(mHero.getName());
    }

    private void loadImg() {
        ImageView image = findViewById(R.id.iv_hero);
        Picasso.with(this)
                .load(mHero.getImgURL())
                .error(R.drawable.ic_action_name)
                .into(image);
    }

    private void updateActivityState(TextView etHeroName, TextView etUrl) {
        mHero.setName(etHeroName.getText().toString());
        mHero.setImgURL(etUrl.getText().toString());
        setHeroName();
        loadImg();
    }
}
