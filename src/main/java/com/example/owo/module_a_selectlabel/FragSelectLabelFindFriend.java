package com.example.owo.module_a_selectlabel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_a_selectlabel.bean.BeanShowTags;
import com.example.owo.module_a_selectlabel.bean.BeanTag;
import com.example.owo.module_a_selectlabel.presenter.PresenterSelectLabel;
import com.example.owo.module_a_selectlabel.presenter.PresenterSelectLabelImpl;
import com.example.owo.module_a_selectlabel.view.ViewSelectLabel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;
import zhouyou.flexbox.interfaces.OnFlexboxSubscribeListener;
import zhouyou.flexbox.widget.TagFlowLayout;

/**
 * @author XQF
 * @created 2017/5/24
 */
public class FragSelectLabelFindFriend extends FragBase implements ViewSelectLabel {


    public static FragSelectLabelFindFriend newInstance(String string) {
        FragSelectLabelFindFriend fragSelectLabelFindFriend = new FragSelectLabelFindFriend();
        Bundle args = new Bundle();
        args.putString("self", string);
        fragSelectLabelFindFriend.setArguments(args);
        return fragSelectLabelFindFriend;
    }

    private List<List<BeanTag>> mFindFriendTags;

    private List<BeanShowTags> mBeanShowTagses;
    @BindView(R.id.recyclerview_tag_container1)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.tagview1)
    protected TagContainerLayout mTagContainerLayout;

    @BindView(R.id.tags_own1)
    protected EditText mEditText;

    @BindView(R.id.btn_ok1)
    protected Button mButton;

    private MyAdapter mAdapter;

    private PresenterSelectLabel mPresenterSelectLabel;

    private List<List<String>> mSendData;

    private String mSelf;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_select_label_find_friend, container, false);
        ButterKnife.bind(this, view);
        mPresenterSelectLabel = new PresenterSelectLabelImpl(this);
        mPresenterSelectLabel.loadLabelFindFriend();
        mSelf = (String) getArguments().get("self");

        mBeanShowTagses = new ArrayList<>();
        mSendData = new ArrayList<>();
        mAdapter = new MyAdapter(getActivity());

        for (int i = 0; i < mFindFriendTags.size(); i++) {
            BeanShowTags beanTags = new BeanShowTags();
            //考虑到假如是女生那么男生部分的内容就是size()==0
            if (mFindFriendTags.size() != 0) {
                if (i == 0) {
                    beanTags.setTitles("通用");
                    beanTags.setTags(mFindFriendTags.get(i));
                } else if (i == 1) {
                    beanTags.setTitles("男生");
                    beanTags.setTags(mFindFriendTags.get(i));
                } else if (i == 2) {
                    beanTags.setTitles("女生");
                    beanTags.setTags(mFindFriendTags.get(i));
                } else if (i == 3) {
                    beanTags.setTitles("兴趣");
                    beanTags.setTags(mFindFriendTags.get(i));
                } else {
                    beanTags.setTitles("运动");
                    beanTags.setTags(mFindFriendTags.get(i));
                }
                mBeanShowTagses.add(beanTags);
            }

        }

        mAdapter.addItems(mBeanShowTagses);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tags = mEditText.getText().toString().trim();
                mTagContainerLayout.addTag(tags);
            }
        });
        return view;
    }

    @Override
    public void getLabelSelfFormNet(List<List<BeanTag>> resultList) {
        mFindFriendTags = resultList;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tags_title)
        protected TextView mTextViewTitle;


        @BindView(R.id.flow_layout)
        protected TagFlowLayout mTagFlowLayout;
        private StringTagAdapter adapter;
        private List<String> dataList;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            dataList = new ArrayList<>();
        }

        public void bind(BeanShowTags beanShowTags) {
            mTextViewTitle.setText(beanShowTags.getTitles());
            List<BeanTag> listTags = beanShowTags.getTags();
            for (int i = 0; i < listTags.size(); i++) {
                dataList.add(listTags.get(i).getTag());
            }
            adapter = new StringTagAdapter(getActivity(), dataList, null);
            adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
                @Override
                public void onSubscribe(List<String> selectedItem) {
                    //获取到选择的除了自定义以外的数据
                    mSendData.add(selectedItem);
                }
            });
            mTagFlowLayout.setAdapter(adapter);
        }

    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        private Context mContext;
        private List<BeanShowTags> mBeanShowTagses;

        public MyAdapter(Context context) {
            mContext = context;
            mBeanShowTagses = new ArrayList<>();
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_selectlabel_recycleritem, parent, false);
            return new MyHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            BeanShowTags beanShowTags = mBeanShowTagses.get(position);
            holder.bind(beanShowTags);
        }

        @Override
        public int getItemCount() {
            return mBeanShowTagses.size();
        }

        public void addItems(List<BeanShowTags> list) {
            mBeanShowTagses = list;
        }
    }


    public String getHobby() {
        StringBuffer sb0 = new StringBuffer(mSelf);
        StringBuffer sb = new StringBuffer("findFriend:[");
        for (int i = 0; i < mSendData.size(); i++) {
            sb.append(i + ":[");
            List<String> items = mSendData.get(i);
            for (int j = 0; j < items.size(); j++) {
                if (j != items.size() - 1) {
                    sb.append("{data:" + items.get(j) + "},");
                } else {
                    sb.append("{data:" + items.get(j) + "}");
                }
            }
            if (i != mSendData.size() - 1) {
                sb.append("],");
            } else {
                sb.append("]");
            }

        }
        sb.append("]");

        return sb0.append("," + sb).toString();
    }


}
