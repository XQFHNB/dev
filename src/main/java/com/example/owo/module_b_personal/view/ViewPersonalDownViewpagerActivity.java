package com.example.owo.module_b_personal.view;

import com.example.owo.module_b_home.bean.BeanTask;

import java.util.List;

/**
 * Created by XQF on 2017/5/21.
 */
public interface ViewPersonalDownViewpagerActivity {
    void getTasksIApplied(List<BeanTask> taskList);

    void getTasksIPublished(List<BeanTask> taskList);
}
