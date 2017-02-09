package com.james.eggplantfastpass.model;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.james.eggplantfastpass.bean.FileInfo;
import com.james.eggplantfastpass.contract.AppContract;
import com.james.eggplantfastpass.utils.FileUtils;

import java.util.List;

/**
* Created by James on 2017/01/20
*/
public class AppModelImpl implements AppContract.AppModel{
    public static final int SUCCESS_CODE = 1234;
    public static final int FAIL_CODE = 1235;
    private Context mContext;
    private AppContract.AppView appView;
    private List<FileInfo> fileInfoList;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SUCCESS_CODE:
                    appView.onSuccessShow(fileInfoList);
                    break;
                case FAIL_CODE:
                    appView.onFailShow();
                    break;
            }
        }
    };
    @Override
    public void getData(Context context, AppContract.AppView appView) {
        mContext = context;
        this.appView = appView;
        new Thread(){
            @Override
            public void run() {
                //获取手机内所有apk应用的数据
                fileInfoList =  FileUtils.scanInstallApp(mContext);
                if(fileInfoList != null) {
                    handler.sendEmptyMessage(SUCCESS_CODE);

                }else {
                    handler.sendEmptyMessage(FAIL_CODE);

                }
                //返回数据给fragment,告诉adapter显示数据.
            }
        }.start();
    }
}