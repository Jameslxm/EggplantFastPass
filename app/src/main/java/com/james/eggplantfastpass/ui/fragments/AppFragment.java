package com.james.eggplantfastpass.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.adapter.AppAdapter;
import com.james.eggplantfastpass.contract.AppContract;
import com.james.eggplantfastpass.presenter.AppPresenterImpl;
import com.james.eggplantfastpass.ui.fragments.base.BaseLazyFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class AppFragment extends BaseLazyFragment implements AppContract.AppView{
    @BindView(R.id.id_tv_send)
    TextView tvSend;
    @BindView(R.id.id_rv_content)
    RecyclerView rvContent;

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isFirst = true;
    private AppContract.AppPresenter appPresenter = new AppPresenterImpl();
    @Override
    public int getLayoutId() {
        return R.layout.frag_app;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        isPrepared = true;
        //new 适配器
        AppAdapter appAdapter = new AppAdapter();
        rvContent.setAdapter(appAdapter);//设置适配器
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        isFirst = false;
        getData();
    }
    @OnClick(R.id.id_tv_send)
    public void send(){

    }
    //获取数据
    private void getData(){


        //获取数据
        appPresenter.getData(this);

        //获取到数据，显示数据，没有获取到数据，提示用户


    }


    @Override
    public void onSuccessShow() {

    }


    @Override
    public void onFailShow() {

    }
}
