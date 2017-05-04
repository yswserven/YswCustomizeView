package com.yswcustomizeview.mViewGroup;

import android.content.Context;
import android.view.View;

/**
 * Created by： Ysw on 2016/6/22.10:24.
 */
public class SlidingMenuPage implements SlidingMenuLayout.SlidingMenuLayoutPage {
    private View mLayout;
    private Context mContent;

    public SlidingMenuPage(Context context,View layout){
        this.mContent = context;
        this.mLayout = layout;
    }
    @Override
    public View getRootView() {
        return mLayout;
    }

    @Override
    public boolean isAtLeft() {
        return false;
    }

    @Override
    public boolean isAtMiddle() {
        return false;
    }

    @Override
    public boolean isAtRight() {
        return false;
    }
}
