package com.james.eggplantfastpass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.james.eggplantfastpass.bean.PicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/2 0002.
 */

public class PicFragAdapter extends RecyclerView.Adapter {
    public static final int TYPE_LAYOUT_ONE = 0;
    public static final int TYPE_LAYOUT_TWO = 1;
    private List<PicInfo> picInfoList;
    public PicFragAdapter(){
        picInfoList = new ArrayList<>();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(isData(position)){
            return TYPE_LAYOUT_ONE;
        }else {
            return TYPE_LAYOUT_TWO;
        }
    }

    private boolean isData(int position) {

        if(picInfoList != null && picInfoList.size() > 0){
            PicInfo picInfo = picInfoList.get(position);
            if(picInfo == null){
                return false;
            }
            if(picInfo.isDate()){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    public void setDatas(List<PicInfo> picInfoList) {
        if(picInfoList != null && picInfoList.size() > 0) {
            this.picInfoList.addAll(picInfoList);
            notifyDataSetChanged();
        }
    }
}
