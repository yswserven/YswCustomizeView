package com.yswcustomizeview.mLineChart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.yswcustomizeview.R;

/**
 * Created by： Ysw on 2016/4/18.11:00.
 */
public class LineChart extends View {
    // 控件的总体宽度 @author Ysw created at 2016/4/20 15:10
    private int mWidth = Dp2Px(getContext(), 350);
    // 控件的总体高度 @author Ysw created at 2016/4/20 15:10
    private int mHeight;
    // 图表标题描述 @author Ysw created at 2016/4/18 13:59
    private String mTitle = "我的图表";
    // 图表标题文字大小 @author Ysw created at 2016/4/18 13:59
    private int mTitleSize = Sp2px(getContext(), 14);
    // 图表标题文字颜色 @author Ysw created at 2016/4/18 14:01
    private int mTitleColor = Color.parseColor("#343434");
    // 标题栏的高度 @author Ysw created at 2016/4/18 18:23
    private int mTitleHeight = Dp2Px(getContext(), 15);
    // 标题栏距离下面的距离 @author Ysw created at 2016/4/18 20:30
    private int mTitleMargBotton = Dp2Px(getContext(), 5);
    // 标题栏距离左边的距离 @author Ysw created at 2016/4/18 20:36
    private int mTitleMargLfet = Dp2Px(getContext(), 0);
    // 绘制曲线区域的高度 @author Ysw created at 2016/4/20 15:16
    private int mDrawHeight = Dp2Px(getContext(), 300);
    // Y 轴文字颜色 @author Ysw created at 2016/4/18 14:02
    private int mYlineTextColor = Color.parseColor("#343434");
    // Y 轴文字大小 @author Ysw created at 2016/4/18 14:03
    private int mYlineTextSize = Sp2px(getContext(), 10);
    // Y 轴文字栏宽度 @author Ysw created at 2016/4/18 21:37
    private int mYtextLineWidth = Dp2Px(getContext(), 20);
    // X 轴文字颜色 @author Ysw created at 2016/4/18 14:03
    private int mXlineTextColor = Color.parseColor("#343434");
    // X 轴文字大小 @author Ysw created at 2016/4/18 14:04
    private int mXlineTextSize = Sp2px(getContext(), 10);
    // X 轴文字栏高度 @author Ysw created at 2016/4/18 21:35
    private int mXtextLineHeight = Dp2Px(getContext(), 20);
    // 曲线的颜色 @author Ysw created at 2016/4/18 14:05
    private int mLineColor = Color.parseColor("#343434");
    // 曲线的宽度 @author Ysw created at 2016/4/18 14:05
    private int mLineStorkWidth = Sp2px(getContext(), 2);
    // 曲线是否圆滑 @author Ysw created at 2016/4/18 14:06
    private boolean mSleek = true;
    // 点的半径大小 @author Ysw created at 2016/4/18 14:07
    private int mPointSize = Sp2px(getContext(), 3);
    // 点的颜色 @author Ysw created at 2016/4/18 14:08
    private int mPointColor = Color.parseColor("#2CE0A6");
    // 点上文字的颜色 @author Ysw created at 2016/4/18 14:10
    private int mPointTextColor = Color.parseColor("#2CE0A6");
    // 点上文字的大小 @author Ysw created at 2016/4/18 14:10
    private int mPointTextSize = Sp2px(getContext(), 10);
    // X 轴即横轴线的宽度 @author Ysw created at 2016/4/18 14:11
    private int mXlineStorkWidth = 1;
    // X 轴即横轴线的颜色 @author Ysw created at 2016/4/18 14:12
    private int mXlineColor = Color.parseColor("#343434");
    // Y 轴即纵轴线的宽度 @author Ysw created at 2016/4/18 14:14
    private int mYlineStorkWidth = 1;
    // Y 轴即纵轴先的颜色 @author Ysw created at 2016/4/18 14:15
    private int mYlineColor = Color.parseColor("#343434");
    // 曲线以下是否渐变绘制颜色 @author Ysw created at 2016/4/18 14:30
    private boolean mGradual = true;
    // X 轴显示的字符串一维数组 @author Ysw created at 2016/4/18 15:32
    private String mXname[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    // Y 轴显示的值一维数组 @author Ysw created at 2016/4/19 11:41
    private String mYname[] = {"2.0", "1.8", "1.6", "1.4", "1.2", "1.0", "0.8", "0.6", "0.4", "0.2", "0.0"};
    // 曲线图上点的集合二维数组 @author Ysw created at 2016/4/18 15:34
    private float mPoint[][] = {{0, 1.4f}, {1, 2.3f}, {2, 1.9f}, {3, 1.7f}, {4, 2.1f}, {5, 1.2f}, {6, 0.9f}};
    // 画笔 @author Ysw created at 2016/4/18 15:51
    private Paint mPaint;
    // 渐变路劲 @author Ysw created at 2016/4/19 15:03
    private Path mPath;

    public LineChart(Context context) {
        super(context, null);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray Array = initArrts(context, attrs);
        Array.recycle();
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray Array = initArrts(context, attrs);
        Array.recycle();
    }

    // 初始化自定义属性 @author Ysw created at 2016/4/18 15:24
    private TypedArray initArrts(Context context, AttributeSet attrs) {
        TypedArray Array = context.obtainStyledAttributes(attrs, R.styleable.LineChart);
        if (Array == null) return null;
        int count = Array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int arrt = Array.getIndex(i);
            switch (arrt) {
                case R.styleable.LineChart_titletext:
                    mTitle = Array.getString(arrt) == null ? "" : Array.getString(arrt);
                    break;
                case R.styleable.LineChart_titletextsize:
                    mTitleSize = Array.getDimensionPixelSize(arrt, mTitleSize);
                    break;
                case R.styleable.LineChart_titletextcolor:
                    mTitleColor = Array.getColor(arrt, mTitleColor);
                    break;
                case R.styleable.LineChart_titleheight:
                    mTitleHeight = Array.getDimensionPixelOffset(arrt, mTitleHeight);
                    break;
                case R.styleable.LineChart_titlemargbotton:
                    mTitleMargBotton = Array.getDimensionPixelOffset(arrt, mTitleMargBotton);
                    break;
                case R.styleable.LineChart_titlemargleft:
                    mTitleMargLfet = Array.getDimensionPixelOffset(arrt, mTitleMargLfet);
                    break;
                case R.styleable.LineChart_drawheight:
                    mDrawHeight = Array.getDimensionPixelOffset(arrt, mDrawHeight);
                    break;
                case R.styleable.LineChart_Ytextcolor:
                    mYlineTextColor = Array.getColor(arrt, mYlineTextColor);
                    break;
                case R.styleable.LineChart_Ytextsize:
                    mYlineTextSize = Array.getDimensionPixelSize(arrt, mYlineTextSize);
                    break;
                case R.styleable.LineChart_Ytextlinewidth:
                    mYtextLineWidth = Array.getDimensionPixelOffset(arrt, mYtextLineWidth);
                    break;
                case R.styleable.LineChart_Xtextcolor:
                    mXlineTextColor = Array.getColor(arrt, mXlineTextColor);
                    break;
                case R.styleable.LineChart_Xtextsize:
                    mXlineTextSize = Array.getDimensionPixelSize(arrt, mXlineTextSize);
                    break;
                case R.styleable.LineChart_linecolor:
                    mLineColor = Array.getColor(arrt, mLineColor);
                    break;
                case R.styleable.LineChart_linestorkwidth:
                    mLineStorkWidth = Array.getDimensionPixelOffset(arrt, mLineStorkWidth);
                    break;
                case R.styleable.LineChart_sleek:
                    mSleek = Array.getBoolean(arrt, mSleek);
                    break;
                case R.styleable.LineChart_pointsize:
                    mPointSize = Array.getDimensionPixelSize(arrt, mPointSize);
                    break;
                case R.styleable.LineChart_pointcolor:
                    mPointColor = Array.getColor(arrt, mPointColor);
                    break;
                case R.styleable.LineChart_pointtextcolor:
                    mPointTextColor = Array.getColor(arrt, mPointTextColor);
                    break;
                case R.styleable.LineChart_pointtextsize:
                    mPointTextSize = Array.getDimensionPixelSize(arrt, mPointTextSize);
                    break;
                case R.styleable.LineChart_Xlinestorkwidth:
                    mXlineStorkWidth = Array.getDimensionPixelSize(arrt, mXlineStorkWidth);
                    break;
                case R.styleable.LineChart_Xlinecolor:
                    mXlineColor = Array.getColor(arrt, mXlineColor);
                    break;
                case R.styleable.LineChart_Ylinestorkwidth:
                    mYlineStorkWidth = Array.getDimensionPixelOffset(arrt, mYlineStorkWidth);
                    break;
                case R.styleable.LineChart_Ylinecolor:
                    mYlineColor = Array.getColor(arrt, mYlineColor);
                    break;
                default:
                    break;
            }
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
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
        } else {
            mHeight = mTitleHeight + mTitleMargBotton + mDrawHeight;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        // 绘制表头 @author Ysw created at 2016/4/18 15:50
        DrawTitle(canvas);
        // 绘制 Y 轴所有的竖线 @author Ysw created at 2016/4/18 15:49
        DrawYlines(canvas, width, height);
        // 绘制 Y 轴文字 @author Ysw created at 2016/4/18 21:28
        DrawYlineText(canvas, width, height);
        // 绘制 X 轴所有的横线 @author Ysw created at 2016/4/18 20:47
        DrawXlines(canvas, width, height);
        // 绘制 X 轴文字 @author Ysw created at 2016/4/18 21:28
        DrawXlineText(canvas, width, height);
        // 绘制三阶贝塞尔曲线 @author Ysw created at 2016/4/19 14:57
        DrawCurve(canvas, width, height);
    }

    // 绘制表头 @author Ysw created at 2016/4/18 15:50
    private void DrawTitle(Canvas canvas) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTitleColor);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTitleSize);
        float startX = mTitleMargLfet + mYtextLineWidth;
        float startY = mTitleHeight / 2 - (mPaint.ascent() + mPaint.descent()) / 2;
        canvas.drawText(mTitle, startX, startY, mPaint);
    }

    // 绘制 Y 轴所有的线 @author Ysw created at 2016/4/18 15:49
    private void DrawYlines(Canvas canvas, float width, float height) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mYlineStorkWidth);
        mPaint.setColor(mYlineColor);
        mPaint.setAntiAlias(true);
        float startY = mTitleHeight + mTitleMargBotton;
        float columnWidth = width / mXname.length;
        for (int i = 0; i < mXname.length; i++) {
            float startX = columnWidth * i + mYtextLineWidth;
            canvas.drawLine(startX, startY, startX, height - mXtextLineHeight, mPaint);
        }
    }

    // 绘制 Y 轴文字 @author Ysw created at 2016/4/18 21:29
    private void DrawYlineText(Canvas canvas, float width, float height) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mYlineTextSize);
        mPaint.setColor(mYlineTextColor);
        float rowHeight = (height - mTitleHeight - mTitleMargBotton - mXtextLineHeight) / mYname.length;
        for (int i = 0; i < mYname.length; i++) {
            String text = mYname[i];
            float startX = mYtextLineWidth / 2 - mPaint.measureText(text) / 2;
            float startY = mTitleHeight + mTitleMargBotton - (mPaint.ascent() + mPaint.descent()) / 2 + rowHeight * (i + 1);
            canvas.drawText(text, startX, startY, mPaint);
        }
    }

    // 绘制 X 轴所有的横线 @author Ysw created at 2016/4/18 20:48
    private void DrawXlines(Canvas canvas, float width, float height) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mXlineStorkWidth);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mXlineColor);
        float startX = mYtextLineWidth;
        float rowHeight = (height - mTitleHeight - mTitleMargBotton - mXtextLineHeight) / mYname.length;
        for (int i = 0; i < mYname.length + 1; i++) {
            float startY = height - mXtextLineHeight - rowHeight * i;
            canvas.drawLine(startX, startY, width, startY, mPaint);
        }
    }

    // 绘制 X 轴文字 @author Ysw created at 2016/4/18 21:32
    private void DrawXlineText(Canvas canvas, float width, float height) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mXlineTextSize);
        mPaint.setColor(mXlineTextColor);
        for (int i = 0; i < mXname.length; i++) {
            String text = mXname[i];
            canvas.drawText(text, mYtextLineWidth - mPaint.measureText(text) / 2 + width / mXname.length * i,
                    height - (mXtextLineHeight / 2 + mPaint.ascent() + mPaint.descent()), mPaint);
        }
    }


    // 绘制三阶贝塞尔曲线 @author Ysw created at 2016/4/19 14:59
    private void DrawCurve(Canvas canvas, float width, float height) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        float columnWidth = width / mXname.length;
        float rowHeight = (height - mTitleHeight - mTitleMargBotton - mXtextLineHeight) / 2.5f;
        for (int i = 0; i < mPoint.length - 1; i++) {
            if (i == 0) {
                mPath.moveTo(mYtextLineWidth, height - mPoint[i][1] * rowHeight - mXtextLineHeight);
            }
            mPath.cubicTo(columnWidth * (i + 1f / 2f) + mYtextLineWidth, height - mPoint[i][1] * rowHeight - mXtextLineHeight,
                    columnWidth * (i + 1f / 2f) + mYtextLineWidth, height - mPoint[i + 1][1] * rowHeight - mXtextLineHeight,
                    columnWidth * (i + 1) + mYtextLineWidth, height - mPoint[i + 1][1] * rowHeight - mXtextLineHeight);
        }
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 图表数据的设置 @author Ysw created at 2016/4/18 15:36
     */
    // X 轴显示字符串数组的设置 @author Ysw created at 2016/4/18 15:36
    public void setmXname(String name[]) {
        if (name == null ? false : true) {
            mXname = name;
            postInvalidate();
        }
    }

    // 曲线上点的集合二维数组的设置 @author Ysw created at 2016/4/18 15:39
    public void setmData(float point[][]) {
        if (point == null ? false : true) {
            mPoint = point;
            postInvalidate();
        }
    }


    /**
     * 单位转换 sp dp 与 px 之间的转换 @author Ysw created at 2016/4/15 11:35
     */

    // 将 dp 转换成为 px @author Ysw created at 2016/4/12 11:49
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    // 将 px 转换为dp @author Ysw created at 2016/4/12 11:49
    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    // 将 px 转换成 sp @author Ysw created at 2016/4/12 17:54
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // 将 sp 转换成 px @author Ysw created at 2016/4/12 17:55
    public static int Sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
