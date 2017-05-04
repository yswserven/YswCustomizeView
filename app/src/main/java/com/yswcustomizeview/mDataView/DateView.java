package com.yswcustomizeview.mDataView;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.yswcustomizeview.R;

import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by： Ysw on 2016/4/12.11:20.
 */
public class DateView extends View {
    /**
     * Top 栏属性的定义 @author Ysw created at 2016/4/12 13:28
     */
    // 控件的总体宽度 @author Ysw created at 2016/4/20 15:02
    private int mWidth = Dp2Px(getContext(),350);
    // 控件的总体高度 @author Ysw created at 2016/4/20 15:02
    private int mHeight = Dp2Px(getContext(),400);
    // 左边的图片 @author Ysw created at 2016/4/12 11:33
    private Bitmap liftBitmap = getBitmap(R.drawable.left);
    // 左边图片与左边的距离 @author Ysw created at 2016/4/12 11:35
    private int leftBitmapMargLeft = Dp2Px(getContext(), 10);
    // 右边的图片 @author Ysw created at 2016/4/12 11:35
    private Bitmap rightBitmap = getBitmap(R.drawable.right);
    // 右边图片与右边的距离 @author Ysw created at 2016/4/12 11:36
    private int rightBitmapMargRight = Dp2Px(getContext(), 10);
    // 顶部的高度 @author Ysw created at 2016/4/12 11:37
    private int topHeight = Dp2Px(getContext(), 60);
    // 顶部年份和月份中间的距离 @author Ysw created at 2016/4/12 11:55
    private int topMiddleMarg = Dp2Px(getContext(), 10);
    // 顶部的背景颜色 @author Ysw created at 2016/4/12 11:55
    private int topBackgroundColor = Color.parseColor("#FFFFFF");
    // 顶部背景的透明度 @author Ysw created at 2016/4/12 11:56
    private int topAlpha = 255;
    // 顶部文字的大小 @author Ysw created at 2016/4/12 11:57
    private int topTextSize = Sp2px(getContext(), 16);
    // 顶部文字的颜色 @author Ysw created at 2016/4/12 11:57
    private int topTextColor = Color.parseColor("#262626");

    /**
     * 星期栏属性的定义 @author Ysw created at 2016/4/12 13:27
     */
    // 星期栏的高度 @author Ysw created at 2016/4/12 12:01
    private int weekLineHeight = Dp2Px(getContext(), 40);
    // 星期栏的背景颜色 @author Ysw created at 2016/4/12 12:02
    private int weekLineBackgroundColor = Color.parseColor("#FFFFFF");
    //  星期栏字体的大小 @author Ysw created at 2016/4/12 12:03
    private int weekLineTextSize = Sp2px(getContext(), 16);
    // 星期栏字体的颜色 @author Ysw created at 2016/4/12 12:04
    private int weekLineTextColor = Color.parseColor("#262626");
    // 星期栏左边距离左边的距离 @author Ysw created at 2016/4/12 12:05
    private int weekLineMargLeft = Dp2Px(getContext(), 10);
    // 星期栏右边距离右边的距离 @author Ysw created at 2016/4/12 12:06
    private int weekLineMargRight = Dp2Px(getContext(), 10);

    /**
     * 日期栏属性的定义 @author Ysw created at 2016/4/12 13:27
     */
    // 日期栏的高度 @author Ysw created at 2016/4/12 13:12
    private int dateLineHeight = Dp2Px(getContext(), 240);
    // 日期栏总体的背景颜色 @author Ysw created at 2016/4/12 13:14
    private int dateLineBackgroundColor = Color.parseColor("#FFFFFF");
    // 日期栏字体的大小 @author Ysw created at 2016/4/12 13:14
    private int dateLineTextSize = Sp2px(getContext(), 14);
    // 日期栏字体的颜色 @author Ysw created at 2016/4/12 13:15
    private int dateLineTextColor = Color.parseColor("#262626");
    // 日期栏当前日期或被选中日期绘制的背景颜色 @author Ysw created at 2016/4/12 13:16
    private int dateLineClickBackgroundColor = Color.parseColor("#007FE0");
    // 日期栏右上角的图片Bitmap @author Ysw created at 2016/4/12 13:17
    private Bitmap dateLineUpperRightBitmap = getBitmap(R.drawable.qian);
    // 日期栏每一行的高度 @author Ysw created at 2016/4/12 23:40
    private int dateLineRowHeight = Dp2Px(getContext(), 40);
    // 日期栏每一列的宽度 @author Ysw created at 2016/4/13 9:43
    private int dateLineColumnWidth;

