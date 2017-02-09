package com.james.eggplantfastpass.ui.fragments;

import android.os.Bundle;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.ui.fragments.base.BaseLazyFragment;
import com.james.eggplantfastpass.ui.widget.ProportionView;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class FileFragment extends BaseLazyFragment{
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isFirst = true;
    @BindView(R.id.id_proportion_view)
    ProportionView proportionView;
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
        proportionView.setUseValue(50);
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
