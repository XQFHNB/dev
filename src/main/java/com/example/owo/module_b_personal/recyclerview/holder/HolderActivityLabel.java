package com.example.owo.module_b_personal.recyclerview.holder;

import android.view.View;
import android.widget.TextView;

import com.example.owo.base.HolderBase;
import com.example.owo.module_b_personal.bean.BeanActivityLabel;

/**
 * @author XQF
 * @created 2017/5/21
 */
public class HolderActivityLabel extends HolderBase<BeanActivityLabel> {

    protected TextView mTextViewLabel;

    public HolderActivityLabel(View itemView) {
        super(itemView);
    }

    public void bind(BeanActivityLabel beanActivityLabel) {
        mTextViewLabel.setText(beanActivityLabel.getContent());
    }
}
