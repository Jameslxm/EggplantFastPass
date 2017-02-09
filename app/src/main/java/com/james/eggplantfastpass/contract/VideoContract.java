package com.james.eggplantfastpass.contract;

import android.content.Context;

import com.james.eggplantfastpass.bean.VideoInfoBean;

import java.util.List;

/**
 * Created by 1 on 2017/2/7.
 */

public class VideoContract {

    public interface VideoView {
        void onSuccessShow(List<VideoInfoBean> videoInfoBeanList);
        void onFailShow();

    }

    public interface VideoPresenter {
        void getData(Context context, VideoView videoView);
    }

    public interface VideoModel {
        void getData(Context context, VideoView videoView);
    }
}