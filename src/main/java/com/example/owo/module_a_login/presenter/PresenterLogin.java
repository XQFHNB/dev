package com.example.owo.module_a_login.presenter;

/**
 * Created by XQF on 2017/5/19.
 */
public interface PresenterLogin {
    void loginByPhoneAndPwd(String phone, String pwd);

    void loadUserById(int userId);
}
