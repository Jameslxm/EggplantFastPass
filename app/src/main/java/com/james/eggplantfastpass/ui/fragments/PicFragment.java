package com.james.eggplantfastpass.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.adapter.PicFragAdapter;
import com.james.eggplantfastpass.bean.PicInfo;
import com.james.eggplantfastpass.contract.PicContract;
import com.james.eggplantfastpass.presenter.PicPresenterImpl;
import com.james.eggplantfastpass.ui.fragments.base.BaseLazyFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class PicFragment extends BaseLazyFragment implements PicContract.View{
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isFirst = true;
    private PicContract.Presenter presenter;
    private PicFragAdapter picFragAdapter;
    @BindView(R.id.id_rv_content)
    RecyclerView rvContent;
    @Override
    public int getLayoutId() {
        return R.layout.frag_pic;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        isPrepared = true;
        presenter = new PicPresenterImpl();
        lazyLoad();
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        picFragAdapter = new PicFragAdapter();
//        rvContent.setAdapter(picFragAdapter);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        isFirst = false;
        getData();
    }

    //获取数据
    private void getData(){
        //
        presenter.getData(getActivity(),this);
    }

    @Override
    public void onFailShow() {
        //提示用户，网络加载出错
        showShortToast(getString(R.string.error_tip));
    }

    @Override
    public void onSuccessShow(List<PicInfo> picInfoList) {
        if(picInfoList != null && picInfoList.size()>0){
            //显示数据
            picFragAdapter.setDatas(picInfoList);
        }else {
            //提示用户没有数据
            showShortToast(getString(R.string.msg_empty));
        }
    }
}
