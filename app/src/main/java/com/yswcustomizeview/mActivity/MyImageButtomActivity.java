package com.yswcustomizeview.mActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mImageButtom.InfoImageButton;

public class MyImageButtomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image_buttom);
        InfoImageButton mInage = (InfoImageButton) findViewById(R.id.mInage);
    }
}
