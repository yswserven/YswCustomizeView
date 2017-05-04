package com.yswcustomizeview.mPassWordView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mUtils.ScreenUtils;
import com.yswcustomizeview.mUtils.UtilsTools;

/**
 * Created by： Ysw on 2016/4/27.10:35.
 */
public class PasswordView extends View {
    // 取消交易的图标 @author Ysw created at 2016/4/27 10:58
    private Bitmap mCancelBitmap = UtilsTools.getBitmap(getContext(), R.mipmap.close);
    // 取消图标距离左边的距离 @author Ysw created at 2016/4/27 14:56
    private int mCancelBitmapMaganLeft = UtilsTools.Dp2Px(getContext(), 20);
    // 交易的标题文字 @author Ysw created at 2016/4/27 11:04
    private String mTitle = "输入交易密码";
    // 标题文字的大小 @author Ysw created at 2016/4/27 11:04
    private int mTitleSize = UtilsTools.Sp2px(getContext(), 20);
    // 标题文字的颜色 @author Ysw created at 2016/4/27 11:07
    private int mTitleColor = Color.parseColor("#000000");
    // 线的颜色 @author Ysw created at 2016/4/27 11:08
    private int mLinesColor = Color.parseColor("#D5D5D5");
    // 密码框线的颜色 @author Ysw created at 2016/4/27 11:09
    private int mPasswordLineColor = Color.parseColor("#808080");
    // 密码框的宽度 @author Ysw created at 2016/4/27 11:10
    private int mPasswordWidth = UtilsTools.Dp2Px(getContext(), 30);
    // 密码的长度 @author Ysw created at 2016/4/27 11:11
    private int mPasswordLenth = 6;
    // 密码显示的图片 @author Ysw created at 2016/4/27 11:12
    private Bitmap mPasswordBitmap;
    // 密码框距离顶部的距离 @author Ysw created at 2016/4/27 11:13
    private int mPaddingTop = UtilsTools.Dp2Px(getContext(), 25);
    // 密码框距离左边的距离 @author Ysw created at 2016/4/27 11:13
    private int mMaginLeft = UtilsTools.Dp2Px(getContext(), 30);
    // 数字的颜色 @author Ysw created at 2016/4/27 11:15
    private int mNumberColor = Color.parseColor("#010101");
    // 数字的大小 @author Ysw created at 2016/4/27 11:17
    private int mNumberSize = UtilsTools.Sp2px(getContext(), 28);
    // 删除建的颜色 @author Ysw created at 2016/4/27 11:18
    private int mDeleteColor = Color.parseColor("#1F7EE8");
    // 删除建的图标 @author Ysw created at 2016/4/27 11:20
    private Bitmap mWhiteBitmap = UtilsTools.getBitmap(getContext(), R.mipmap.delete_white);
    private Bitmap mDarkBitmap = UtilsTools.getBitmap(getContext(), R.mipmap.delete_dark);
    private Bitmap mDeleteBitmap = mWhiteBitmap;
    // 控件总体的背景 @author Ysw created at 2016/4/27 11:20
    private int mBackgroundColor = Color.parseColor("#F2F2F2");
    // 数字键的高度 @author Ysw created at 2016/4/27 11:23
    private int mNumberHeight = UtilsTools.Dp2Px(getContext(), 50);
    // 数字键的宽度 @author Ysw created at 2016/4/28 9:26
    private int mNumberWidth;
    // 数字键的行数 @author Ysw created at 2016/4/27 11:25
    private int mNumberLines = 4;
    // 总体表面的颜色 @author Ysw created at 2016/4/27 11:53
    private int mSurfaceColor = Color.parseColor("#FFFFFF");
    // 控件顶部的高度 @author Ysw created at 2016/4/27 14:34
    private int mTopHeight = UtilsTools.Dp2Px(getContext(), 50);
    // 控件密码框的高度 @author Ysw created at 2016/4/27 14:35
    private int mPasswordHeight = UtilsTools.Dp2Px(getContext(), 100);
    // 控件的总高度 @author Ysw created at 2016/4/27 14:37
    private int mHeight = mTopHeight + mPasswordHeight + mNumberHeight * 4;
    // 控件的总宽度 @author Ysw created at 2016/4/27 14:38
    private int mWidth = UtilsTools.Dp2Px(getContext(), 350);
    // 画笔 @author Ysw created at 2016/4/27 14:51
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 选中时的画笔 @author Ysw created at 2016/4/28 8:52
    private Paint mSelectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 数字的二维数组 @author Ysw created at 2016/4/27 15:52
    private int mNumbers[][] = new int[4][3];
    // 已输入的密码输 @author Ysw created at 2016/4/28 10:55
    private int mPasswordNum;
    // 密码圆圈的颜色 @author Ysw created at 2016/4/28 10:58
    private int mPasswordColor = Color.parseColor("#050505");
    // 密码圆圈的半径 @author Ysw created at 2016/4/28 10:59
    private int mPassworRadius = UtilsTools.Dp2Px(getContext(), 12.5f);
    // 我的密码 @author Ysw created at 2016/4/28 11:16
    private String mPassword = "";
    // 手势按下去时的X轴坐标值 @author Ysw created at 2016/4/28 9:18
    private double downX;
    // 手势按下去时的Y轴坐标值 @author Ysw created at 2016/4/28 9:18
    private double downY;
    // 手势离开屏幕时X轴的坐标值 @author Ysw created at 2016/4/28 9:18
    private double upX;
    // 手势离开屏幕时Y轴的坐标值 @author Ysw created at 2016/4/28 9:19
    private double upY;
    // 密码输入完成的监听 @author Ysw created at 2016/4/28 13:15
    private onPasswordOverListener mListener;

