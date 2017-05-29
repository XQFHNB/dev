package com.example.owo.module_b_explore.presenter;

import com.example.owo.module_a_selectlabel.presenter.PresenterSelectLabelImpl;
import com.example.owo.module_b_explore.bean.BeanRecyclerViewItem;
import com.example.owo.module_b_explore.bean.BeanViewPagerItem;
import com.example.owo.module_b_explore.model.ModelExplore;
import com.example.owo.module_b_explore.model.ModelExploreImpl;
import com.example.owo.module_b_explore.model.OnLoadListener;
import com.example.owo.module_b_explore.view.ViewExploreNearby;
import com.example.owo.module_b_explore.view.ViewExploreRecommend;
import com.example.owo.utils.util_http.HttpHelper;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/26
 */
public class PresenterExploreImpl implements PresenterExplore {


    private ModelExplore mModelExplore;
    private ViewExploreNearby mViewExplore;

    private ViewExploreRecommend mViewExploreRecommend;

    public PresenterExploreImpl(ViewExploreNearby viewExplore) {
        mViewExplore = viewExplore;
        mModelExplore = new ModelExploreImpl();
    }

    public PresenterExploreImpl(ViewExploreRecommend viewExploreRecommend) {
        mModelExplore = new ModelExploreImpl();
        mViewExploreRecommend = viewExploreRecommend;
    }

    @Override
    public void getUserOrderByMatchIndex(String findFriendhobby) {

        HashMap<String, String> map = new HashMap<>();
        map.put("findFriend", findFriendhobby);
        mModelExplore.loadRecommendUser(map, new OnLoadListener() {

            @Override
            public void onSucess(String str) {

                try {
                    List<BeanViewPagerItem> result = HttpHelper.AJResultGetUserByMatchIndex(str);
                    mViewExploreRecommend.getResultUserByMatchDegree(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getUserOrderByDistance(String latitude, String longtitude) {
        HashMap<String, String> map = new HashMap<>();
        map.put("latitude", latitude);
        map.put("longtitude", longtitude);
        mModelExplore.loadUserByDistance(map, new OnLoadListener() {
            @Override
            public void onSucess(String str) {

                try {
                    List<BeanRecyclerViewItem> result = HttpHelper.AJResultGetUserByDis(str);
                    mViewExplore.getResultUserByDis(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void sendAddFriendByIdAndFriendId(int userId, int friendId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userID", userId + "");
        map.put("friendID", friendId + "");
        mModelExplore.sendAddFriend(map);
    }
}
