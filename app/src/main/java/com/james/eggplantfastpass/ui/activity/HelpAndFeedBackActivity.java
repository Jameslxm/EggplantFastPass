package com.james.eggplantfastpass.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.adapter.HelpAndFeedBackAdapter;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;

import org.james.material.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Created by 1 on 2017/2/13.
 */

public class HelpAndFeedBackActivity extends BaseActivity {
    @BindView(R.id.id_rv_content)
    RecyclerView rvContent;
    @BindView(R.id.id_tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String[] hotspotIssues;
    private HelpAndFeedBackAdapter helpAndFeedBackAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.aty_help_and_feedback;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示默认的title
        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.arrow_selector));
        tvTitle.setText(getString(R.string.help_feed_back));

        hotspotIssues = getResources().getStringArray(R.array.hotspot_issues_array);
        helpAndFeedBackAdapter = new HelpAndFeedBackAdapter(hotspotIssues);

        rvContent.setAdapter(helpAndFeedBackAdapter);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setIndex(2);
        rvContent.addItemDecoration(dividerItemDecoration);

    }
}
