package com.example.xqf;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/27
 */
public class AtyTestGrid extends AppCompatActivity {


    private Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_test_grid);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new Adapter(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("我就是 " + i);
        }
        mAdapter.addItems(list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mAdapter);
    }


    class Holder extends RecyclerView.ViewHolder {


        TextView mTextView;
        private int counter = 0;

        public Holder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textview);

        }

        void bind(String string) {
            mTextView.setText(string);
            final String str = string;
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AtyTestGrid.this, str, Toast.LENGTH_SHORT).show();
                    if (counter % 2 == 0) {
                        mTextView.setBackgroundColor(getResources().getColor(R.color.color_red));
                    } else {
                        mTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    }
                    counter++;

                }
            });
        }
    }

    class Adapter extends RecyclerView.Adapter<Holder> {


        List<String> list;
        Context mContext;

        public Adapter(Context context) {
            mContext = context;
            list = new ArrayList<>();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(mContext).inflate(R.layout.test_item, parent, false);
            return new Holder(itemView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            String str = list.get(position);
            holder.bind(str);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        void addItems(List<String> list) {
            this.list = list;
        }
    }
}
