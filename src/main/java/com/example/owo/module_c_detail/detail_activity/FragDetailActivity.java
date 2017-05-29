package com.example.owo.module_c_detail.detail_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_home.bean.BeanTask;

import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/5/23
 */
public class FragDetailActivity extends FragBase {


    public static FragDetailActivity newInstance(BeanTask beanTask) {
        FragDetailActivity fragDetailActivity = new FragDetailActivity();
        Bundle args = new Bundle();
        args.putSerializable("beantask", beanTask);
        fragDetailActivity.setArguments(args);
        return fragDetailActivity;
    }

    private BeanTask mBeanTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_activity, container, false);
        ButterKnife.bind(this, view);
        mBeanTask = (BeanTask) getArguments().get("beantask");
        return view;
    }
}
