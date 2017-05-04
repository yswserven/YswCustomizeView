package com.yswcustomizeview.mScrollDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MultiViewGroup extends ViewGroup {

    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private int mTouchState = TOUCH_STATE_REST;
    public static int SNAP_VELOCITY = 600;
    private int curScreen = 0;  //当前屏
    private Context mContext;
    private Scroller mScroller = null;
    private View mTopScreen;
    private View mBottomScreen;
    //处理触摸事件
    private int mTouchSlop = 0;
    private float mLastionMotionX = 0;
    private float mLastionMotionY = 0;
    //处理触摸的速率
    private VelocityTracker mVelocityTracker = null;

    public MultiViewGroup(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public MultiViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mScroller = new Scroller(mContext);
        mTouchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int startLeft = 0;
        int startTop = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(startLeft, startTop, startLeft + getWidth(), startTop + getHeight());
            }
            startTop = startTop + getHeight();
        }
    }

    // 这个感觉没什么作用 不管true还是false 都是会执行onTouchEvent的 因为子view里面onTouchEvent返回false了
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if ((action == MotionEvent.ACTION_MOVE) && (mTouchState != TOUCH_STATE_REST)) {
            return true;
        }
        final float x = ev.getX();
        final float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastionMotionX = x;
                mLastionMotionY = y;
                mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SCROLLING;
                break;
            case MotionEvent.ACTION_MOVE:
                final int xDiff = (int) Math.abs(mLastionMotionY - y);
                //超过了最小滑动距离
                if (xDiff > mTouchSlop) {
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

    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //如果屏幕的动画还没结束，你就按下了，我们就结束该动画
                if (mScroller != null) {
                    if (!mScroller.isFinished()) {
                        mScroller.abortAnimation();
                    }
                }
                mLastionMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int detaY = (int) (mLastionMotionY - y);
                int detaX = (int) (mLastionMotionX - x);
                scrollBy(0, detaY);
                mLastionMotionY = y;
                mLastionMotionX = x;
                break;
            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000);
                int velocityX = (int) velocityTracker.getXVelocity();
                int velocityY = (int) velocityTracker.getYVelocity();
                Log.e("Ysw", "velocityY = " + velocityY);
                //滑动速率达到了一个标准(快速向右滑屏，返回上一个屏幕) 马上进行切屏处理
                if (velocityY > SNAP_VELOCITY && curScreen > 0) {
                    snapToScreen(curScreen - 1);
                } else if (velocityY < -SNAP_VELOCITY && curScreen < (getChildCount() - 1)) {
                    snapToScreen(curScreen + 1);
                }
                //以上为快速移动的 ，强制切换屏幕
                else {
                    //我们是缓慢移动的，因此先判断是保留在本屏幕还是到下一屏幕
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
        //当前的偏移位置
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int destScreen = (scrollY + getHeight() / 8) / getHeight();
        snapToScreen(destScreen);
    }

    // 滑动到哪个屏幕 @author Ysw created at 2016/6/5 13:06
    private void snapToScreen(int whichScreen) {
        curScreen = whichScreen;
        if (curScreen > getChildCount() - 1) {
            curScreen = getChildCount() - 1;
        }
        int dy = curScreen * getHeight() - getScrollY();
        mScroller.startScroll(0, getScrollY(), 0, dy, Math.abs(dy) / 3);
        //此时需要手动刷新View 否则没效果
        invalidate();
    }

    //  只有当前LAYOUT中的某个CHILD导致SCROLL发生滚动，才会致使自己的COMPUTESCROLL被调用 @author Ysw created at 2016/6/8 10:06
    @Override
    public void computeScroll() {
        // 如果返回true，表示动画还没有结束
        // 因为前面startScroll，所以只有在startScroll完成时 才会为false
        if (mScroller.computeScrollOffset()) {
            // 产生了动画效果 每次滚动一点
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

    public void startMove() {
        curScreen++;
        mScroller.startScroll((curScreen - 1) * getWidth(), 0, getWidth(), 0, 1000);
        invalidate();
    }

    public void stopMove() {
        if (mScroller != null) {
            //如果动画还没结束，我们就按下了结束的按钮，那我们就结束该动画，即马上滑动指定位置
            if (!mScroller.isFinished()) {
                int scrollCurX = mScroller.getCurrX();
                //判断是否达到下一屏的中间位置，如果达到就抵达下一屏，否则保持在原屏幕
                int descScreen = (scrollCurX + getWidth() / 2) / getWidth();
                mScroller.abortAnimation();
                //停止了动画，我们马上滑倒目标位置
                scrollTo(descScreen * getWidth(), 0);
                mScroller.forceFinished(true);
                curScreen = descScreen;
            }
        }
    }
}
