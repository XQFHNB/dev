package com.example.owo.module_b_personal.view;

import com.example.owo.module_b_personal.bean.BeanComment;
import com.example.owo.module_b_personal.bean.BeanLike;
import com.example.owo.module_b_personal.bean.BeanUser;

import java.util.List;

/**
 * Created by XQF on 2017/5/23.
 */
public interface ViewPersonalViewpagerLeftHolder {
    void getResultComment(List<BeanComment> list);

    void getResultLike(List<BeanLike> like);

    void getPersonalInfo(BeanUser beanUser);

}
