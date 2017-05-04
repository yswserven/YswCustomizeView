package com.yswcustomizeview.mViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by: Ysw on 2017/3/20.
 */

public class MyLinearLayout extends LinearLayout {
    private int mLastMotionY;
    private int mLastMotionX;
    private Scroller mScroller;
    private int mHeight;
    private int mCurrent;

    public MyLinearLayout(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final int deltaY = y - mLastMotionY;
                int scrollY = getScrollY();
                if (scrollY < 120 && scrollY > -1) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                Log.e("Ysw","scrollY_Parent = " + scrollY);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                intercepted = false;
                break;
        }
        mLastMotionY = y;
        mLastMotionX = x;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = mLastMotionY - y;
                int scrollY = getScrollY();
                if (scrollY < 120 && scrollY > -1) {
                    scrollBy(0, deltaY);
                }
                Log.e("Ysw","scrollY = " + scrollY);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        mLastMotionY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
