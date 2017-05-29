package com.example.owo.module_b_home.recyclerview.normal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.BeanBase;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_home.recyclerview.normal.bean.BeanActivityLabel;
import com.example.owo.module_b_home.recyclerview.normal.holder.HolderActivity;
import com.example.owo.module_b_home.recyclerview.normal.holder.HolderActivityLabel;
import com.example.owo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/25
 */
public class AdapterNormal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<BeanBase> mList;
    Context mContext;

    public AdapterNormal(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        if (viewType == Constants.BEAN_TYPE_TASK) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_home_normal_recycler_activity_item, parent, false);
            return new HolderActivity(mContext, itemView);
        } else if (viewType == Constants.BEAN_TYPE_LABEL) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_home_normal_recycler_label_item, parent, false);
            return new HolderActivityLabel(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BeanBase beanBase = mList.get(position);
        if (holder instanceof HolderActivity) {
            ((HolderActivity) holder).bind((BeanTask) beanBase);
        } else if (holder instanceof HolderActivityLabel) {
            ((HolderActivityLabel) holder).bind((BeanActivityLabel) beanBase);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getBeanType();
    }

    public void addItems(List<BeanBase> list) {
        mList.addAll(list);
    }

}
