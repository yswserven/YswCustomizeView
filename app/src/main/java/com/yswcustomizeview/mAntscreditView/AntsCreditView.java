package com.yswcustomizeview.mAntscreditView;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mUtils.UtilsTools;

/**
 * Created by: Ysw on 2016/12/1.
 */

public class AntsCreditView extends View {
    private Context mContext;
    // 最大的信用数值 @author Ysw created at 2016/12/6 11:45 
    private int mMaxNum;
    // 当前的进度值 @author Ysw created at 2016/12/7 15:47
    private int currentNum = 0;
    // 起始的圆弧角度 @author Ysw created at 2016/12/6 11:45 
    private int mStartAngle;
    // 总的圆弧角度 @author Ysw created at 2016/12/6 11:44 
    private int mSweepAngle;
    // 外部小圆弧的宽度 @author Ysw created at 2016/12/6 11:45
    private int mSweepOutWidth;
    // 内部大圆弧的宽度 @author Ysw created at 2016/12/6 11:46
    private int mSweepInWidth;
    // 控件的宽度 @author Ysw created at 2016/12/6 15:08
    private int mWidth;
    // 控件的高度 @author Ysw created at 2016/12/6 15:09
    private int mHeight;
    // 圆弧的半径 @author Ysw created at 2016/12/6 15:47
    private int mRadius;
    // 画笔 @author Ysw created at 2016/12/7 15:19
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 扫描渐变颜色数组 @author Ysw created at 2016/12/7 15:55
    private int[] mIndicatorColor = {0xffffffff, 0x00ffffff, 0x99ffffff, 0xffffffff};
    // 描述信用好坏的文字数组 @author Ysw created at 2016/12/7 16:30
    private String[] mText = {"较差", "中等", "良好", "优秀", "极好"};

    public AntsCreditView(Context context) {
        super(context);
        this.mContext = context;
    }

