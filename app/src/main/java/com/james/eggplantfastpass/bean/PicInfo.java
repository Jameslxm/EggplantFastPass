package com.james.eggplantfastpass.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/29 0029.
 */

public class PicInfo {
    private boolean isDate;
    private String date;
    private List<PicBean> picBeanList;

    public boolean isDate() {
        return isDate;
    }

    public void setDate(boolean date) {
        isDate = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<PicBean> getPicBeanList() {
        return picBeanList;
    }

    public void setPicBeanList(List<PicBean> picBeanList) {
        this.picBeanList = picBeanList;
    }
}
