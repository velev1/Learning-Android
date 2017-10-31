package com.example.velev.swipepartial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by velev on 30.9.2017 г..
 */

public abstract  class SwipeHelper extends ItemTouchHelper.SimpleCallback{

    private RecyclerView mRecyclerView;
    private int mButtonWidth;
    private List<UnderlayButton> mButtons;
    private GestureDetector mGestureDetector;
    private int mSwipedPos = -1;
    private float mSwipeThreshold = 0.5f;
    private Map<Integer, List<UnderlayButton>> mButtonsBuffer;
    private Queue<Integer> mRecoverQueue;

    private GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            for (UnderlayButton button : mButtons){
                if(button.onClick(e.getX(), e.getY())) {
                    break;
                }
            }

            return true;
        }
    };

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent e) {
            if (mSwipedPos < 0) {
                return false;
            }

            Point point = new Point((int) e.getRawX(), (int) e.getRawY());

            RecyclerView.ViewHolder swipedViewHolder = mRecyclerView.findViewHolderForAdapterPosition(mSwipedPos);

            if (swipedViewHolder != null) {
                View swipedItem = swipedViewHolder.itemView;
                Rect rect = new Rect();
                swipedItem.getGlobalVisibleRect(rect);

                if (e.getAction() == MotionEvent.ACTION_DOWN || e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_MOVE) {
                    if (rect.top < point.y && rect.bottom > point.y) {
                        mGestureDetector.onTouchEvent(e);
                    } else {
                        mRecoverQueue.add(mSwipedPos);
                        mSwipedPos = -1;
                        recoverSwipedItem();
                    }
                }
            }

            return false;
        }
    };

    public SwipeHelper(Context context, RecyclerView recyclerView, int buttonWidth) {
        super(0, ItemTouchHelper.LEFT);
        mButtons = new ArrayList<>();
        mGestureDetector = new GestureDetector(context, mGestureListener);
        mRecyclerView = recyclerView;
        mRecyclerView.setOnTouchListener(mOnTouchListener);
        mButtonWidth =  (int) (buttonWidth * context.getResources().getDisplayMetrics().density);
        mButtonsBuffer = new HashMap<>();
        mRecoverQueue = new LinkedList<Integer>(){
            @Override
            public boolean add(Integer o) {
                if (contains(o)) {
                    return false;
                } else {
                    return super.add(o);
                }
            }
        };

        attachSwipe();
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();

        if (mSwipedPos != pos) {
            mRecoverQueue.add(mSwipedPos);
        }

        mSwipedPos = pos;

        if (mButtonsBuffer.containsKey(mSwipedPos)) {
            mButtons = mButtonsBuffer.get(mSwipedPos);
        } else {
            mButtons.clear();
        }

        mButtonsBuffer.clear();
        mSwipeThreshold = 0.5f * mButtons.size() * mButtonWidth;
        recoverSwipedItem();
    }

    @Override
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return mSwipeThreshold;
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return 0.1f * defaultValue;
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        return 5.0f * defaultValue;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        int pos = viewHolder.getAdapterPosition();
        float translationX = dX;
        View itemView = viewHolder.itemView;

        if (pos < 0){
            mSwipedPos = pos;
            return;
        }

        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            if(dX < 0) {
                List<UnderlayButton> buffer = new ArrayList<>();

                if (!mButtonsBuffer.containsKey(pos)){
                    instantiateUnderlayButton(viewHolder, buffer);
                    mButtonsBuffer.put(pos, buffer);
                }
                else {
                    buffer = mButtonsBuffer.get(pos);
                }

                translationX = dX * buffer.size() * mButtonWidth / itemView.getWidth();
                drawButtons(c, itemView, buffer, pos, translationX);
            }
        }

        super.onChildDraw(c, recyclerView, viewHolder, translationX, dY, actionState, isCurrentlyActive);
    }

    private synchronized void recoverSwipedItem(){
        while (!mRecoverQueue.isEmpty()){
            int pos = mRecoverQueue.poll();

            if (pos > -1) {
                mRecyclerView.getAdapter().notifyItemChanged(pos);
            }
        }
    }

    private void drawButtons(Canvas c, View itemView, List<UnderlayButton> buffer, int pos, float dX){
        float right = itemView.getRight();
        float dButtonWidth = (-1) * dX / buffer.size();

        for (UnderlayButton button : buffer) {
            float left = right - dButtonWidth;
            button.onDraw(
                    c,
                    new RectF(
                            left,
                            itemView.getTop(),
                            right,
                            itemView.getBottom()
                    ),
                    pos
            );

            right = left;
        }
    }

    public void attachSwipe(){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    public abstract void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons);

    public static class UnderlayButton {
        private String mText;
        private int mImageResId;
        private int mBackgroundColor;
        private int mTextColor;
        private float mTextSize;
        private int mPosition;
        private RectF mClickRegion;
        private UnderlayButtonClickListener mClickListener;

        public UnderlayButton(Context context,
                              String text,
                              float textSize,
                              int textColor ,
                              int backgroundColor,
                              int imageResId,
                              UnderlayButtonClickListener clickListener) {
            mText = text;
            mTextSize = textSize * context.getResources().getDisplayMetrics().density;
            mImageResId = imageResId;
            mTextColor = textColor;
            mBackgroundColor = backgroundColor;
            mClickListener = clickListener;
        }

        public boolean onClick(float x, float y){
            if (mClickRegion != null && mClickRegion.contains(x, y)){
                mClickListener.onClick(mPosition);
                return true;
            }

            return false;
        }

        public void onDraw(Canvas canvas, RectF rect, int pos){
            Paint paint = new Paint();

            // Draw background
            paint.setColor(mBackgroundColor);
            canvas.drawRect(rect, paint);

            // Draw Text
            paint.setColor(mTextColor);
            paint.setTextSize(mTextSize);

            Rect r = new Rect();
            float cHeight = rect.height();
            float cWidth = rect.width();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.getTextBounds(mText, 0, mText.length(), r);
            float x = cWidth / 2f - r.width() / 2f - r.left;
            float y = cHeight / 2f + r.height() / 2f - r.bottom;
            canvas.drawText(mText, rect.left + x, rect.top + y, paint);

            mClickRegion = rect;
            mPosition = pos;
        }
    }

    public interface UnderlayButtonClickListener {
        void onClick(int pos);
    }

}
