package com.example.owo.module_b_personal.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_personal.bean.BeanMoment;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.recyclerview.adapter.AdapterFragPersonalDownViewpagerMoment;
import com.example.owo.module_b_personal.view.ViewPersonalDownViewpagerMoment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/5/17
 */
public class FragPersonalDownViewpagerMoment extends FragBase implements ViewPersonalDownViewpagerMoment {

    public static FragPersonalDownViewpagerMoment newInstance(BeanUser beanUser) {

        Bundle args = new Bundle();
        args.putSerializable("userbean", beanUser);
        FragPersonalDownViewpagerMoment fragPersonalDownViewpagerMoment = new FragPersonalDownViewpagerMoment();
        fragPersonalDownViewpagerMoment.setArguments(args);
        return fragPersonalDownViewpagerMoment;
    }


    @BindView(R.id.recyclerview)
    protected RecyclerView mRecyclerView;

    protected AdapterFragPersonalDownViewpagerMoment mAdapterFragPersonalDownViewpagerMoment;
    private PresenterPersonal mPresenterPersonal;
    private List<BeanMoment> mBeanMoments;
    private BeanUser mBeanUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_personal_down_viewpager_moment, container, false);
        ButterKnife.bind(this, view);
        mBeanUser = (BeanUser) getArguments().get("userbean");
        mPresenterPersonal = new PresenterPersonalImpl(this);
        mPresenterPersonal.loadUserFriendMomentById(mBeanUser.getId());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapterFragPersonalDownViewpagerMoment = new AdapterFragPersonalDownViewpagerMoment(getActivity());
        mAdapterFragPersonalDownViewpagerMoment.addItems(mBeanMoments);
        mRecyclerView.setAdapter(mAdapterFragPersonalDownViewpagerMoment);
        return view;
    }


    @Override
    public void getResultMomentFromSever(List<BeanMoment> list) {
        mBeanMoments = list;
    }
}
