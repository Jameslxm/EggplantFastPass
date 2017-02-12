package com.james.eggplantfastpass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.james.eggplantfastpass.adapter.MultiTypeAdapter;
import com.james.eggplantfastpass.model.Five;
import com.james.eggplantfastpass.model.Four;
import com.james.eggplantfastpass.model.Normal;
import com.james.eggplantfastpass.model.One;
import com.james.eggplantfastpass.model.Three;
import com.james.eggplantfastpass.model.Two;
import com.james.eggplantfastpass.model.Visitable;
import com.james.eggplantfastpass.ui.activity.AboutActivity;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.id_mainNavView)
    NavigationView nvDrawer;//抽屉
    @BindView(R.id.id_rv_content)
    RecyclerView rvContent;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        nvDrawer.setItemTextColor(null);
        nvDrawer.setItemIconTintList(null);
        //new 适配器
//        HomePageAdapter homePageAdapter = new HomePageAdapter();
//
//        //设置适配器
//        rvContent.setAdapter(homePageAdapter);
        //设置RecyclerView 的布局
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        List<Visitable> list = getData();
        list.add(0, new One("Type One 0"));
        list.add(1, new Two("Type Two 0"));
        list.add(2, new Three("Type Three 0"));
        list.add(3, new Normal("Type Three 0"));
        list.add(4, new Normal("Type Three 0"));
        list.add(5, new Normal("Type Three 0"));

        list.add(6, new Four());
        list.add(7, new Five());
        rvContent.setAdapter(new MultiTypeAdapter(list));
        setupDrawerContent(nvDrawer);

    }

    private List<Visitable> getData() {
        List<Visitable> models = new ArrayList<>();

        for (int index = 0; index < 50; index++) {
            models.add(new Normal("Type normal " + index));
        }

        return models;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            //连接电脑
            case R.id.id_menu_link_computer:

                break;

            //网页传
            case R.id.id_menu_web_page_spread:

                break;

            //空间清理
            case R.id.id_menu_space_clean_up:
                break;
            //应用锁
            case R.id.id_menu_application_of_lock:


                break;

            //茄子换机
            case R.id.id_menu_eggplant_replacement:

                break;

            //设置
            case R.id.id_menu_setting:

                break;

            //检查版本
            case R.id.id_menu_check_version:

                break;
            //帮助与反馈
            case R.id.id_menu_help_feed_back:

                break;
            //关于
            case R.id.id_menu_about:
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
        }
        mDrawer.closeDrawers();

    }

}
