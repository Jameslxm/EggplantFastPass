package com.james.eggplantfastpass.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by 1 on 2017/2/9.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.id_tv_title)
    TextView tvTitle;
    @Override
    protected int getLayoutId() {
        return R.layout.aty_about;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示默认的title
        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.arrow_selector));
        tvTitle.setText(getString(R.string.about));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
