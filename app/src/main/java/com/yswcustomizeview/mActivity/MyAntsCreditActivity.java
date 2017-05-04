package com.yswcustomizeview.mActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mAntscreditView.AntsCreditView;
import com.yswcustomizeview.mAntscreditView.RoundIndicatorView;

import java.util.Random;

public class MyAntsCreditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ants_credit);
        Button change = (Button) findViewById(R.id.change);
        final RoundIndicatorView mAntscreditView = (RoundIndicatorView) findViewById(R.id.mAntscreditView);
        final AntsCreditView mAntscreditView1 = (AntsCreditView) findViewById(R.id.mAntscreditView1);
        mAntscreditView.setCurrentNumAnim(499);
        mAntscreditView1.setYswCurrentNum(899);
        final Random random = new Random();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextInt = random.nextInt(500);
                mAntscreditView.setCurrentNumAnim(nextInt);
                int mNext = random.nextInt(900);
                mAntscreditView1.setYswCurrentNum(mNext);
            }
        });
    }
}
