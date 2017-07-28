package com.example.velev.uitraining;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FabSnackBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_snack_bar);

        final CoordinatorLayout root = (CoordinatorLayout) findViewById(R.id.root);

        Button btnShowShanckbar = (Button) findViewById(R.id.btn_show_snackbar);
        btnShowShanckbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(root,"simple SnackBar", Snackbar.LENGTH_SHORT)
                        .setAction("close", new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                // do stuff
                            }
                        }).show();
            }
        });
    }
}
