package com.example.owo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author XQF
 * @created 2017/5/4
 */
public class FragBase extends Fragment {
    protected AppCompatActivity mAty = (AppCompatActivity) getActivity();

    /**
     * 显示进度条
     *
     * @return
     */
    public ProgressDialog showSpinnerDialog() {
        //activity = modifyDialogContext(activity);
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.setMessage("正在努力加载。。。");
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    public static void start(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void toast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
