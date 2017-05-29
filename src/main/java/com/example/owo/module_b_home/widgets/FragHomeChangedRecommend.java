package com.example.owo.module_b_home.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_home.presenter.PresenterHomeImpl;
import com.example.owo.module_b_home.presenter.PresneterHome;
import com.example.owo.module_b_home.recyclerview.changed.AdapterHomeChangedRecyclerview;
import com.example.owo.module_b_home.recyclerview.changed.BeanBlue;
import com.example.owo.module_b_home.recyclerview.changed.BeanItemFragHomeChanged;
import com.example.owo.module_b_home.view.ViewHomeChangedRecommend;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/5/11
 */
public class FragHomeChangedRecommend extends FragBase implements ViewHomeChangedRecommend {


    @BindView(R.id.recyclerview)
    protected RecyclerView mRecyclerView;

    private AdapterHomeChangedRecyclerview mAdapterHomeChangedRecyclerview;
    private PresneterHome mPresneterHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home_changed_recommend_layout, container, false);
        ButterKnife.bind(this, view);
        mPresneterHome = new PresenterHomeImpl(this);
        List<BeanItemFragHomeChanged> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BeanBlue beanBlue = new BeanBlue();
            beanBlue.setTitle(i + "heheh");
            list.add(beanBlue);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapterHomeChangedRecyclerview = new AdapterHomeChangedRecyclerview(getActivity(), list);
        mRecyclerView.setAdapter(mAdapterHomeChangedRecyclerview);
        return view;
    }

    @Override
    public void getAllTaskByRecommend(List<BeanTask> tasks) {

    }
}
