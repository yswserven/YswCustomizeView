package com.yswcustomizeview.mViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyParentVerticalScrollLayout extends ViewGroup {
    public static final String TAG = "MyVerticalScrollLayout";
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mCurScreen;
    private int mDefaultScreen = 0;
    private static final int SNAP_VELOCITY = 600;
    private int mTouchSlop;
    private int mLastMotionY;
    private boolean isOver = true;
    private MyChildVerticalScrollLayout mChild;

    public MyParentVerticalScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyParentVerticalScrollLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScroller = new Scroller(context);
        mCurScreen = mDefaultScreen;
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int height = MeasureSpec.getSize(heightMeasureSpec);
        final int HeightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (HeightMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException("ScrollLayout only canmCurScreen run at EXACTLY mode!");
        }
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException("ScrollLayout only can run at EXACTLY mode!");
        }
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LayoutParams params = child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, 0, params.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, params.height);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
        scrollTo(0, mCurScreen * height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childTop = 0;
            final int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View childView = getChildAt(i);
                if (childView.getVisibility() != View.GONE) {
                    final int childHeight = childView.getMeasuredHeight();
                    childView.layout(0, childTop, childView.getMeasuredWidth(), childTop + childHeight);
                    childTop += childHeight;
                }
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionY = y;
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastMotionY;
                if (deltaY < 0) {
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = mLastMotionY - y;
                if (deltaY < 0 && getScrollY() + deltaY <= 0) {
                } else {
                    scrollBy(0, deltaY);
                }
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                int velocityY = (int) mVelocityTracker.getYVelocity();
                if (velocityY > SNAP_VELOCITY && mCurScreen > 0) {
                    snapToScreen(mCurScreen - 1);
                } else if (velocityY < -SNAP_VELOCITY && mCurScreen < getChildCount() - 1) {
                    snapToScreen(mCurScreen + 1);
                } else {
                    snapToDestination();
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        mLastMotionY = y;
        return true;
    }

    public void snapToDestination() {
        final int screenHeight = getHeight();
        final int destScreen = (getScrollY() + screenHeight / 2) / screenHeight;
        snapToScreen(destScreen);
    }

    public void snapToScreen(int whichScreen) {
        whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
        if (getScrollY() != (whichScreen * getHeight())) {
            final int delta = whichScreen * getHeight() - getScrollY();
            mScroller.startScroll(0, getScrollY(), 0, delta, Math.min(500, Math.abs(delta)));
            mCurScreen = whichScreen;
            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public void setChild(MyChildVerticalScrollLayout child) {
        this.mChild = child;
    }
}
