package com.example.velev.dragselection.gridview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.velev.dragselection.R;
import com.example.velev.dragselection.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 11.9.2017 г..
 */

public class GridFragment extends Fragment
        implements GridAdapter.OnLongPressSelectedItem {

    private RecyclerView rvHours;
    private CustomScrollView scrollView;
    private GridAdapter adapter;
    private int currentPosition;

    private float mX;
    private float mY;
    private Direction mDirection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_grid, container, false);


        List<String> data = new ArrayList<>();
        int length = 7 * 24;
        int counter = 0;
        for (int i = 0; i < length; i++) {

            String day = getDayOfWeekByIndex(i % 7);
            data.add(day + " " + String.valueOf(counter));
            if ((i + 1) % 7 == 0) {
                counter++;
            }
        }

        scrollView = (CustomScrollView) view.findViewById(R.id.sv_horizontal);

        rvHours = (RecyclerView) view.findViewById(R.id.rv_grid);
        int numberOfColumns = 7;
        rvHours.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new GridAdapter(data, this);
        rvHours.setAdapter(adapter);


        return view;
    }

    @Override
    public void onLongPressed() {
        // reset the coordinates and direction
        mX = -1;
        mY = -1;
        mDirection = Direction.NONE;

        scrollView.setEnableScrolling(false);

        rvHours.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        // TODO think about initialise mX and mY here...
                        return true;
                    case (MotionEvent.ACTION_MOVE):

                        // if first time after longPress
                        if (mX == -1 && mY == -1) {
                            mX = event.getX();
                            mY = event.getY();
                        }

                        View view = rvHours.findChildViewUnder(event.getX(), event.getY());

                        if (view != null) {
                            int position = rvHours.getChildAdapterPosition(view);

                            boolean isToggleSelectionON = detectDirection(event.getX(), event.getY());

                            if (isToggleSelectionON) {
                                adapter.unSelect(position);
                                view.setBackgroundResource(R.drawable.cell_background);
                            }
                            Toast.makeText(getContext(), String.valueOf(mDirection), Toast.LENGTH_SHORT).show();
                            if (position != currentPosition) {
                                currentPosition = position;

                                if (adapter.checkIfSelected(position)) {
                                    adapter.unSelect(position);
                                    view.setBackgroundResource(R.drawable.cell_background);
                                } else {
                                    adapter.select(position);
                                    view.setBackgroundResource(R.drawable.cell_background_selected);
                                }
                            }
                        }
                        // update the values
                        mX = event.getX();
                        mY = event.getY();

                        return true;

                    case (MotionEvent.ACTION_UP):
                        rvHours.setOnTouchListener(null);
                        scrollView.setEnableScrolling(true);

                        return true;
                }

                return true;
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
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };

        return days[index];
    }
}
