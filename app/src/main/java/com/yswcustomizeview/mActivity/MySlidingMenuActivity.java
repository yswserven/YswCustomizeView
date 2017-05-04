package com.yswcustomizeview.mActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mViewGroup.SlidingContentPage;
import com.yswcustomizeview.mViewGroup.SlidingMenuLayout;
import com.yswcustomizeview.mViewGroup.SlidingMenuPage;

public class MySlidingMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sliding_menu);
        SlidingMenuLayout mSlidingMenuLayout = (SlidingMenuLayout) findViewById(R.id.mSlidingMenuLayout);
        SlidingMenuPage menuPage = new SlidingMenuPage(this, getLayoutInflater().inflate(R.layout.menu, null));
        SlidingContentPage contentPage = new SlidingContentPage(this, getLayoutInflater().inflate(R.layout.contentpage, null));
        mSlidingMenuLayout.addChilds(menuPage, contentPage);
    }
}
