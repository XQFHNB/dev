package com.example.owo.module_a_selectlabel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import zhouyou.flexbox.widget.BaseTagView;

/**
 * @author XQF
 * @created 2017/5/28
 */
class StringTagView extends BaseTagView<String> {

    public StringTagView(Context context) {
        this(context, null);
    }

    public StringTagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public StringTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setItem(String item) {
        super.setItem(item);
        textView.setText(item);
    }
}