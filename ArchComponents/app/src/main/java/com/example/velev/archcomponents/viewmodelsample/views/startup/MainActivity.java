package com.example.velev.archcomponents.viewmodelsample.views.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.velev.archcomponents.R;
import com.example.velev.archcomponents.viewmodelsample.views.herolist.HeroesListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenViewModelDemo = findViewById(R.id.btn_view_model);
        btnOpenViewModelDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HeroesListActivity.class);
                startActivity(intent);
            }
        });
    }
}
