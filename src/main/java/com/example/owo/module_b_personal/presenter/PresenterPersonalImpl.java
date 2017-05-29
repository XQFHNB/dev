package com.example.owo.module_b_personal.presenter;

import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_personal.bean.BeanMoment;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.bean.BeanUserCommentLabel;
import com.example.owo.module_b_personal.model.ModelPersonal;
import com.example.owo.module_b_personal.model.ModelPersonalImpl;
import com.example.owo.module_b_personal.model.OnLoadListener;
import com.example.owo.module_b_personal.view.ViewPersonal;
import com.example.owo.module_b_personal.view.ViewPersonalDownViewpagerActivity;
import com.example.owo.module_b_personal.view.ViewPersonalDownViewpagerMoment;
import com.example.owo.module_b_personal.view.ViewPersonalViewpagerLeft;
import com.example.owo.module_b_personal.view.ViewPersonalViewpagerLeftHolder;
import com.example.owo.module_b_personal.view.ViewPersonalViewpagerRight;
import com.example.owo.utils.Constants;
import com.example.owo.utils.util_http.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/21
 */
public class PresenterPersonalImpl implements PresenterPersonal {


    private ModelPersonal mModelPersonal;
    private ViewPersonal mViewPersonal;
    private ViewPersonalDownViewpagerMoment mViewPersonalDownViewpagerMoment;
    private ViewPersonalDownViewpagerActivity mViewPersonalDownViewpagerActivity;
    private ViewPersonalViewpagerLeft mViewPersonalViewpagerLeft;
    private ViewPersonalViewpagerLeftHolder mViewPersonalViewpagerLeftHolder;
    private ViewPersonalViewpagerRight mViewPersonalViewpagerRight;

    public PresenterPersonalImpl(ViewPersonal viewPersonal) {
        mModelPersonal = new ModelPersonalImpl();
        mViewPersonal = viewPersonal;
    }

    public PresenterPersonalImpl(ViewPersonalDownViewpagerMoment viewPersonalDownViewpagerMoment) {
        mModelPersonal = new ModelPersonalImpl();
        mViewPersonalDownViewpagerMoment = viewPersonalDownViewpagerMoment;
    }

    public PresenterPersonalImpl(ViewPersonalDownViewpagerActivity viewPersonalDownViewpagerActivity) {
        mModelPersonal = new ModelPersonalImpl();
        mViewPersonalDownViewpagerActivity = viewPersonalDownViewpagerActivity;
    }

    public PresenterPersonalImpl(ViewPersonalViewpagerLeft viewPersonalViewpagerLeft) {
        mModelPersonal = new ModelPersonalImpl();
        mViewPersonalViewpagerLeft = viewPersonalViewpagerLeft;
    }

    public PresenterPersonalImpl(ViewPersonalViewpagerLeftHolder viewPersonalViewpagerLeftHolder) {
        mModelPersonal = new ModelPersonalImpl();
        mViewPersonalViewpagerLeftHolder = viewPersonalViewpagerLeftHolder;
    }

    public PresenterPersonalImpl(ViewPersonalViewpagerRight viewPersonalViewpagerRight) {
        mModelPersonal = new ModelPersonalImpl();
        mViewPersonalViewpagerRight = viewPersonalViewpagerRight;
    }


