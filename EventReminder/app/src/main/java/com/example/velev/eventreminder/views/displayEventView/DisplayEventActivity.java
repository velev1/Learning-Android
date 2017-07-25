package com.example.velev.eventreminder.views.displayEventView;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.velev.eventreminder.R;
import com.example.velev.eventreminder.data.LocalData;
import com.example.velev.eventreminder.data.models.Event;
import com.example.velev.eventreminder.views.createEventView.CreateEventActivity;

import java.util.ArrayList;
import java.util.List;

public class DisplayEventActivity extends AppCompatActivity implements DisplayEventsContract.View{

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private boolean initialVisibilityStateFAB = true;

    private EventAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);

//        // test with LocalData
//        LocalData data = new LocalData();
//        EventAdapter adapter = new EventAdapter(data.getAllEvents());

       // DisplayEventViewModel viewModel = new DisplayEventViewModel();

        List<Event> e = new ArrayList<>();
        this.adapter = new EventAdapter(e);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        openCreateEventView();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        getSupportLoaderManager().initLoader(1, null,loaderCallbacks);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        onScroll();
        openEventDetailsView();
    }

    @Override
    public void openCreateEventView() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayEventActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void openEventDetailsView() {

    }

    @Override
    public void deleteEventAt(int position) {
        adapter.deleteEventAt(position);
        adapter.notifyItemRemoved(position);
        showUndoSnackbar();
    }

    public void showUndoSnackbar() {
        Snackbar.make(
                findViewById(R.id.item_row),
                getString(R.string.action_delete_item),
                Snackbar.LENGTH_LONG
        )
                .setAction(R.string.action_undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = adapter.getSwipedPosition();
                        Event event = adapter.getSwipedEvent();
                        if(position >= 0 && event != null) {
                            adapter.insertEventAt(position, event);
                            adapter.notifyItemInserted(position);
                        }
                    }
                })
                .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);

                        // TODO delete from the database
                    }
                })
                .show();
    }

    private void onScroll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                floatingActionButton.show();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(!initialVisibilityStateFAB) {
                    floatingActionButton.hide();
                }

                initialVisibilityStateFAB = false;
            }
        });
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        /*First Param is for Up/Down motion, second is for Left/Right.
        Note that we can supply 0, one constant (e.g. ItemTouchHelper.LEFT), or two constants (e.g.
        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) to specify what directions are allowed.
        */
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }


            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                Event event  = adapter.getEvent(position);
                adapter.setSwipedEvent(event);
                adapter.setSwipedPosition(position);
                deleteEventAt(position);
            }
        };

        return simpleItemTouchCallback;
    }

    private LoaderManager.LoaderCallbacks<List<Event>> loaderCallbacks = new LoaderManager.LoaderCallbacks<List<Event>>(){
        @Override
        public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
            return new EventLoader(getApplicationContext());
        }

        @Override
        public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {
            adapter.swapData(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Event>> loader) {

        }
    };
}
