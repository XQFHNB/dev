package com.example.owo.module_b_home.widgets;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.owo.R;
import com.example.owo.base.BeanBase;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_home.bean.BeanProp;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_home.presenter.PresenterHomeImpl;
import com.example.owo.module_b_home.presenter.PresneterHome;
import com.example.owo.module_b_home.recyclerview.normal.adapter.AdapterNormal;
import com.example.owo.module_b_home.recyclerview.normal.bean.BeanActivityLabel;
import com.example.owo.module_b_home.view.ViewHome;
import com.example.owo.utils.Common;
import com.example.owo.utils.Constants;
import com.example.owo.utils.UtilLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltipUtils;

/**
 * @author XQF
 * @created 2017/5/10
 */
public class FragHomeNormal extends FragBase implements ViewHome {
    private static final int REQUEST_CODE_GALLERY = 111;


    //中间的点我按钮
    @BindView(R.id.btn_test)
    protected Button mBtnTest;

    //定位按钮
    @BindView(R.id.btn_location)
    protected Button mBtnLocation;

    //道具按钮
    @BindView(R.id.btn_prop)
    protected Button mBtnProp;

    //查看自己活动按钮
    @BindView(R.id.btn_activities)
    protected Button mBtnActivities;

    private PresneterHome mPresneterHome;
    private int mUserId;
    private List<BeanTask> mTasks;

    private List<List<BeanTask>> mBeanTasksResult;

    //正在接受报名
    private List<BeanTask> mTaskOnReceiving;
    //正在进行中的
    private List<BeanTask> mTaskOnWorking;
    private List<BeanBase> mBeanBases;


    private String[] mlabels = new String[]{
            "接收报名中",
            "正在进行中",
    };


    private List<BeanProp> mBeanProps;

    private String picPath;
    private Bitmap bitmap;


    public static FragHomeNormal newInsstance() {
        return new FragHomeNormal();
    }

    private boolean mIsOpenDialog = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home_normal_layout, container, false);
        ButterKnife.bind(this, view);
        mPresneterHome = new PresenterHomeImpl(this);

        mIsOpenDialog = getActivity().getIntent().getBooleanExtra("flag", false);
        mTasks = new ArrayList<>();
        mBeanProps = new ArrayList<>();
        //从前面放进去的数据里面取出ID
        mUserId = Common.userSP.getInt("ID", 0);
