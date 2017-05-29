package com.example.owo.module_b_personal.recyclerview.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.owo.R;
import com.example.owo.base.HolderBase;
import com.example.owo.module_b_personal.bean.BeanComment;
import com.example.owo.module_b_personal.bean.BeanLike;
import com.example.owo.module_b_personal.bean.BeanMoment;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.view.ViewPersonalViewpagerLeftHolder;
import com.example.owo.module_c_detail.AtyDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/5/17
 */
public class HolderMoment extends HolderBase<BeanMoment> implements ViewPersonalViewpagerLeftHolder {

    @BindView(R.id.moment_avatar)
    protected CircleImageView mCircleImageView;


    @BindView(R.id.moment_username)
    protected TextView mTextViewUsername;

    @BindView(R.id.moment_content_text)
    protected TextView mTextViewContentText;

    @BindView(R.id.moment_content_image)
    protected ImageView mImageViewContentImage;


    @BindView(R.id.moment_cmnt)
    protected ImageView mImageViewMomentCmnt;

    @BindView(R.id.moment_cmnt_num)
    protected TextView mTextViewMomentCmntSum;


    @BindView(R.id.moment_like)
    protected ImageView mImageViewMomentLike;


    @BindView(R.id.moment_like_num)
    protected TextView mTextViewMomentLikeSum;

    private Context mContext;
    private PresenterPersonal mPresenterPersonal;
    private List<BeanComment> mBeanCommentList;
    private List<BeanLike> mBeanLikes;
    private BeanUser mBeanUser;
    private int mCommentId;
    private BeanMoment mBeanMoment;

    public HolderMoment(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mPresenterPersonal = new PresenterPersonalImpl(this);
    }

    @Override
    public void bind(BeanMoment beanMoment) {
        mBeanMoment = beanMoment;
        mCommentId = beanMoment.getMomentCommentId();


        //这个地方有问题，加载评论的时候
//        mPresenterPersonal.loadResultCommentByCommentId(mCommentId);
//        mPresenterPersonal.loadResultCommentByUserId(mCommentId);
        mPresenterPersonal.loadResultLikesByCommentId(mCommentId);
        mPresenterPersonal.loadUserPersaonlInfoById(beanMoment.getMomentUserId());

        Glide.with(mContext).load(beanMoment.getMomentUserAvatar()).into(mCircleImageView);
        mTextViewUsername.setText(beanMoment.getMomentUserName());
        mTextViewContentText.setText(beanMoment.getMomentContent());
        Glide.with(mContext).load(beanMoment.getMomentImage()).into(mImageViewContentImage);
        mTextViewMomentCmntSum.setText(mBeanCommentList.size());
        mTextViewMomentLikeSum.setText(mBeanLikes.size());
    }


    /**
     * 点击查看详情或评论
     */
    @OnClick(R.id.moment_cmnt)
    public void onClickCmnt() {
        AtyDetail.startAtyDetail(mContext, AtyDetail.class, mBeanUser, mBeanCommentList, mBeanLikes);
    }


    /**
     * 点赞
     */
    @OnClick(R.id.moment_like)
    public void onClickLike() {
        mPresenterPersonal.sendUpdateMomentLikeByMomentIdAndLikeNum(mBeanMoment.getMomentId(), mBeanLikes.size() + 1);
    }


    @Override
    public void getResultComment(List<BeanComment> list) {
        mBeanCommentList = list;
    }

    @Override
    public void getResultLike(List<BeanLike> like) {
        mBeanLikes = like;
    }

    @Override
    public void getPersonalInfo(BeanUser beanUser) {
        mBeanUser = beanUser;
    }
}
