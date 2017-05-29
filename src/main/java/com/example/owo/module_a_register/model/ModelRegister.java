package com.example.owo.module_a_register.model;

import java.util.HashMap;

/**
 * Created by XQF on 2017/5/18.
 */
public interface ModelRegister {

    void register(HashMap<String, String> map, OnLoadListener listener);

    void registerPoseCamera(HashMap<String, String> map, OnLoadListener listener);

    void loginPoseCamera(HashMap<String, String> map, int userId);



}
