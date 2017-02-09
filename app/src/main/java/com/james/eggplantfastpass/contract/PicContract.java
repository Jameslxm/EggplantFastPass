package com.james.eggplantfastpass.contract;

import android.content.Context;

import com.james.eggplantfastpass.bean.PicInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/1/25 0025.
 */

public class PicContract {
    public interface View {
        void onFailShow();
        void onSuccessShow(List<PicInfo> picInfoList);
    }
    public interface Presenter {
        void getData(Context context, View view);
    }
    public interface Model {
        void getData(Context context, View view);
    }
}