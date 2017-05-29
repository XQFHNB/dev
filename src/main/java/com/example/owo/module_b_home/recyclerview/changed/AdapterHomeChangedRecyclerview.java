package com.example.owo.module_b_home.recyclerview.changed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.utils.Constants;
import com.example.owo.utils.UtilLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/15
 */
public class AdapterHomeChangedRecyclerview extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<BeanItemFragHomeChanged> mList = new ArrayList<>();

    Context mContext;

    public AdapterHomeChangedRecyclerview(Context context) {
        mContext = context;
    }

    public AdapterHomeChangedRecyclerview(Context context, List<BeanItemFragHomeChanged> list) {
        mContext = context;
        mList = list;

        UtilLog.d("test", "home " + Arrays.toString(list.toArray()));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        if (viewType == Constants.FRAG_HOME_ITEM_VIEWTYPE_BLUE) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_home_changed_item_blue, parent, false);
            return new HolderBlue(itemView);
        } else if (viewType == Constants.FRAG_HOME_ITEM_VIEWTYPE_RED) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_home_changed_item_red, parent, false);
            return new HolderRed(itemView);
        } else if (viewType == Constants.FRAG_HOME_ITEM_VIEWTYPE_YELLOW) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.frag_home_changed_item_yellow, parent, false);
            return new HolderYellow(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BeanItemFragHomeChanged item = mList.get(position);
        if (holder instanceof HolderBlue) {
            ((HolderBlue) holder).bind((BeanBlue) item);
        } else if (holder instanceof HolderRed) {
            ((HolderRed) holder).bind((BeanRed) item);
        } else if (holder instanceof HolderYellow) {
            ((HolderYellow) holder).bind((BeanYellow) item);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {

        BeanItemFragHomeChanged item = mList.get(position);
        return item.getViewType();
    }

    public void addItems(List<BeanItemFragHomeChanged> list) {
        if (mList.size() == 0) {
            mList = list;
        }
    }
}
