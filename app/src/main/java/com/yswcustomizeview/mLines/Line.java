package com.yswcustomizeview.mLines;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mUtils.UtilsTools;

/**
 * Created by： Ysw on 2016/4/28.16:15.
 */
public class Line extends View {
    private int mBackgroundColor = Color.parseColor("#E3067B");
    private int mColor = Color.parseColor("#2274BD");
    private int mWidth = UtilsTools.Dp2Px(getContext(), 350);
    private int mHeight = UtilsTools.Dp2Px(getContext(), 4);
    private float mPercent = 10f;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Line(Context context) {
        super(context);
    }

    public Line(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray Array = initAttrs(context, attrs);
        Array.recycle();
    }


    public Line(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray Array = initAttrs(context, attrs);
        Array.recycle();
    }

    private TypedArray initAttrs(Context context, AttributeSet attrs) {
        TypedArray Array = context.obtainStyledAttributes(attrs, R.styleable.Line);
        if (Array == null) return null;
        int count = Array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = Array.getIndex(i);
            switch (attr) {
                case R.styleable.Line_lineBackground:
                    mBackgroundColor = Array.getColor(attr, mBackgroundColor);
                    break;
                case R.styleable.Line_mlinecolor:
                    mColor = Array.getColor(attr, mColor);
                    break;
                default:
                    break;
            }
        }
        return Array;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DrawBackground(canvas);
        DrawLine(canvas);
    }

    private void DrawLine(Canvas canvas) {
        mPaint.reset();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, mPercent / 100f * mWidth, mHeight, mPaint);
    }

    private void DrawBackground(Canvas canvas) {
        mPaint.reset();
        mPaint.setColor(mBackgroundColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }

    public void setBackground(int color) {
        mBackgroundColor = color;
        postInvalidate();
    }

    public void setColor(int color) {
        mColor = color;
        postInvalidate();
    }

    public void setPersent(float percent) {
        mPercent = percent;
        postInvalidate();
    }
}
