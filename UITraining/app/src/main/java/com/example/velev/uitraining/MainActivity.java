package com.example.velev.uitraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openCardDemo();

        openFabSnackBarDemo();

        openFragmentDemo();
    }

    private void openFabSnackBarDemo() {
        Button btnFabSnackBar = (Button) findViewById(R.id.btn_fab_and_snackbar);
        btnFabSnackBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FabSnackBarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openFragmentDemo() {
        Button btnOpenFragment = (Button) findViewById(R.id.btn_open_fragment_activity);
        btnOpenFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openCardDemo() {
        img = (ImageView) findViewById(R.id.img_dexter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(intent);

            }
        });
    }
}
