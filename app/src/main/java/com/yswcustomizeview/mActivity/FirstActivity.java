package com.yswcustomizeview.mActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mProgressBar.SimpleProgressBar;
import com.yswcustomizeview.mWaveView.WaveView;

public class FirstActivity extends Activity implements View.OnClickListener {
    private int i;
    private SimpleProgressBar mProgress;
    private WaveView mWaveview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mProgress = (SimpleProgressBar) findViewById(R.id.mProgress);
        mWaveview = (WaveView) findViewById(R.id.mWaveview);
        Button mButton = (Button) findViewById(R.id.mButton);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mButton:
                change();
                break;
        }
    }

    private void change() {
        if (i == 0) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(25f);
            mWaveview.setPercent(25f);
            i++;
        } else if (i == 1) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(85f);
            mWaveview.setPercent(50f);
            i++;
        } else if (i == 2) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(75f);
            mWaveview.setPercent(75f);
            i++;
        } else if (i == 3) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(100f);
            mWaveview.setPercent(100f);
            i++;
        } else if (i == 4) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(0f);
            mWaveview.setPercent(0f);
            i++;
        } else if (i == 5) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(50.9f);
            mWaveview.setPercent(50.9f);
            i++;
        } else if (i == 6) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(30f);
            mWaveview.setPercent(30.5f);
            i++;
        } else if (i == 7) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(87.7f);
            mWaveview.setPercent(87.7f);
            i++;
        } else if (i == 8) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(2f);
            mWaveview.setPercent(2f);
            i++;
        } else if (i == 9) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(4f);
            mWaveview.setPercent(4f);
            i++;
        } else if (i == 10) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(6f);
            mWaveview.setPercent(6f);
            i++;
        } else if (i == 11) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(8f);
            mWaveview.setPercent(8f);
            i++;
        } else if (i == 12) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(10f);
            mWaveview.setPercent(10f);
            i++;
        } else if (i == 13) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(12f);
            mWaveview.setPercent(12f);
            i++;
        } else if (i == 14) {
            mProgress.setmProgressMax(100f);
            mProgress.setmProgress(14f);
            mWaveview.setPercent(14f);
            i = 0;
        }
    }
}
