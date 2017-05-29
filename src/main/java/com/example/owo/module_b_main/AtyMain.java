package com.example.owo.module_b_main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owo.R;
import com.example.owo.module_b_addactivity.presenter.PresenterAddAty;
import com.example.owo.module_b_addactivity.presenter.PresenterAddAtyImpl;
import com.example.owo.module_b_addactivity.view.ViewAddAty;
import com.example.owo.module_b_explore.widgets.FragExplore;
import com.example.owo.module_b_home.widgets.FragHome;
import com.example.owo.module_b_message.widgets.FragMessage;
import com.example.owo.module_b_personal.widgets.FragPersonal;
import com.example.owo.utils.Common;
import com.example.owo.utils.UtilLog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltipUtils;
import zhouyou.flexbox.adapter.TagAdapter;
import zhouyou.flexbox.interfaces.OnFlexboxSubscribeListener;
import zhouyou.flexbox.widget.BaseTagView;
import zhouyou.flexbox.widget.TagFlowLayout;


public class AtyMain extends AppCompatActivity implements ViewAddAty {

    private static final int REQUEST_CODE_GALLERY = 111;
    public static final int FRAGMENT_N = 4;


    //底部按钮的正常背景资源ID数组
    public static final int[] tabsNormalBackIds = new int[]{
            R.drawable.home_gray,
            R.drawable.message_gray,
            R.drawable.discovery_gray,
            R.drawable.me_gray};
    //底部按钮的点击后的背景资源ID数组
    public static final int[] tabsActiveBackIds = new int[]{
            R.drawable.home_yellow,
            R.drawable.message_yellow,
            R.drawable.discovery_yellow,
            R.drawable.me_yellow};


    //fragment的TAG标识，，。表示之前没有这么用过
    private static final String FRAGMENT_TAG_HOME = "home";
    private static final String FRAGMENT_TAG_MESSAGE = "message";
    private static final String FRAGMENT_TAG_DISCOVER = "discover";
    private static final String FRAGMENT_TAG_PRESONAL = "personal";


    //放进一个数组中方便随机访问
    private static final String[] fragmentTags = new String[]{
            FRAGMENT_TAG_HOME,
            FRAGMENT_TAG_MESSAGE,
            FRAGMENT_TAG_DISCOVER,
            FRAGMENT_TAG_PRESONAL
    };


    @BindView(R.id.btn_chat)
    protected Button mBtnChat;

    @BindView(R.id.btn_contact)
    protected Button mBtnMessage;

    @BindView(R.id.btn_discover)
    protected Button mBtnExplore;

    @BindView(R.id.btn_personal_space)
    protected Button mBtnPersonalSapce;

    @BindView(R.id.btn_add_task)
    protected Button mBtnAddTask;


    @BindView(R.id.iv_recent_tips)
    protected ImageView mRecentTips;


    protected Toolbar mToolbar;


    protected Button[] tabs;
    private FragHome mFragHome;
    private FragMessage mFragMessage;
    private FragExplore mFragExplore;

    private FragPersonal mFragPersonal;

    private PresenterAddAty mPresenterAddAty;
    private String picPath;
    private Bitmap bitmap;

    private int mUserId;
    private boolean mIsOpenDialog = false;

    public static void start(Context context, Class<?> cls, boolean isOpenDialog) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("flag", isOpenDialog);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mIsOpenDialog = getIntent().getBooleanExtra("flag", false);
        mPresenterAddAty = new PresenterAddAtyImpl(this);
        tabs = new Button[]{mBtnChat, mBtnMessage, mBtnExplore, mBtnPersonalSapce};

//        mUserId = 173;

