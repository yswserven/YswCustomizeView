package com.yswcustomizeview.mViewGroup;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyChildVerticalScrollLayout extends ViewGroup {
    public static final String TAG = "MyVerticalScrollLayout";
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mCurScreen;
    private int mDefaultScreen = 0;
    private static final int SNAP_VELOCITY = 600;
    private int mTouchSlop;
    private int mLastMotionY;
    private float mY;
    private boolean isOver = true;
    private ChildScrollLayoutChangeListener mListeners;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mListeners.overRefresh();
                    break;
                case 2:
                    snapToOrigin();
                    break;
                default:
                    break;
            }
        }
    };

    public MyChildVerticalScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void addChangeListener(ChildScrollLayoutChangeListener listener) {
        mListeners = listener;
    }

    public MyChildVerticalScrollLayout(Context context, AttributeSet attrs, int defStyle) {
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
                if (i == 0 && childView.getVisibility() != View.GONE) {
                    final int childHeight = childView.getMeasuredHeight();
                    childView.layout(0, -childHeight, childView.getMeasuredWidth(), 0);
                } else if (childView.getVisibility() != View.GONE) {
                    final int childHeight = childView.getMeasuredHeight();
                    childView.layout(0, childTop, childView.getMeasuredWidth(), childTop + childHeight);
                    childTop += childHeight;
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isOver) {
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
                    mY += deltaY;
                    if (mCurScreen == 0 && mY < -500) {
                        // 最大下拉长度为500，之后不能再继续下拉 @author Ysw created at 2017/3/20 13:16
                    } else if (mCurScreen == 0 && mY < -300) {
                        scrollBy(0, deltaY);
                        mListeners.onReady();
                    } else {
                        scrollBy(0, deltaY);
                        mListeners.onReadyRrfresh();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mVelocityTracker.computeCurrentVelocity(1000);
                    int velocityY = (int) mVelocityTracker.getYVelocity();
                    if (mCurScreen == 0 && getScrollY() < -300) {
                        snapToRefresh();
                        isOver = false;
                    } else if (velocityY > SNAP_VELOCITY && mCurScreen > 0) {
                        snapToScreen(mCurScreen - 1);
                    } else if (velocityY < -SNAP_VELOCITY && mCurScreen < getChildCount() - 2) {
                        snapToScreen(mCurScreen + 1);
                    } else {
                        snapToDestination();
                    }
                    if (mVelocityTracker != null) {
                        mVelocityTracker.recycle();
                        mVelocityTracker = null;
                    }
                    mY = 0f;
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
            }
            mLastMotionY = y;
            return true;
        } else {
            return false;
        }
    }

    public void snapToDestination() {
        final int screenHeight = getHeight();
        final int destScreen = (getScrollY() + screenHeight / 2) / screenHeight;
        snapToScreen(destScreen);
    }

    public void snapToScreen(int whichScreen) {
        int lastIndex = mCurScreen;
        whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 2));
        if (getScrollY() != (whichScreen * getHeight())) {
            final int delta = whichScreen * getHeight() - getScrollY();
            mScroller.startScroll(0, getScrollY(), 0, delta, Math.min(500, Math.abs(delta)));
            mCurScreen = whichScreen;
            invalidate();
        }
        mListeners.doChange(lastIndex, whichScreen);
    }

    public void snapToRefresh() {
        View first = getChildAt(0);
        int height = first.getHeight();
        int data = Math.abs(getScrollY() + height);
        mScroller.startScroll(0, getScrollY(), 0, data, 500);
        mListeners.doRefresh();
        invalidate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Message message = new Message();
                    message.what = 1;
                    mHandler.sendMessage(message);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    public void snapToOrigin() {
        View first = getChildAt(0);
        int height = first.getHeight();
        mScroller.startScroll(0, -height, 0, height, 500);
        isOver = true;
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public void setOvre(boolean over) {
        this.isOver = over;
    }

    public void setToScreen(int whichScreen) {
        whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
        mCurScreen = whichScreen;
        scrollTo(0, whichScreen * getHeight());
    }

    public int getCurScreen() {
        return mCurScreen;
    }
}
