package com.example.owo.module_b_explore.bean;

/**
 * @author XQF
 * @created 2017/5/28
 */
public class BeanRecyclerViewItem {

    private int mUserId;
    private String mUrlAvatar;
    private int mSex;
    private double mDis;

    public int getCounter() {
        return mCounter;
    }

    public void setCounter(int counter) {
        mCounter = counter;
    }

    private int mCounter=0;

    public int getSex() {
        return mSex;
    }

    public void setSex(int sex) {
        mSex = sex;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUrlAvatar() {
        return mUrlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        mUrlAvatar = urlAvatar;
    }



    public double getDis() {
        return mDis;
    }

    public void setDis(double dis) {
        mDis = dis;
    }
}
