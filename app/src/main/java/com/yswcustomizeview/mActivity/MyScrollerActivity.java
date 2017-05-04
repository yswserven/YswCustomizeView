package com.yswcustomizeview.mActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mUtils.ScreenUtils;
import com.yswcustomizeview.mScrollView.MyScrollView;

public class MyScrollerActivity extends AppCompatActivity implements MyScrollView.OnScrollListener {

    private MyScrollView mScrollView;
    private RelativeLayout rl_more;
    private RelativeLayout rl_title;
    private RelativeLayout rl_bottom;
    private boolean isAtTop = true;
    private boolean isAtDown;
    private int mTitleHeight;
    private int mScreenHeight;
    private int mTop;
    private int mBottomHeight;
    private int one;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        mScrollView = (MyScrollView) findViewById(R.id.mScrollView);
        rl_more = (RelativeLayout) findViewById(R.id.rl_more);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        mScrollView.setOnScrollListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mScreenHeight = ScreenUtils.getScreenHeight(this);
        Log.e("Ysw", "mScreenHeight = " + mScreenHeight);
        mTitleHeight = rl_title.getMeasuredHeight();
        Log.e("Ysw", "mTitleHeight = " + mTitleHeight);
        mBottomHeight = rl_bottom.getMeasuredHeight();
        Log.e("Ysw", "mBottomHeight = " + mBottomHeight);
        mTop = rl_more.getTop();
        mHeight = rl_more.getMeasuredHeight();
        Log.e("Ysw", "mTop = " + mTop);
        one = mTop - (mScreenHeight - mTitleHeight - mBottomHeight - 50) + mHeight;
        Log.e("Ysw", "one = " + one);
    }

    @Override
    public void onScroll(int scrollY) {
        Log.e("Ysw", "scrollY = " + scrollY);
        Log.e("Ysw", "isAtTop = " + isAtTop);
        if (isAtTop) {
            if (scrollY > one && scrollY < (one + 300)) {
                mScrollView.smoothScrollTo(0, one);
            } else if (scrollY > (one + 300)) {
                mScrollView.smoothScrollTo(0, mTop + mHeight);
                isAtTop = false;
                isAtDown = true;
                return;
            }
        }
        if (isAtDown) {
            if (scrollY < (mTop + mHeight)) {
                mScrollView.smoothScrollTo(0, 0);
                isAtTop = true;
                isAtDown = false;
                return;
            }
        }
    }
}
