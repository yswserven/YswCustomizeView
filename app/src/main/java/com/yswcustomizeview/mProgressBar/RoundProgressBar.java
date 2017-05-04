package com.yswcustomizeview.mProgressBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yswcustomizeview.mUtils.UtilsTools;

/**
 * Created by: Ysw on 2017/3/9.
 */
public class RoundProgressBar extends View {
    public static final String TAG = "RoundProgressBar";
    private int mOutColor = Color.parseColor("#0E72E2");
    private int mColor = Color.parseColor("#FFFFFF");
    private int mTextColor = Color.parseColor("#323232");
    private int mWidth;
    private int mHeight;
    private String mText = "" + 60;
    private Context mContext;
    private float mRadius;
    private int mPaintStroke = 5;
    private int mStroke = mPaintStroke + 2;
    private int mTextSize;
    private float mAngle;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RoundProgressBar(Context context) {
        super(context);
        mContext = context;
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
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
            mWidth = UtilsTools.Dp2Px(mContext, 40);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = UtilsTools.Dp2Px(mContext, 40);
        }
        mRadius = Math.min(mWidth / 2f - mPaintStroke, mHeight / 2f - mPaintStroke);
        mTextSize = UtilsTools.Dp2Px(mContext, 18);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawText(canvas);
        drawArc(canvas);
    }

    private void drawArc(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStroke + 4);
        mPaint.setDither(true);
        mPaint.setColor(mColor);
        canvas.drawArc(mStroke, mStroke, mWidth - mStroke, mHeight - mStroke, -90f, mAngle, false, mPaint);
    }

    private void drawText(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(mText, mWidth / 2f - mPaint.measureText(mText) / 2, mHeight / 2 - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mPaintStroke);
        mPaint.setDither(true);
        mPaint.setColor(mOutColor);
        canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius, mPaint);
    }

    public void setProgress(float angle, String text) {
        this.mAngle = angle;
        this.mText = text;
        postInvalidate();
    }
}
