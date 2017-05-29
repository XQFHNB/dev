package com.example.owo.module_b_personal.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_a_selectlabel.bean.BeanTag;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.bean.BeanUserCommentLabel;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.view.ViewPersonalViewpagerRight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/5/17
 */
public class FragPersonalViewpagerRight extends FragBase implements ViewPersonalViewpagerRight {


    public static FragPersonalViewpagerRight newInstance(BeanUser beanUser) {
        FragPersonalViewpagerRight fragPersonalViewpagerRight = new FragPersonalViewpagerRight();
        Bundle args = new Bundle();
        args.putSerializable("beanUser", beanUser);
        fragPersonalViewpagerRight.setArguments(args);
        return fragPersonalViewpagerRight;
    }

    @BindView(R.id.right_ratingbar)
    protected RatingBar mRatingBar;

    @BindView(R.id.right_btn_selfcomment1)
    protected TextView mTextViewSelfTags1;

    @BindView(R.id.right_btn_selfcomment2)
    protected TextView mTextViewSelfTags2;

    @BindView(R.id.right_btn_selfcomment3)
    protected TextView mTextViewSelfTags3;

    @BindView(R.id.right_btn_selfcomment4)
    protected TextView mTextViewSelfTags4;

    protected TextView[] selfTags = new TextView[]{
            mTextViewSelfTags1, mTextViewSelfTags2, mTextViewSelfTags3, mTextViewSelfTags4};


    @BindView(R.id.right_btn_othercomment1)
    protected TextView mTextViewOtherComment1;

    @BindView(R.id.right_btn_othercomment2)
    protected TextView mTextViewOtherComment2;
    @BindView(R.id.right_btn_othercomment3)
    protected TextView mTextViewOtherComment3;
    @BindView(R.id.right_btn_othercomment4)
    protected TextView mTextViewOtherComment4;

    protected TextView[] otherComments = new TextView[]{
            mTextViewOtherComment1, mTextViewOtherComment2, mTextViewOtherComment3, mTextViewOtherComment4};


    @BindView(R.id.right_btn_overalltags)
    protected Button mBtnLookAllTags;

    @BindView(R.id.right_mycoins)
    protected TextView mTextViewMyCoins;

    @BindView(R.id.right_mysteps)
    protected TextView mTextViewMySteps;

    private BeanUser mBeanUser;
    private int mUserScore;
    private List<BeanUserCommentLabel> mUserLabels;
    private List<BeanTag> mUserSelfTag;

    private PresenterPersonal mPresenterPersonal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_personal_viewpager_right, container, false);
        ButterKnife.bind(this, view);
        mBeanUser = (BeanUser) getArguments().get("beanUser");
        mPresenterPersonal = new PresenterPersonalImpl(this);
        try {
            mUserSelfTag = getUserSelfTag();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenterPersonal.loadUserCommentBy(mBeanUser.getId());
        mRatingBar.setNumStars(mUserScore);

        for (int i = 0; i < 4; i++) {
            selfTags[i].setText(mUserSelfTag.get(i).getTag());
            otherComments[i].setText(mUserLabels.get(i).getLabel());
        }

        mTextViewMyCoins.setText(mBeanUser.getMoney());
        mTextViewMySteps.setText(mBeanUser.getStepsToday());
        return view;
    }

    @Override
    public void getUserComment(int score, List<BeanUserCommentLabel> list) {
        mUserScore = score;
        mUserLabels = list;
    }

    public List<BeanTag> getUserSelfTag() throws JSONException {
        String hobby = mBeanUser.getHobby();
        JSONObject jsonObject = new JSONObject(hobby);
        JSONArray selfTagsArray = (JSONArray) jsonObject.get("self");

        List<BeanTag> result = new ArrayList<>();
        for (int i = 0; i < selfTagsArray.length(); i++) {
            JSONObject jsonObject1 = (JSONObject) selfTagsArray.get(i);

            if (mBeanUser.getSex() == "1") {
                JSONArray array = (JSONArray) jsonObject1.get("" + i);
                for (int j = 0; j < array.length(); j++) {
                    BeanTag beanTag = new BeanTag();
                    JSONObject jsonObject2 = (JSONObject) array.get(j);
                    beanTag.setTag((String) jsonObject2.get("data"));
                    result.add(beanTag);
                }
            } else {
                JSONArray array = (JSONArray) jsonObject1.get("" + i + 1);
                for (int j = 0; j < array.length(); j++) {
                    BeanTag beanTag = new BeanTag();
                    JSONObject jsonObject2 = (JSONObject) array.get(j);
                    beanTag.setTag((String) jsonObject2.get("data"));
                    result.add(beanTag);
                }
            }

        }


        return result;
    }

    @OnClick(R.id.right_btn_overalltags)
    public void onClickOverAllTags() {

    }
}
