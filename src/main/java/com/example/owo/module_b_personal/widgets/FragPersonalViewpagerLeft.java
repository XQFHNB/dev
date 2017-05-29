package com.example.owo.module_b_personal.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.view.ViewPersonalViewpagerLeft;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/5/17
 */
public class FragPersonalViewpagerLeft extends FragBase implements ViewPersonalViewpagerLeft {


    public static FragPersonalViewpagerLeft newInstance(BeanUser beanUser) {
        FragPersonalViewpagerLeft fragPersonalViewpagerLeft = new FragPersonalViewpagerLeft();
        Bundle args = new Bundle();
        args.putSerializable("beanUser", beanUser);
        fragPersonalViewpagerLeft.setArguments(args);
        return fragPersonalViewpagerLeft;
    }

    public static int[] levelImages = new int[]{
            R.drawable.girl
    };


    private BeanUser mBeanUser;

    @BindView(R.id.left_avatar)
    protected CircleImageView mCircleImageViewUserAvatar;

    @BindView(R.id.left_level)
    protected CircleImageView mCircleImageViewUserLevel;
    @BindView(R.id.left_name)
    protected TextView mTextViewUserName;


    @BindView(R.id.left_height)
    protected TextView mTextViewHeight;

    @BindView(R.id.left_age)
    protected TextView mTextViewAge;

    @BindView(R.id.left_weight)
    protected TextView mTextViewWeight;

    @BindView(R.id.left_signature)
    protected TextView mTextViewSignNature;
    @BindView(R.id.left_followed)
    protected TextView mTextViewUserFollowedSum;


    @BindView(R.id.left_followme)
    protected TextView mTextViewUserFollowMeSum;

    private PresenterPersonal mPresenterPersonal;
    private int mSumIFollowed;
    private int mSumFollowMe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_personal_viewpager_left, container, false);
        ButterKnife.bind(this, view);
        mPresenterPersonal = new PresenterPersonalImpl(this);
        mBeanUser = (BeanUser) getArguments().get("beanUser");
        mPresenterPersonal.loadSumUserFollowedByUserId(mBeanUser.getId());
        mPresenterPersonal.loadSumUserFollowMeByUserId(mBeanUser.getId());
        String avatarUrl = mBeanUser.getAvatar();

        Glide.with(getActivity()).load(avatarUrl).into(mCircleImageViewUserAvatar);
        //// TODO: 2017/5/22 根据用户等级判断应该使用哪一张图片
        int level = mBeanUser.getLevel();
        mCircleImageViewUserLevel.setImageResource(R.drawable.girl);
        mTextViewUserName.setText(mBeanUser.getUserName());
        mTextViewHeight.setText(mBeanUser.getHeight());
        mTextViewAge.setText(mBeanUser.getAge());
        mTextViewWeight.setText(mBeanUser.getWeight());


        mTextViewSignNature.setText(mBeanUser.getSignature());
        mTextViewUserFollowedSum.setText(mSumIFollowed + "");
        mTextViewUserFollowMeSum.setText(mSumFollowMe + "");
        return view;
    }

    @Override
    public void getSumIFoloowed(int sum) {
        mSumIFollowed = sum;
    }

    @Override
    public void getSumFollowMe(int sum) {
        mSumFollowMe = sum;
    }
}