    @Override
    public void loadUserPersaonlInfoById(int userId) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("id", userId + " ");
        mModelPersonal.loadUserPersonalInfo(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                try {
                    HashMap<String, Object> mapResult = HttpHelper.AnalysisUserInfo(string);
                    BeanUser beanUser = Constants.getBeanUserFromMap(mapResult);
                    mViewPersonal.getPersonalInfo(beanUser);
                    mViewPersonalViewpagerLeftHolder.getPersonalInfo(beanUser);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void loadUserFriendMomentById(int userId) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("id", userId + " ");
        mModelPersonal.loadPersoalFriendMoment(map, new OnLoadListener() {
            List<BeanMoment> resultListMoment = new ArrayList<>();

            @Override
            public void onSucess(String string) {
                try {
                    ArrayList<HashMap<String, Object>> resultList = HttpHelper.AJgetMommentByUID(string);
                    for (int i = 0; i < resultList.size(); i++) {
                        BeanMoment beanMoment = new BeanMoment();
                        HashMap<String, Object> map = resultList.get(i);
                        beanMoment.setMomentUserId((Integer) map.get("userID"));
                        beanMoment.setMomentUserAvatar((String) map.get("avatar"));
                        beanMoment.setMomentUserName((String) map.get("username"));
                        beanMoment.setMomentCommentId((Integer) map.get("commentID"));
                        beanMoment.setMomentId((Integer) map.get("id"));

                        beanMoment.setMomentContent((String) map.get("momentContent"));
                        beanMoment.setMomentImage((String) map.get("momentImage"));
                        beanMoment.setMomentDate((String) map.get("momentDate"));
                        beanMoment.setMomentType((Integer) map.get("momentType"));
                        resultListMoment.add(beanMoment);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mViewPersonalDownViewpagerMoment.getResultMomentFromSever(resultListMoment);
            }
        });
    }

    @Override
    public void loadTasksAppliedByUserId(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        mModelPersonal.loadActivityIApplied(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                List<BeanTask> result = HttpHelper.getBeanTasksFromJson(string);
                mViewPersonalDownViewpagerActivity.getTasksIApplied(result);
            }
        });
    }

    @Override
    public void loadTasksPublishedByUserId(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        mModelPersonal.loadActivityIPublishedAndReceiveApplicatoin(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                List<BeanTask> result = HttpHelper.getBeanTasksFromJson(string);
                mViewPersonalDownViewpagerActivity.getTasksIPublished(result);
            }
        });
    }

    @Override
    public void loadSumUserFollowedByUserId(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        mModelPersonal.loadSumIFollowed(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                try {
                    int result = HttpHelper.AJSumFollow(string);
                    mViewPersonalViewpagerLeft.getSumIFoloowed(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void loadSumUserFollowMeByUserId(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        mModelPersonal.loadSumFollowMe(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                try {
                    int result = HttpHelper.AJSumFollow(string);
                    mViewPersonalViewpagerLeft.getSumFollowMe(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadResultCommentByUserId(int momentId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("momentID", momentId + "");
        mModelPersonal.loadResultComment(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {

//                mViewPersonalViewpagerLeftHolder.getResultComment();
            }
        });
    }

    @Override
    public void loadResultLikesByCommentId(int commentId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("commentID", commentId + "");
        mModelPersonal.loadResultLike(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
//                mViewPersonalViewpagerLeftHolder.getResultLike();
            }
        });
    }

    @Override
    public void sendUpdateMomentLikeByMomentIdAndLikeNum(int momentId, int likeNum) {
        HashMap<String, String> map = new HashMap<>();
        map.put("momentID", momentId + "");
        map.put("likeNum", likeNum + "");
        mModelPersonal.sendUpdateLikeSum(map);
    }

    @Override
    public void loadUserCommentBy(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userID", userId + "");
        mModelPersonal.loadUserComment(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    JSONObject jsonObjectData = (JSONObject) jsonObject.get("data");
                    int score = (int) jsonObjectData.get("userScore");
                    JSONArray jsonArray = jsonObjectData.getJSONArray("label");
                    List<BeanUserCommentLabel> list = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                        BeanUserCommentLabel beanUserCommentLabel = new BeanUserCommentLabel();
                        beanUserCommentLabel.setLabel((String) jsonObject1.get("userLabel"));
                        beanUserCommentLabel.setLabelNum((String) jsonObject1.get("labelNum"));
                        list.add(beanUserCommentLabel);
                    }
                    mViewPersonalViewpagerRight.getUserComment(score, list);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