        setSupportActionBar(mToolbar);
        mBtnChat.performClick();

    }

    /**
     * 初始化隐藏所有的fragment
     */
    private void hideFragments(FragmentManager mFragmentManager, FragmentTransaction mFragmentTransaction) {
        for (int i = 0; i < fragmentTags.length; i++) {
            Fragment fragment = mFragmentManager.findFragmentByTag(fragmentTags[i]);
            if (fragment != null && fragment.isVisible()) {
                mFragmentTransaction.hide(fragment);
            }
        }

    }

    /**
     * 将底部按钮的背景都设为正常
     */
    private void setBottomBtnNormalBgs() {
        for (int i = 0; i < tabs.length; i++) {
            Button btn = tabs[i];
            setBgDrawable(btn, tabsNormalBackIds[i]);
        }
    }

    /**
     * 更新该btn的背景值为
     *
     * @param btn   btn控件
     * @param resId 资源,根据不同的资源代表不同的状态
     */
    private void setBgDrawable(Button btn, int resId) {
        //更换radio的图片，按照原有比例大小显示图片就使用，getDrawable()弃用
        btn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(resId), null, null);
    }


    /**
     * 底部按钮的按钮事件
     *
     * @param v
     */
    public void onTabSelect(View v) {

        //每次都获取一个manager和transaction，否则会炸
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        int id = v.getId();
        hideFragments(mFragmentManager, mFragmentTransaction);
        //所有按钮正常
        //为什么不把所有的fragment一股脑添加进去？然后根据用户点击来显示。那样做的代码量应该更少
        setBottomBtnNormalBgs();
        if (id == R.id.btn_chat) {

            if (mFragHome == null) {
                mFragHome = new FragHome();
                mFragmentTransaction.add(R.id.fragment_container, mFragHome, FRAGMENT_TAG_HOME);
            }
            mFragmentTransaction.show(mFragHome);
        } else if (id == R.id.btn_contact) {
            if (mFragMessage == null) {
                mFragMessage = new FragMessage();
                mFragmentTransaction.add(R.id.fragment_container, mFragMessage, FRAGMENT_TAG_MESSAGE);
            }
            mFragmentTransaction.show(mFragMessage);
        } else if (id == R.id.btn_discover) {
            if (mFragExplore == null) {
                mFragExplore = new FragExplore();
                mFragmentTransaction.add(R.id.fragment_container, mFragExplore, FRAGMENT_TAG_DISCOVER);
            }
            mFragmentTransaction.show(mFragExplore);
        } else if (id == R.id.btn_personal_space) {
            if (mFragPersonal == null) {
                mFragPersonal = new FragPersonal();
                mFragmentTransaction.add(R.id.fragment_container, mFragPersonal, FRAGMENT_TAG_PRESONAL);
            }
            mFragmentTransaction.show(mFragPersonal);
        }

        //通过遍历来确定点击的button在button实例数组中的索引pos
        int pos;
        for (pos = 0; pos < FRAGMENT_N; pos++) {
            if (tabs[pos] == v) {
                break;
            }
        }
        mFragmentTransaction.commit();
        //根据pos来更新按钮的背景状态
        setBgDrawable(tabs[pos], tabsActiveBackIds[pos]);
    }


    @OnClick(R.id.btn_add_task)
    public void onBtnAddTaskClick() {
        mUserId = Common.userSP.getInt("ID", 0);
        UtilLog.d("test", "从本地取出的userid " + mUserId);
        Toast.makeText(this, "hehehh", Toast.LENGTH_SHORT).show();
        final SimpleTooltip tooltip = new SimpleTooltip.Builder(this)
                .anchorView(mBtnAddTask)
                .text("任务")
                .gravity(Gravity.TOP)
                .dismissOnOutsideTouch(false)
                .dismissOnInsideTouch(false)
                .transparentOverlay(false)
                .modal(true)
                .animated(false)
                .showArrow(false)
                .contentView(R.layout.aty_main_tooltip_addtask, R.id.tv_text)
                .focusable(true)
                .build();
        ImageView online = tooltip.findViewById(R.id.imageV_aty_main_tooltip_online);
        ImageView offline = tooltip.findViewById(R.id.imageV_aty_main_tooltip_offline);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AtyMain.this);
                dialog.setContentView(R.layout.aty_main_tooltip_addtask_online);
                dialog.show();

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = (int) SimpleTooltipUtils.pxFromDp(400);
                lp.height = (int) SimpleTooltipUtils.pxFromDp(400);
                dialog.getWindow().setAttributes(lp);
                final TextView textViewDescription = (TextView) dialog.findViewById(R.id.activity_online_description);
                TextView textViewLastTime = (TextView) dialog.findViewById(R.id.activity_online_lasttime);
                final ImageButton imageButton = (ImageButton) dialog.findViewById(R.id.activity_online_image);
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

                final Button btnPublish = (Button) dialog.findViewById(R.id.activity_online_publish);
                btnPublish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("taskUserId", mUserId + "");
                        map.put("taskType", 1 + "");//线上任务
                        map.put("taskLabel", "线上");
                        map.put("taskContent", textViewDescription.getText().toString().trim());

                        dialog.dismiss();

                    }
                });
            }
        });

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(AtyMain.this);
                dialog.setContentView(R.layout.aty_main_tooltip_addtask_offline);
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = (int) SimpleTooltipUtils.pxFromDp(400);
                lp.height = (int) SimpleTooltipUtils.pxFromDp(400);
                dialog.getWindow().setAttributes(lp);

                final List<String> resultSelectedItem = new ArrayList<>();

                TagFlowLayout tagFlowLayout = (TagFlowLayout) dialog.findViewById(R.id.flowlayout);
                List<String> data = new ArrayList<>();
                data.add("吃的");
                data.add("玩的");
                StringTagAdapter adapter = new StringTagAdapter(AtyMain.this, data, null);
                tagFlowLayout.setAdapter(adapter);
                adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
                    @Override
                    public void onSubscribe(List<String> selectedItem) {
                        resultSelectedItem.addAll(selectedItem);
                    }
                });


                final TextView textViewDescription = (TextView) dialog.findViewById(R.id.activity_offline_description);
                final TextView textViewActivityTime = (TextView) dialog.findViewById(R.id.activity_offline_time);


