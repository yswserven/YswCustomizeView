package com.yswcustomizeview.mViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by： Ysw on 2016/6/21.10:47.
 */
public class SlidingMenuLayout extends ViewGroup {

    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private int mTouchState = TOUCH_STATE_REST;
    public static int SNAP_VELOCITY = 1000;
    private int mTouchSlop = 0;
    private Scroller mScroller;
    private int mMaximumVelocity;
    private VelocityTracker mVelocityTracker = null;
    private float mLastMotionX = 0;
    private float mScrollerXdiff = 0;
    private SlidingMenuLayoutPage mSlidingMenu;
    private SlidingMenuLayoutPage mSlidingPage;
    private SlidingMenuLayoutPage mLeftPage;
    private SlidingMenuLayoutPage mRightPage;
    private int mDataScreen = 0;
    private int mCurrentScreen = 0;
    private int mScreenWidth;

    public SlidingMenuLayout(Context context) {
        super(context);
        init();
    }

    public SlidingMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlidingMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
    }

    public interface SlidingMenuLayoutPage {
        View getRootView();

        boolean isAtLeft();

        boolean isAtMiddle();

        boolean isAtRight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View menu = this.getChildAt(0);
        menu.measure((int) (mScreenWidth * 0.75), heightMeasureSpec);
        View content = this.getChildAt(1);
        content.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != View.GONE) {
                if (i == 0) {
                    childAt.layout(-(int) (mScreenWidth * 0.75), 0, 0, childAt.getMeasuredHeight());
                    mScrollerXdiff = childAt.getMeasuredWidth();
                } else {
                    childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
                }
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if ((action == MotionEvent.ACTION_MOVE) && (mTouchState != TOUCH_STATE_REST)) return true;
        final float x = ev.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SCROLLING;
                break;
            case MotionEvent.ACTION_MOVE:
                final int xDiff = (int) (x - mLastMotionX);
                boolean xMoved = Math.abs(xDiff) > mTouchSlop;
                if (xMoved) {
                    mTouchState = TOUCH_STATE_SCROLLING;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        return mTouchState != TOUCH_STATE_REST;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(event);
        int action = event.getAction();
        float x = event.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) mScroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mTouchState != TOUCH_STATE_SCROLLING) {
                    int diffX = (int) Math.abs(x - mLastMotionX);
                    if (diffX > mTouchSlop && x < 100) {
                        mTouchState = TOUCH_STATE_SCROLLING;
                    }
                }
                if (mTouchState == TOUCH_STATE_SCROLLING) {
                    int currentX = (int) event.getX();
                    int diffX = (int) (mLastMotionX - currentX);
                    int scrollX = getScrollX() + diffX;
                    if (scrollX >= -this.getChildAt(0).getWidth() && scrollX <= 0) {
                        scrollBy(diffX, 0);
                    }
                    mLastMotionX = currentX;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mTouchState == TOUCH_STATE_SCROLLING) {
                    VelocityTracker velocityTracker = mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(SNAP_VELOCITY, mMaximumVelocity);
                    int xVelocity = (int) velocityTracker.getXVelocity();
                    if (xVelocity > 0) {
                        if (Math.abs(xVelocity) > SNAP_VELOCITY) {
                            mScroller.startScroll(getScrollX(), 0, -getChildAt(0).getWidth() - getScrollX(), 0, Math.abs((-getChildAt(0).getWidth() - getScrollX())));
                            invalidate();
                        } else if (Math.abs(getScrollX()) > getChildAt(0).getWidth() / 2) {
                            mScroller.startScroll(getScrollX(), 0, -getChildAt(0).getWidth() - getScrollX(), 0, Math.abs((-getChildAt(0).getWidth() - getScrollX())));
                            invalidate();
                        } else {
                            mScroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0, Math.abs(getScrollX()));
                            invalidate();
                        }
                    } else {
                        mScroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0, Math.abs(getScrollX()));
                        invalidate();
                    }
                    if (mVelocityTracker != null) {
                        mVelocityTracker.recycle();
                        mVelocityTracker = null;
                    }
                }
                break;
        }
        return true;
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public void addChilds(SlidingMenuLayoutPage slidingMenu, SlidingMenuLayoutPage slidingPage) {
        this.mSlidingMenu = slidingMenu;
        this.mSlidingPage = slidingPage;
        View topView = mSlidingMenu.getRootView();
        View bottomView = mSlidingPage.getRootView();
        topView.setId(0);
        bottomView.setId(1);
        addView(topView);
        addView(bottomView);
        postInvalidate();
    }

    // 滑动到菜单栏 @author Ysw created at 2016/6/21 17:45 
    public void scrollerToMenu() {
        mScroller.startScroll(0, 0, -(int) mScrollerXdiff, 0, Math.abs((int) mScrollerXdiff) / 3);
        invalidate();
    }

    // 滑动到主界面 @author Ysw created at 2016/6/21 17:46
    public void scrollerToContent() {
        mScroller.startScroll(0, 0, 0, 0, Math.abs((int) mScrollerXdiff) / 3);
    }
}
