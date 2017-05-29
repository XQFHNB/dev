package com.example.owo.module_a_login.widgets;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_a_register.AtyRegister;
import com.example.owo.module_b_main.AtyMain;
import com.example.owo.module_a_login.presenter.PresenterLogin;
import com.example.owo.module_a_login.presenter.PresenterLoginImpl;
import com.example.owo.module_a_login.view.ViewLogin;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.utils.Common;
import com.example.owo.utils.util_http.HttpHelper;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * @author XQF
 * @created 2017/5/18
 */
public class FragLoginOrRegister extends FragBase implements ViewLogin {

    public static FragLoginOrRegister newInstance() {
        return new FragLoginOrRegister();
    }


    //登陆按钮
    @BindView(R.id.btn_login)
    protected Button mBtn;

    //电话信息栏
    @BindView(R.id.editV_frag_login_or_register_phone)
    protected EditText mEditTextPhone;

    //密码信息栏
    @BindView(R.id.editV_frag_login_or_register_password)
    protected EditText mEditTextPassword;


    //忘记密码
    @BindView(R.id.editV_frag_login_or_register_forget_password)
    protected TextView mTextViewForgetPwd;

    //注册
    @BindView(R.id.editV_frag_login_or_register_toregister)
    protected TextView mTextViewRegister;
    //交互者
    private PresenterLogin mPresenterLogin;
    //网络获取的登陆结果
    private String mResultLogin;

    //用户信息的bean
    private BeanUser mBeanUser;

    //根据id拿到用户信息后
    private HashMap<String, Object> mMap;
    //这是保存数据的sharedp,主要是用来保存ID的
    private SharedPreferences.Editor mEditor;
    // 
    private int mUserId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_login_or_register_layout, container, false);
        ButterKnife.bind(this, view);
        mPresenterLogin = new PresenterLoginImpl(this);
        Common.userSP = getActivity().getSharedPreferences("userSp", 0);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Common.userSP = getActivity().getSharedPreferences("userSP", 0);
        //从splash过来不用传数据，但是会有退出登录的地方到这里来
        Intent intent = getActivity().getIntent();
        String intentStr = intent.getStringExtra("logout");

        //检查一下网络设置
        if (!Common.isNetworkAvailable(getActivity())) {
            Common.display(getActivity(), "检查手机网络设置！");
        } else {
            if (intentStr == null) {//不是由其他界面跳转过来的，检查是不是有本地信息的id，要是有则直接进行跳转，要是没有，等待用户按下登陆键
                mUserId = Common.userSP.getInt("ID", 0);
                mPresenterLogin.loadUserById(mUserId);
                if (mUserId != 0) {
//                    jpushUpdate();
                    start(getActivity(), AtyMain.class);
                    AtyMain.start(getActivity(), AtyMain.class, false);
                }
            }
        }
    }

    //如果有记录就自动登陆

    /**
     * 点击登陆
     */
    @OnClick(R.id.btn_login)
    public void onBtnClick() {
        String phone = mEditTextPhone.getText().toString().trim();
        String password = mEditTextPassword.getText().toString().trim();
        loginByPhoneAndPassword(phone, password);
    }

    /*
    忘记密码
     */
    @OnClick(R.id.editV_frag_login_or_register_forget_password)
    public void onClickForgetPwd() {
    }

    /*
    前往注册
     */
    @OnClick(R.id.editV_frag_login_or_register_toregister)
    public void onClickRegiter() {
        start(getActivity(), AtyRegister.class);
    }


    /**
     * 登陆
     *
     * @param phone    电话
     * @param password 密码
     */
    private void loginByPhoneAndPassword(String phone, String password) {
        mPresenterLogin.loginByPhoneAndPwd(phone, password);
        try {
            Object data = HttpHelper.AnalysisData(mResultLogin);
            if (data != null) {
                mUserId = (int) data;
                mPresenterLogin.loadUserById(mUserId);
                mEditor = Common.userSP.edit();
                mEditor.putInt("ID", mUserId);//保存信息
                mEditor.putString("phone", phone);
                mEditor.putString("password", password);
                mEditor.commit();
//                jpushUpdate();
                start(getActivity(), AtyMain.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getResultLogin(String string) {
        mResultLogin = string;
    }

    @Override
    public void getResultGetUserById(BeanUser beanUser) {
        mBeanUser = beanUser;
    }

    /**
     * 不知道是什么作用，抄下来就是了
     */
    private void jpushUpdate() {
        Set<String> tags = new HashSet<>();
        int a = mBeanUser.getStatus();
        tags.add(mBeanUser.getStatus() + "");
        if (mUserId != 0 && mBeanUser.getStatus() != 0) {
            JPushInterface.setAliasAndTags(getActivity().getApplicationContext(), mUserId + "", tags, new TagAliasCallback() {
                @Override
                public void gotResult(int responseCode, String alias, Set<String> tags) {
                    // TODO
                    if (responseCode == 0) {
                        //加载成功
                        Log.i("AliasAndTags", alias + " " + tags.toString());
                    }
                }
            });
        }
    }

}
