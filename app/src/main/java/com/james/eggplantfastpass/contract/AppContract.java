package com.james.eggplantfastpass.contract;

import android.content.Context;

import com.james.eggplantfastpass.bean.FileInfo;

import java.util.List;

/**
 * Created by 1 on 2017/1/20.
 */

public class AppContract {

    public interface AppView {
        void onFailShow();
        void onSuccessShow(List<FileInfo> fileInfoList);
    }

    public interface AppPresenter {

        void getData(Context context, AppView appView);
    }

    public interface AppModel {
        void getData(Context context, AppView appView);

    }
}