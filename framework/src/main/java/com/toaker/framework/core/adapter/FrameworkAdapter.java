package com.toaker.framework.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class FrameworkAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected final int mItemLayoutId;
    protected Object [] args;
    protected int position = 0;

    public FrameworkAdapter(Context context, List<T> mData, int itemLayoutId, Object... args) {
        this.mContext = context;
        this.mData = mData;
        this.args = args;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 :mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public int getCurrentPosition(){
        return this.position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.position = position;
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        T item = getItem(position);
        if(item != null){
            convert(viewHolder, item);
        }
        return viewHolder.getConvertView();

    }

    public abstract void convert(ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    @Override
    public void notifyDataSetChanged() {
        this.position = 0;
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        this.position = 0;
        super.notifyDataSetInvalidated();
    }

    public Activity getActivity(){
        if(mContext instanceof Activity){
            return (Activity) mContext;
        }
        return null;
    }

    public void startActivity(Intent intent){
        mContext.startActivity(intent);
    }

    public void overridePendingTransition(int enterAnim, int exitAnim){
        if(getActivity() != null){
            getActivity().overridePendingTransition(enterAnim,exitAnim);
        }
    }
}