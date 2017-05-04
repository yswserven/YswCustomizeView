package com.yswcustomizeview.mProgressBar;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yswcustomizeview.R;


/**
 * Created by： Ysw on 2016/4/11.8:59.
 */
public class SimpleProgressBar extends View {

    // 控件的总体宽度 @author Ysw created at 2016/4/20 15:02
    private int mWidth = Dp2Px(getContext(), 350);
    // 控件的总体高度 @author Ysw created at 2016/4/20 15:02
    private int mHeight = Dp2Px(getContext(), 20);
    // 最大值 @author Ysw created at 2016/4/9 23:42
    private float mProgressMax = 100f;
    // 当前值 @author Ysw created at 2016/4/9 23:42
    private float mProgress = 0;
    // 进度条背景颜色 @author Ysw created at 2016/4/9 23:43lO
    private int mProgressBackground = Color.parseColor("#E0007F");
    // 进度条颜色 @author Ysw created at 2016/4/9 23:44
    private int mProgressColor = Color.parseColor("#007FE0");
    // 定义进度条背景画笔 @author Ysw created at 2016/4/10 0:14
    private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 定义进度条画笔 @author Ysw created at 2016/4/10 0:15
    private Paint mProgressPiant = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 定义进度条画布画笔 @author Ysw created at 2016/4/10 0:59
    private Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 定义进度条画笔 @author Ysw created at 2016/4/10 0:15
    private Paint mTextPiant = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 定义进度条画布 @author Ysw created at 2016/4/10 0:18
    private Rect mProgressRect = new Rect();
    // 路径 @author Ysw created at 2016/4/10 18:30
    private Path mPath = new Path();
    // 计算文字位置所需要用到的三个画布 @author Ysw created at 2016/4/11 10:41
    private Rect mRect;
    private Rect mRects;
    private Rect mRectss;

    public SimpleProgressBar(Context context) {
        super(context);
    }

    public SimpleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = initArray(context, attrs);
        a.recycle();
    }

    public SimpleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = initArray(context, attrs);
        a.recycle();
    }

    // 初始化自定义属性 @author Ysw created at 2016/4/11 10:38
    private TypedArray initArray(Context context, AttributeSet attrs) {
        TypedArray Array = context.obtainStyledAttributes(attrs, R.styleable.SimpleProgressBar);
        int count = Array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = Array.getIndex(i);
            switch (attr) {
                case R.styleable.SimpleProgressBar_ProgressMax:
                    mProgressMax = Array.getInt(attr, (int) mProgressMax);
                    break;
                case R.styleable.SimpleProgressBar_Progress:
                    mProgress = Array.getInt(attr, (int) mProgress);
                    break;
                case R.styleable.SimpleProgressBar_ProgressBackground:
                    mProgressBackground = Array.getColor(attr, mProgressBackground);
                    break;
                case R.styleable.SimpleProgressBar_ProgressColor:
                    mProgressColor = Array.getColor(attr, mProgressColor);
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
        mRect = new Rect();
        mRects = new Rect();
        mRectss = new Rect();
        mTextPiant.setTextSize(17);
        mTextPiant.setColor(Color.WHITE);
        mTextPiant.getTextBounds("0%", 0, 2, mRect);
        mTextPiant.getTextBounds("10%", 0, 3, mRects);
        mTextPiant.getTextBounds("100%", 0, 4, mRectss);
        final int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        final int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        mBackgroundPaint.setColor(mProgressBackground);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(25, 45, width - 25, height, mBackgroundPaint);

        mProgressPiant.setColor(mProgressColor);
        mProgressPiant.setStyle(Paint.Style.FILL);
        canvas.drawRect(25, 45, mProgress / mProgressMax * (width - 25), height, mProgressPiant);

        float a = mProgress / mProgressMax * (width - 50);

        float b = mProgress / mProgressMax * (width - 25);
        mPath.reset();
        if (a == 0) {
            mPath.moveTo(0, 0);//a
            mPath.lineTo(50, 0);
            mPath.lineTo(50, 30);

            mPath.lineTo(35, 30);
            mPath.lineTo(25, 45);
            mPath.lineTo(15, 30);

            mPath.lineTo(0, 30);//a
            mPath.lineTo(0, 0);//a

        } else {

            mPath.moveTo(b - 25, 0);//a
            mPath.lineTo(b + 25, 0);
            mPath.lineTo(25 + b, 30);

            mPath.lineTo(10 + b, 30);
            mPath.lineTo(b, 45);
            mPath.lineTo(b - 10, 30);

            mPath.lineTo(b - 25, 30);//a
            mPath.lineTo(b - 25, 0);//a

        }

        canvas.drawPath(mPath, mProgressPiant);
        if (a == 0) {
            canvas.drawText(0 + "%", 25 - mRect.width() / 2, 30 - mRect.height() / 2, mTextPiant);
        } else if (mProgress == 100f) {
            canvas.drawText(100 + "%", b - (mRectss.width() / 2), 30 - mRectss.height() / 2, mTextPiant);
        } else {
            canvas.drawText((int) (mProgress * 100 / mProgressMax) + "%", b - mRects.width() / 2, 30 - mRects.height() / 2, mTextPiant);
        }
    }

    public void setmProgress(final float progress) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f);
        anim.setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mProgress = progress * value;
                invalidate();
            }
        });
        anim.start();
    }

    public void setmProgressMax(final float max) {
        mProgressMax = max;
        invalidate();
    }

    /**
     * 单位转换 sp dp 与 px 之间的转换 @author Ysw created at 2016/4/12 17:57
     */

    // 将 dp 转换成为 px @author Ysw created at 2016/4/12 11:49
    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    // 将 px 转换为dp @author Ysw created at 2016/4/12 11:49
    public int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    // 将 px 转换成 sp @author Ysw created at 2016/4/12 17:54
    public int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // 将 sp 转换成 px @author Ysw created at 2016/4/12 17:55
    public int Sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    // 转换文字大小 @author Ysw created at 2016/4/12 18:33
    public int getDefValue(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, i, getResources().getDisplayMetrics());
    }
}
