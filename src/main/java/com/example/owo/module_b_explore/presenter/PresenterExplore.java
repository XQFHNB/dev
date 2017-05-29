package com.example.owo.module_b_explore.presenter;

/**
 * Created by XQF on 2017/5/20.
 */
public interface PresenterExplore {
    void getUserOrderByMatchIndex(String hobby);

    void getUserOrderByDistance(String latitude, String langtitude);

    void sendAddFriendByIdAndFriendId(int userId, int friendId);
}
