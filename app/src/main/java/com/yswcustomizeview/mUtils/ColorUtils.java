package com.yswcustomizeview.mUtils;

import android.app.Activity;
import android.graphics.Color;

import com.yswcustomizeview.R;


public class ColorUtils {
    public static int[] getRefreshColor(Activity activity) {
        if (activity == null) return new int[]{Color.parseColor("#26c0ff"), Color.parseColor("#26c0ff"),
                Color.parseColor("#26c0ff"), Color.parseColor("#26c0ff")};
        int[] color = new int[]{activity.getResources().getColor(R.color.blue_background),
                activity.getResources().getColor(R.color.blue_background),
                activity.getResources().getColor(R.color.blue_background),
                activity.getResources().getColor(R.color.blue_background)};
        return color;
    }
}
