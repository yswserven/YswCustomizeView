package com.yswcustomizeview.mActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yswcustomizeview.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);
        Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(this);
        Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(this);
        Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(this);
        Button button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(this);
        Button button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(this);
        Button button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(this);
        Button button16 = (Button) findViewById(R.id.button16);
        button16.setOnClickListener(this);
        Button button17 = (Button) findViewById(R.id.button17);
        button17.setOnClickListener(this);
        Button button18 = (Button) findViewById(R.id.button18);
        button18.setOnClickListener(this);
        Button button19 = (Button) findViewById(R.id.button19);
        button19.setOnClickListener(this);
        Button button20 = (Button) findViewById(R.id.button20);
        button20.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.button1:
                intent.setClass(MainActivity.this, FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent.setClass(MainActivity.this, MultiScreenActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent.setClass(MainActivity.this, MyActivity.class);
                startActivity(intent);
                break;
            case R.id.button4:
                intent.setClass(MainActivity.this, MyFlowLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.button5:
                intent.setClass(MainActivity.this, MyImageButtomActivity.class);
                startActivity(intent);
                break;
            case R.id.button6:
                intent.setClass(MainActivity.this, MyPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.button7:
                intent.setClass(MainActivity.this, MyRecycleActivity.class);
                startActivity(intent);
                break;
            case R.id.button8:
                intent.setClass(MainActivity.this, MyRxJavaActivity.class);
                startActivity(intent);
                break;
            case R.id.button9:
                intent.setClass(MainActivity.this, MyScrollerActivity.class);
                startActivity(intent);
                break;
            case R.id.button10:
                intent.setClass(MainActivity.this, MyScrollerLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.button11:
                intent.setClass(MainActivity.this, MySlideActivity.class);
                startActivity(intent);
                break;
            case R.id.button12:
                intent.setClass(MainActivity.this, MySlidingMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.button13:
                intent.setClass(MainActivity.this, MyTextViewActivity.class);
                startActivity(intent);
                break;
            case R.id.button14:
                intent.setClass(MainActivity.this, MyViewGroupActivity.class);
                startActivity(intent);
                break;
            case R.id.button15:
                intent.setClass(MainActivity.this, MyAntsCreditActivity.class);
                startActivity(intent);
                break;
            case R.id.button16:
                intent.setClass(MainActivity.this, MyCityActivity.class);
                startActivity(intent);
                break;
            case R.id.button17:
                intent.setClass(MainActivity.this, MyAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.button18:
                intent.setClass(MainActivity.this, MyProgressBarActivity.class);
                startActivity(intent);
            case R.id.button19:
                intent.setClass(MainActivity.this, MyRotateAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.button20:
                intent.setClass(MainActivity.this, MyVerticalScrollLayoutActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
