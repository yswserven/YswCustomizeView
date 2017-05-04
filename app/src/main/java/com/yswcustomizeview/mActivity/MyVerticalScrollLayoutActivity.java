package com.yswcustomizeview.mActivity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mViewGroup.ChildScrollLayoutChangeListener;
import com.yswcustomizeview.mViewGroup.MyChildVerticalScrollLayout;
import com.yswcustomizeview.mViewGroup.MyParentVerticalScrollLayout;

public class MyVerticalScrollLayoutActivity extends Activity implements ChildScrollLayoutChangeListener {
    private MyParentVerticalScrollLayout mParentScrollLayoutView;
    private View image_refresh;
    private TextView tv_refresh;
    private MyChildVerticalScrollLayout mChildScrollLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vertical_scroll_layout);
        mParentScrollLayoutView = (MyParentVerticalScrollLayout) findViewById(R.id.mParentScrollLayoutView);
        mChildScrollLayout = (MyChildVerticalScrollLayout) findViewById(R.id.mChildScrollLayout);
        image_refresh = findViewById(R.id.image_refresh);
        tv_refresh = (TextView) findViewById(R.id.tv_refresh);
        mParentScrollLayoutView.setChild(mChildScrollLayout);
        mChildScrollLayout.addChangeListener(this);
    }

    @Override
    public void doChange(int lastIndex, int currentIndex) {

    }

    @Override
    public void onReadyRrfresh() {
        image_refresh.setBackgroundResource(R.drawable.one);
        tv_refresh.setText("下拉赚钱");
    }

    @Override
    public void onReady() {
        image_refresh.setBackgroundResource(R.drawable.one);
        tv_refresh.setText("释放赚钱");
    }

    @Override
    public void doRefresh() {
        tv_refresh.setText("正在赚钱");
        image_refresh.setBackgroundResource(R.drawable.refresh);
        AnimationDrawable drawable = (AnimationDrawable) image_refresh.getBackground();
        drawable.start();
    }

    @Override
    public void overRefresh() {
        image_refresh.setBackgroundResource(R.drawable.four);
        tv_refresh.setText("刷新成功");
    }

}