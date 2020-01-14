package com.sqlite.pitch;

import android.view.MotionEvent;
import android.view.View;

abstract class OnSwipeTouchListener implements View.OnTouchListener {
    public OnSwipeTouchListener(MainActivity mainActivity) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public abstract void onSwipeDown();

    public abstract void onSwipeLeft();

    public abstract void onSwipeUp();

    public abstract void onSwipeRight();
}
