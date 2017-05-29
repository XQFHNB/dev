package com.example.xqf;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * @author XQF
 * @created 2017/5/27
 */
public class AtyTestTagView extends AppCompatActivity {

    private TagContainerLayout mTagContainerLayout;

    private Button mBtn;
    private Button mBtnRemove;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_test_tag_view);
        mBtn = (Button) findViewById(R.id.addbtn);
        mBtnRemove = (Button) findViewById(R.id.remove);
        mTagContainerLayout = (TagContainerLayout) findViewById(R.id.tagview);
        List<String> list1 = new ArrayList<>();
        list1.add("Java");
        list1.add("C++");
        list1.add("Python");
        list1.add("Swift");
        list1.add("你好，这是一个TAG。你好，这是一个TAG。你好，这是一个TAG。你好，这是一个TAG。");
        list1.add("PHP");
        list1.add("JavaScript");
        list1.add("Html");
        list1.add("Welcome to use AndroidTagView!");
        mTagContainerLayout.setTags(list1);

//        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
//            @Override
//            public void onTagClick(int position, String text) {
//                Toast.makeText(AtyTestTagView.this, "click-position:" + position + ", text:" + text,
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onTagLongClick(int position, String text) {
//
//            }
//        });
        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                Toast.makeText(AtyTestTagView.this, "click-position:" + position + ", text:" + text,
                        Toast.LENGTH_SHORT).show();
                mTagContainerLayout.removeTag(position);
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                AlertDialog dialog = new AlertDialog.Builder(AtyTestTagView.this)
                        .setTitle("long click")
                        .setMessage("You will delete this tag!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (position < mTagContainerLayout.getChildCount()) {
                                    mTagContainerLayout.removeTag(position);
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }

        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTagContainerLayout.addTag("hehehheh");
            }
        });
        mBtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTagContainerLayout.removeTag(5);

            }
        });


    }

}
