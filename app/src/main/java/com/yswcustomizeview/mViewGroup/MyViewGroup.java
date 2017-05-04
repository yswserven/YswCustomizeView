package com.yswcustomizeview.mViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by： Ysw on 2016/6/5.17:52.
 */
public class MyViewGroup extends ViewGroup {

    // 滑动的状态 @author Ysw created at 2016/6/5 18:49
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private int mTouchState = TOUCH_STATE_REST;
    public static int SNAP_VELOCITY = 600;
    private int mCurrentScreen = 0;
    private Scroller mScroller = null;
    private View mTopScreen;
    private View mBottomScreen;
    private int mTouchSlop = 0;
    private float mLastMotionY = 0;
    private float mLastMotionX = 0;
    private VelocityTracker mVelocityTracker = null;

    public MyViewGroup(Context context) {
        super(context);
        init(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int startLeft = 0;
        int childTop = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != View.GONE) {
                childAt.layout(startLeft, childTop, getWidth(), childTop + getHeight());
            }
            childTop = childTop + getHeight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if ((action == MotionEvent.ACTION_MOVE) && mTouchState != TOUCH_STATE_REST) return true;
        final float x = ev.getX();
        final float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionY = y;
                mLastMotionX = x;
                mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SCROLLING;
                break;
            case MotionEvent.ACTION_MOVE:
                final int yDiff = (int) Math.abs(mLastMotionY - y);
                if (yDiff > mTouchSlop) mTouchState = TOUCH_STATE_SCROLLING;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        return mTouchState != TOUCH_STATE_REST;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mScroller != null) if (!mScroller.isFinished()) mScroller.abortAnimation();
                mLastMotionY = y;
                mLastMotionX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                int dataX = (int) (mLastMotionX - x);
                int dataY = (int) (mLastMotionY - y);
                scrollBy(0, dataY);
                mLastMotionX = x;
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000);
                int xVelocity = (int) velocityTracker.getXVelocity();
                int yVelocity = (int) velocityTracker.getYVelocity();
                if (yVelocity > SNAP_VELOCITY && mCurrentScreen > 0) {
                    snapToScreen(mCurrentScreen - 1);
                } else if (yVelocity < -SNAP_VELOCITY && mCurrentScreen < (getChildCount() - 1)) {
                    snapToScreen(mCurrentScreen + 1);
                } else {
                    snapToDestination();
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                mTouchState = TOUCH_STATE_REST;
                break;
            case MotionEvent.ACTION_CANCEL:
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        return true;
    }

    private void snapToDestination() {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int destScreen = (scrollY + getHeight() / 8) / getHeight();
        snapToScreen(destScreen);
    }

    private void snapToScreen(int whichScreen) {
        mCurrentScreen = whichScreen;
        if (mCurrentScreen > getChildCount() - 1) {
            mCurrentScreen = getChildCount() - 1;
        } else if (mCurrentScreen < 0) {
            mCurrentScreen = 0;
        }
        int dy = mCurrentScreen * getHeight() - getScrollY();
        mScroller.startScroll(0, getScrollY(), 0, dy, Math.abs(dy) / 3);
        Log.e("Ysw", "getScrollY = " + getScrollY());
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public void addChilds(View top, View bottom) {
        mTopScreen = top;
        mBottomScreen = bottom;
        addView(mTopScreen, 0);
        addView(mBottomScreen, 1);
        invalidate();
    }
}
