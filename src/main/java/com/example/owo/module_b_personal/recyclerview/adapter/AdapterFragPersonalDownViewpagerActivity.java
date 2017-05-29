package com.example.owo.module_b_personal.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.BeanBase;
import com.example.owo.module_b_home.bean.BeanTask;
import com.example.owo.module_b_personal.bean.BeanActivityLabel;
import com.example.owo.module_b_personal.recyclerview.holder.HolderActivity;
import com.example.owo.module_b_personal.recyclerview.holder.HolderActivityLabel;
import com.example.owo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/21
 */
public class AdapterFragPersonalDownViewpagerActivity extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<BeanBase> mlist = new ArrayList<>();
    public Context mContext;

    public AdapterFragPersonalDownViewpagerActivity(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == Constants.BEAN_TYPE_TASK) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_personal_down_viewpager_activity_item_activity, parent, false);
            return new HolderActivity(mContext, itemView);
        } else if (viewType == Constants.BEAN_TYPE_LABEL) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_personal_down_viewpager_activity_item_label, parent, false);
            return new HolderActivityLabel(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BeanBase beanBase = mlist.get(position);
        if (holder instanceof HolderActivity) {
            ((HolderActivity) holder).bind((BeanTask) beanBase);
        } else if (holder instanceof HolderActivityLabel) {
            ((HolderActivityLabel) holder).bind((BeanActivityLabel) beanBase);
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        BeanBase beanBase = mlist.get(position);
        return beanBase.getBeanType();
    }

    public void addItems(List<BeanBase> list) {
        mlist = list;
    }
}
