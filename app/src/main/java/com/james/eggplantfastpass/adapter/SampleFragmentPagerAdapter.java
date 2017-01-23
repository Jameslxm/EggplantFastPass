package com.james.eggplantfastpass.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.james.eggplantfastpass.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = null;
    private Context context;
    private List<Fragment> fragmentList;
    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

        tabTitles = context.getResources().getStringArray(R.array.titles);
        fragmentList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return tabTitles==null ? 0 : tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles==null ? "" :tabTitles[position];
    }
    public void addFragmentToList(List<Fragment> fragmentList){
        if(fragmentList != null && fragmentList.size()>0){
            this.fragmentList.addAll(fragmentList);
            notifyDataSetChanged();
        }
    }
}
