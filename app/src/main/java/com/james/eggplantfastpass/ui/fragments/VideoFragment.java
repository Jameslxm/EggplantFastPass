package com.james.eggplantfastpass.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.adapter.VideoAdapter;
import com.james.eggplantfastpass.bean.VideoInfoBean;
import com.james.eggplantfastpass.contract.VideoContract;
import com.james.eggplantfastpass.presenter.VideoPresenterImpl;
import com.james.eggplantfastpass.ui.fragments.base.BaseLazyFragment;
import com.james.eggplantfastpass.ui.widget.DividerDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.james.material.utils.MyLog;
import org.james.material.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class VideoFragment extends BaseLazyFragment implements VideoContract.VideoView{
    @BindView(R.id.id_recyclerview)
    RecyclerView recyclerView;

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isFirst = true;
    private VideoContract.VideoPresenter videoPresenter;
    private VideoAdapter videoAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.frag_video;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        isPrepared = true;
        videoPresenter = new VideoPresenterImpl();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoAdapter = new VideoAdapter();

        recyclerView.setAdapter(videoAdapter);

        // Add decoration for dividers between list items
        lazyLoad();
    }
    @Override
    protected void lazyLoad() {
        //数据
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        isFirst = false;
        getData();


    }

    //获取数据
    private void getData(){

        videoPresenter.getData(getActivity(),this);
    }
    @Override
    public void onSuccessShow(List<VideoInfoBean> videoInfoBeanList) {

        //显示数据
        if(videoInfoBeanList.size() > 0){
            videoAdapter.addAll(videoInfoBeanList);
            final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(videoAdapter);
            recyclerView.addItemDecoration(headersDecor);
            recyclerView.addItemDecoration(new DividerDecoration(getActivity()));
            MyLog.d("size:"+videoInfoBeanList.size());
        }else {
            ToastUtil.showShortToast(getActivity(),R.string.msg_empty);
        }

    }
    @Override
    public void onFailShow() {
        ToastUtil.showShortToast(getActivity(),R.string.error_tip);
    }

}
