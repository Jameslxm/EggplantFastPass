package com.james.eggplantfastpass.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

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
    @BindView(R.id.id_ll_no_content_tip)
    LinearLayout llNoContent;
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
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(videoAdapter);
        recyclerView.addItemDecoration(headersDecor);
        recyclerView.addItemDecoration(new DividerDecoration(getActivity()));
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
            recyclerView.setVisibility(View.VISIBLE);
            llNoContent.setVisibility(View.GONE);
            videoAdapter.addAll(videoInfoBeanList);
            MyLog.d("size:"+videoInfoBeanList.size());
        }else {
            llNoContent.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }
    @Override
    public void onFailShow() {
        ToastUtil.showShortToast(getActivity(),R.string.error_tip);
    }
}
