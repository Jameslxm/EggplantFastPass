package com.james.eggplantfastpass;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.james.eggplantfastpass.adapter.MultiTypeAdapter;
import com.james.eggplantfastpass.model.Five;
import com.james.eggplantfastpass.model.Four;
import com.james.eggplantfastpass.model.Normal;
import com.james.eggplantfastpass.model.One;
import com.james.eggplantfastpass.model.Three;
import com.james.eggplantfastpass.model.Two;
import com.james.eggplantfastpass.model.Visitable;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.id_mainNavView)
    NavigationView mainNavView;//抽屉
    @BindView(R.id.id_rv_content)
    RecyclerView rvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mainNavView.setItemTextColor(null);
        mainNavView.setItemIconTintList(null);
        //new 适配器
//        HomePageAdapter homePageAdapter = new HomePageAdapter();
//
//        //设置适配器
//        rvContent.setAdapter(homePageAdapter);
        //设置RecyclerView 的布局
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        List<Visitable> list = getData();
        list.add(0,new One("Type One 0"));
        list.add(1,new Two("Type Two 0"));
        list.add(2,new Three("Type Three 0"));
        list.add(3,new Normal("Type Three 0"));
        list.add(4,new Normal("Type Three 0"));
        list.add(5,new Normal("Type Three 0"));

        list.add(6,new Four());
        list.add(7,new Five());
        rvContent.setAdapter(new MultiTypeAdapter(list));
    }

    private List<Visitable> getData(){
        List<Visitable> models = new ArrayList<>();

        for (int index = 0; index < 50; index++ ){
            models.add(new Normal("Type normal "+ index));
        }

        return models;
    }

}
