package com.james.eggplantfastpass.contract;

/**
 * Created by 1 on 2017/1/20.
 */

public class AppContract {

    public interface AppView {
        void onFailShow();
        void onSuccessShow();
    }

    public interface AppPresenter {

        void getData(AppView appView);
    }

    public interface AppModel {
        void getData(AppView appView);

    }
}