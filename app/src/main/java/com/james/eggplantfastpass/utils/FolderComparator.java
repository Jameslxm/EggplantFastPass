package com.james.eggplantfastpass.utils;

import com.james.eggplantfastpass.bean.VideoInfoBean;

import java.util.Comparator;

/**
 * Created by 1 on 2017/2/8.
 */

public class FolderComparator implements Comparator<VideoInfoBean> {
    @Override
    public int compare(VideoInfoBean o1, VideoInfoBean o2) {
        return o1.getFolder().compareTo(o2.getFolder());
    }
}
