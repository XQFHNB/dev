package com.example.owo.module_b_home.view;

import com.example.owo.module_b_home.bean.BeanProp;
import com.example.owo.module_b_home.bean.BeanTask;

import java.util.List;

/**
 * Created by XQF on 2017/5/20.
 */
public interface ViewHome {

    void getTasksIApplied(List<BeanTask> taskList);

    void getTasksIPublished(List<BeanTask> taskList);

    void getMyProps(List<BeanProp> propList);
}
