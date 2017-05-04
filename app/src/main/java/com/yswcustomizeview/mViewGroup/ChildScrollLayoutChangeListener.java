package com.yswcustomizeview.mViewGroup;

public interface ChildScrollLayoutChangeListener {
    void doChange(int lastIndex, int currentIndex);
    void onReadyRrfresh();
    void onReady();
    void doRefresh();
    void overRefresh();
}
