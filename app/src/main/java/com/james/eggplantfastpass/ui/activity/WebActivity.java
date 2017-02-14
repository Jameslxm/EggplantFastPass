package com.james.eggplantfastpass.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;
import com.james.eggplantfastpass.ui.widget.BrowserLayout;

import butterknife.BindView;

/**
 * Created by 1 on 2017/2/14.
 */

public class WebActivity extends BaseActivity {
    public static final String FILE_NAME_KEY = "file_name_key";
    @BindView(R.id.brower)
    BrowserLayout browserLayout;


    @Override
    protected int getLayoutId() {
        return R.layout.aty_web;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        String fileName = getIntent().getStringExtra(FILE_NAME_KEY);
        if(!TextUtils.isEmpty(fileName)) {
            String url = "file:///android_asset/help/" + fileName+".html";
            browserLayout.loadUrl(url);
        }
        tvTitle.setText("Details");
        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.close_selector));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (browserLayout.canGoBack()) {
            browserLayout.getWebView().goBack();
        } else {
            super.onBackPressed();
        }
    }
}
