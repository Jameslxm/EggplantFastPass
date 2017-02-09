package com.james.eggplantfastpass.model;
import android.content.Context;

import com.james.eggplantfastpass.bean.VideoInfoBean;
import com.james.eggplantfastpass.contract.VideoContract;
import com.james.eggplantfastpass.utils.FileUtils;

import java.util.List;

/**
* Created by MVPHelper on 2017/02/07
*/

public class VideoModelImpl implements VideoContract.VideoModel{

    @Override
    public void getData(Context context, VideoContract.VideoView videoView) {

        //获取数据
        List<VideoInfoBean> videoInfoBeanList = FileUtils.getVideoInfo(context);

        //返回数据
        if(videoInfoBeanList != null) {
            videoView.onSuccessShow(videoInfoBeanList);
        }else {
            videoView.onFailShow();
        }

    }
}