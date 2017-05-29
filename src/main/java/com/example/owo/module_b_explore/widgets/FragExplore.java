package com.example.owo.module_b_explore.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.owo.R;
import com.example.owo.base.FragBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/5/5
 */
public class FragExplore extends FragBase {


    private static String FRAG_RECOMMEND = "recommend";
    private static String FRAG_NEARBY = "bearby";
    private static final String[] fragmentTags = new String[]{
            FRAG_RECOMMEND,
            FRAG_NEARBY
    };


    @BindView(R.id.btn_recommend_common_header_search_and_recommend)
    protected Button mBtnRecommend;

    @BindView(R.id.btn_nearby_common_header_search_and_recommend)
    protected Button mBtnNearby;

    private FragmentManager mFragmentManager;

    private FragExploreNearby mFragExploreNearby;
    private FragExploreRecommend mFragExploreRecommend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_explore_layout, container, false);

        ButterKnife.bind(this, view);
        mFragmentManager = getActivity().getSupportFragmentManager();
        mBtnRecommend.performClick();
        return view;
    }


    @OnClick(R.id.btn_nearby_common_header_search_and_recommend)
    public void onBtnNearbyClick() {
        mBtnNearby.setTextColor(getResources().getColor(R.color.word_color_white));
        mBtnNearby.setBackgroundColor(getResources().getColor(R.color.toolbar_bg_color));

        if (mFragExploreNearby == null) {
            mFragExploreNearby = FragExploreNearby.newInstance();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_explore_main_content, mFragExploreNearby, FRAG_NEARBY);
            fragmentTransaction.show(mFragExploreNearby);
            fragmentTransaction.commit();
            mFragExploreRecommend = null;
            mBtnRecommend.setTextColor(getResources().getColor(R.color.word_color_black));
            mBtnRecommend.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        }
    }

    @OnClick(R.id.btn_recommend_common_header_search_and_recommend)
    public void onBtnRecommendClick() {
        mBtnRecommend.setTextColor(getResources().getColor(R.color.word_color_white));
        mBtnRecommend.setBackgroundColor(getResources().getColor(R.color.toolbar_bg_color));
        if (mFragExploreRecommend == null) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            mFragExploreRecommend = FragExploreRecommend.newInstance();
            fragmentTransaction.add(R.id.frag_explore_main_content, mFragExploreRecommend, FRAG_RECOMMEND);
            fragmentTransaction.show(mFragExploreRecommend);
            fragmentTransaction.commit();
            mFragExploreNearby = null;
            mBtnNearby.setTextColor(getResources().getColor(R.color.word_color_black));
            mBtnNearby.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        }
    }
}
