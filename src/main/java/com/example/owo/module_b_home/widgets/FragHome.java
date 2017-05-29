package com.example.owo.module_b_home.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
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
 * @created 2017/5/4
 */
public class FragHome extends FragBase {

    private static final String FRAG_NORMAL = "normal";
    private static final String FRAG_CHANGED = "changed";


    private static final String[] fragmentTags = new String[]{
            FRAG_NORMAL,
            FRAG_CHANGED
    };


    //状态切换按钮
    @BindView(R.id.toolbar_btn_frag_home_change_state)
    protected Button mBtnToolbarFragHomeChangeState;


    //    搜索栏
    @BindView(R.id.toolbar_searchview_frag_home)
    protected SearchView mSearchView;

    private FragmentManager mFragmentManager;


    private FragHomeChanged mFragHomeChanged;

    private FragHomeNormal mFragHomeNormal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home_layout, container, false);
        mFragmentManager = getActivity().getSupportFragmentManager();
        if (mFragHomeNormal == null) {
            mFragHomeNormal = FragHomeNormal.newInsstance();
        }
        mFragmentManager.beginTransaction().add(R.id.frag_home_main_content, mFragHomeNormal).commit();
        ButterKnife.bind(this, view);
        return view;
    }


    /**
     * 按钮成功完成切换
     */
    @OnClick(R.id.toolbar_btn_frag_home_change_state)
    public void test() {
        if (mFragHomeChanged == null) {
            mSearchView.setVisibility(View.GONE);
            mFragHomeChanged = FragHomeChanged.newInstance();
            mFragmentManager.beginTransaction().replace(R.id.frag_home_main_content, mFragHomeChanged).commit();
            mFragHomeNormal = null;
            return;
        }
        if (mFragHomeNormal == null) {
            mFragHomeNormal = FragHomeNormal.newInsstance();
            mFragmentManager.beginTransaction().replace(R.id.frag_home_main_content, mFragHomeNormal).commit();
            mFragHomeChanged = null;
        }

    }
}
