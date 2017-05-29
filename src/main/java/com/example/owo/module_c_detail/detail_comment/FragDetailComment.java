package com.example.owo.module_c_detail.detail_comment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_personal.bean.BeanComment;
import com.example.owo.module_b_personal.bean.BeanLike;
import com.example.owo.module_b_personal.bean.BeanUser;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/5/23
 */
public class FragDetailComment extends FragBase {

    public static FragDetailComment newInstance(BeanUser beanUser, List<BeanComment> comments, List<BeanLike> likes) {
        FragDetailComment fragDetailComment = new FragDetailComment();
        Bundle args = new Bundle();
        args.putSerializable("beanuser", beanUser);
        args.putSerializable("comments", (Serializable) comments);
        args.putSerializable("likes", (Serializable) likes);
        fragDetailComment.setArguments(args);
        return fragDetailComment;
    }


    private BeanUser mBeanUser;
    private List<BeanComment> mBeanComments;
    private List<BeanLike> mBeanLikes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_comment, container, false);
        ButterKnife.bind(this, view);
        mBeanUser = (BeanUser) getArguments().get("beanuser");
        mBeanComments = (List<BeanComment>) getArguments().get("comments");
        mBeanLikes = (List<BeanLike>) getArguments().get("likes");
        return view;
    }

}
