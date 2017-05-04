package com.yswcustomizeview.mActivity;

import android.app.Activity;
import android.os.Bundle;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mViewGroupDemo.McoyProductContentPage;
import com.yswcustomizeview.mViewGroupDemo.McoyProductDetailInfoPage;
import com.yswcustomizeview.mViewGroupDemo.McoySnapPageLayout;

public class MyActivity extends Activity {

    private McoySnapPageLayout mcoySnapPageLayout = null;

    private McoyProductContentPage bottomPage = null;
    private McoyProductDetailInfoPage topPage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mcoySnapPageLayout = (McoySnapPageLayout) findViewById(R.id.flipLayout);
        topPage = new McoyProductDetailInfoPage(MyActivity.this, getLayoutInflater().inflate(R.layout.mcoy_produt_detail_layout, null));
        bottomPage = new McoyProductContentPage(MyActivity.this, getLayoutInflater().inflate(R.layout.mcoy_product_content_page, null));
        mcoySnapPageLayout.setSnapPages(topPage, bottomPage);
    }
}
