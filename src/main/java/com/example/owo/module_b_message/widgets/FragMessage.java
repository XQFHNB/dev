package com.example.owo.module_b_message.widgets;

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
 * @created 2017/5/5
 */
public class FragMessage extends FragBase {


    @BindView(R.id.btn_message_test)
    protected Button mBtnTest;
    @BindView(R.id.btn_message_test1)
    protected Button mBtnTest1;


    private FragmentManager mFragmentManager;

    private FragMessageNearby mFragMessageNearby;
    private FragMessageRecommend mFragMessageRecommend;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_message_layout, container, false);
        ButterKnife.bind(this, view);
        mFragmentManager = getActivity().getSupportFragmentManager();
        mBtnTest.performClick();
        return view;
    }

    @OnClick(R.id.btn_message_test)
    public void onBtnClick() {

        mBtnTest.setTextColor(getResources().getColor(R.color.word_color_white));
        mBtnTest.setBackgroundColor(getResources().getColor(R.color.toolbar_bg_color));
        if (mFragMessageRecommend == null) {
            mFragMessageRecommend = new FragMessageRecommend();
            mFragmentManager.beginTransaction().add(R.id.frag_message_main_content, mFragMessageRecommend).commit();
            mFragMessageNearby = null;


            mBtnTest1.setTextColor(getResources().getColor(R.color.word_color_black));
            mBtnTest1.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        }
    }

    @OnClick(R.id.btn_message_test1)
    public void onBtn1Click() {
        mBtnTest1.setTextColor(getResources().getColor(R.color.word_color_white));
        mBtnTest1.setBackgroundColor(getResources().getColor(R.color.toolbar_bg_color));
        if (mFragMessageNearby == null) {
            mFragMessageNearby = new FragMessageNearby();
            mFragmentManager.beginTransaction().add(R.id.frag_message_main_content, mFragMessageNearby).commit();
            mFragMessageRecommend = null;
            toast(getActivity(), "test1");
            mBtnTest.setTextColor(getResources().getColor(R.color.word_color_black));
            mBtnTest.setBackgroundColor(getResources().getColor(R.color.tab_color_normal));
        }
    }
}