    public interface onPasswordOverListener {
        void onPasswordOverListener(String password);
    }

    public void setOnPasswordOverListener(onPasswordOverListener listener) {
        this.mListener = listener;
    }

    public PasswordView(Context context) {
        super(context);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray Array = initAttrs(context, attrs);
        Array.recycle();
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray Array = initAttrs(context, attrs);
        Array.recycle();
    }

    private TypedArray initAttrs(Context context, AttributeSet attrs) {
        TypedArray Array = context.obtainStyledAttributes(attrs, R.styleable.PasswordView);
        if (Array == null) return null;
        int count = Array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = Array.getIndex(i);
            switch (attr) {
                case R.styleable.PasswordView_cancerImage:
                    mCancelBitmap = UtilsTools.getBitmap(Array.getDrawable(attr));
                    break;
                case R.styleable.PasswordView_mpasswordtitle:
                    mTitle = Array.getString(attr);
                    break;
                case R.styleable.PasswordView_titlesize:
                    mTitleSize = Array.getDimensionPixelSize(attr, mTitleSize);
                    break;
                case R.styleable.PasswordView_titlecolor:
                    mTitleColor = Array.getColor(attr, mTitleColor);
                    break;
                case R.styleable.PasswordView_linescolor:
                    mLinesColor = Array.getColor(attr, mLinesColor);
                    break;
                case R.styleable.PasswordView_passwordlinecolor:
                    mPasswordLineColor = Array.getColor(attr, mPasswordLineColor);
                    break;
                case R.styleable.PasswordView_passwordwidth:
                    mPasswordWidth = Array.getDimensionPixelOffset(attr, mPasswordWidth);
                    break;
                case R.styleable.PasswordView_passwordlength:
                    mPasswordLenth = Array.getInteger(attr, mPasswordLenth);
                    break;
                case R.styleable.PasswordView_passwordimage:
                    mPasswordBitmap = UtilsTools.getBitmap(Array.getDrawable(attr));
                    break;
                case R.styleable.PasswordView_passwordpaddingtop:
                    mPaddingTop = Array.getDimensionPixelOffset(attr, mPaddingTop);
                    break;
                case R.styleable.PasswordView_passwordpaddingleft:
                    mMaginLeft = Array.getDimensionPixelOffset(attr, mMaginLeft);
                    break;
                case R.styleable.PasswordView_numbercolor:
                    mNumberColor = Array.getColor(attr, mNumberColor);
                    break;
                case R.styleable.PasswordView_numbersize:
                    mNumberSize = Array.getDimensionPixelSize(attr, mNumberSize);
                    break;
                case R.styleable.PasswordView_deletecolor:
                    mDeleteColor = Array.getColor(attr, mDeleteColor);
                    break;
                case R.styleable.PasswordView_deleteimage:
                    mDeleteBitmap = UtilsTools.getBitmap(Array.getDrawable(attr));
                    break;
                case R.styleable.PasswordView_mpasswordbackground:
                    mBackgroundColor = Array.getColor(attr, mBackgroundColor);
                    break;
                case R.styleable.PasswordView_numberlineheight:
                    mNumberHeight = Array.getDimensionPixelOffset(attr, mNumberHeight);
                    break;
                case R.styleable.PasswordView_surfacecolor:
                    mSurfaceColor = Array.getColor(attr, mSurfaceColor);
                    break;
                case R.styleable.PasswordView_numberlines:
                    mNumberLines = Array.getInteger(attr, mNumberLines);
                    break;
                default:
                    break;
            }
        }
        return Array;
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
            if (widthSize == 0) {
                mWidth = ScreenUtils.getScreenWidth(getContext());
            }
            mNumberWidth = mWidth / 3;
        } else {
            mWidth = ScreenUtils.getScreenWidth(getContext());
            mNumberWidth = mWidth / 3;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = mTopHeight + mPasswordHeight + mNumberHeight * 4;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制一个白色的底面 @author Ysw created at 2016/4/28 8:49
        DrawBackground(canvas);
        // 绘制顶部 @author Ysw created at 2016/4/27 14:51
        DrawTop(canvas);
        // 绘制密码 框 @author Ysw created at 2016/4/27 15:16
        DrawPasswordLine(canvas);
        // 绘制数字部分 @author Ysw created at 2016/4/27 15:32
        DrawNumber(canvas);
        // 绘制点击事件 @author Ysw created at 2016/4/28 10:29
        ActionDown(canvas);
        // 绘制密码 @author Ysw created at 2016/4/28 11:11
        DrawPassword(canvas);
    }

    // 绘制整体背景 @author Ysw created at 2016/4/28 11:11
    private void DrawBackground(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mSurfaceColor);
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }

    // 绘制顶部 @author Ysw created at 2016/4/27 14:51
    private void DrawTop(Canvas canvas) {
        //  绘制取消图标 @author Ysw created at 2016/4/27 15:08
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        canvas.drawBitmap(mCancelBitmap, mCancelBitmapMaganLeft, mTopHeight / 2 - mCancelBitmap.getHeight() / 2, mPaint);
        // 绘制文字 @author Ysw created at 2016/4/27 15:08
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mTitleColor);
        mPaint.setTextSize(mTitleSize);
        canvas.drawText(mTitle, mWidth / 2 - (int) mPaint.measureText(mTitle) / 2, mTopHeight / 2f - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);
        // 绘制线 @author Ysw created at 2016/4/27 15:13
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mLinesColor);
        mPaint.setStrokeWidth(3);
        canvas.drawLine(0, mTopHeight, mWidth, mTopHeight, mPaint);
    }

    // 绘制密码框 @author Ysw created at 2016/4/27 15:16
    private void DrawPasswordLine(Canvas canvas) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mPasswordLineColor);
        mPaint.setStrokeWidth(3);
        canvas.drawLine(mMaginLeft, mTopHeight + mPaddingTop, mWidth - mMaginLeft, mTopHeight + mPaddingTop, mPaint);
        canvas.drawLine(mMaginLeft, mTopHeight + mPasswordHeight - mPaddingTop, mWidth - mMaginLeft, mTopHeight + mPasswordHeight - mPaddingTop, mPaint);
        float rowWidth = (mWidth - 2 * mMaginLeft) / mPasswordLenth;
        for (int i = 0; i < mPasswordLenth + 1; i++) {
            canvas.drawLine(mMaginLeft + rowWidth * i, mTopHeight + mPaddingTop, mMaginLeft + rowWidth * i, mTopHeight + mPasswordHeight - mPaddingTop, mPaint);
        }
    }

    // 绘制数字部分 @author Ysw created at 2016/4/27 15:33
    private void DrawNumber(Canvas canvas) {
        // 绘制数字部分的线 @author Ysw created at 2016/4/27 15:33
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mLinesColor);
        mPaint.setStrokeWidth(3);
        float startY = mTopHeight + mPasswordHeight;
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(0, startY + mNumberHeight * i, mWidth, startY + mNumberHeight * i, mPaint);
        }
        float startX = mWidth / 3;
        for (int i = 1; i < 3; i++) {
            canvas.drawLine(startX * i, startY, startX * i, mHeight, mPaint);
        }
        // 绘制数字 @author Ysw created at 2016/4/27 15:44
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 3 && j == 0) {
                    mNumbers[i][j] = 100;
                } else if (i == 3 && j == 1) {
                    mNumbers[i][j] = 0;
                } else if (i == 3 && j == 2) {
                    mNumbers[i][j] = 100;
                } else {
                    mNumbers[i][j] = 3 * i + j + 1;
                }
            }
        }
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mNumberColor);
        mPaint.setTextSize(mNumberSize);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 3 && j == 0) {
                    mSelectPaint.reset();
                    mSelectPaint.setColor(mBackgroundColor);
                    mSelectPaint.setAntiAlias(true);
                    mSelectPaint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(0, mHeight - mNumberHeight, startX, mHeight, mSelectPaint);
                } else if (i == 3 && j == 2) {
                } else {
                    canvas.drawText("" + mNumbers[i][j], startX * j + startX / 2 - mPaint.measureText("" + mNumbers[i][j]) / 2,
                            mTopHeight + mPasswordHeight + mNumberHeight * i + mNumberHeight / 2 - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);
                }
            }
        }
        // 绘制删除键 @author Ysw created at 2016/4/27 16:14
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mDeleteColor);
        canvas.drawRect(startX * 2, mHeight - mNumberHeight, mWidth, mHeight, mPaint);
        canvas.drawBitmap(mDeleteBitmap, startX * 3 - startX / 2 - mDeleteBitmap.getWidth() / 2, mHeight - mNumberHeight / 2 - mDeleteBitmap.getHeight() / 2, mPaint);
    }

    // 点击效果 @author Ysw created at 2016/4/28 11:53
    private void ActionDown(Canvas canvas) {
        if ((int) downY > (mTopHeight + mPasswordHeight)) {
            int j = (int) downX / mNumberWidth;
            int i = (int) (downY - mTopHeight - mPasswordHeight) / mNumberHeight;
            if (i == 3 && j == 0) return;
            if (i == 3 && j == 2) {
                if (mPasswordNum == 0) return;
                mPasswordNum--;
                mPassword = mPassword.substring(0, mPasswordNum);
                mPaint.reset();
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setAntiAlias(true);
                mPaint.setColor(mDeleteColor);
                canvas.drawRect(mNumberWidth * 2, mHeight - mNumberHeight, mWidth, mHeight, mPaint);
                mDeleteBitmap = mDarkBitmap;
                canvas.drawBitmap(mDeleteBitmap, mNumberWidth * 3 - mNumberWidth / 2 - mDeleteBitmap.getWidth() / 2, mHeight - mNumberHeight / 2 - mDeleteBitmap.getHeight() / 2, mPaint);
            } else {
                mSelectPaint.reset();
                mSelectPaint.setColor(mBackgroundColor);
                mSelectPaint.setAntiAlias(true);
                mSelectPaint.setStyle(Paint.Style.FILL);
                canvas.drawRect(mNumberWidth * j, mTopHeight + mPasswordHeight + mNumberHeight * i,
                        mNumberWidth + mNumberWidth * j, mTopHeight + mPasswordHeight + mNumberHeight + mNumberHeight * i, mSelectPaint);
                mPaint.reset();
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setAntiAlias(true);
                mPaint.setColor(mNumberColor);
                mPaint.setTextSize(mNumberSize);
                canvas.drawText("" + mNumbers[i][j], mNumberWidth * j + mNumberWidth / 2 - mPaint.measureText("" + mNumbers[i][j]) / 2,
                        mTopHeight + mPasswordHeight + mNumberHeight * i + mNumberHeight / 2 - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);
                if (mPasswordNum < mPasswordLenth) {
                    mPasswordNum++;
                    mPassword = mPassword + mNumbers[i][j];
                    if (mPasswordNum == mPasswordLenth) {
                        if (mListener != null) {
                            mListener.onPasswordOverListener(mPassword);
                        }
                    }
                }
            }
        } else if (downX > 0 && downX < mTopHeight && downY > 0 && downY < mTopHeight) {
            Log.e("Ysw", "我点击了 X 号");
        }
        downX = 0;
        downY = 0;
    }

    // 绘制密码 @author Ysw created at 2016/4/28 11:12
    private void DrawPassword(Canvas canvas) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mPasswordColor);
        float rowWidth = (mWidth - 2 * mMaginLeft) / mPasswordLenth;
        for (int k = 0; k < mPasswordNum; k++) {
            canvas.drawCircle(mMaginLeft + rowWidth + rowWidth * k - rowWidth / 2, mTopHeight + mPasswordHeight / 2, mPassworRadius, mPaint);
        }
    }

    // 点击事件 @author Ysw created at 2016/4/28 9:05
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                performClick();
                postInvalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                downX = 0;
                downY = 0;
                mDeleteBitmap = mWhiteBitmap;
                performClick();
                postInvalidate();
                break;
            default:
                downX = 0;
                downY = 0;
                upX = 0;
                upY = 0;
                mDeleteBitmap = mWhiteBitmap;
                performClick();
                postInvalidate();
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    /**
     * 外部调用方法 @author Ysw created at 2016/4/28 11:55
     */

    // 设置标题 @author Ysw created at 2016/4/28 11:55
    public void setTitle(String title) {
        this.mTitle = title;
        postInvalidate();
    }

    // 设置标题文字大小 @author Ysw created at 2016/4/28 11:57
    public void setTitleSize(int size) {
        int i = UtilsTools.Sp2px(getContext(), size);
        mTitleSize = i;
        postInvalidate();
    }

    // 设置标题文字颜色 @author Ysw created at 2016/4/28 11:58
    public void setTitleColor(int color) {
        this.mTitleColor = color;
        postInvalidate();
    }

    // 设置顶部的高度 @author Ysw created at 2016/4/28 11:59
    public void setTopHeight(int height) {
        int i = UtilsTools.Dp2Px(getContext(), height);
        this.mTopHeight = i;
        postInvalidate();
    }

    // 设置顶部图标 @author Ysw created at 2016/4/28 12:00
    public void setCancelBitmap(int srcId) {
        Bitmap bitmap = UtilsTools.getBitmap(getContext(), srcId);
        this.mCancelBitmap = bitmap;
        postInvalidate();
    }

    // 设置线的颜色 @author Ysw created at 2016/4/28 12:42
    public void setLinesColor(int color) {
        this.mLinesColor = color;
        postInvalidate();
    }

    // 设置密码框线的颜色 @author Ysw created at 2016/4/28 12:43
    public void setPasswordColor(int color) {
        this.mPasswordLineColor = color;
        postInvalidate();
    }

    // 设置密码图标的半径 @author Ysw created at 2016/4/28 12:46
    public void setPassworRadius(int radius) {
        int i = UtilsTools.Dp2Px(getContext(), radius);
        this.mPassworRadius = i;
        postInvalidate();
    }

    // 设置密码图标的颜色 @author Ysw created at 2016/4/28 12:48
    public void setPassworColor(int color) {
        this.mPasswordColor = color;
        postInvalidate();
    }

    // 设置数字的大小  @author Ysw created at 2016/4/28 12:49
    public void setNumberSize(int size) {
        int i = UtilsTools.Sp2px(getContext(), size);
        this.mNumberSize = i;
        postInvalidate();
    }

    // 设置数字的颜色 @author Ysw created at 2016/4/28 12:50
    public void setNumberColor(int color) {
        this.mNumberColor = color;
        postInvalidate();
    }

    // 设置总体背景的颜色 @author Ysw created at 2016/4/28 12:51
    public void setBackgroundColor(int color) {
        this.mBackgroundColor = color;
        postInvalidate();
    }

    // 设置总体表面的颜色 @author Ysw created at 2016/4/28 12:52
    public void setSurfaceColor(int coloe) {
        this.mSurfaceColor = coloe;
        postInvalidate();
    }

    // 设置删除的图标 @author Ysw created at 2016/4/28 12:53
    public void setDeleteBitmap(int srcId) {
        Bitmap bitmap = UtilsTools.getBitmap(getContext(), srcId);
        this.mDeleteBitmap = bitmap;
        postInvalidate();
    }

    // 设置删除按钮的背景 @author Ysw created at 2016/4/28 12:54
    public void setDeleteColor(int color) {
        this.mDeleteColor = color;
        postInvalidate();
    }

    // 设置密码框的总高度 @author Ysw created at 2016/4/28 12:56
    public void setmPasswordHeight(int height) {
        int i = UtilsTools.Dp2Px(getContext(), height);
        this.mPasswordHeight = i;
        postInvalidate();
    }

    // 设置密码框距离左边的距离 @author Ysw created at 2016/4/28 13:00
    public void setPasswordMargLeft(int margLeft) {
        int i = UtilsTools.Dp2Px(getContext(), margLeft);
        this.mMaginLeft = i;
        postInvalidate();
    }

    // 设置密码框距离顶部的距离 @author Ysw created at 2016/4/28 13:02
    public void setPasswordPaddingTop(int paddingTop) {
        int i = UtilsTools.Dp2Px(getContext(), paddingTop);
        this.mPaddingTop = i;
        postInvalidate();
    }

    // 设置取消图标距离左边的距离 @author Ysw created at 2016/4/28 13:04
    public void setCancelMargLeft(int margLeft) {
        int i = UtilsTools.Dp2Px(getContext(), margLeft);
        this.mCancelBitmapMaganLeft = i;
        postInvalidate();
    }

    // 设置密码的长度 @author Ysw created at 2016/4/28 13:05
    public void setPasswordLenth(int lenth) {
        this.mPasswordLenth = lenth;
        postInvalidate();
    }

    // 设置数字键的高度 @author Ysw created at 2016/4/28 13:06
    public void setNumberHeight(int height) {
        int i = UtilsTools.Dp2Px(getContext(), height);
        this.mNumberHeight = i;
        postInvalidate();
    }

    // 获取密码 @author Ysw created at 2016/4/28 12:55
    public String getPassword() {
        return mPassword;
    }
}
