package com.mutablestudio.swipeomatic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements NavigationController {

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this, new SwipeLeftGestureListener());

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

        FragmentOne fragment = FragmentOne.newInstance();
        setContentFragment(fragment, false, "FRAGMENT_ONE");
    }

    public void setContentFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (tag=="FRAGMENT_ONE") {}
        else {
            ft.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right);
        }
        if (addToBackStack) ft.addToBackStack(tag);
        ft.add(R.id.fragmentContainer, fragment, tag);
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (f!=null) ft.hide(f);
        ft.commit();
    }

    @Override
    public void goToFragmentTwo() {
        FragmentTwo fragment = FragmentTwo.newInstance();
        setContentFragment(fragment, true, "FRAGMENT_TWO");
    }

    @Override
    public void goToFragmentThree() {
        FragmentThree fragment = FragmentThree.newInstance();
        setContentFragment(fragment, true, "FRAGMENT_THREE");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class SwipeLeftGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: ");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(DEBUG_TAG, "onScroll: " + distanceX);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if (velocityX>800) {
                Log.d(DEBUG_TAG, "onFling: >800 " + getSupportFragmentManager().getBackStackEntryCount());
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    Log.i("MainActivity", "popping backstack");
                    getSupportFragmentManager().popBackStack();
                } else {
                    MainActivity.this.onBackPressed();
                }
            }
            return true;
        }
    }

}
