package com.example.velev.dragselection.gridview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.velev.dragselection.R;
import com.example.velev.dragselection.gridview.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 11.9.2017 г..
 */

public class GridFragment extends Fragment
        implements GridAdapter.OnLongPressSelectedItem {

    private static final int DAYS_OF_WEEK_COUNT = 7;

    private RecyclerView mRvHours;
    private CustomHorizontalScrollView mHorizontalScroll;
    private Switch mSwitch;
    private FloatingActionButton mFab;
    private GridAdapter mAdapter;
    private int mCurrentPosition;
    private Button mBtnClear;

    private float mX;
    private float mY;
    private Direction mDirection;
    private boolean mInitialVisibilityStateFAB = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        initUi(view);

        return view;
    }

    @Override
    public void onLongPressed() {

    }

    private void initUi(View view) {
        mSwitch = (Switch) view.findViewById(R.id.sw_select_mode);
        onSwitchButtonSwitched();

        mHorizontalScroll = (CustomHorizontalScrollView) view.findViewById(R.id.sv_horizontal);

        mRvHours = (RecyclerView) view.findViewById(R.id.rv_grid);
        mRvHours.setLayoutManager(new GridLayoutManager(getActivity(), DAYS_OF_WEEK_COUNT));
        mAdapter = new GridAdapter(createData(), this, getContext());
        mRvHours.setAdapter(mAdapter);

        mBtnClear = (Button) view.findViewById(R.id.btn_clear);
        clearGrid();

        mFab = (FloatingActionButton) view.findViewById(R.id.fab_save);
        onScroll();
    }

    private List<String> createData() {
        List<String> data = new ArrayList<>();
        int length = 7 * 24;
        int counter = 0;
        for (int i = 0; i < length; i++) {

            String day = getDayOfWeekByIndex(i % 7);
            data.add(day + " " + createStringHour(counter));
            if ((i + 1) % 7 == 0) {
                counter++;
            }
        }

        return data;
    }

    private void onSwitchButtonSwitched() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getContext(), "ON", Toast.LENGTH_SHORT).show();
                    onSelectModeOn();
                } else {
                    // The toggle is disabled
                    Toast.makeText(getContext(), "OFF", Toast.LENGTH_SHORT).show();
                    mRvHours.setOnTouchListener(null);
                    mHorizontalScroll.setEnableScrolling(true);
                }
            }
        });
    }

    private void onSelectModeOn() {
        mDirection = Direction.NONE;

        mHorizontalScroll.setEnableScrolling(false);

        mRvHours.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {

                    case (MotionEvent.ACTION_MOVE):
                        View view = mRvHours.findChildViewUnder(event.getX(), event.getY());

                        if (view != null) {
                            int position = mRvHours.getChildAdapterPosition(view);
                            boolean isToggleSelectionON = detectDirection(event.getX(), event.getY());

                            if (isToggleSelectionON) {
                                mAdapter.unSelect(position, view);
                                view.setBackgroundResource(R.drawable.cell_background);
                            }

                            if (position != mCurrentPosition) {
                                mCurrentPosition = position;

                                if (mAdapter.checkIfSelected(position)) {
                                    mAdapter.unSelect(position, view);
                                    view.setBackgroundResource(R.drawable.cell_background);
                                } else {
                                    mAdapter.select(position, view);
                                    view.setBackgroundResource(R.drawable.cell_background_selected);
                                }
                            }
                        }
                        // update the values
                        mX = event.getX();
                        mY = event.getY();

                        return true;
                }

                return true;
            }
        });
    }

    private void onScroll() {

        mRvHours.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mFab.show();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!mInitialVisibilityStateFAB) {
                    mFab.hide();
                }

                mInitialVisibilityStateFAB = false;
            }
        });

    }

    private boolean detectDirection(float x, float y) {
        float dX = mX - x;
        float dY = mY - y;
        boolean isOpposite = false;

        if (Math.abs(dX) > Math.abs(dY)) {
            if (dX > 0) {
                isOpposite = checkIfOppositeDirection(mDirection, Direction.LEFT);
                mDirection = Direction.LEFT;
            } else {
                isOpposite = checkIfOppositeDirection(mDirection, Direction.RIGHT);
                mDirection = Direction.RIGHT;
            }

        } else {
            if (dY > 0) {
                isOpposite = checkIfOppositeDirection(mDirection, Direction.UP);
                mDirection = Direction.UP;
            } else {
                isOpposite = checkIfOppositeDirection(mDirection, Direction.DOWN);
                mDirection = Direction.DOWN;
            }
        }

        return isOpposite;
    }

    private boolean checkIfOppositeDirection(Direction oldDirection, Direction newDirection) {

        if ((oldDirection == Direction.RIGHT && newDirection == Direction.LEFT) ||
                (oldDirection == Direction.LEFT && newDirection == Direction.RIGHT) ||
                (oldDirection == Direction.UP && newDirection == Direction.DOWN) ||
                (oldDirection == Direction.DOWN && newDirection == Direction.UP)) {
            return true;
        }

        return false;
    }

    private String getDayOfWeekByIndex(int index) {

        String[] days = new String[]{
                "Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri",
                "Sat"
        };

        return days[index];
    }

    private String createStringHour(int hour) {

        if (hour < 10) {
            return "0" + hour + ":00";
        } else {
            return hour + ":00";
        }
    }

    private void clearGrid() {

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] cellStatus = mAdapter.getIsSelected();
                for (int i = 0; i < cellStatus.length; i++) {
                    if (cellStatus[i]) {
                        mAdapter.unSelect(i);
                    }
                }
            }
        });
    }
}
