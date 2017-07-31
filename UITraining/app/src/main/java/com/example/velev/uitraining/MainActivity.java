package com.example.velev.uitraining;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
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

        openFabFollowsWidgetDemo();

        openCollapsingToolbarAppbarDemo();

        openFragmentDemo();
    }

    private void openCollapsingToolbarAppbarDemo() {
        Button btnCollapsingDemo = (Button) findViewById(R.id.btn_collapsing_demo);
        btnCollapsingDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CollapsingToolbarAppbarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openFabFollowsWidgetDemo() {
        Button btnFabFollowsWidget = (Button) findViewById(R.id.btn_fab_follows_widget);
        btnFabFollowsWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FabFollowsWidget.class);
                startActivity(intent);
            }
        });

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
                //intent.putExtra(CardActivity.EXTRA_CONTACT)


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this, img, "avatar");
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }
}
