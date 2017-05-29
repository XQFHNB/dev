package com.example.owo.module_b_home.recyclerview.changed;

/**
 * @author XQF
 * @created 2017/5/14
 */
public class BeanBlue extends BeanItemFragHomeChanged {


    int mViewType = 0;
    //标题
    String mTitle;

    //头像
    String mAvatatrUrl;

    //线上活动剩余时间
    String mTime;


    //参加人数
    String mSum;

    //距离
    String mDis;

    //性别
    String mSex;

    //昵称
    String mName;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setViewType(int viewType) {
        mViewType = viewType;
    }

    public String getAvatatrUrl() {
        return mAvatatrUrl;
    }

    public void setAvatatrUrl(String avatatrUrl) {
        mAvatatrUrl = avatatrUrl;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getSum() {
        return mSum;
    }

    public void setSum(String sum) {
        mSum = sum;
    }

    public String getDis() {
        return mDis;
    }

    public void setDis(String dis) {
        mDis = dis;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int getViewType() {
        return mViewType;
    }
}
