package com.example.owo.module_b_home.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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
 * @created 2017/5/10
 */
public class FragHomeChanged extends FragBase {

    public static FragHomeChanged newInstance() {
        return new FragHomeChanged();
    }


    @BindView(R.id.btn_recommend_common_header_search_and_recommend)
    protected Button mBtnRecommend;

    @BindView(R.id.btn_nearby_common_header_search_and_recommend)
    protected Button mBtnNearby;


    public static final int[] tabsNormalBackIds = new int[]{
            R.drawable.home_gray,
            R.drawable.message_gray,
            R.drawable.discovery_gray,
            R.drawable.me_gray};

    public static final int[] tabsActiveBackIds = new int[]{
            R.drawable.home_yellow,
            R.drawable.message_yellow,
            R.drawable.discovery_yellow,
            R.drawable.me_yellow};


    private Button[] btns;

    private FragmentManager mFragmentManager;
    private FragHomeChangedNearby mFragHomeChangedNearby;
    private FragHomeChangedRecommend mFragHomeChangedRecommend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home_changed_layout, container, false);
        ButterKnife.bind(this, view);
        btns = new Button[]{mBtnRecommend, mBtnNearby};
        mFragmentManager = getActivity().getSupportFragmentManager();
        mBtnRecommend.performClick();
        return view;
    }


    @OnClick(R.id.btn_recommend_common_header_search_and_recommend)
    public void onBtnRecommend() {
        mBtnRecommend.setTextColor(getResources().getColor(R.color.word_color_white));
        mBtnRecommend.setBackgroundColor(getResources().getColor(R.color.toolbar_bg_color));
        if (mFragHomeChangedRecommend == null) {
            mFragHomeChangedRecommend = new FragHomeChangedRecommend();
            mFragmentManager.beginTransaction().add(R.id.main_content, mFragHomeChangedRecommend).commit();
            mFragHomeChangedNearby = null;
            mBtnNearby.setTextColor(getResources().getColor(R.color.word_color_black));
            mBtnNearby.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        }

    }

    @OnClick(R.id.btn_nearby_common_header_search_and_recommend)
    public void onBtnNearby() {
        mBtnNearby.setTextColor(getResources().getColor(R.color.word_color_white));
        mBtnNearby.setBackgroundColor(getResources().getColor(R.color.toolbar_bg_color));
        if (mFragHomeChangedNearby == null) {
            mFragHomeChangedNearby = new FragHomeChangedNearby();
            mFragmentManager.beginTransaction().add(R.id.main_content, mFragHomeChangedNearby).commit();
            mFragHomeChangedRecommend = null;
            mBtnRecommend.setTextColor(getResources().getColor(R.color.word_color_black));
            mBtnRecommend.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        }
    }
}
