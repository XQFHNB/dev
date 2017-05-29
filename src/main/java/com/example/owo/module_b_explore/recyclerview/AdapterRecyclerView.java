package com.example.owo.module_b_explore.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.module_b_explore.bean.BeanRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/11
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<Holder> {

    List<BeanRecyclerViewItem> list;
    Context mContext;

    public AdapterRecyclerView(Context context) {
        mContext = context;
        list = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_explore_nearby_item, parent, false);
        return new Holder(mContext, view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        BeanRecyclerViewItem item = list.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public void addItems(List<BeanRecyclerViewItem> list) {
        this.list = list;
    }
}
