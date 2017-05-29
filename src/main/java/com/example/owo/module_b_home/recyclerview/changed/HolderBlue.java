package com.example.owo.module_b_home.recyclerview.changed;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owo.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/5/14
 */
public class HolderBlue extends HolderTest<BeanBlue> {


    //头像
    @BindView(R.id.item_blue_portrait)
    protected CircleImageView mCircleImageViewAvatar;

    //性别

    @BindView(R.id.item_blue_sex)
    protected ImageView mImageViewSex;

    //标题
    @BindView(R.id.item_blue_title)
    protected TextView mTextViewTitle;

    //线上活动剩余时间
    @BindView(R.id.item_blue_resttime)
    protected TextView mTextViewRestTime;

    //活动参与人数
    @BindView(R.id.item_blue_sum)
    protected TextView mTextViewSum;

    public HolderBlue(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(BeanBlue beanBlue) {
        mCircleImageViewAvatar.setImageResource(R.drawable.tabbar_me);
    }
}
