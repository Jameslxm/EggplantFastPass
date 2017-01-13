package com.james.eggplantfastpass;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.james.eggplantfastpass.adapter.HomePageAdapter;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.id_rv_content)
    RecyclerView rvContent;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //适配器
        HomePageAdapter homePageAdapter = new HomePageAdapter();
        rvContent.setAdapter(homePageAdapter);//显示数据
        rvContent.setLayoutManager(new LinearLayoutManager(this));
    }
}
