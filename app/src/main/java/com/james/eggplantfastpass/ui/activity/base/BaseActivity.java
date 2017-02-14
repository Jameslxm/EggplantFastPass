package com.james.eggplantfastpass.ui.activity.base;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.james.eggplantfastpass.R;

import org.james.material.statusbar.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/15 0015.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.id_tv_title)
    protected TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.colorPrimaryDark));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        afterCreate(savedInstanceState);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    protected abstract int getLayoutId();
    protected abstract void afterCreate(Bundle savedInstanceState);

}
