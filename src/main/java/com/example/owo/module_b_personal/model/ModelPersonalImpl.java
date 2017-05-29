package com.example.owo.module_b_personal.model;

import com.example.owo.utils.util_http.HttpHelper;
import com.example.owo.utils.util_http.MyURL;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author XQF
 * @created 2017/5/21
 */
public class ModelPersonalImpl implements ModelPersonal {


    @Override
    public void loadUserPersonalInfo(final HashMap<String, String> map, final OnLoadListener listener) {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String jsonData = null;
                try {
                    jsonData = HttpHelper.postData(MyURL.GET_USER_BY_ID, map, null);
                    listener.onSucess(jsonData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);

    }

    @Override
    public void loadPersoalFriendMoment(final HashMap<String, String> map, final OnLoadListener listener) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonData = HttpHelper.postData(MyURL.GET_MOMENT_BY_UID, map, null);
                    listener.onSucess(jsonData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public void loadActivityIApplied(final HashMap<String, String> map, final OnLoadListener listener) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonResultData = HttpHelper.postData(MyURL.GET_TASK_I_APPLIED, map, null);
                    listener.onSucess(jsonResultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public void loadActivityIPublishedAndReceiveApplicatoin(final HashMap<String, String> map, final OnLoadListener listener) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonResultData = HttpHelper.postData(MyURL.GET_TASK_I_PUBLISHED, map, null);
                    listener.onSucess(jsonResultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }


    @Override
    public void loadPersonalDetailMoment(HashMap<String, String> map, OnLoadListener listener) {

    }

    @Override
    public void loadPersonalDetailActivity(HashMap<String, String> map, OnLoadListener listener) {

    }

    @Override
    public void loadSumIFollowed(final HashMap<String, String> map, final OnLoadListener listener) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonResultData = HttpHelper.postData(MyURL.GET_SUMIFOLLOWED__BY_FID, map, null);
                    listener.onSucess(jsonResultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public void loadSumFollowMe(final HashMap<String, String> map, final OnLoadListener listener) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonResultData = HttpHelper.postData(MyURL.GET_SUMFOLLOW_ME__BY_FID, map, null);
                    listener.onSucess(jsonResultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public void loadResultComment(final HashMap<String, String> map, final OnLoadListener listener) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonResultData = HttpHelper.postData(MyURL.GET_MOMENT_COMMENTS_BY_COMMENT_ID, map, null);
                    listener.onSucess(jsonResultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public void loadResultLike(final HashMap<String, String> map, final OnLoadListener listener) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonResultData = HttpHelper.postData(MyURL.GET_MOMENT_LIKES_BY_COMMENT_ID, map, null);
                    listener.onSucess(jsonResultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public void sendUpdateLikeSum(final HashMap<String, String> map) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonResultData = HttpHelper.postData(MyURL.UPDATE_MOMENT_LIKE_BY_ID, map, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public void loadUserComment(final HashMap<String, String> map, final OnLoadListener listener) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    String jsonResultData = HttpHelper.postData(MyURL.UPDATE_MOMENT_LIKE_BY_ID, map, null);
                    listener.onSucess(jsonResultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 100);
    }


}
