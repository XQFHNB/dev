package com.example.owo.module_b_home.presenter;

import com.example.owo.module_b_home.bean.BeanProp;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_home.model.ModelHome;
import com.example.owo.module_b_home.model.ModelHomeImpl;
import com.example.owo.module_b_home.model.OnLoadListener;
import com.example.owo.module_b_home.view.ViewHome;
import com.example.owo.module_b_home.view.ViewHomeChangedNearby;
import com.example.owo.module_b_home.view.ViewHomeChangedRecommend;
import com.example.owo.utils.UtilLog;
import com.example.owo.utils.util_http.HttpHelper;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/20
 */
public class PresenterHomeImpl implements PresneterHome {

    private ModelHome mModelHome;
    private ViewHome mViewHome;

    private ViewHomeChangedNearby mViewHomeChangedNearby;
    private ViewHomeChangedRecommend mViewHomeChangedRecommend;

    public PresenterHomeImpl(ViewHome viewHome) {
        mModelHome = new ModelHomeImpl();
        mViewHome = viewHome;
    }

    public PresenterHomeImpl(ViewHomeChangedNearby viewHomeChangedNearby) {
        mModelHome = new ModelHomeImpl();
        mViewHomeChangedNearby = viewHomeChangedNearby;
    }

    public PresenterHomeImpl(ViewHomeChangedRecommend viewHomeChangedRecommend) {
        mModelHome = new ModelHomeImpl();
        mViewHomeChangedRecommend = viewHomeChangedRecommend;
    }

    @Override
    public void loadTasksAppliedByUserId(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        mModelHome.loadActivityIApplied(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                List<BeanTask> result = HttpHelper.getBeanTasksFromJson(string);
                mViewHome.getTasksIApplied(result);
            }
        });
    }

    @Override
    public void loadTasksPublishedByUserId(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        mModelHome.loadActivityIPublishedAndReceiveApplicatoin(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                List<BeanTask> result = HttpHelper.getBeanTasksFromJson(string);
                mViewHome.getTasksIPublished(result);
            }
        });
    }

    @Override
    public void loadUserPropNyUserId(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        mModelHome.loadMyProps(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                List<BeanProp> props = new ArrayList<>();
                try {
                    List<HashMap<String, Object>> resultObject = HttpHelper.AJItems(string, true);
                    for (int i = 0; i < resultObject.size(); i++) {
                        BeanProp prop = new BeanProp();
                        HashMap<String, Object> map = resultObject.get(i);
                        prop.setPropId((Integer) map.get("id"));
                        prop.setPropPrivilege((Integer) map.get("itemPrivilege"));
                        prop.setPropLevel((Integer) map.get("itemLevel"));
                        prop.setPropType((Integer) map.get("itemtype"));
                        prop.setPropPrice((Integer) map.get("itemprice"));

                        prop.setPropFunction((String) map.get("itemfunction"));
                        prop.setPropImage((String) map.get("itemimage"));
                        prop.setPropName((String) map.get("itemname"));
                        prop.setCount((Integer) map.get("count"));
                        props.add(prop);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mViewHome.getMyProps(props);
            }
        });
    }

    @Override
    public void loadTasksRecommendByUserId(int userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        mModelHome.loadActivityHomeChangedRecommend(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                List<BeanTask> result = HttpHelper.getBeanTasksFromJson(string);
                mViewHomeChangedRecommend.getAllTaskByRecommend(result);
            }
        });
    }

    @Override
    public void loadTasksNearbyByLatitudeAndLongtitude(String latitude, String longtitude) {
        HashMap<String, String> map = new HashMap<>();
        map.put("latitude", latitude);
        map.put("longtitude", longtitude);
        mModelHome.loadActivityHomeChangedNearby(map, new OnLoadListener() {
            @Override
            public void onSucess(String string) {
                List<BeanTask> result = HttpHelper.getBeanTasksFromJson(string);
                mViewHomeChangedNearby.getAllTaskOrderByDistance(result);
            }
        });
    }

    @Override
    public void sendUserHobbyById(int userId, String hobby) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId + "");
        map.put("hobby", hobby);
        mModelHome.sendUserHobby(map);
    }


    /**
     * 上传后面的对话框中的数据
     * 0 是userId  map ,
     * 1 是头像的文件map,
     * 2 是用户名map
     * 3 是用户性别map
     * 4 是用户年龄map
     */

    @Override
    public void sendUserOtherInfoById(int userId, String avatar, String useranme, int sex, String age) {


        UtilLog.d("test", "userID " + userId + " " + "avatar: " +
                avatar + " username:" + useranme + " sex:" + sex + " age:" + age);
        List<HashMap<String, String>> result = new ArrayList<>();

        HashMap<String, String> map0 = new HashMap<>();
        map0.put("userId", userId + "");

        result.add(map0);

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("avatar", avatar != null ? avatar : null);

        result.add(map1);

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("userId", userId + "");
        map2.put("userName", useranme.length() != 0 ? useranme : null);

        result.add(map2);

        HashMap<String, String> map3 = new HashMap<>();
        map3.put("userId", userId + "");
        map3.put("sex", sex + "");
        result.add(map3);

        HashMap<String, String> map4 = new HashMap<>();
        map4.put("userId", userId + "");
        map4.put("age", age.length() != 0 ? age : null);
        result.add(map4);


        mModelHome.sendUserUpdateInfo(result);
    }


}
