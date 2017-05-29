package com.example.owo.module_a_selectlabel.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_a_selectlabel.FragSelectLabelFindFriend;
import com.example.owo.module_a_selectlabel.FragSelectLabelSelf;
import com.example.owo.module_a_selectlabel.bean.BeanTag;
import com.example.owo.module_a_selectlabel.presenter.PresenterSelectLabel;
import com.example.owo.module_a_selectlabel.presenter.PresenterSelectLabelImpl;
import com.example.owo.module_a_selectlabel.view.ViewSelectLabel;
import com.example.owo.module_b_main.AtyMain;
import com.example.owo.utils.Common;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/5/24
 */
public class FragSlectLabel extends FragBase implements ViewSelectLabel {


    @BindView(R.id.btn_next_step)
    protected Button mBtnNextStep;

    private FragSelectLabelFindFriend mFragSelectLabelFindFriend;
    private FragSelectLabelSelf mFragSelectLabelSelf;

    private FragmentManager mFragmentManager;

    private PresenterSelectLabel mPresenterSelectLabel;

    private int mUserId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.aty_select_label, container, false);
        ButterKnife.bind(this, view);
        mUserId = Common.userSP.getInt("userId", 0);
        mPresenterSelectLabel = new PresenterSelectLabelImpl(this);
        mFragmentManager = getActivity().getSupportFragmentManager();
        if (mFragSelectLabelSelf == null) {
            mFragSelectLabelSelf = FragSelectLabelSelf.newInstance();
        }
        mFragmentManager.beginTransaction().add(R.id.frag_container_aty_select_label, mFragSelectLabelSelf).commit();
        return view;
    }

    /**
     * 本情景中的两次点击按钮
     */

    @OnClick(R.id.btn_next_step)
    public void onClickBtnNext() {
        if (mFragSelectLabelFindFriend == null && mFragSelectLabelSelf != null) {
            mFragmentManager.beginTransaction().replace(R.id.frag_container_aty_select_label, mFragSelectLabelFindFriend).commit();
            mFragSelectLabelFindFriend = FragSelectLabelFindFriend.newInstance(mFragSelectLabelSelf.getSelfLabels());
            mFragSelectLabelSelf = null;
        } else if (mFragSelectLabelSelf == null && mFragSelectLabelFindFriend != null) {
            String hobby = mFragSelectLabelFindFriend.getHobby();
            mPresenterSelectLabel.updateHobbyById(mUserId, hobby);
            start(getActivity(), AtyMain.class);
        }
    }

    @Override
    public void getLabelSelfFormNet(List<List<BeanTag>> resultList) {

    }
}
