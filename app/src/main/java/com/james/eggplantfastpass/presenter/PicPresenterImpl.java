package com.james.eggplantfastpass.presenter;
import android.content.Context;

import com.james.eggplantfastpass.contract.PicContract;
import com.james.eggplantfastpass.model.PicModelImpl;

/**
* Created by Administrator on 2017/01/25
*/

public class PicPresenterImpl implements PicContract.Presenter{
    private PicContract.Model model;
    public PicPresenterImpl(){
        model = new PicModelImpl();
    }
    @Override
    public void getData(Context context, PicContract.View view) {
        model.getData(context,view);
    }
}