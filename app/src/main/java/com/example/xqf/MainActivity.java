package com.example.xqf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import me.samthompson.bubbleactions.Callback;
import me.samthompson.bubbleactions.BubbleActions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.my_view).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                BubbleActions.on(v)
                        .addAction("Star", R.drawable.add, new Callback() {
                            @Override
                            public void doAction() {
                                Toast.makeText(v.getContext(), "Star pressed!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addAction("Share", R.drawable.add, new Callback() {
                            @Override
                            public void doAction() {
                                Toast.makeText(v.getContext(), "Share pressed!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addAction("Hide", R.drawable.add, new Callback() {
                            @Override
                            public void doAction() {
                                Toast.makeText(v.getContext(), "Hide pressed!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                return true;
            }
        });

    }
}
