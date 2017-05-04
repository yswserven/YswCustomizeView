package com.yswcustomizeview.mViewGroupDemo;

import android.content.Context;
import android.view.View;
import android.widget.ScrollView;

import com.yswcustomizeview.R;

public class McoyProductContentPage implements McoySnapPageLayout.McoySnapPage {

    private Context context;

    private View rootView = null;
    private ScrollView mcoyScrollView = null;

    public McoyProductContentPage(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        mcoyScrollView = (ScrollView) this.rootView.findViewById(R.id.mcoyScrollView);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public boolean isAtTop() {
        int scrollY = mcoyScrollView.getScrollY();
        if (scrollY == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAtBottom() {
        return true;
    }
}