    public AntsCreditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setBackgroundColor(0xFFFF6347);//25415
        initArrts(attrs);
    }

    public AntsCreditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setBackgroundColor(0xFFFF6347);
        initArrts(attrs);
    }

    private void initArrts(AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.AntsCreditView);
        mMaxNum = array.getInt(R.styleable.AntsCreditView_YswMaxNum, 900);
        mStartAngle = array.getInt(R.styleable.AntsCreditView_YswStartAngle, 160);
        mSweepAngle = array.getInt(R.styleable.AntsCreditView_YswSweepAngle, 220);
        mSweepInWidth = UtilsTools.Dp2Px(mContext, 8);
        mSweepOutWidth = UtilsTools.Dp2Px(mContext, 3);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = UtilsTools.Dp2Px(mContext, 300);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = UtilsTools.Dp2Px(mContext, 400);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRadius = getMeasuredWidth() / 4;
        // 保存之前的画布 @author Ysw created at 2016/12/6 17:44
        canvas.save();
        // 将画布进行平移，重新设置其原点 @author Ysw created at 2016/12/6 17:44
        canvas.translate(mWidth / 2, mHeight / 2);
        // 绘制内外半圆 @author Ysw created at 2016/12/7 10:31
        drawRound(canvas);
        // 绘制刻度 @author Ysw created at 2016/12/7 16:34
        drawScale(canvas);
        // 绘制当前的进度 @author Ysw created at 2016/12/7 16:34
        drawIndicator(canvas);
        // 绘制中间的文字 @author Ysw created at 2016/12/7 16:34
        drawCenterText(canvas);
        canvas.restore();
    }

    // 绘制内外半圆 @author Ysw created at 2016/12/7 10:35
    private void drawRound(Canvas canvas) {
        canvas.save();
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setDither(true);
        mPaint.setColor(0xffffffff);
        mPaint.setAlpha(0x40);
        mPaint.setStrokeWidth(mSweepInWidth);
        RectF inRectf = new RectF(-mRadius, -mRadius, mRadius, mRadius);
        canvas.drawArc(inRectf, mStartAngle, mSweepAngle, false, mPaint);
        int w = UtilsTools.Dp2Px(mContext, 10);
        mPaint.setStrokeWidth(mSweepOutWidth);
        RectF outRectf = new RectF(-mRadius - w, -mRadius - w, mRadius + w, mRadius + w);
        canvas.drawArc(outRectf, mStartAngle, mSweepAngle, false, mPaint);
        canvas.restore();
    }

    // 绘制刻度线 @author Ysw created at 2016/12/7 10:37
    private void drawScale(Canvas canvas) {
        canvas.save();
        // 每一跟刻度线之间的角度间隔 @author Ysw created at 2016/12/7 10:50
        float angle = (float) mSweepAngle / 30;
        // 将画布进行旋转到起始位置 @author Ysw created at 2016/12/7 10:51
        canvas.rotate(mStartAngle + 90);
        for (int i = 0; i <= 30; i++) {
            mPaint.reset();
            mPaint.setDither(true);
            if (i % 6 == 0) {
                mPaint.setColor(0xffffffff);
                mPaint.setAlpha(0x70);
                mPaint.setStrokeWidth(UtilsTools.Dp2Px(mContext, 2));
                canvas.drawLine(0, -mRadius - mSweepInWidth / 2, 0, -mRadius + mSweepInWidth / 2 + UtilsTools.Dp2Px(mContext, 1), mPaint);
                drawText(canvas, i * mMaxNum / 30 + "", mPaint);
            } else {
                mPaint.setColor(0xffffffff);
                mPaint.setAlpha(0x50);
                mPaint.setStrokeWidth(UtilsTools.Dp2Px(mContext, 1));
                canvas.drawLine(0, -mRadius - mSweepInWidth / 2, 0, -mRadius + mSweepInWidth / 2, mPaint);
            }
            if (i == 3 || i == 9 || i == 15 || i == 21 || i == 27) {  //画刻度区间文字
                mPaint.setStrokeWidth(UtilsTools.Dp2Px(mContext, 2));
                mPaint.setAlpha(0x50);
                drawText(canvas, mText[(i - 3) / 6], mPaint);
            }
            canvas.rotate(angle);
        }
        canvas.restore();
    }

    // 绘制当前进度的值 @author Ysw created at 2016/12/7 15:43
    private void drawIndicator(Canvas canvas) {
        canvas.save();
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        // 当前进度的角度值 @author Ysw created at 2016/12/7 16:10
        int sweep;
        if (currentNum <= mMaxNum) {
            sweep = (int) ((float) currentNum / (float) mMaxNum * mSweepAngle);
        } else {
            sweep = mSweepAngle;
        }
        mPaint.setStrokeWidth(mSweepOutWidth);
        // 创建扫描梯度渐变 @author Ysw created at 2016/12/7 16:10
        SweepGradient shader = new SweepGradient(0, 0, mIndicatorColor, null);
        // 画笔设置梯度渐变 @author Ysw created at 2016/12/7 16:11
        mPaint.setShader(shader);
        // 通过绘制圆弧来绘制当前进度的渐变效果 @author Ysw created at 2016/12/7 16:12
        int w = UtilsTools.Dp2Px(mContext, 10);
        mPaint.setStrokeWidth(mSweepOutWidth);
        RectF outRectf = new RectF(-mRadius - w, -mRadius - w, mRadius + w, mRadius + w);
        canvas.drawArc(outRectf, mStartAngle, sweep, false, mPaint);
        // 通过三角函数来确定当前进度值中点的坐标位置 @author Ysw created at 2016/12/7 16:14
        float x = (float) ((mRadius + UtilsTools.Dp2Px(mContext, 10)) * Math.cos(Math.toRadians(mStartAngle + sweep)));
        float y = (float) ((mRadius + UtilsTools.Dp2Px(mContext, 10)) * Math.sin(Math.toRadians(mStartAngle + sweep)));
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffffffff);
        if (canvas.isHardwareAccelerated()){
            this.setLayerType(View.LAYER_TYPE_HARDWARE,mPaint);
        }
        // 设置画笔外围模糊面具 @author Ysw created at 2016/12/7 16:25
        mPaint.setMaskFilter(new BlurMaskFilter(UtilsTools.Dp2Px(mContext, 3), BlurMaskFilter.Blur.OUTER));
        canvas.drawCircle(x, y, UtilsTools.Dp2Px(mContext, 3), mPaint);
        canvas.restore();
    }

    // 绘制中间的文字 @author Ysw created at 2016/12/7 16:28
    private void drawCenterText(Canvas canvas) {
        canvas.save();
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mRadius / 2);
        mPaint.setColor(0xffffffff);
        canvas.drawText(currentNum + "", -mPaint.measureText(currentNum + "") / 2, 0, mPaint);
        mPaint.setTextSize(mRadius / 4);
        String content = "信用";
        if (currentNum < mMaxNum * 1 / 5) {
            content += mText[0];
        } else if (currentNum >= mMaxNum * 1 / 5 && currentNum < mMaxNum * 2 / 5) {
            content += mText[1];
        } else if (currentNum >= mMaxNum * 2 / 5 && currentNum < mMaxNum * 3 / 5) {
            content += mText[2];
        } else if (currentNum >= mMaxNum * 3 / 5 && currentNum < mMaxNum * 4 / 5) {
            content += mText[3];
        } else if (currentNum >= mMaxNum * 4 / 5) {
            content += mText[4];
        }
        Rect r = new Rect();
        mPaint.getTextBounds(content, 0, content.length(), r);
        canvas.drawText(content, -r.width() / 2, r.height() + 20, mPaint);
        canvas.restore();
    }

    // 绘制文字 @author Ysw created at 2016/12/7 16:28
    private void drawText(Canvas canvas, String text, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(UtilsTools.Dp2Px(mContext, 8));
        float width = paint.measureText(text);
        //相比getTextBounds来说，这个方法获得的类型是float，更精确些
        //Rect rect = new Rect();
        //paint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text, -width / 2, -mRadius + UtilsTools.Dp2Px(mContext, 15), paint);
        paint.setStyle(Paint.Style.STROKE);
    }

    // 设置当前的进度值 @author Ysw created at 2016/12/7 16:39
    public void setYswCurrentNum(int num) {
        float duration = (float) Math.abs(num - currentNum) / mMaxNum * 1500 + 900;
        // 此处利用属性动画自动调用get和set方法来进行动画的处理，方法中的第二个参数是变量名 @author Ysw created at 2016/12/7 17:58
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "currentNum", num);
        animator.setDuration((long) Math.min(duration, 2400));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                int color = calculateColor(value);
                setBackgroundColor(color);
            }
        });
        animator.start();
    }

    private int calculateColor(int value) {
        ArgbEvaluator evealuator = new ArgbEvaluator();
        float fraction = 0;
        int color = 0;
        if (value <= mMaxNum / 2) {
            fraction = (float) value / (mMaxNum / 2);
            color = (int) evealuator.evaluate(fraction, 0xFFFF6347, 0xFFFF8C00); //由红到橙
        } else {
            fraction = ((float) value - mMaxNum / 2) / (mMaxNum / 2);
            color = (int) evealuator.evaluate(fraction, 0xFFFF8C00, 0xFF00CED1); //由橙到蓝
        }
        return color;
    }

    /**
     * 这两个方法是提供给属性动画使用的，自动生成get和set方法，
     * 需要注意的是变量名的写法会影响到这两个方法的正常调用
     *
     * @author Ysw created at 2016/12/7 17:52
     */
    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
        invalidate();
    }
}