//                TextView textViewActivityWhere = (TextView) dialog.findViewById(R.id.activity_offline_where);
                final TextView texViewActivitySum = (TextView) dialog.findViewById(R.id.activity_offline_num);
                final TextView textViewActivityMoney = (TextView) dialog.findViewById(R.id.activity_offline_money);
                final ImageButton imageButton = (ImageButton) dialog.findViewById(R.id.activity_offline_imagebtn_image);
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


                final Button btnClose = (Button) dialog.findViewById(R.id.acttivity_offline_publish);
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map1 = new HashMap<>();
                        map1.put("taskContent", textViewDescription.getText().toString().trim());
                        map1.put("taskDeadline", textViewActivityTime.getText().toString().trim());
                        map1.put("taskMoney", textViewActivityMoney.getText().toString().trim());
                        map1.put("taskMaxNum", texViewActivitySum.getText().toString().trim());
                        map1.put("taskType", 2 + "");//线下任务
                        map1.put("taskUserId", mUserId + "");
                        map1.put("taskLabel", resultSelectedItem.get(0));
                        // TODO: 2017/5/27 获取经度和纬度
                        HashMap<String, String> mapFile = new HashMap<>();
                        mapFile.put("taskImage", picPath);

                        //上传
                        mPresenterAddAty.sendTaskByParamMap(map1, mapFile);
                        dialog.dismiss();

                    }
                });
            }
        });
        tooltip.show();
    }


    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

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

    class StringTagAdapter extends TagAdapter<StringTagView, String> {

        StringTagAdapter(Context context, List<String> data) {
            this(context, data, null);
        }

        StringTagAdapter(Context context, List<String> data, List<String> selectItems) {
            super(context, data, selectItems);
        }

        /**
         * 检查item和所选item是否一样
         *
         * @param view
         * @param item
         * @return
         */
        @Override
        protected boolean checkIsItemSame(StringTagView view, String item) {
            return TextUtils.equals(view.getItem(), item);
        }

        /**
         * 检查item是否是空指针
         *
         * @return
         */
        @Override
        protected boolean checkIsItemNull(String item) {
            return TextUtils.isEmpty(item);
        }

        /**
         * 添加标签
         *
         * @param item
         * @return
         */
        @Override
        protected StringTagView addTag(String item) {
            StringTagView tagView = new StringTagView(getContext());
            tagView.setPadding(20, 20, 20, 20);

            TextView textView = tagView.getTextView();
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            textView.setGravity(Gravity.CENTER);

            tagView.setItemDefaultDrawable(itemDefaultDrawable);
            tagView.setItemSelectDrawable(itemSelectDrawable);
            tagView.setItemDefaultTextColor(itemDefaultTextColor);
            tagView.setItemSelectTextColor(itemSelectTextColor);
            tagView.setItem(item);
            return tagView;
        }
    }

    class StringTagView extends BaseTagView<String> {

        public StringTagView(Context context) {
            this(context, null);
        }

        public StringTagView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs, 0);
        }

        public StringTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public void setItem(String item) {
            super.setItem(item);
            textView.setText(item);
        }
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        int size = mTagContainerLayout.getTagMaxLength();
//        List<String> customsTags = new ArrayList<>();
//        for (int i = 0; i < size; i++) {
//            customsTags.add(mTagContainerLayout.getTagText(i));
//        }
//    }


}
