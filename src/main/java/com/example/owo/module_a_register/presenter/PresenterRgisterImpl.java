package com.example.owo.module_a_register.presenter;

import com.example.owo.module_a_register.model.ModelRegister;
import com.example.owo.module_a_register.model.ModelRegisterImpl;
import com.example.owo.module_a_register.model.OnLoadListener;
import com.example.owo.module_a_register.view.ViewRegister;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.utils.UtilLog;
import com.example.owo.utils.util_http.HttpHelper;

import org.json.JSONException;

import java.util.HashMap;

/**
 * @author XQF
 * @created 2017/5/18
 */
public class PresenterRgisterImpl implements PresenterRegister {

    ModelRegister mModelRegister;
    ViewRegister mViewRegister;

    public PresenterRgisterImpl(ViewRegister viewRegister) {
        mModelRegister = new ModelRegisterImpl();
        mViewRegister = viewRegister;
    }

    @Override
    public void registerByPhoneAndPsw(String username, String phone, String password) {
        HashMap<String, String> paramHM = new HashMap<>();
        paramHM.put("phonenumber", phone);
        paramHM.put("password", password);
        paramHM.put("username", username);

        mModelRegister.register(paramHM, new OnLoadListener() {
            @Override
            public void onSucess(String jsonData) {
                mViewRegister.getRegisterResult(jsonData);
            }
        });
    }

    @Override
    public void registerPoseCameraByPhoneAndMd5passsword(String username, String phone, String md5Password) {

        UtilLog.d("test", " pose username: " + username + " phone:" + phone + " md5:" + md5Password);
        HashMap<String, String> paramHM = new HashMap<>();
        paramHM.put("username", username);
        paramHM.put("userphone", phone);
        paramHM.put("password", md5Password);
        paramHM.put("userpb", "0");
        paramHM.put("taskpic_url", "119.29.245.167:8089/head.png");
        mModelRegister.registerPoseCamera(paramHM, new OnLoadListener() {
            @Override
            public void onSucess(String jsonData) {

            }
        });
    }


    @Override
    public void loginPoseCameraByNameAndMd5password(String username, String md5Password, int userId) {
        HashMap<String, String> paramHM = new HashMap<>();
        paramHM.put("username", username);
        paramHM.put("password", md5Password);
        mModelRegister.loginPoseCamera(paramHM, userId);
    }


}
