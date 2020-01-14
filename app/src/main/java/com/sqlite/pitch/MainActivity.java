package com.sqlite.pitch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.*;




public class MainActivity extends Fragment {

    public static int fingercount = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final GestureDetector gdt = new GestureDetector(getActivity(),
                (GestureDetector.OnGestureListener) new RemoteGestureListener());

        View view = inflater.inflate(R.layout.gestures, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                int action = event.getAction();

                switch (action & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.d(TAG,
                                "Pointer-Count ="
                                        + String.valueOf(fingercount = event
                                        .getPointerCount()));

                        break;
                }
                gdt.onTouchEvent(event);
                return true;
            }
        });
        return view;
    }
}

public class RemoteGestureListener implements OnGestureListenerr {

    private final static String TAG = "RemoteGestureListener";

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;


    public RemoteGestureListener() {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

        Log.d(TAG, "onFling");
        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            if (RemoteFragment.fingercount == 1) {
                Log.d(TAG, "Left Swipe");
            } else {
                Log.d(TAG, "Left xFinger Swipe");
                RemoteFragment.fingercount = 1;
            }
            return true; // Right to left
        } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            if (RemoteFragment.fingerCount == 1) {
                Log.d(TAG, "Right Swipe");
            } else {
                Log.d(TAG, "Right xFinger Swipe");
                RemoteFragment.fingerCount = 1;
            }
            return true; // Left to right
        }

        if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            if (RemoteFragment.fingerCount == 1) {
                Log.d(TAG, "Top Swipe");
            } else {
                Log.d(TAG, "Top xFinger Swipe");
                RemoteFragment.fingerCount = 1;
            }
            return true; // Bottom to top
        } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            if (RemoteFragment.fingerCount == 1) {
                Log.d(TAG, "Bottom Swipe");
            } else {
                Log.d(TAG, "Bottom xFinger Swipe");
                RemoteFragment.fingerCount = 1;
            }
            return true;
        }
        return true;
    }
