package com.example.owo.module_b_explore.recyclerview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.owo.R;
import com.example.owo.module_b_explore.bean.BeanRecyclerViewItem;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.view.ViewPersonal;
import com.example.owo.module_c_detail.AtyDetail;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/5/11
 */
public class Holder extends RecyclerView.ViewHolder implements ViewPersonal {

    @BindView(R.id.frag_explore_nearby_item_name)
    protected TextView mTextViewName;

    @BindView(R.id.frag_explore_nearby_item_avatar)
    protected CircleImageView mCircleImageView;

    @BindView(R.id.frag_explore_nearby_item_dis)
    protected TextView mTextViewDis;

    @BindView(R.id.frag_explore_nearby_item_sex)
    protected ImageView mImageViewSex;

    @BindView(R.id.frag_explore_nearby_item_age)
    protected TextView mTextViewAge;

    @BindView(R.id.frag_explore_nearby_item_height)
    protected TextView mTextViewHeight;

    @BindView(R.id.frag_explore_nearby_item_weight)
    protected TextView mTextViewWeight;

    @BindView(R.id.frag_explore_nearby_item_autograph)
    protected TextView mTextViewAutograph;

    @BindView(R.id.frag_explore_nearby_item_status)
    protected Button mButtonStatus;

    @BindView(R.id.nearby_item_cardview)
    protected CardView mCardView;


    private PresenterPersonal mPresenterPersonal;
    private BeanUser mBeanUser;
    private Context mContext;

    public Holder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mPresenterPersonal = new PresenterPersonalImpl(this);
        mContext = context;
    }

    public void bind(final BeanRecyclerViewItem item) {

        int useId = item.getUserId();
        mPresenterPersonal.loadUserPersaonlInfoById(useId);
        mTextViewName.setText(mBeanUser.getUserName());
        Glide.with(mContext).load(mBeanUser.getAvatar()).into(mCircleImageView);
        mTextViewDis.setText(item.getDis() + "m");
        if (item.getSex() == 1) {
            mImageViewSex.setImageResource(R.drawable.frag_home_change_item_men);
        } else {
            mImageViewSex.setImageResource(R.drawable.frag_home_change_item_wemen);
        }
        mTextViewAge.setText(mBeanUser.getAge());
        mTextViewHeight.setText(mBeanUser.getHeight());
        mTextViewWeight.setText(mBeanUser.getWeight());
        mTextViewAutograph.setText(mBeanUser.getSignature());
        mButtonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter = item.getCounter();
                if (counter % 2 == 0) {
                    mButtonStatus.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                } else {
                    mButtonStatus.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
                }
                counter++;
                item.setCounter(counter);
            }
        });
    }

    @Override
    public void getPersonalInfo(BeanUser beanUser) {
        mBeanUser = beanUser;
    }


    /**
     * 跳往主界面
     */
    @OnClick(R.id.nearby_item_cardview)
    public void onClickCardView() {
        AtyDetail.startAtyDetail(mContext, AtyDetail.class, mBeanUser);
    }
}
