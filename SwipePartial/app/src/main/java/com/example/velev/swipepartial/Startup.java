package com.example.velev.swipepartial;

import android.graphics.Paint;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.velev.swipepartial.data.DataItemProvider;
import com.example.velev.swipepartial.data.ItemModel;

import java.util.List;

public class Startup extends AppCompatActivity {

    private static final float FONT_SIZE = 16f;

    private RecyclerView rvItems;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        DataItemProvider data = new DataItemProvider();

        adapter = new ItemAdapter(this, data.getData());
        rvItems = (RecyclerView) findViewById(R.id.rv_items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvItems.setLayoutManager(layoutManager);
        rvItems.setItemAnimator(new DefaultItemAnimator());
        rvItems.setAdapter(adapter);

        onSwipe();
    }

    private void onSwipe() {

        float size = FONT_SIZE;
        try {
            float fontScale = Settings.System.getFloat(this.getContentResolver(), Settings.System.FONT_SCALE);
            size = FONT_SIZE * fontScale;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        final float textFontSize = size;

        Paint paint = new Paint();
        paint.setTextSize(textFontSize);
        int padding = 20;
        int btnDeleteWidth = (int)(paint.measureText(this.getResources().getString(R.string.delete)) + padding);

        SwipeHelper swipeHelper = new SwipeHelper(this, rvItems, btnDeleteWidth) {

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)   {
                int position = viewHolder.getAdapterPosition();
                ItemModel item = adapter.getItem(position);
                if(item.isReadOnly()){
                    return 0;
                }

                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        Startup.this,
                        Startup.this.getResources().getString(R.string.delete),
                        textFontSize,
                        ContextCompat.getColor(Startup.this, R.color.white),
                        ContextCompat.getColor(Startup.this, R.color.colorAccent),
                        0,
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                                Toast.makeText(Startup.this, "Delete" + String.valueOf(pos), Toast.LENGTH_SHORT).show();
                            }
                        }
                ));

            }
        };
    }
}
