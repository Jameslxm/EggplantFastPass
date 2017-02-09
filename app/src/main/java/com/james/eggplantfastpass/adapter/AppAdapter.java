package com.james.eggplantfastpass.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.bean.FileInfo;

import org.james.material.utils.ScreenUtils;
import org.james.material.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1 on 2017/1/20.
 */

public class AppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FileInfo> fileInfoList;
    private int mWidth;
    private int mHeight;
    private int screenWidth;
    public AppAdapter() {
        this.fileInfoList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        screenWidth = ScreenUtils.getScreenWidth(mContext);
        mWidth = (int) ((screenWidth - SizeUtils.dp2px(mContext,20))/ 4.0);
        mHeight = mWidth;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_app_rv, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = ((MyViewHolder) holder);
            FileInfo fileInfo = fileInfoList.get(position);
            String fileName = fileInfo.getFileName();
            String fileSizeDesc = fileInfo.getSizeDesc();
            if(!TextUtils.isEmpty(fileName)) {
                myViewHolder.idTvAppInfo.setText(fileName);
            }
            Bitmap bitmap = fileInfo.getBitmap();
            if(bitmap != null) {
                myViewHolder.idIvThumb.setLayoutParams(new LinearLayout.LayoutParams(mWidth,mHeight));
                myViewHolder.idIvThumb.setImageBitmap(bitmap);
            }
            if(!TextUtils.isEmpty(fileSizeDesc)) {
                myViewHolder.idTvSize.setText(fileSizeDesc);
            }
        }
    }

    @Override
    public int getItemCount() {
        return fileInfoList == null ? 0 : fileInfoList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_iv_thumb)
        ImageView idIvThumb;
        @BindView(R.id.id_tv_app_name)
        TextView idTvAppInfo;
        @BindView(R.id.id_tv_size)
        TextView idTvSize;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setDatas(List<FileInfo> fileInfoList) {
        if (fileInfoList != null && fileInfoList.size() > 0) {
            this.fileInfoList.addAll(fileInfoList);
            notifyDataSetChanged();
        }
    }
}
