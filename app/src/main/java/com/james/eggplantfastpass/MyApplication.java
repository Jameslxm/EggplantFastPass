package com.james.eggplantfastpass;

import android.app.Application;

import org.james.material.utils.MyLog;

/**
 * Created by Administrator on 2017/1/29 0029.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        MyLog.init(true);
    }
}
