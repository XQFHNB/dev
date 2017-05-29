package com.example.owo.module_b_home.recyclerview.normal.holder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.owo.R;
import com.example.owo.base.HolderBase;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.view.ViewPersonal;
import com.example.owo.module_c_detail.AtyDetail;
import com.example.owo.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/5/25
 */
public class HolderActivity extends HolderBase<BeanTask> implements ViewPersonal {


    @BindView(R.id.imageV_normal_item_activity_avatar)
    protected CircleImageView mCircleImageViewAvatar;
    @BindView(R.id.textV_normal_item_activity_username)
    protected TextView mTextViewUserName;
    @BindView(R.id.textV_normal_item_activity_name)
    protected TextView mTextViewActivityName;

    @BindView(R.id.textV_normal_item_activity_waitcomment)
    protected TextView mTextViewWaitComment;
    @BindView(R.id.textV_normal_item_activity_news)
    protected TextView mTextViewNews;
    @BindView(R.id.textV_normal_item_activity_deadline)

    protected TextView mTextViewTime;
    @BindView(R.id.textV_normal_item_activity_where)
    protected TextView mTextViewWhere;

    @BindView(R.id.cardView_normal_item_activity)
    protected CardView mCardView;

    private Context mContext;
    private String mUrlAvatar;
    private String mUserName;
    private PresenterPersonal mPresenterPersonal;
    private BeanTask mBeanTask;


    public HolderActivity(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mPresenterPersonal = new PresenterPersonalImpl(this);
    }

    @Override
    public void bind(BeanTask beanTask) {
        int status = beanTask.getTaskStatus();
        int userId = beanTask.getTaskUserID();
        mPresenterPersonal.loadUserPersaonlInfoById(userId);
        Glide.with(mContext).load(mUrlAvatar).into(mCircleImageViewAvatar);
        mTextViewUserName.setText(mUserName);
        mTextViewActivityName.setText(beanTask.getTaskName());
        //正在报名中
        if (status == Constants.TASK_STATUS_ENROOLING) {
            mTextViewWaitComment.setVisibility(View.GONE);
            mTextViewNews.setVisibility(View.VISIBLE);
            int sum = beanTask.getTaskTakenNum();
            mTextViewNews.setText(sum + " ");
        } else if (status == Constants.TASK_STATUS_WORKING) {
            mTextViewNews.setVisibility(View.GONE);
            mTextViewWaitComment.setVisibility(View.VISIBLE);
        }
        mTextViewTime.setText(beanTask.getTaskDeadLine());
        String latitude = beanTask.getTaskLatitude();
        String longitude = beanTask.getTaskLongitude();
        mTextViewWhere.setText(getWhere(latitude, longitude));
    }

    @Override
    public void getPersonalInfo(BeanUser beanUser) {
        mUrlAvatar = beanUser.getAvatar();
        mUserName = beanUser.getUserName();
    }

    public String getWhere(String latitude, String longitude) {
        return null;
    }

    /**
     * 活动的点击事件
     */
    @OnClick(R.id.cardView_normal_item_activity)
    public void onClickCardView() {
        AtyDetail.startAtyDetail(mContext, AtyDetail.class, mBeanTask);
    }
}