//        mUserId = 173;
        UtilLog.d("test", "本地取出来的userID: " + mUserId);


        //加载申请任务
        mPresneterHome.loadTasksAppliedByUserId(mUserId);
        //加载发布的任务
        mPresneterHome.loadTasksPublishedByUserId(mUserId);
        //加载我的道具
        mPresneterHome.loadUserPropNyUserId(mUserId);

        mTasks = new ArrayList<>();
        mBeanTasksResult = new ArrayList<>();
        mTaskOnReceiving = new ArrayList<>();
        mTaskOnWorking = new ArrayList<>();
        mBeanBases = new ArrayList<>();

        if (mIsOpenDialog) {

            mBtnTest.performClick();
        } else {
            mBtnTest.setVisibility(View.GONE);

        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int i = 0; i < mTasks.size(); i++) {
            BeanTask task = mTasks.get(i);
            if (task.getTaskStatus() == Constants.TASK_STATUS_ENROOLING) {
                mTaskOnReceiving.add(task);
            } else if (task.getTaskStatus() == Constants.TASK_STATUS_WORKING) {
                mTaskOnWorking.add(task);
            }
        }
        mTasks.clear();

        mBeanTasksResult.add(mTaskOnReceiving);
        mBeanTasksResult.add(mTaskOnWorking);
        for (int i = 0; i < mBeanTasksResult.size(); i++) {
            mBeanBases.add(new BeanActivityLabel(mlabels[i]));
            mBeanBases.addAll(mBeanTasksResult.get(i));
        }


    }

    /**
     * 点击定位按钮弹出内容
     */
    @OnClick(R.id.btn_location)
    public void onBtnLocationClick() {
        new SimpleTooltip.Builder(getActivity())
                .anchorView(mBtnLocation)
                .text(R.string.btn_simple)
                .gravity(Gravity.START)
                .build()
                .show();

    }


    /**
     * 点击道具按钮弹出的内容
     */
    @OnClick(R.id.btn_prop)
    public void onBtnPropClick() {
        new SimpleTooltip.Builder(getActivity())
                .anchorView(mBtnProp)
                .text("使用道具")
                .gravity(Gravity.TOP)
                .contentView(R.layout.tooltip_custom_xqf_prop, R.id.textV_useProp)
                .build()
                .show();
    }


    //点击活动按钮查看活动

    @OnClick(R.id.btn_activities)
    public void onBtnActivitiesClick() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.frag_home_activities_dialog);
        dialog.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = (int) SimpleTooltipUtils.pxFromDp(400);
        lp.height = (int) SimpleTooltipUtils.pxFromDp(400);
        dialog.getWindow().setAttributes(lp);
        RecyclerView mRecyclerview = (RecyclerView) dialog.findViewById(R.id.dialog_recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterNormal adapterNormal = new AdapterNormal(getActivity());
        adapterNormal.addItems(mBeanBases);
        mRecyclerview.setAdapter(adapterNormal);
    }

    @Override
    public void getTasksIApplied(List<BeanTask> list) {
        mTasks.addAll(list);
    }

    @Override
    public void getTasksIPublished(List<BeanTask> taskList) {
        mTasks.addAll(taskList);
    }

    @Override
    public void getMyProps(List<BeanProp> propList) {
        mBeanProps = propList;
    }

    /*
 * 默认的点击按钮会弹出最后注册对话框，选择完成后可以上传个人信息
 */
    @OnClick(R.id.btn_test)
    public void onBtnTestClick() {


        int age = -1;
        String username = null;
        final int[] sex = {1};


        final PresneterHome presenterHome = mPresneterHome;
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);
        dialog.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = (int) SimpleTooltipUtils.pxFromDp(400);
        lp.height = (int) SimpleTooltipUtils.pxFromDp(500);
        dialog.getWindow().setAttributes(lp);

        final ImageButton imageButton = (ImageButton) dialog.findViewById(R.id.avatar);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FunctionConfig functionConfig = new FunctionConfig.Builder()
                        .setEnableCrop(true)
                        .setEnableRotate(true)
                        .setCropSquare(false)
                        .setEnablePreview(true)
                        .setEnableEdit(true)//编辑功能
                        .setEnableCrop(true)//裁剪功能
                        .setEnableCamera(true)//相机功能
                        .setForceCropEdit(true)
                        .setCropHeight(520)
                        .setCropWidth(720)
                        .setForceCrop(true)
                        .build();
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageButton.setImageBitmap(bitmap);
            }
        });
        final Button btnClose = (Button) dialog.findViewById(R.id.btn_close);
        final EditText editTextUserName = (EditText) dialog.findViewById(R.id.dialog_editT_username);
        final EditText editTextAge = (EditText) dialog.findViewById(R.id.dialog_editT_age);


        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.dialog_radiogroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.male) {
                    sex[0] = 1;
                } else {
                    sex[0] = 2;
                }
            }
        });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUserName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                //把对话框中获取的数据全部上传
                presenterHome.sendUserOtherInfoById(mUserId, picPath, username, sex[0], age);
                mBtnTest.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });


    }

    GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            Log.v("onHanlderSuccess", "reqeustCode: " + reqeustCode + "  resultList.size" + resultList.size());
            for (PhotoInfo info : resultList) {
                switch (reqeustCode) {
                    case REQUEST_CODE_GALLERY:
                        picPath = info.getPhotoPath();
                        bitmap = BitmapFactory.decodeFile(info.getPhotoPath());
                }
            }


        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {

        }

    };
}
