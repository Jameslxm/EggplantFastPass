package com.james.eggplantfastpass.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.adapter.ProblemListAdapter;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;

import org.james.material.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 1 on 2017/2/14.
 */

public class ProblemListActivity extends BaseActivity {
    public static final String PROBLEM_KEY = "problem_key";
    public static final String FILES_NAME_KEY = "files_name_key";
    public static final String TITLE_KEY = "title_key";
    @BindView(R.id.id_tv_title)
    TextView tvTitle;
    @BindView(R.id.id_rv_content)
    RecyclerView rvContent;
    private ProblemListAdapter problemListAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.aty_problem_list;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示默认的title
        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.arrow_selector));
        List<String> problemList = getIntent().getStringArrayListExtra(PROBLEM_KEY);
        final List<String> fileNameList = getIntent().getStringArrayListExtra(FILES_NAME_KEY);
        String title = getIntent().getStringExtra(TITLE_KEY);
        tvTitle.setText(title);
        problemListAdapter = new ProblemListAdapter(problemList);
        rvContent.setAdapter(problemListAdapter);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        problemListAdapter.setOnRecyclerViewClickListener(new ProblemListAdapter.OnRecyclerViewClickListener() {
            @Override
            public void onRecyclerViewClick(int position) {
                if(fileNameList != null && fileNameList.size()>0) {
                    Intent intent = new Intent(ProblemListActivity.this, WebActivity.class);
                    Bundle bundle = new Bundle();
                    String fileName = fileNameList.get(position);
                    bundle.putString(WebActivity.FILE_NAME_KEY, fileName);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}
