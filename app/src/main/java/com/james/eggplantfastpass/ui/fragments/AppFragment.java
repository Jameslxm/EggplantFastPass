package com.james.eggplantfastpass.ui.fragments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.adapter.AppAdapter;
import com.james.eggplantfastpass.bean.FileInfo;
import com.james.eggplantfastpass.contract.AppContract;
import com.james.eggplantfastpass.presenter.AppPresenterImpl;
import com.james.eggplantfastpass.ui.fragments.base.BaseLazyFragment;

import org.james.material.utils.ToastUtil;
import org.james.material.widget.ScrollChildSwipeRefreshLayout;

import java.util.List;

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
    @BindView(R.id.refresh_layout)
    ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    AppAdapter appAdapter;
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
        appAdapter = new AppAdapter();
        rvContent.setAdapter(appAdapter);//设置适配器
        rvContent.setLayoutManager(new GridLayoutManager(getActivity(),4));
        lazyLoad();
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        swipeRefreshLayout.setScrollUpChild(rvContent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                Log.d("debug", "refresh");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
        swipeRefreshLayout.setRefreshing(true);
        appPresenter.getData(getActivity(),this);
    }


    @Override
    public void onSuccessShow(List<FileInfo> fileInfoList) {
        //获取到数据，显示数据，没有获取到数据，提示用户
        swipeRefreshLayout.setRefreshing(false);
        if(fileInfoList!=null && fileInfoList.size()>0) {
            appAdapter.setDatas(fileInfoList);
        }else {
            ToastUtil.showShortToast(getActivity(),getString(R.string.msg_empty));
        }
    }


    @Override
    public void onFailShow() {
        swipeRefreshLayout.setRefreshing(false);
        ToastUtil.showShortToast(getActivity(),getString(R.string.error_tip));
    }
}
