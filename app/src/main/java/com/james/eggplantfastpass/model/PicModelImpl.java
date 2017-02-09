package com.james.eggplantfastpass.model;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.james.eggplantfastpass.bean.PicInfo;
import com.james.eggplantfastpass.contract.PicContract;
import com.james.eggplantfastpass.utils.FileUtils;

import java.util.List;

/**
* Created by Administrator on 2017/01/25
*/

public class PicModelImpl implements PicContract.Model{
    public static final int SUCCESS_CODE = 1234;
    public static final int FAIL_CODE = 1235;
    private PicContract.View view;
    private List<PicInfo> picInfoList;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SUCCESS_CODE:
                    if(picInfoList != null){
                        view.onSuccessShow(picInfoList);
                    }else {
                        view.onFailShow();
                    }
                    break;
                case FAIL_CODE:
                    view.onFailShow();
                    break;
            }

        }
    };
    @Override
    public void getData(final Context context, PicContract.View view) {
        this.view = view;
        new Thread(){
            @Override
            public void run() {
                //数据
                picInfoList = FileUtils.getCameraImage(context);
                if(picInfoList != null){
                    handler.sendEmptyMessage(SUCCESS_CODE);
                }else {
                    handler.sendEmptyMessage(FAIL_CODE);
                }
            }
        }.start();
    }
}