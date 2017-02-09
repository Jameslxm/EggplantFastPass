package com.james.eggplantfastpass.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.bean.VideoInfoBean;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import org.james.material.utils.MyLog;
import org.james.material.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1 on 2017/2/7.
 */

public class VideoAdapter extends AnimalsAdapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    @BindView(R.id.id_iv_thumb)
    ImageView ivThumb;
    @BindView(R.id.id_tv_title)
    TextView tvTitle;
    @BindView(R.id.id_tv_size)
    TextView tvSize;
    private Context mContext;
    private int picWidth;
    private int picHeight;
    private int screenWidth;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        screenWidth = ScreenUtils.getScreenWidth(mContext);
        picWidth = (int) (screenWidth / 3.0);
        picHeight = (int) (picWidth * (2/3.0));
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video,parent,false);
        ButterKnife.bind(this,view);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoInfoBean videoInfoBean = getListVideoInfoBean(position);
        if(videoInfoBean != null) {
            String title = videoInfoBean.getTitle();
            MyLog.d("onBindViewHolder-->title:" + title);
            String url = videoInfoBean.getData();
            if(TextUtils.isEmpty(url)) {
               url = "www.bai";
            }
            if(!TextUtils.isEmpty(title)) {
                tvTitle.setText(title);
            }
            ivThumb.setLayoutParams(new RelativeLayout.LayoutParams(picWidth, picHeight));
            Glide.with(mContext).load(url).placeholder(R.drawable.ic_pic_placeholder).error(R.drawable.ic_pic_placeholder).into(ivThumb);
//        String folder = getItem(position);
//        MyLog.d("onBindViewHolder-->folder:"+folder);
        }
    }

    @Override
    public long getHeaderId(int position) {
        if(position == 0){
            return -1;
        }else {
            String folder = getListVideoInfoBean(position).getFolder();
            if(!TextUtils.isEmpty(folder)) {
                return folder.charAt(0);//
            }else {
                return -1;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_header,parent,false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        String folder = getListVideoInfoBean(position).getFolder();
        MyLog.d("onBindHeaderViewHolder folder:"+folder);
        if(!TextUtils.isEmpty(folder)) {
            textView.setText(folder);
        }
    }


}
