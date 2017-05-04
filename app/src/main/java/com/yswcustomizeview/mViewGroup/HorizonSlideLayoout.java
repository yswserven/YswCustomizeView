package com.yswcustomizeview.mViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by： Ysw on 2016/6/16.15:02.
 */
public class HorizonSlideLayoout extends ViewGroup {

    private int mHspace = 0;
    private int mVspace = 0;
    private int mWidth = 0;
    private int mHeight = 0;
    private int mTouchSlop = 0;
    private int mDataScreen = 0;
    private int mCurrentScreen = 0;
    private float mLastMotionY = 0;
    private int mMaximumVelocity;
    private Scroller mScroller = null;
    private VelocityTracker mVelocityTracker = null;

    public HorizonSlideLayoout(Context context) {
        super(context);
        init();
    }

    public HorizonSlideLayoout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizonSlideLayoout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);
            childAt.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int startLeft = mHspace;
        int startTop = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);
            if (i == 0) {
                childAt.layout(startLeft, 0, mWidth - mHspace, getMeasuredHeight());
            } else {

            }
        }
    }
}
