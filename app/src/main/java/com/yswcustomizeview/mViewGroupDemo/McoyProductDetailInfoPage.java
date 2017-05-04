package com.yswcustomizeview.mViewGroupDemo;

import android.content.Context;
import android.view.View;
import android.widget.ScrollView;

import com.yswcustomizeview.R;


public class McoyProductDetailInfoPage implements McoySnapPageLayout.McoySnapPage {

    private Context context;
    private View rootView = null;
    private ScrollView mcoyScrollView = null;

    public McoyProductDetailInfoPage(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        mcoyScrollView = (ScrollView) this.rootView.findViewById(R.id.product_scrollview);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public boolean isAtTop() {
        return true;
    }

    @Override
    public boolean isAtBottom() {
        int scrollY = mcoyScrollView.getScrollY();
        int height = mcoyScrollView.getHeight();
        int scrollViewMeasuredHeight = mcoyScrollView.getChildAt(0).getMeasuredHeight();
        if ((scrollY + height) >= scrollViewMeasuredHeight) {
            return true;
        }
        return false;
    }
}
