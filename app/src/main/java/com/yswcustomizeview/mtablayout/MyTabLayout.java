package com.yswcustomizeview.mtablayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yswcustomizeview.mUtils.ScreenUtils;
import com.yswcustomizeview.mUtils.UtilsTools;

import java.util.ArrayList;

/**
 * Created by: Ysw on 2016/12/14.
 */

public class MyTabLayout extends View {
    private int mWidth;
    private int mHeight;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Context context;
    private ArrayList<String> mAddressList = new ArrayList<>();

    public MyTabLayout(Context context) {
        super(context);
        this.context = context;
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = ScreenUtils.getScreenWidth(context);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = UtilsTools.Dp2Px(context,40);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DrawText(canvas);
    }

    private void DrawText(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

    }
}
