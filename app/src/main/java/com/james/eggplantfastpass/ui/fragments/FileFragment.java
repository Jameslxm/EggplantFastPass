package com.james.eggplantfastpass.ui.fragments;

import android.os.Bundle;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.ui.fragments.base.BaseLazyFragment;


/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class FileFragment extends BaseLazyFragment{
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isFirst = true;
    @Override
    public int getLayoutId() {
        return R.layout.frag_file;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        isPrepared = true;
//        fragmentFPresenter = new FragmentFPresenterImpl();
//        pageFragmentFAdapter = new PageFragmentFAdapter(getActivity());
//        pageFragmentFAdapter.setiRecyclerViewItemListener(this);
//        init();
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        isFirst = false;
//        getData();
    }
}
