package com.james.eggplantfastpass.ui.activity.base;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.james.eggplantfastpass.R;

import org.james.material.statusbar.StatusBarUtil;
import org.james.material.swipebacklayout.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1 on 2016/9/20.
 */
public abstract class BaseSwipeBackActivity extends SwipeBackActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(ContextCompat.getColor(BaseSwipeBackActivity.this, R.color.colorPrimaryDark));
        afterCreate(savedInstanceState);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    protected abstract int getLayoutId();
    protected abstract void afterCreate(Bundle savedInstanceState);

}
