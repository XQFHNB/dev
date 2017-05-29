package com.example.owo.module_b_home.recyclerview.changed;

import com.example.owo.module_b_home.recyclerview.changed.BeanItemFragHomeChanged;

/**
 * @author XQF
 * @created 2017/5/14
 */
public class BeanRed extends BeanItemFragHomeChanged {


    int mViewType = 1;

    //头像
    String mAvatatrUrl;

    //昵称
    String mName;

    //性别
    String mSex;

    //标题
    String mTitle;

    //玩类线下时间
    String mTime;

    //线下活动的位置
    String mWhere;

    //参加人数
    String mSum;

    //距离
    String mDis;

    @Override
    public int getViewType() {
        return mViewType;
    }
}
