package com.yswcustomizeview.mActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mViewGroup.MyViewGroup;

public class MyViewGroupActivity extends AppCompatActivity {

    private MyViewGroup mViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        mViewGroup = (MyViewGroup) findViewById(R.id.mViewGroup);
        initUI();
    }

    private void initUI() {
        View top = getLayoutInflater().inflate(R.layout.top, null);
        View bottom = getLayoutInflater().inflate(R.layout.bottom, null);
        mViewGroup.addChilds(top, bottom);
    }
}
