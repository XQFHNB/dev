package com.example.xqf;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltipUtils;

/**
 * @author XQF
 * @created 2017/5/24
 */
public class AtyTestBtn extends AppCompatActivity {


    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_test_btn);
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AtyTestBtn.this);
                dialog.setContentView(R.layout.dialog);
                dialog.show();

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = (int) SimpleTooltipUtils.pxFromDp(300);
                lp.height = (int) SimpleTooltipUtils.pxFromDp(300);
                dialog.getWindow().setAttributes(lp);

                final Button btnInDialog = (Button) dialog.findViewById(R.id.btn_in_dialog);
                btnInDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new SimpleTooltip.Builder(AtyTestBtn.this)
                                .anchorView(btnInDialog)
                                .text("hehh")
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(false)
                                .build()
                                .show();
                    }
                });
                final Button btnClose = (Button) dialog.findViewById(R.id.btn_close);
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mButton.setVisibility(View.GONE);
                    }
                });
            }
        });
        mButton.performClick();
    }
}
