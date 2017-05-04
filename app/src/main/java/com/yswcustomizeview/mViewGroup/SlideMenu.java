package com.yswcustomizeview.mViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class SlideMenu extends ViewGroup {

    //记录上一次移动的位置
    private int mMostRecentX;
    //标记菜单界面
    private final int MENU_VIEW = 1;
    // 标记主界面
    private final int MAIN_VIEW = 2;
    //标记当前屏幕显示的界面
    private int currentView = MAIN_VIEW;
    //模拟数据
    private Scroller scroller;
    //向左滑动菜单隐藏的一定距离
    private int mTouchSlop;

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 初始化菜单和主界面的宽和高
        initView(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 测量菜单和主界面的宽和高
     */
    private void initView(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取菜单并且测量宽高
        View menuView = this.getChildAt(0);
        // menuView.getLayoutParams().width拿到布局参数 heightMeasureSpec屏幕高度
        menuView.measure(menuView.getLayoutParams().width, heightMeasureSpec);

        // 获取主界面并且测量宽高
        View mainView = this.getChildAt(1);
        mainView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 菜单
        View menuView = this.getChildAt(0);
        menuView.layout(-menuView.getMeasuredWidth(), 0, 0, b);
        // 主界面
        View mainView = this.getChildAt(1);
        mainView.layout(l, t, r, b);
    }

    /**
     * 事件分发机制，处理菜单向左滑动时，时间消费事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMostRecentX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int diff = moveX - mMostRecentX;
                if (Math.abs(diff) > mTouchSlop)
                    return true;
                // 认为是横向移动，消耗掉此事件
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMostRecentX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                // 1. 计算增量值 增量值 = x轴的最后一次偏移量 - 当前最新的x轴偏移量;
                int currentX = (int) event.getX();
                int delta = mMostRecentX - currentX;
                // 2. 根据增量值, 更新屏幕显示的位置 scrollBy(增量值, 0), 判断是否超出左右边界
                int scrollX = getScrollX() + delta; // 移动后的x轴偏移量
                if (scrollX < -this.getChildAt(0).getWidth()) {
                    // 超出左边界
                    scrollTo(-this.getChildAt(0).getWidth(), 0);
                } else if (scrollX > 0) {
                    // 超出右边界
                    scrollTo(0, 0);
                } else {
                    scrollBy(delta, 0);
                }

                // 3. x轴的最后一次偏移量 = 当前最新的x轴偏移量;
                mMostRecentX = currentX;

                break;
            case MotionEvent.ACTION_UP: // 手指抬起
                // 菜单的中心点
                int menuCenter = -this.getChildAt(0).getWidth() / 2;
                // 当前移动到的X轴坐标
                int _x = getScrollX();
                if (_x < menuCenter) {
                    // 切换到菜单界面
                    // scrollTo(-this.getChildAt(0).getWidth(), 0);
                    currentView = MENU_VIEW;
                } else {
                    // 切换到主界面
                    // scrollTo(0, 0);
                    currentView = MAIN_VIEW;
                }
                switchView();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void computeScroll() {
        // 更新当前的X轴偏移量
        if (scroller.computeScrollOffset()) { // 返回true代表正在模拟数据，false 已经停止模拟数据
            scrollTo(scroller.getCurrX(), 0); // 更新X轴的偏移量
            invalidate();
        }
    }

    /**
     * 菜单和主界面切换
     */
    private void switchView() {
        int startX = getScrollX();
        int dx = 0;
        if (currentView == MAIN_VIEW) {
            dx = 0 - startX;
        } else if (currentView == MENU_VIEW) {
            dx = -getChildAt(0).getWidth() - startX;
        }
        // 开始模拟数据
        scroller.startScroll(startX, 0, dx, 0, Math.abs(dx));
        invalidate();
    }

    /**
     * 判断菜单是否显示
     *
     * @return
     */
    public boolean isShowMenu() {
        return currentView == MENU_VIEW;
    }

    /**
     * 隐藏菜单
     */
    public void hideMenu() {
        currentView = MAIN_VIEW;
        switchView();
    }

    /**
     * 显示菜单
     */
    public void showMenu() {
        currentView = MENU_VIEW;
        switchView();
    }

}
