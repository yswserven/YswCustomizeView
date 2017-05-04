package com.yswcustomizeview.mActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mScrollDemo.MultiViewGroup;

/**
 * @author http://http://blog.csdn.net/qinjuning
 */
public class MultiScreenActivity extends Activity {
    private MultiViewGroup mulTiViewGroup;

    //    public static int screenWidth;  // 屏幕宽度
    //    public static int scrrenHeight;  //屏幕高度
    private int curscreen = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获得屏幕分辨率大小
        // DisplayMetrics metric = new DisplayMetrics();
        // getWindowManager().getDefaultDisplay().getMetrics(metric);
        // screenWidth = metric.widthPixels;
        // scrrenHeight = metric.heightPixels;
        setContentView(R.layout.multiview);
        mulTiViewGroup = (MultiViewGroup) findViewById(R.id.mymultiViewGroup);
        View top = getLayoutInflater().inflate(R.layout.top, null);
        View bottom = getLayoutInflater().inflate(R.layout.bottom, null);
        mulTiViewGroup.addChilds(top, bottom);
    }
}
