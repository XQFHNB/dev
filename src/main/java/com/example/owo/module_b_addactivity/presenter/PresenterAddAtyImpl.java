package com.example.owo.module_b_addactivity.presenter;

import com.example.owo.module_b_addactivity.model.ModelAddAty;
import com.example.owo.module_b_addactivity.model.ModelAddAtyImpl;
import com.example.owo.module_b_addactivity.view.ViewAddAty;

import java.util.HashMap;

/**
 * @author XQF
 * @created 2017/5/26
 */
public class PresenterAddAtyImpl implements PresenterAddAty {

    ModelAddAty mModelAddAty;
    ViewAddAty mViewAddAty;

    public PresenterAddAtyImpl(ViewAddAty viewAddAty) {
        mModelAddAty = new ModelAddAtyImpl();
        mViewAddAty = viewAddAty;
    }


    @Override
    public void sendTaskByParamMap(HashMap<String, String> map, HashMap<String, String> map1) {
        mModelAddAty.sendTask(map, map1);
    }
}
