package com.example.owo.module_b_home.recyclerview.normal.holder;

import android.view.View;
import android.widget.TextView;

import com.example.owo.R;
import com.example.owo.base.HolderBase;
import com.example.owo.module_b_home.recyclerview.normal.bean.BeanActivityLabel;

import butterknife.BindView;

/**
 * @author XQF
 * @created 2017/5/21
 */
public class HolderActivityLabel extends HolderBase<BeanActivityLabel> {

    @BindView(R.id.item_label1)
    protected TextView mTextViewLabel;

    public HolderActivityLabel(View itemView) {
        super(itemView);
    }

    public void bind(BeanActivityLabel beanActivityLabel) {
        mTextViewLabel.setText(beanActivityLabel.getContent());
    }
}
