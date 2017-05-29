package com.example.owo.module_b_explore.model;

import java.util.HashMap;

/**
 * Created by XQF on 2017/5/20.
 */
public interface ModelExplore {

    void loadRecommendUser(HashMap<String, String> map, OnLoadListener listener);

    void loadUserByDistance(HashMap<String, String> map, OnLoadListener listener);

    void sendAddFriend(HashMap<String, String> map);
}
