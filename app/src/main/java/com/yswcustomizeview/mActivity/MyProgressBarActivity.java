package com.yswcustomizeview.mActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mProgressBar.RoundProgressBar;

public class MyProgressBarActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_start;
    private RoundProgressBar pb_round;
    private int mSecond = 60;
    private boolean isRunning = true;
    private Thread mTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress_bar);
        initUI();
    }

    private void initUI() {
        pb_round = (RoundProgressBar) findViewById(R.id.pb_round);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                startTiming();
                break;
            default:
                break;
        }
    }

    private void startTiming() {
        mTheme = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (mSecond > 0) {
                        mSecond--;
                        pb_round.setProgress((60 - mSecond) * 6f, "" + mSecond);
                    } else {
                        pb_round.setProgress((60 - mSecond) * 6f, "");
                        isRunning = false;
                        mSecond = 60;
                    }
                }
            }
        });
        mTheme.start();
    }
}