    /**
     * 画笔的定义 @author Ysw created at 2016/4/12 13:29
     */
    // Top 画笔的定义 @author Ysw created at 2016/4/12 13:30
    private Paint topPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 星期画笔的定义 @author Ysw created at 2016/4/12 13:30
    private Paint weekPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 日期画笔的定义 @author Ysw created at 2016/4/12 13:31
    private Paint datePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 日期的行数、列数 @author Ysw created at 2016/4/12 13:34
     */
    // 行数 @author Ysw created at 2016/4/12 13:34
    private int Row = 6;
    // 列数 @author Ysw created at 2016/4/12 13:35
    private int Column = 7;

    /**
     * 星期数组 @author Ysw created at 2016/4/12 18:40
     */
    // 星期天到星期六 @author Ysw created at 2016/4/12 18:40
    String weeks[] = new String[]{"日", "一", "二", "三", "四", "五", "六"};

    /**
     * 系统时间的 年、月、日 @author Ysw created at 2016/4/12 23:11
     */
    // 系统的年数 @author Ysw created at 2016/4/12 23:12
    private int systemYear;
    // 系统的月数 @author Ysw created at 2016/4/12 23:12
    private int systemMonth;
    // 系统的日数 @author Ysw created at 2016/4/12 23:13
    private int systemDay;
    // 一个月所有时间的数组 @author Ysw created at 2016/4/13 16:08
    private int daysString[][];

    /**
     * 触摸事件相对应的坐标值 @author Ysw created at 2016/4/13 15:31
     */
    // 手势按下去时的X轴坐标值 @author Ysw created at 2016/4/13 15:32
    private double downX;
    // 手势按下去时的Y轴坐标值 @author Ysw created at 2016/4/13 15:33
    private double downY;
    // 手势离开屏幕时X轴的坐标值 @author Ysw created at 2016/4/13 15:33
    private double upX;
    // 手势离开屏幕时Y轴的坐标值 @author Ysw created at 2016/4/13 15:34
    private double upY;


    public DateView(Context context) {
        super(context);
        getSestemDate();
    }

