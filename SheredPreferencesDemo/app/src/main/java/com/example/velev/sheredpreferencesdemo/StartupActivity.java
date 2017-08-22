package com.example.velev.sheredpreferencesdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StartupActivity extends AppCompatActivity {

    private LinearLayout dexterContainer;
    private LinearLayout deeDeeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        dexterContainer = (LinearLayout) findViewById(R.id.container_img_dexter);
        deeDeeContainer = (LinearLayout) findViewById(R.id.container_img_deedee);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setDexterVisibility(sharedPreferences.getBoolean(getString(R.string.key_show_dexter), true));
        setDeeDeeVisibility(sharedPreferences.getBoolean(getString(R.string.key_show_deedee), true));

        loadColorFromPreferences(sharedPreferences);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our visualizer_menu layout to this menu */
        inflater.inflate(R.menu.menu_settings, menu);
        /* Return true so that the visualizer_menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDexterVisibility(boolean isVisible) {
        if(isVisible) {
            dexterContainer.setVisibility(View.VISIBLE);
        } else {
            dexterContainer.setVisibility(View.GONE);
        }
    }

    private void setDeeDeeVisibility(boolean isVisible) {
        if(isVisible) {
            deeDeeContainer.setVisibility(View.VISIBLE);
        } else {
            deeDeeContainer.setVisibility(View.GONE);
        }
    }

    private void loadColorFromPreferences(SharedPreferences sharedPreferences) {
        setBackgroundColor(sharedPreferences.getString(getString(R.string.pref_color_key),
                getString(R.string.pref_color_red_key)));
    }

    private void setBackgroundColor(String colorKey) {

        if(colorKey.equals(getString(R.string.pref_color_red_key))){
            deeDeeContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            dexterContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        } else if(colorKey.equals(getString(R.string.pref_color_blue_key))) {
            deeDeeContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            dexterContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        } else if (colorKey.equals(getString(R.string.pref_color_green_key))) {
            deeDeeContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
            dexterContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        }
    }
}
