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

/**
 * Created by 1 on 2017/2/7.
 */

public class VideoAdapter extends AnimalsAdapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private int picWidth;
    private int picHeight;
    private int screenWidth;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        screenWidth = ScreenUtils.getScreenWidth(mContext);
        picWidth = (int) (screenWidth / 3.0);
        picHeight = (int) (picWidth * (2 / 3.0));
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView tvTitle = (TextView) holder.itemView.findViewById(R.id.id_tv_title);
        TextView tvSize = (TextView) holder.itemView.findViewById(R.id.id_tv_size);
        ImageView ivThumb = (ImageView) holder.itemView.findViewById(R.id.id_iv_thumb);
        VideoInfoBean videoInfoBean = getListVideoInfoBean(position);
        if (videoInfoBean != null) {
            String title = videoInfoBean.getTitle();
            String size = videoInfoBean.getSize();
            MyLog.d("onBindViewHolder-->title:" + title);
            if (TextUtils.isEmpty(title)) {
                title = "title";
            }
            String url = videoInfoBean.getData();
            if (TextUtils.isEmpty(url)) {
                url = "www.bai";
            }
            if (TextUtils.isEmpty(size)) {
                size = "大小未知";
            }
            tvTitle.setText(title);
            tvSize.setText(size);
            MyLog.d("position:" + position);
            ivThumb.setLayoutParams(new RelativeLayout.LayoutParams(picWidth, picHeight));
            Glide.with(mContext).load(url).placeholder(R.drawable.ic_pic_placeholder).error(R.drawable.ic_pic_placeholder).into(ivThumb);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public long getHeaderId(int position) {
        String folder = getListVideoInfoBean(position).getFolder();
        return folder.charAt(0);//
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        String folder = getListVideoInfoBean(position).getFolder();
        if ("Camera".equals(folder)) {
            folder = "拍摄";
        } else if ("QQfile_recv".equals(folder)) {
            folder = "QQ";
        } else if ("WeiXin".equals(folder)) {
            folder = "微信";
        }
        if (!TextUtils.isEmpty(folder)) {
            textView.setText(folder);
        }
    }

}
