package com.example.owo.module_c_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.owo.base.AtyBase;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_personal.bean.BeanComment;
import com.example.owo.module_b_personal.bean.BeanLike;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_c_detail.detail_activity.FragDetailActivity;
import com.example.owo.module_c_detail.detail_comment.FragDetailComment;
import com.example.owo.module_c_detail.detail_otheruser.FragDetailOtherUser;

import java.io.Serializable;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/23
 */
public class AtyDetail extends AtyBase {

    public static final String KEY_TYPE = "type";
    public static final String KEY_BEANUSER = "beanuser";
    public static final String KEY_COMMENTS = "comments";
    public static final String KEY_LIKES = "likes";
    public static final String VALUE_TYPE_COMMENTDETAIL = "commentdetail";

    public static final String KEY_BEANTASK = "beantask";
    public static final String VALUE_TYPE_ATYDETAIL = "activitydetail";

    public static final String VALUE_TYPE_OTHERUSERDETAIL = "otheruserdetail";


    /**
     * 在评论点击的时候启动
     *
     * @param context
     * @param cls
     * @param beanuser
     * @param comments
     * @param likes
     */
    public static void startAtyDetail(Context context, Class<?> cls, BeanUser beanuser, List<BeanComment> comments, List<BeanLike> likes) {
        Bundle args = new Bundle();
        args.putString("type", "commentdetail");
        args.putSerializable("beanuser", beanuser);
        args.putSerializable("comments", (Serializable) comments);
        args.putSerializable("likes", (Serializable) likes);
        Intent intent = new Intent(context, cls);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    /**
     * 活动细节
     *
     * @param context
     * @param cls
     * @param beanTask
     */
    public static void startAtyDetail(Context context, Class<?> cls, BeanTask beanTask) {
        Bundle args = new Bundle();
        args.putString(KEY_TYPE, VALUE_TYPE_ATYDETAIL);
        args.putSerializable(KEY_BEANTASK, beanTask);
        Intent intent = new Intent(context, cls);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    /**
     * 查看其他人的个人主页
     *
     * @param context
     * @param cls
     * @param beanuser
     */
    public static void startAtyDetail(Context context, Class<?> cls, BeanUser beanuser) {
        Bundle args = new Bundle();
        args.putString(KEY_TYPE, VALUE_TYPE_OTHERUSERDETAIL);
        args.putSerializable(KEY_BEANTASK, beanuser);
        Intent intent = new Intent(context, cls);
        intent.putExtras(args);
        context.startActivity(intent);
    }


    @Override
    public Fragment createFragment() {
        Intent intent = getIntent();
        String string = intent.getStringExtra(KEY_TYPE);
        Fragment fragment = null;
        if (string.equals(VALUE_TYPE_COMMENTDETAIL)) {
            BeanUser beanuser = (BeanUser) intent.getSerializableExtra(KEY_BEANUSER);
            List<BeanComment> comments = (List<BeanComment>) intent.getSerializableExtra(KEY_COMMENTS);
            List<BeanLike> likes = (List<BeanLike>) intent.getSerializableExtra(KEY_LIKES);
            fragment = FragDetailComment.newInstance(beanuser, comments, likes);
        } else if (string.equals(VALUE_TYPE_ATYDETAIL)) {
            BeanTask beantask = (BeanTask) intent.getSerializableExtra(KEY_BEANTASK);
            fragment = FragDetailActivity.newInstance(beantask);
        } else if (string.equals(VALUE_TYPE_OTHERUSERDETAIL)) {
            BeanUser beanuser = (BeanUser) intent.getSerializableExtra(KEY_BEANUSER);
            fragment = FragDetailOtherUser.newInstance(beanuser);
        }
        return fragment;
    }
}
