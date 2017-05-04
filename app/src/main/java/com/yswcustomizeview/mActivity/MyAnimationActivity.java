package com.yswcustomizeview.mActivity;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mAnimation.BezierCurve;
import com.yswcustomizeview.mAnimation.ClipPath;
import com.yswcustomizeview.mAnimation.MyAnimationListener;

public class MyAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rl_first;
    private RelativeLayout rl_second;
    private Button bt_start;
    private RelativeLayout rl_color;
    private Button bt_color;
    private int mColor[] = {Color.parseColor("#7187F6"), Color.parseColor("#518DEF")};
    private BezierCurve bezierCurve;
    private Button bt_change;
    private float mState = 0f;
    private float value = 2;
    private float oldValue = 2;
    private Button bt_delete;
    private RelativeLayout rl_three;
    private int times;
//    private ClipPath clipPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animation);
        initUI();
    }

    private void initUI() {
        rl_first = (RelativeLayout) findViewById(R.id.rl_first);
        rl_second = (RelativeLayout) findViewById(R.id.rl_second);
        rl_three = (RelativeLayout) findViewById(R.id.rl_three);
        rl_color = (RelativeLayout) findViewById(R.id.rl_color);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_color = (Button) findViewById(R.id.bt_color);
        bt_change = (Button) findViewById(R.id.bt_change);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bezierCurve = (BezierCurve) findViewById(R.id.bezierCurve);
//        clipPath = (ClipPath) findViewById(R.id.clipPath);
        bt_start.setOnClickListener(this);
        bt_color.setOnClickListener(this);
        bt_change.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, mColor);
        rl_color.setBackground(drawable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                startTranslationAnimation(times);
                times++;
                break;
            case R.id.bt_color:
                startColorAnimation();
                break;
            case R.id.bt_change:
                changeValue();
                break;
            case R.id.bt_delete:
                delete();
                backTranslationAnimation(times);
                times--;
                break;
            default:
                break;
        }
    }

    private void delete() {
        if (mState == 0)
            return;
        mState--;
        if (value > 20) {
            value -= 9;
        } else if (value > 2 && value < 13) {
            value -= 5;
        } else {
            value = 2;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(oldValue, value);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                bezierCurve.setProgrelss(value, mState, true);
//                clipPath.setProgrelss(value, mState, true);
            }
        });
        animator.addListener(new MyAnimationListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                oldValue = value;
            }
        });
        animator.setDuration(1000);
        animator.start();
    }

    private void changeValue() {
        if (mState == 3)
            return;
        mState++;
        if (value < 11) {
            value += 5;
        } else if (value > 11 && value < 21) {
            value += 9;
        } else {
            value = 2;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(oldValue, value);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                bezierCurve.setProgrelss(value, mState, false);
//                clipPath.setProgrelss(value, mState, false);
            }
        });

        animator.addListener(new MyAnimationListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                oldValue = value;
            }
        });
        animator.setDuration(1000);
        animator.start();
    }

    private void backTranslationAnimation(int times) {
        ObjectAnimator one = ObjectAnimator.ofFloat(rl_first, "translationX", times * rl_first.getWidth(), (times - 1) * rl_first.getWidth()).setDuration(1000);
        one.start();
        ObjectAnimator two = ObjectAnimator.ofFloat(rl_second, "translationX", times * rl_first.getWidth(), (times - 1) * rl_second.getWidth()).setDuration(1000);
        two.start();
        ObjectAnimator three = ObjectAnimator.ofFloat(rl_three, "translationX", times * rl_first.getWidth(), (times - 1) * rl_three.getWidth()).setDuration(1000);
        three.start();
    }

    private void startTranslationAnimation(int times) {
        /**
         * ObjectAnimation是一个View的单个属性动画 @author Ysw created at 2017/3/2 16:25
         */
        ViewGroup.MarginLayoutParams lp_first = (ViewGroup.MarginLayoutParams) rl_first.getLayoutParams();
        ViewGroup.MarginLayoutParams lp_second = (ViewGroup.MarginLayoutParams) rl_second.getLayoutParams();
        ViewGroup.MarginLayoutParams lp_three = (ViewGroup.MarginLayoutParams) rl_three.getLayoutParams();
        ObjectAnimator one = ObjectAnimator.ofFloat(rl_first, "translationX", times * rl_first.getWidth(), (times + 1) * rl_first.getWidth()).setDuration(1000);
        one.start();
        ObjectAnimator two = ObjectAnimator.ofFloat(rl_second, "translationX", times * rl_first.getWidth(), (times + 1) * rl_second.getWidth()).setDuration(1000);
        two.start();
        ObjectAnimator three = ObjectAnimator.ofFloat(rl_three, "translationX", times * rl_first.getWidth(), (times + 1) * rl_three.getWidth()).setDuration(1000);
        three.start();

        /**
         * 属性动画中的一种通过监听动画过程中值的改变,并设置其属性值来达到动画的效果 @author Ysw created at 2017/3/2 16:23
         */
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 0f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue();
//                rl_first.setAlpha(animatedValue);
//                rl_second.setAlpha(animatedValue);
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();

        /**
         * 属性动画集合，只有设置了setFillAfter(true)的时候View的位置属性才变化，是一个动画集合 @author Ysw created at 2017/3/2 16:21
         */
//        AnimationSet set = new AnimationSet(true);// 设置为True表示同用一个共同的差值器 @author Ysw created at 2017/3/2 16:20
//        TranslateAnimation one = new TranslateAnimation(0, -lp_first.leftMargin, 0, 0);// 位移动画 @author Ysw created at 2017/3/2 16:20
//        TranslateAnimation two = new TranslateAnimation(0, rl_first.getWidth() + lp_first.rightMargin, 0, 0);
//        AlphaAnimation three = new AlphaAnimation(1f, 0f);// 透明度动画 @author Ysw created at 2017/3/2 16:19
//        set.addAnimation(one);
//        set.addAnimation(two);
//        set.addAnimation(three);
//        set.setFillAfter(true);// 动画结束后View留在动画结束的位置，不然在回到原来的位置 @author Ysw created at 2017/3/2 16:18
//        set.setStartOffset(100);// 每个动画间隔100ms后播放 @author Ysw created at 2017/3/2 16:19
//        set.setDuration(5000);
//        rl_first.startAnimation(set);
    }

    private void startColorAnimation() {
        ObjectAnimator colorAnim = ObjectAnimator.ofInt(rl_color, "backgroundColor", Color.parseColor("#FFBBFF"), Color.parseColor("#518DEF"));
        colorAnim.setDuration(2000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }
}
