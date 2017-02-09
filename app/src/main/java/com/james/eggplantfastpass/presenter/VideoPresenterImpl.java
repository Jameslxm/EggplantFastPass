package com.james.eggplantfastpass.presenter;
import android.content.Context;

import com.james.eggplantfastpass.contract.VideoContract;
import com.james.eggplantfastpass.model.VideoModelImpl;

/**
* Created by MVPHelper on 2017/02/07
*/

public class VideoPresenterImpl implements VideoContract.VideoPresenter{

    private VideoContract.VideoModel videoModel;
    public VideoPresenterImpl(){
        videoModel = new VideoModelImpl();
    }
    @Override
    public void getData(Context context, VideoContract.VideoView videoView) {

        videoModel.getData(context,videoView);
    }
}