    public DateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getSestemDate();
        TypedArray array = initAttributes(context, attrs);
        array.recycle();
    }


    public DateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getSestemDate();
        TypedArray array = initAttributes(context, attrs);
        array.recycle();
    }

    // 初始化attrs自定义属性 @author Ysw created at 2016/4/12 11:21
    private TypedArray initAttributes(Context context, AttributeSet attrs) {
        TypedArray Array = context.obtainStyledAttributes(attrs, R.styleable.DateView);
        if (Array == null) return null;
        int count = Array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = Array.getIndex(i);
            switch (attr) {
                case R.styleable.DateView_leftBitmap:
                    //  liftBitmap = getBitmap(Array.getDrawable(attr));会放大
                    liftBitmap = getBitmap(R.drawable.left);
                    break;
                case R.styleable.DateView_leftBitmapMargLeft:
                    leftBitmapMargLeft = Array.getDimensionPixelOffset(attr, leftBitmapMargLeft);
                    break;
                case R.styleable.DateView_rightBitmap:
                    //  rightBitmap = getBitmap(Array.getDrawable(attr));会放大
                    rightBitmap = getBitmap(R.drawable.right);
                    break;
                case R.styleable.DateView_rightBitmapMargRight:
                    rightBitmapMargRight = Array.getDimensionPixelOffset(attr, rightBitmapMargRight);
                    break;
                case R.styleable.DateView_topHeight:
                    topHeight = Array.getDimensionPixelOffset(attr, topHeight);
                    break;
                case R.styleable.DateView_topMiddleMarg:
                    topMiddleMarg = Array.getDimensionPixelOffset(attr, topMiddleMarg);
                    break;
                case R.styleable.DateView_topBackgroundColor:
                    topBackgroundColor = Array.getColor(attr, topBackgroundColor);
                    break;
                case R.styleable.DateView_topAlpha:
                    topAlpha = Array.getInt(attr, topAlpha);
                    break;
                case R.styleable.DateView_topTextSize:
                    // 获取文字的大小 @author Ysw created at 2016/4/12 16:35
                    topTextSize = Array.getDimensionPixelSize(attr, topTextSize);
                    break;
                case R.styleable.DateView_topTextColor:
                    topTextColor = Array.getColor(attr, topTextColor);
                    break;

                case R.styleable.DateView_weekLineHeight:
                    weekLineHeight = Array.getDimensionPixelOffset(attr, weekLineHeight);
                    break;
                case R.styleable.DateView_weekLineBackgroundColor:
                    weekLineBackgroundColor = Array.getColor(attr, weekLineBackgroundColor);
                    break;
                case R.styleable.DateView_weekLineTextSize:
                    // 获取文字的大小 @author Ysw created at 2016/4/12 16:35
                    weekLineTextSize = Array.getDimensionPixelSize(attr, weekLineTextSize);
                    break;
                case R.styleable.DateView_weekLineTextColor:
                    weekLineTextColor = Array.getColor(attr, weekLineTextColor);
                    break;
                case R.styleable.DateView_weekLineMargLeft:
                    weekLineMargLeft = Array.getDimensionPixelOffset(attr, weekLineMargLeft);
                    break;
                case R.styleable.DateView_weekLineMargRight:
                    weekLineMargRight = Array.getDimensionPixelOffset(attr, weekLineMargRight);
                    break;
                case R.styleable.DateView_dateLineHeight:
                    dateLineHeight = Array.getDimensionPixelOffset(attr, dateLineHeight);
                    break;
                case R.styleable.DateView_dateLineBackgroundColor:
                    dateLineBackgroundColor = Array.getColor(attr, dateLineBackgroundColor);
                    break;
                case R.styleable.DateView_dateLineTextSize:
                    dateLineTextSize = Array.getDimensionPixelSize(attr, dateLineTextSize);
                    break;
                case R.styleable.DateView_dateLineTextColor:
                    dateLineTextColor = Array.getColor(attr, dateLineTextColor);
                    break;
                case R.styleable.DateView_dateLineClickBackgroundColor:
                    dateLineClickBackgroundColor = Array.getColor(attr, dateLineClickBackgroundColor);
                    break;
                case R.styleable.DateView_dateLineUpperRightBitmap:
                    dateLineUpperRightBitmap = getBitmap(R.drawable.qian);
                    break;
                case R.styleable.DateView_dateLineRowHeight:
                    dateLineRowHeight = Array.getDimensionPixelOffset(attr, dateLineRowHeight);
                    if (dateLineRowHeight > dateLineHeight / Row) dateLineRowHeight = dateLineHeight / Row;
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
        } else {
            mHeight = topHeight + weekLineHeight + dateLineHeight;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DrawTop(canvas);
        DrwaWeek(canvas);
        DrawDate(canvas);
    }

    // Top 部分的绘制 @author Ysw created at 2016/4/12 15:41
    private void DrawTop(Canvas canvas) {
        // 绘制顶部的背景 @author Ysw created at 2016/4/12 20:47
        topPaint.reset();
        topPaint.setStyle(Paint.Style.FILL);
        topPaint.setColor(topBackgroundColor);
        topPaint.setAntiAlias(true);
        topPaint.setAlpha(topAlpha);
        canvas.drawRect(0, 0, getWidth(), topHeight, topPaint);
        // 绘制左边上一个月按钮 @author Ysw created at 2016/4/12 16:05
        topPaint.reset();
        topPaint.setAntiAlias(true);
        canvas.drawBitmap(liftBitmap, leftBitmapMargLeft, (topHeight - liftBitmap.getHeight()) / 2, topPaint);
        // 绘制顶部中间文字部分 @author Ysw created at 2016/4/12 16:04
        topPaint.reset();
        topPaint.setStyle(Paint.Style.FILL);
        topPaint.setColor(topTextColor);
        topPaint.setAntiAlias(true);
        topPaint.setTextSize(topTextSize);
        String year = systemYear + "年";
        int startX = getWidth() / 2 - (int) topPaint.measureText(year) - topMiddleMarg / 2;
        int startY = (int) (topHeight / 2 - (topPaint.ascent() + topPaint.descent()) / 2);
        canvas.drawText(year, startX, startY, topPaint);
        String month = systemMonth + 1 + "月" + systemDay + "号";
        startX = getWidth() / 2 + topMiddleMarg / 2;
        startY = (int) (topHeight / 2 - (topPaint.ascent() + topPaint.descent()) / 2);
        canvas.drawText(month, startX, startY, topPaint);
        // 绘制右边下一个月按钮 @author Ysw created at 2016/4/12 16:19
        topPaint.reset();
        topPaint.setAntiAlias(true);
        canvas.drawBitmap(rightBitmap, getWidth() - rightBitmap.getWidth() - rightBitmapMargRight, (topHeight - rightBitmap.getHeight()) / 2, topPaint);
    }

    // 画星期栏 @author Ysw created at 2016/4/12 20:42
    private void DrwaWeek(Canvas canvas) {
        // 绘制星期栏的背景 @author Ysw created at 2016/4/12 22:34
        weekPaint.reset();
        weekPaint.setStyle(Paint.Style.FILL);
        weekPaint.setColor(weekLineBackgroundColor);
        weekPaint.setAntiAlias(true);
        canvas.drawRect(0, topHeight, getWidth(), topHeight + weekLineHeight, weekPaint);
        // 绘制星期数 @author Ysw created at 2016/4/12 22:34
        weekPaint.reset();
        weekPaint.setStyle(Paint.Style.FILL);
        weekPaint.setColor(weekLineTextColor);
        weekPaint.setTextSize(weekLineTextSize);
        weekPaint.setAntiAlias(true);
        int count = weeks.length;
        int columnSize = (getWidth() - weekLineMargLeft - weekLineMargRight) / 7;
        for (int i = 0; i < count; i++) {
            String week = weeks[i];
            int startX = weekLineMargLeft + columnSize * (i + 1) - (int) (weekPaint.measureText(week)) / 2 - columnSize / 2;
            int startY = (int) (topHeight + weekLineHeight / 2 - (weekPaint.ascent() + weekPaint.descent()) / 2);
            canvas.drawText(week, startX, startY, weekPaint);
        }
    }

    // 绘制日期部分 @author Ysw created at 2016/4/12 23:08
    private void DrawDate(Canvas canvas) {
        // 获取每个月的总天数 @author Ysw created at 2016/4/12 9:58
        int allDays = DateUtils.getMonthDays(systemYear, systemMonth);
        // 获取每个月的1号是周几 @author Ysw created at 2016/4/12 9:58
        int weekNumber = DateUtils.getFirstDayWeek(systemYear, systemMonth);
        // 计算每一列的宽度 @author Ysw created at 2016/4/13 9:45
        dateLineColumnWidth = (getWidth() - weekLineMargLeft - weekLineMargRight) / Column;
        // 日期总体背景的绘制 @author Ysw created at 2016/4/13 10:37
        datePaint.reset();
        datePaint.setStyle(Paint.Style.FILL);
        datePaint.setColor(dateLineBackgroundColor);
        datePaint.setAntiAlias(true);
        canvas.drawRect(0, topHeight + weekLineHeight, getWidth(), topHeight + weekLineHeight + dateLineRowHeight * Row, datePaint);
        // 初始化画笔 @author Ysw created at 2016/4/13 9:59
        datePaint.reset();
        datePaint.setStyle(Paint.Style.FILL);
        datePaint.setTextSize(dateLineTextSize);
        datePaint.setColor(dateLineTextColor);
        datePaint.setAntiAlias(true);
        daysString = new int[6][7];
        for (int i = 0; i < allDays; i++) {
            int day = i + 1;
            // 计算当天属于第几行 @author Ysw created at 2016/4/12 10:04
            int row = (i + weekNumber - 1) / 7;
            // 获取当天是周几，星期天返回的是"1"以此类推 @author Ysw created at 2016/4/13 9:36
            int dayofWeek = DateUtils.getDayofWeek(systemYear, systemMonth, i + 1);
            // 将这个月的所有天数放到一个数组里面去 @author Ysw created at 2016/4/13 16:07
            daysString[row][dayofWeek - 1] = day;

            // 计算绘制文字的坐标点 @author Ysw created at 2016/4/13 10:58
            int startX = weekLineMargLeft + dateLineColumnWidth * dayofWeek - dateLineColumnWidth / 2 - (int) datePaint.measureText("" + day) / 2;
            int startY = topHeight + weekLineHeight + dateLineRowHeight * (row + 1) - (int) (datePaint.ascent() + datePaint.descent()) / 2 - dateLineRowHeight / 2;
            // 绘制当前日期背景 @author Ysw created at 2016/4/13 10:46
            if (i == systemDay - 1) {
                datePaint.setColor(dateLineClickBackgroundColor);
                int x = startX + (int) datePaint.measureText("" + day) / 2;
                int y = startY + (int) (datePaint.ascent() + datePaint.descent()) / 2;
                float radius = dateLineColumnWidth / 4;
                canvas.drawCircle(x, y, radius, datePaint);
                datePaint.setColor(dateLineTextColor);
                canvas.drawBitmap(dateLineUpperRightBitmap, weekLineMargLeft + dateLineColumnWidth * dayofWeek - dateLineUpperRightBitmap.getWidth(),
                        topHeight + weekLineHeight + dateLineRowHeight * row, datePaint);
            }
            canvas.drawText("" + day, startX, startY, datePaint);
        }
    }

    /**
     * 屏幕的点击监听 @author Ysw created at 2016/4/13 14:32
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int eventAction = event.getAction();
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                upX = event.getX();
                upY = event.getY();
                double sqrt = Math.sqrt(Math.abs(downX - upX) * Math.abs(downX - upX) + Math.abs(downY - upY) * Math.abs(downY - upY));
                if (sqrt < 20) {
                    performClick();
                    onActionClick((int) (downX + upX) / 2, (int) (downY + upY) / 2);
                }
                downX = 0.0;
                downY = 0.0;
                upX = 0.0;
                upY = 0.0;
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    /**
     * 屏幕的点击事件 @author Ysw created at 2016/4/13 15:35
     */
    public void onActionClick(int X, int Y) {
        if (X <= topHeight && Y < topHeight) {
            setLastMonth();
        } else if (X > (getWidth() - topHeight) && Y < topHeight) {
            setNextMonth();
        } else if (Y > (topHeight + weekLineHeight) && Y < (topHeight + weekLineHeight + dateLineRowHeight * Row)) {
            int column = (X - weekLineMargLeft) / dateLineColumnWidth;
            int row = (Y - topHeight - weekLineHeight) / dateLineRowHeight;
            if (column >= 0 && column < 7 && row >= 0 && row < 6) {
                int day = daysString[row][column];
                if (day != 0) {
                    systemDay = day;
                    postInvalidate();
                }
            }
        }
    }

    /**
     * 获取系统的时间 @author Ysw created at 2016/4/12 23:15
     */
    private void getSestemDate() {
        // 获取系统的年月日 @author Ysw created at 2016/4/12 9:26
        Calendar calendar = Calendar.getInstance();
        systemYear = calendar.get(Calendar.YEAR);
        systemMonth = calendar.get(Calendar.MONTH);
        systemDay = calendar.get(Calendar.DATE);
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

    /**
     * 获取 Bitmap 对象的方法，通过资源 ID Drawable 等 @author Ysw created at 2016/4/12 17:58
     */
    // 获取图片资源方法一 @author Ysw created at 2016/4/12 14:46
    public Bitmap getBitmap(int id) {
        Resources rec = getResources();
        InputStream in = rec.openRawResource(id);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        return bitmap;
    }

    // 获取图片资源方法二 @author Ysw created at 2016/4/12 15:35
    public Bitmap getBitmap(int id, int i) {
        Resources rec = getResources();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) rec.getDrawable(id);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        return bitmap;
    }

    // 获取图片资源方法三 @author Ysw created at 2016/4/12 15:36
    public Bitmap getBitmap(int id, String str) {
        Resources rec = getResources();
        InputStream in = rec.openRawResource(id);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(in);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        return bitmap;
    }

    // 将 Drawable 转换成 Bitmap @author Ysw created at 2016/4/12 17:49
    public Bitmap getBitmap(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        return bitmap;
    }

    /**
     * 对日期进行相关的设置 @author Ysw created at 2016/4/13 12:59
     */

    // 设置左边上一个月图标 @author Ysw created at 2016/4/13 11:43
    public void setLiftBitmap(int id) {
        liftBitmap = getBitmap(id);
        postInvalidate();
    }

    // 设置左边上一个月图标与左边的距离 @author Ysw created at 2016/4/13 11:46
    public void setLeftBitmapMargLeft(int margLeft) {
        leftBitmapMargLeft = Dp2Px(getContext(), margLeft);
        postInvalidate();
    }

    // 设置右边下一个月图标 @author Ysw created at 2016/4/13 11:43
    public void setRightBitmap(int id) {
        rightBitmap = getBitmap(id);
        postInvalidate();
    }

    // 设置右边下一个月与右边的距离 @author Ysw created at 2016/4/13 11:49
    public void setRightBitmapMargRight(int margRight) {
        rightBitmapMargRight = Dp2Px(getContext(), margRight);
        postInvalidate();
    }

    // 设置年和时间的中间距离 @author Ysw created at 2016/4/13 11:44
    public void setTopMiddleMarg(int marg) {
        topMiddleMarg = Dp2Px(getContext(), marg);
        postInvalidate();
    }

    // 设置顶部总体背景颜色 @author Ysw created at 2016/4/13 11:50
    public void setTopBackgroundColor(int color) {
        topBackgroundColor = color;
        postInvalidate();
    }

    // 设置顶部背景透明度 @author Ysw created at 2016/4/13 11:51
    public void setTopAlpha(int alpha) {
        topAlpha = alpha;
        postInvalidate();
    }

    // 设置顶部字体颜色 @author Ysw created at 2016/4/13 11:52
    public void setTopTextColor(int textColor) {
        topTextColor = textColor;
        postInvalidate();
    }

    // 设置顶部字体大小 @author Ysw created at 2016/4/13 11:53
    public void setTopTextSize(int textSize) {
        topTextSize = Sp2px(getContext(), textSize);
        postInvalidate();
    }

    // 设置顶部的高度 @author Ysw created at 2016/4/13 11:56
    public void setTopHeight(int height) {
        topHeight = Dp2Px(getContext(), height);
        postInvalidate();
    }

    // 设置星期栏高度 @author Ysw created at 2016/4/13 11:58
    public void setWeekLineHeight(int height) {
        weekLineHeight = Dp2Px(getContext(), height);
        postInvalidate();
    }

    // 设置星期栏背景颜色 @author Ysw created at 2016/4/13 11:59
    public void setWeekLineBackgroundColor(int color) {
        weekLineBackgroundColor = color;
        postInvalidate();
    }

    // 设置星期栏左边与左边的距离 @author Ysw created at 2016/4/13 12:00
    public void setWeekLineMargLeft(int margLeft) {
        weekLineMargLeft = Dp2Px(getContext(), margLeft);
        postInvalidate();
    }

    // 设置星期栏右边与右边的距离 @author Ysw created at 2016/4/13 12:02
    public void setWeekLineMargRight(int margRight) {
        weekLineMargRight = Dp2Px(getContext(), margRight);
        postInvalidate();
    }

    // 设置星期栏字体大小 @author Ysw created at 2016/4/13 12:22
    public void setWeekLineTextSize(int textSize) {
        weekLineTextSize = Dp2Px(getContext(), textSize);
        postInvalidate();
    }

    // 设置星期栏字体颜色 @author Ysw created at 2016/4/13 12:23
    public void setWeekLineTextColor(int color) {
        weekLineTextColor = color;
        postInvalidate();
    }

    // 设置日期栏总体背景颜色 @author Ysw created at 2016/4/13 12:41 
    public void setDateLineBackgroundColor(int color) {
        dateLineBackgroundColor = color;
        postInvalidate();
    }

    // 设置日期栏点击或被选中日期的背景颜色 @author Ysw created at 2016/4/13 12:43
    public void setDateLineClickBackgroundColor(int color) {
        dateLineClickBackgroundColor = color;
        postInvalidate();
    }

    // 设置日期栏字体大小 @author Ysw created at 2016/4/13 12:52
    public void setDateLineTextSize(int textSize) {
        dateLineTextSize = Dp2Px(getContext(), textSize);
        postInvalidate();
    }

    // 设置日期栏字体颜色 @author Ysw created at 2016/4/13 12:53
    public void setDateLineTextColor(int color) {
        dateLineTextColor = color;
        postInvalidate();
    }

    // 设置日期栏事项显示图片 @author Ysw created at 2016/4/13 12:54
    public void setDateLineUpperRightBitmap(int id) {
        dateLineUpperRightBitmap = getBitmap(id);
        postInvalidate();
    }

    // 设置日期栏每一行的高度 @author Ysw created at 2016/4/13 12:55
    public void setDateLineRowHeight(int height) {
        dateLineRowHeight = Dp2Px(getContext(), height);
        postInvalidate();
    }

    // 设置日期栏总体的高度，这个方法与日期栏每一行高度的设置在逻辑上会有冲突 @author Ysw created at 2016/4/13 12:56
    public void setDateLineHeight(int height) {
        dateLineHeight = Dp2Px(getContext(), height);
        postInvalidate();
    }

    // 设置年份 @author Ysw created at 2016/4/13 13:24
    public void setYear(int year) {
        systemYear = year;
        postInvalidate();
    }

    // 设置月份 @author Ysw created at 2016/4/13 13:25
    public void setSystemMonth(int month) {
        systemMonth = month - 1;
        postInvalidate();
    }

    // 设置日期 @author Ysw created at 2016/4/13 13:26
    public void setSystemDay(int day) {
        systemDay = day;
        postInvalidate();
    }

    // 点击上一个月 @author Ysw created at 2016/4/13 13:27
    public void setLastMonth() {
        if (systemMonth == 0) {
            systemMonth = 11;
            systemYear--;
        } else {
            systemMonth--;
        }
        switch (systemMonth + 1) {
            case 2:
                // 判断是否是闰年 @author Ysw created at 2016/4/13 20:30
                if (((systemYear % 4 == 0) && (systemYear % 100 != 0)) || (systemYear % 400 == 0)) {
                    if (systemDay > 29) {
                        systemDay = 29;
                    }
                } else {
                    if (systemDay > 28) {
                        systemDay = 28;
                    }
                }
                postInvalidate();
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (systemDay > 30) {
                    systemDay = 30;
                }
                postInvalidate();
                break;
            default:
                postInvalidate();
                break;
        }
    }

    // 点击下一个月 @author Ysw created at 2016/4/13 13:31
    public void setNextMonth() {
        if (systemMonth == 11) {
            systemMonth = 0;
            systemYear++;
        } else {
            systemMonth++;
        }
        switch (systemMonth + 1) {
            case 2:
                // 判断是否是闰年 @author Ysw created at 2016/4/13 20:30
                if (((systemYear % 4 == 0) && (systemYear % 100 != 0)) || (systemYear % 400 == 0)) {
                    if (systemDay > 29) {
                        systemDay = 29;
                    }
                } else {
                    if (systemDay > 28) {
                        systemDay = 28;
                    }
                }
                postInvalidate();
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (systemDay > 30) {
                    systemDay = 30;
                }
                postInvalidate();
                break;
            default:
                postInvalidate();
                break;
        }
    }
}
