package com.example.owo.module_a_register.widgets;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_main.AtyMain;
import com.example.owo.module_a_register.presenter.PresenterRegister;
import com.example.owo.module_a_register.presenter.PresenterRgisterImpl;
import com.example.owo.module_a_register.view.ViewRegister;
import com.example.owo.utils.Common;
import com.example.owo.utils.EncodeAndDecode;
import com.example.owo.utils.util_http.HttpHelper;
import com.example.owo.utils.UtilLog;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/5/18
 */
public class FragRegister extends FragBase implements ViewRegister {


    public static FragRegister newInstance() {
        return new FragRegister();
    }


    //交互中介
    private PresenterRegister mPresenterRegister;
    //用户ID
    private int mUserId;

    //保存用户ID
    private SharedPreferences.Editor mEditor;


    private String mUserName;
    private String mUserPhone;
    private String mUserPassword;
    private String mUserPasswordAgain;
    private String mUserMd5password;

    private String mResultRegister;


    @BindView(R.id.editV_frag_login_or_register_phone)
    protected EditText mEditTextPhone;
    @BindView(R.id.editV_frag_login_or_register_password)
    protected EditText mEditTextPwd;
    @BindView(R.id.btn_register)
    protected Button mButtonRegister;


    public ProgressDialog mDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_register_layout, container, false);
        ButterKnife.bind(this, view);
        mPresenterRegister = new PresenterRgisterImpl(this);
        return view;
    }


    /**
     * 注册时的逻辑
     */

    @OnClick(R.id.btn_register)
    public void onBtnRegisterClick() {
        mDialog = showSpinnerDialog();
        mUserPhone = mEditTextPhone.getText().toString();
        mUserPassword = mEditTextPwd.getText().toString();
        mUserName = "u_" + Common.createRandom(true, 6);
        mDialog.show();
        //注册应用
        mPresenterRegister.registerByPhoneAndPsw(mUserName, mUserPhone, mUserPassword);

//        start(getActivity(), AtyMain.class);
        AtyMain.start(getActivity(), AtyMain.class, true);

    }

    /**
     * 注册成功返回数据
     *
     * @param string 返回的数据
     */

    @Override
    public void getRegisterResult(String string) {
        if (string != null) {
            mDialog.dismiss();
        }

        mResultRegister = string;
        UtilLog.d("test", "注册成功返回的数据  mResultRegister" + mResultRegister);
        UtilLog.d("test", "进入注册pose相机之前  mResultRegister" + mResultRegister);
        registerPoseCameraAndSoOn(mResultRegister);
    }


    /**
     * 根据注册返回的信息数据注册pose相机等等一系列的操作
     *
     * @param string
     */
    private void registerPoseCameraAndSoOn(String string) {
        UtilLog.d("test", "准备解析的pose相机: " + string);
        try {
            Object objId = HttpHelper.AnalysisData(string);
//            UtilLog.d("test", "objectid " + objId);

//            if (objId != null) {
//                Object object = HttpHelper.AnalysisData(string);
            //拿到用户ID后保存在本地
            mUserId = Integer.parseInt(objId.toString());
            UtilLog.d("test", "mUserId: " + mUserId);
            mEditor = Common.userSP.edit();
            mEditor.putInt("ID", mUserId);
            mEditor.putInt("status", 1);
            mEditor.commit();


            int testUserId = Common.userSP.getInt("ID", 0);
            UtilLog.d("test", "testUserId: " + testUserId);

            mUserMd5password = EncodeAndDecode.getMD5Str(mUserPassword);
            //注册pose相机
            mPresenterRegister.registerPoseCameraByPhoneAndMd5passsword(mUserName, mUserPhone, mUserMd5password);
            //登陆pose相机
            mPresenterRegister.loginPoseCameraByNameAndMd5password(mUserName, mUserMd5password, mUserId);
//            } else {
//                UtilLog.d("test", "objectid is null" + objId);
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
