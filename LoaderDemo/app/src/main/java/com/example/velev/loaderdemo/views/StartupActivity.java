package com.example.velev.loaderdemo.views;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.velev.loaderdemo.R;
import com.example.velev.loaderdemo.data.FakeData;

import java.util.List;

public class StartupActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<String>> {

    private static final int MY_LOADER_ID = 1;
    private RecyclerView rvItems;
    private MyAdapter adapter;
    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);


        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        adapter = new MyAdapter(null);
        rvItems = (RecyclerView) findViewById(R.id.rv_items);
        rvItems.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvItems.setLayoutManager(layoutManager);

        rvItems.setItemAnimator(new DefaultItemAnimator());

        getSupportLoaderManager().initLoader(MY_LOADER_ID, null, this);

    }


    @Override
    public Loader<List<String>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<List<String>>(this) {

            @Override
            protected void onStartLoading() {

                /*
                 * When we initially begin loading in the background, we want to display the
                 * loading indicator to the user
                 */

                loadingIndicator.setVisibility(View.VISIBLE);

                forceLoad();
            }

            @Override
            public List<String> loadInBackground() {

                // simulate retrieving data
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // decouple this
                FakeData data = new FakeData();

                return data.getData();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
         /* When we finish loading, we want to hide the loading indicator from the user. */
        loadingIndicator.setVisibility(View.GONE);

        adapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {

    }
}
