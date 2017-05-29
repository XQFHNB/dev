package com.example.owo.module_b_explore.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_explore.bean.BeanRecyclerViewItem;
import com.example.owo.module_b_explore.presenter.PresenterExplore;
import com.example.owo.module_b_explore.presenter.PresenterExploreImpl;
import com.example.owo.module_b_explore.recyclerview.AdapterRecyclerView;
import com.example.owo.module_b_explore.view.ViewExploreNearby;
import com.example.owo.utils.Common;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/5/11
 */
public class FragExploreNearby extends FragBase implements ViewExploreNearby {
    public static FragExploreNearby newInstance() {
        return new FragExploreNearby();
    }

    @BindView(R.id.recyclerview_frag_explore_nearby)
    protected RecyclerView mRecyclerView;

    private List<BeanRecyclerViewItem> mBeanRecyclerViewItemList;
    private PresenterExplore mPresenterExplore;
    private int mUserId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_explore_nearby_layout, container, false);
        ButterKnife.bind(this, view);
        mUserId = Common.userSP.getInt("userId", 0);
        mPresenterExplore = new PresenterExploreImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterRecyclerView adapter = new AdapterRecyclerView(getActivity());
        adapter.addItems(mBeanRecyclerViewItemList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void getResultUserByDis(List<BeanRecyclerViewItem> list) {
        mBeanRecyclerViewItemList = list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < mBeanRecyclerViewItemList.size(); i++) {
            if (mBeanRecyclerViewItemList.get(i).getCounter() % 2 == 0) {
                int friendId = mBeanRecyclerViewItemList.get(i).getUserId();
                mPresenterExplore.sendAddFriendByIdAndFriendId(mUserId, friendId);
            }
        }
    }
}
