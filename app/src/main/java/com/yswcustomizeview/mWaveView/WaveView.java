package com.yswcustomizeview.mWaveView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yswcustomizeview.R;

import java.text.DecimalFormat;

/**
 * Created by： Ysw on 2016/4/15.10:57.
 */
public class WaveView extends View {
    // 控件的总体宽度 @author Ysw created at 2016/4/20 15:02
    private int mWidth;
    // 控件的总体高度 @author Ysw created at 2016/4/20 15:02
    private int mHeight;
    //  总体背景颜色 @author Ysw created at 2016/4/15 11:15 
    private int waveViewBackground = Color.parseColor("#ffffff");
    // 水波纹颜色 @author Ysw created at 2016/4/15 11:15 
    private int waveViewColor = Color.parseColor("#0000FF");
    // 整体圆形的半径 @author Ysw created at 2016/4/15 11:16 
    private int waveViewRadius = Dp2Px(getContext(), 30);
    // 水波纹占的百分比 @author Ysw created at 2016/4/15 11:17
    private float waveViewPercent = 30f;
    // 水波纹的最大值 @author Ysw created at 2016/4/16 16:02
    private float waveViewMax = 100f;
    // 水波纹外围描边的宽度 @author Ysw created at 2016/4/15 11:18 
    private int waveViewStrokeWidth = Dp2Px(getContext(), 2);
    // 水波纹外围描边的颜色 @author Ysw created at 2016/4/15 11:19 
    private int waveViewStrokeColor = Color.parseColor("#DADADA");
    // 文字的颜色 @author Ysw created at 2016/4/15 11:20
    private int waveViewTextColor = Color.parseColor("#343434");
    // 文字的大小 @author Ysw created at 2016/4/15 11:20
    private int waveViewTextsize = Sp2px(getContext(), 14);
    // 水波纹的透明度 @author Ysw created at 2016/4/15 11:48
    private int waveViewAlpha = 255;
    // 画笔 @author Ysw created at 2016/4/15 11:45
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 水波纹的路劲 @author Ysw created at 2016/4/15 13:39
    private Path mPath = new Path();
    // 覆盖模型 @author Ysw created at 2016/4/15 15:00
    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    // 用来控制坐标前进的参数，一个波长向前移动 @author Ysw created at 2016/4/18 10:16
    private int x;
    // 用来控制一个波长结束 @author Ysw created at 2016/4/18 11:03
    boolean isLeft;
    // 用来控制说波纹波动的幅度,默认为1个像素 @author Ysw created at 2016/4/18 11:06
    private int xDegree = 1;
    // 用来控制水波纹Y轴的波动幅度，默认为30个像素 @author Ysw created at 2016/4/18 11:09
    private int yDegree = 30;
    // 用来控制水波纹重绘的时间，默认为20毫秒 @author Ysw created at 2016/4/18 11:12
    private int redrawTime = 100;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = initArrts(context, attrs);
        array.recycle();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = initArrts(context, attrs);
        array.recycle();
    }

    private TypedArray initArrts(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int arrt = array.getIndex(i);
            switch (arrt) {
                case R.styleable.WaveView_wavebackground:
                    waveViewBackground = array.getColor(arrt, waveViewBackground);
                    break;
                case R.styleable.WaveView_waveradius:
                    waveViewRadius = array.getDimensionPixelOffset(arrt, waveViewRadius);
                    break;
                case R.styleable.WaveView_wavecolor:
                    waveViewColor = array.getColor(arrt, waveViewColor);
                    break;
                case R.styleable.WaveView_wavepercent:
                    waveViewPercent = array.getFloat(arrt, (float) waveViewPercent);
                    break;
                case R.styleable.WaveView_wavemax:
                    waveViewMax = array.getFloat(arrt, waveViewMax);
                    break;
                case R.styleable.WaveView_wavestrokewidth:
                    waveViewStrokeWidth = array.getDimensionPixelOffset(arrt, waveViewStrokeWidth);
                    break;
                case R.styleable.WaveView_wavestorkecolor:
                    waveViewStrokeColor = array.getColor(arrt, waveViewStrokeColor);
                    break;
                case R.styleable.WaveView_wavetextsize:
                    waveViewTextsize = array.getDimensionPixelOffset(arrt, waveViewTextsize);
                    break;
                case R.styleable.WaveView_wavetextcolor:
                    waveViewTextColor = array.getColor(arrt, waveViewTextColor);
                    break;
                case R.styleable.WaveView_waveviewalpha:
                    waveViewAlpha = array.getInteger(arrt, waveViewAlpha);
                    break;
                default:
                    break;
            }
        }
        return array;
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
            mWidth = waveViewRadius * 2;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = waveViewRadius * 2;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int Width = getWidth();
        int Height = getHeight();
        // 绘制最大的圆 @author Ysw created at 2016/4/15 11:43
        DrawCircle(canvas, Width, Height);
        // 绘制圆的描边 @author Ysw created at 2016/4/15 12:00
        DrawCicleStorke(canvas, Width, Height);
        // 绘制水波纹 @author Ysw created at 2016/4/15 13:38
        DrawWave(canvas, Width, Height);
        // 绘制百分比文字 @author Ysw created at 2016/4/16 16:22
        DrawText(canvas, Width, Height);
    }

    // 绘制最外层的背景，可绘制可不绘制 @author Ysw created at 2016/4/16 16:26
    private void DrawCircle(Canvas canvas, int Width, int Height) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(waveViewBackground);
        mPaint.setAlpha(0);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(Width / 2, Height / 2, waveViewRadius, mPaint);
    }

    // 绘制描边 @author Ysw created at 2016/4/16 16:25
    private void DrawCicleStorke(Canvas canvas, int Width, int Height) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(waveViewStrokeWidth);
        mPaint.setColor(waveViewStrokeColor);
        mPaint.setAntiAlias(true);
        RectF rectF = new RectF((float) (0 + waveViewStrokeWidth / 2), (float) (0 + waveViewStrokeWidth / 2), (float) (Width - waveViewStrokeWidth / 2), (float) (Height - waveViewStrokeWidth / 2));
        canvas.drawArc(rectF, 0.0f, 360f, false, mPaint);
    }

    // 绘制水波纹 @author Ysw created at 2016/4/16 16:24
    private void DrawWave(Canvas canvas, int Width, int Height) {
        if (x < Width) {
            isLeft = true;
        } else {
            isLeft = false;
        }
        if (isLeft) {
            x = x + xDegree;
        } else {
            x = 0;
        }
        // 需要擦除的百分比所对应的高度 @author Ysw created at 2016/4/18 10:17
        int y = (int) ((1 - waveViewPercent / waveViewMax) * Width);
        // 求圆上任意一点的坐标 @author Ysw created at 2016/4/17 2:02
        int mY = (int) (waveViewPercent / waveViewMax * Width);
        int mX = (int) Math.sqrt(Math.pow(Width / 2, 2) - Math.pow(mY, 2) + 2 * Height / 2 * mY - Math.pow(Height / 2, 2)) + Width / 2;

        mPath.reset();
        mPath.moveTo(-Width, y);
        mPath.lineTo(-mX + x, y);
        mPath.cubicTo((2 * mX - Width) / 4 - Width + x, y + yDegree, (2 * mX - Width) / 4 * 3 - Width + x, y - yDegree, mX - Width + x, y);
        mPath.cubicTo((2 * mX - Width) / 4 + x, y + yDegree, (2 * mX - Width) / 4 * 3 + x, y - yDegree, mX + x, y);
        mPath.lineTo(2 * Width + x, y);
        mPath.lineTo(2 * Width + x, 0);
        mPath.lineTo(-Width, 0);
        mPath.close();

        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(waveViewColor);
        mPaint.setAntiAlias(true);
        mPaint.setAlpha((int) (waveViewPercent / 100f * 255));
        // 设置图层，很重要 @author Ysw created at 2016/4/15 19:31
        int layerId = canvas.saveLayer(0, 0, Width, Height, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(Width / 2, Height / 2, Width / 2 - waveViewStrokeWidth, mPaint);
        mPaint.setXfermode(mMode);
        canvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
        // 每隔10毫秒进行重绘形成水波纹动画效果 @author Ysw created at 2016/4/18 10:48
        postInvalidateDelayed(redrawTime);
    }

    // 绘制百分比数据 @author Ysw created at 2016/4/16 16:24
    private void DrawText(Canvas canvas, int width, int height) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(waveViewTextColor);
        mPaint.setTextSize(waveViewTextsize);
        // 控制小数点的个数 @author Ysw created at 2016/4/16 17:16
        String text = "";
        if (waveViewPercent > 0) {
            DecimalFormat df = new DecimalFormat("###.0");
            text = df.format(waveViewPercent) + "%";
        } else {
            text = waveViewPercent + "%";
        }
        float startX = width / 2f - mPaint.measureText(text) / 2f;
        float startY = height / 2f - (mPaint.ascent() + mPaint.descent()) / 2f;
        canvas.drawText(text, startX, startY, mPaint);
    }

    /**
     * 单位转换 sp dp 与 px 之间的转换 @author Ysw created at 2016/4/15 11:35
     */
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int Sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 进行参数的设定 @author Ysw created at 2016/4/18 10:49
     */
    // 设定百分比数量 @author Ysw created at 2016/4/18 10:49
    public void setPercent(final float percent) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f);
        anim.setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                waveViewPercent = percent * value;
                postInvalidate();
            }
        });
        anim.start();
    }

    // 设置水波纹的颜色 @author Ysw created at 2016/4/18 10:49
    public void setWaveViewColor(int color) {
        waveViewColor = color;
        postInvalidate();
    }

    // 设置外层描边的颜色 @author Ysw created at 2016/4/18 10:51
    public void setWaveViewStrokeColor(int color) {
        waveViewStrokeColor = color;
        postInvalidate();
    }

    // 设置外层描边的宽度 @author Ysw created at 2016/4/18 10:52
    public void setWaveViewStrokeWidth(int width) {
        waveViewStrokeWidth = width;
        postInvalidate();
    }

    // 设置水波纹的透明度 @author Ysw created at 2016/4/18 10:54
    public void setWaveViewAlpha(int alpha) {
        waveViewAlpha = alpha;
        postInvalidate();
    }

    // 设置水波纹的背景颜色 @author Ysw created at 2016/4/18 10:55
    public void setWaveViewBackground(int color) {
        waveViewBackground = color;
        postInvalidate();
    }

    // 设置水波纹最大值 @author Ysw created at 2016/4/18 10:57
    public void setWaveViewMax(float max) {
        waveViewMax = max;
        postInvalidate();
    }

    // 设置百分比字体大小 @author Ysw created at 2016/4/18 10:58
    public void setWaveViewTextsize(int size) {
        waveViewTextsize = Sp2px(getContext(), size);
        postInvalidate();
    }

    // 设置百分比字体颜色 @author Ysw created at 2016/4/18 11:00
    public void setWaveViewTextColor(int color) {
        waveViewTextColor = color;
        postInvalidate();
    }

    // 设置水波纹X轴前进的速度 @author Ysw created at 2016/4/18 11:13
    public void setxDegree(int degree) {
        xDegree = degree;
        postInvalidate();
    }

    // 设置水波纹Y轴震动的幅度 @author Ysw created at 2016/4/18 11:14
    public void setyDegree(int degree) {
        yDegree = degree;
        postInvalidate();
    }

    // 设置水波纹重绘的时间 @author Ysw created at 2016/4/18 11:15
    public void setRedrawTime(int time) {
        redrawTime = time;
        postInvalidate();
    }
}
