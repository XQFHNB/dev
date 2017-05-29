package com.example.owo.module_b_personal.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.BeanBase;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_personal.bean.BeanActivityLabel;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.recyclerview.adapter.AdapterFragPersonalDownViewpagerActivity;
import com.example.owo.module_b_personal.view.ViewPersonalDownViewpagerActivity;
import com.example.owo.utils.Common;
import com.example.owo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/5/17
 */
public class FragPersonalDownViewpagerActivity extends FragBase implements ViewPersonalDownViewpagerActivity {


    public static FragPersonalDownViewpagerActivity newInstance(BeanUser beanUser) {
        Bundle args = new Bundle();
        args.putSerializable("userbean", beanUser);
        FragPersonalDownViewpagerActivity fragPersonalDownViewpagerActivity = new FragPersonalDownViewpagerActivity();
        fragPersonalDownViewpagerActivity.setArguments(args);
        return fragPersonalDownViewpagerActivity;
    }


    private PresenterPersonal mPresenterPersonal;
    private List<BeanTask> mBeanTaskList;

    private List<List<BeanTask>> mBeanTasksResult;

    private List<BeanTask> mBeanTaskTop;
    private List<BeanTask> mBeanTaskMid;
    private List<BeanTask> mBeanTaskBottom;

    private List<BeanBase> mBeanBases;

    private String[] mlabels = new String[]{
            "接收报名中",
            "正在进行中",
            "已结束"
    };

    @BindView(R.id.recyclerview_my_activity)
    protected RecyclerView mRecyclerView;

    private AdapterFragPersonalDownViewpagerActivity mAdapterFragPersonalDownViewpagerActivity;
    private BeanUser mBeanUser;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_personal_down_viewpager_activity, container, false);
        ButterKnife.bind(this, view);
        mBeanUser = (BeanUser) getArguments().get("userbean");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenterPersonal = new PresenterPersonalImpl(this);
        mBeanTaskList = new ArrayList<>();
        mBeanTaskTop = new ArrayList<>();
        mBeanTaskMid = new ArrayList<>();
        mBeanTaskBottom = new ArrayList<>();
        mBeanBases = new ArrayList<>();
        mBeanTasksResult = new ArrayList<>();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int userId = Common.userSP.getInt("ID", 0);
        mPresenterPersonal.loadTasksAppliedByUserId(userId);
        mPresenterPersonal.loadTasksPublishedByUserId(userId);
        for (int i = 0; i < mBeanTaskList.size(); i++) {
            BeanTask BeanTask = mBeanTaskList.get(i);
            if (BeanTask.getTaskStatus() == Constants.TASK_STATUS_ENROOLING) {
                mBeanTaskTop.add(BeanTask);
            } else if (BeanTask.getTaskStatus() == Constants.TASK_STATUS_WORKING) {
                mBeanTaskMid.add(BeanTask);
            } else if (BeanTask.getTaskStatus() == Constants.TASK_STATUS_FINISHED) {
                mBeanTaskBottom.add(BeanTask);
            }
        }
        mBeanTasksResult.add(mBeanTaskTop);
        mBeanTasksResult.add(mBeanTaskMid);
        mBeanTasksResult.add(mBeanTaskBottom);

        for (int i = 0; i < mBeanTasksResult.size(); i++) {
            mBeanBases.add(new BeanActivityLabel(mlabels[i]));
            mBeanBases.addAll(mBeanTasksResult.get(i));
        }
        mAdapterFragPersonalDownViewpagerActivity = new AdapterFragPersonalDownViewpagerActivity(getActivity());
        mAdapterFragPersonalDownViewpagerActivity.addItems(mBeanBases);
        mRecyclerView.setAdapter(mAdapterFragPersonalDownViewpagerActivity);

    }

    @Override
    public void getTasksIApplied(List<BeanTask> taskList) {
        mBeanTaskList.addAll(taskList);
    }

    @Override
    public void getTasksIPublished(List<BeanTask> taskList) {
        mBeanTaskList.addAll(taskList);
    }
}
