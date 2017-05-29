package com.example.owo.module_b_personal.recyclerview.holder;

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

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/5/21
 */
public class HolderActivity extends HolderBase<BeanTask> implements ViewPersonal {


    @BindView(R.id.imageV_item_activity_avatar)
    protected CircleImageView mCircleImageViewAvatar;

    @BindView(R.id.textV_item_activity_username)
    protected TextView mTextViewUserName;

    @BindView(R.id.textV_item_activity_name)
    protected TextView mTextViewActivityName;

    @BindView(R.id.textV_item_activity_deadline)
    protected TextView mTextViewDeadLine;

    @BindView(R.id.textV_item_activity_where)
    protected TextView mTextViewWhere;

    @BindView(R.id.textV_item_activity_status)
    protected TextView mTextViewStatus;

    @BindView(R.id.textV_item_activity_publishtime)
    protected TextView mTextViewPublishtime;

    @BindView(R.id.cardView_item_activity)
    protected CardView mCardView;
    private Context mContext;
    private String mUrlAvatar;
    private String mUserName;
    private PresenterPersonal mPresenterPersonal;

    public HolderActivity(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mPresenterPersonal = new PresenterPersonalImpl(this);
    }

    public void bind(BeanTask beanTask) {

        int userId = beanTask.getTaskUserID();
        mPresenterPersonal.loadUserPersaonlInfoById(userId);
        //头像
        Glide.with(mContext).load(mUrlAvatar).into(mCircleImageViewAvatar);
        mTextViewUserName.setText(mUserName);
        mTextViewActivityName.setText(beanTask.getTaskName());
        mTextViewDeadLine.setText(beanTask.getTaskDeadLine());
        String latitude = beanTask.getTaskLatitude();
        String longitude = beanTask.getTaskLongitude();
        // TODO: 2017/5/21 根据地图获取where

        mTextViewWhere.setText(getWhere(latitude, longitude));
        mTextViewStatus.setText(beanTask.getTaskStatus());
        mTextViewPublishtime.setText(beanTask.getTaskPublishTime());

    }

    @Override
    public void getPersonalInfo(BeanUser beanUser) {
        mUrlAvatar = beanUser.getAvatar();
        mUserName = beanUser.getUserName();
    }

    public String getWhere(String latitude, String longitude) {
        return null;
    }

    @OnClick(R.id.cardView_item_activity)
    public void onClickItem() {
        // TODO: 2017/5/24 跳转到细节

    }
}
