package com.yswcustomizeview.mPieChart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yswcustomizeview.mUtils.UtilsTools;

/**
 * Created by： Ysw on 2016/5/10.14:45.
 */
public class PieChart extends View {
    private int mWidth = UtilsTools.Dp2Px(getContext(), 100);
    private int mHeight = UtilsTools.Dp2Px(getContext(), 100);
    private float mArcwidth = UtilsTools.Dp2Px(getContext(), 20);
    private float mData[] = {120f, 140f, 160f, 180f};
    private float mStartAngle = 0f;
    private float mEndAngle = 0f;
    private int mTextSize = UtilsTools.Sp2px(getContext(), 16);
    private int mPieColors[] = {Color.parseColor("#8BE9EB"), Color.parseColor("#FFEC69"), Color.parseColor("#868394"), Color.parseColor("#FF574A")};
    private String mPieName = "总资产(元)";
    private float mTatolMoney;

    private int mTextColor = Color.parseColor("#06000F");
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public PieChart(Context context) {
        super(context);
        mTatolMoney = mData[0] + mData[1] + mData[2] + mData[3];
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTatolMoney = mData[0] + mData[1] + mData[2] + mData[3];
    }

    public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTatolMoney = mData[0] + mData[1] + mData[2] + mData[3];
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
        mWidth = Math.max(mWidth, mHeight);
        mHeight = Math.max(mWidth, mHeight);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DrawArc(canvas);
        DrawText(canvas);
    }

    private void DrawArc(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mArcwidth);
        for (int i = 0; i < mPieColors.length; i++) {
            mPaint.setColor(mPieColors[i]);
            float percent = mData[i] / mTatolMoney;
            mEndAngle = percent * 360f;
            canvas.drawArc(new RectF(0 + mArcwidth / 2, 0 + mArcwidth / 2, mWidth - mArcwidth / 2, mHeight - mArcwidth / 2),
                    mStartAngle, mEndAngle, false, mPaint);
            mStartAngle = mStartAngle + mEndAngle;
        }
    }

    private void DrawText(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        Rect one = new Rect();
        mPaint.getTextBounds(mPieName, 0, mPieName.length(), one);
        canvas.drawText(mPieName, mWidth / 2 - one.width() / 2, mHeight / 2 - one.height() / 2, mPaint);

        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        String text = mTatolMoney + "";
        Rect two = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), two);
        canvas.drawText(text, mWidth / 2 - two.width() / 2, mHeight / 2 + 2 * two.height() / 3, mPaint);
    }

    public void setArcColor(int color[]) {
        this.mPieColors = color;
        postInvalidate();
    }

    public void setData(float data[]) {
        this.mData = data;
        for (int i = 0; i < (data == null ? 0 : data.length); i++) {
            mTatolMoney += data[i];
        }
        postInvalidate();
    }

    public void setTotalMoney(float money) {
        this.mTatolMoney = money;
        postInvalidate();
    }

    public void setPieName(String name) {
        this.mPieName = name;
        postInvalidate();
    }

    public void setTextSize(int size) {
        this.mTextSize = UtilsTools.Sp2px(getContext(), size);
        postInvalidate();
    }

    public void setTextColor(int color) {
        this.mTextColor = color;
        postInvalidate();
    }

    public void setStartAngle(float startAngle) {
        this.mStartAngle = startAngle;
        postInvalidate();
    }


}
