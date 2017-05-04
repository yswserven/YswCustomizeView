package com.yswcustomizeview.mActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mViewGroup.PageBottom;
import com.yswcustomizeview.mViewGroup.PageTop;
import com.yswcustomizeview.mViewGroup.ScrollerLayout;

public class MyScrollerLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scroll_layout);
        ScrollerLayout mScrollerLayout = (ScrollerLayout) findViewById(R.id.mScrollerLayout);
        PageTop pageTop = new PageTop(this, getLayoutInflater().inflate(R.layout.top, null));
        PageBottom pageBottom = new PageBottom(this, getLayoutInflater().inflate(R.layout.bottom, null));
        mScrollerLayout.addChilds(pageTop, pageBottom);
    }
}
