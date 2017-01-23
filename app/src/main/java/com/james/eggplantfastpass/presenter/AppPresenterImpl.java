package com.james.eggplantfastpass.presenter;
import com.james.eggplantfastpass.contract.AppContract;
import com.james.eggplantfastpass.model.AppModelImpl;

/**
* Created by MVPHelper on 2017/01/20
*/

public class AppPresenterImpl implements AppContract.AppPresenter{

    private AppContract.AppModel appModel;
    public AppPresenterImpl(){
        appModel = new AppModelImpl();
    }

    @Override
    public void getData(AppContract.AppView appView) {
        appModel.getData(appView);
    }
}