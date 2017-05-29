package com.example.owo.module_b_addactivity.model;

import com.example.owo.utils.util_http.HttpHelper;
import com.example.owo.utils.util_http.MyURL;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author XQF
 * @created 2017/5/26
 */
public class ModelAddAtyImpl implements ModelAddAty {
    @Override
    public void sendTask(final HashMap<String, String> map, final HashMap<String,String > file) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    HttpHelper.postData(MyURL.INSERT_TASK, map, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
