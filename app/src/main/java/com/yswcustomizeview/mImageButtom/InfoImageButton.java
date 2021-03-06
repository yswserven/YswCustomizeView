package com.yswcustomizeview.mImageButtom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mUtils.UtilsTools;

import java.io.InputStream;

/**
 * Created by： Ysw on 2016/6/2.11:26.
 */
public class InfoImageButton extends View {

    private Bitmap mImage = null;

    private int mTextBackGroundColor = Color.parseColor("#FF0000");

    private int mTextColor = Color.parseColor("#FFFFFF");

    private String mText = "8";

    private float mWidth = UtilsTools.Dp2Px(getContext(), 30);

    private float mHeight = UtilsTools.Dp2Px(getContext(), 30);

    private float mImageWidth = 0;

    private float mImageHeight = 0;

    private float mTextSize = UtilsTools.Sp2px(getContext(), 7);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Rect mBounds = new Rect();

    private float mPadding = 20;

    private float mScale;

    private Matrix mMatrix = null;

    public InfoImageButton(Context context) {
        super(context);
        init();
    }

    public InfoImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InfoImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mImage = getBitmap(R.drawable.slide_button);
        mImageWidth = mImage.getWidth();
        mImageHeight = mImage.getHeight();
        mMatrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize + mPadding;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = mImage.getWidth() + mPadding;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize + mPadding;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = mImage.getHeight() + mPadding;
        }
        mScale = (mWidth - mPadding) / mImageWidth;
        Log.e("Ysw", "mScale = " + mScale);
        Log.e("Ysw", "mWidth = " + mWidth);
        Log.e("Ysw", "mPadding = " + mPadding);
        Log.e("Ysw", "mImageWidth = " + mImageWidth);
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawImage(canvas);
        drawText(canvas);
    }

    private void drawImage(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
//        canvas.drawBitmap(mImage, mPadding / 2, mPadding / 2, mPaint);
        mMatrix.reset();
        //将图像放大，参数：水平放大倍数，垂直放大倍数；如果小于1，则是缩小
        mMatrix.postScale(mScale, mScale);
        canvas.drawBitmap(mImage, mMatrix, mPaint);
        mMatrix.reset();
    }

    private void drawText(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextBackGroundColor);
        mPaint.getTextBounds(mText, 0, mText.length(), mBounds);
        float w = mBounds.width() / 2.0f + 5;
        float h = mBounds.height() / 2.0f + 5;
        float radius = w > h ? w : h;
        float x = (float) (mWidth / 2.0f + mWidth / 2 / Math.sqrt(2)) - mPadding;
        float y = (float) (mHeight / 2.0f - (mHeight - mPadding) / 2 / Math.sqrt(2));
        if (mText.equals("0") || mText.equals("")) return;
        canvas.drawCircle(x, y, radius, mPaint);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        if (mText.length() > 1) {
            mText = "...";
            canvas.drawText(mText, x - mPaint.measureText(mText) / 2.0f, y + (mPaint.ascent() + mPaint.descent()) / 2 + 6, mPaint);
        } else {
            canvas.drawText(mText, x - mPaint.measureText(mText) / 2.0f, y - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);
        }
    }


    private Bitmap getBitmap(int id) {
        Resources rec = getResources();
        InputStream in = rec.openRawResource(id);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        return bitmap;
    }

    public void setmText(String text) {
        mText = text;
        postInvalidate();
    }

    public void setImage(int resId) {
        mImage = getBitmap(resId);
        postInvalidate();
    }

    public void setTextBackGroundColor(int color) {
        mTextBackGroundColor = color;
        postInvalidate();
    }

    public void setmTextColor(int color) {
        mTextColor = color;
        postInvalidate();
    }

    public void setTextSize(float size) {
        mTextSize = UtilsTools.Sp2px(getContext(), size);
        postInvalidate();
    }

    public void setPadding(int padding) {
        mPadding = padding;
        postInvalidate();
    }

    public void scaleImage(float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        mImage = Bitmap.createBitmap(mImage, 0, 0, (int) mImageWidth, (int) mImageHeight, matrix, true);
        postInvalidate();
    }
}
