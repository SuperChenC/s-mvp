package com.superc.lib.widget.recyclerview.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.superc.lib.util.LogUtils;
import com.superc.lib.widget.recyclerview.ItemViewDelegate;
import com.superc.lib.widget.recyclerview.ItemViewDelegateManager;
import com.superc.lib.widget.recyclerview.callback.OnItemClickListener;
import com.superc.lib.widget.recyclerview.RViewHolder;

import java.util.List;

/**
 * Created by superchen on 2017/6/20.
 */
public abstract class RAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private List<T> mDatas;
    private Context mContext;
    private ItemViewDelegateManager<T> mItemViewDelegateManager;
    private OnItemClickListener<T> mItemClickListener;

    public RAdapter(Context context, List<T> list) {
        this.mContext = context;
        mDatas = list;
        mItemViewDelegateManager = new ItemViewDelegateManager<T>();
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.d("RAdapter onCreateViewHolder viewType = " + viewType);
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.setLayoutResId();
        RViewHolder holder = RViewHolder.createViewHolder(mContext, parent, layoutId);
        onViewHolderCreated(holder, holder.getConvertView());
        setListener(parent, holder, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        if (checkPositionAvailable(position)) {
            mItemViewDelegateManager.onBindViewHolder(holder, mDatas.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        LogUtils.d("RAdapter getItemCount = " + (mDatas == null ? 0 : mDatas.size()));
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        LogUtils.d("RAdapter getItemViewType position = " + position);
        if (checkPositionAvailable(position)) {
            if (!useItemViewDelegateManager()) {
                return super.getItemViewType(position);
            }
            int type = mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
            return type;
        } else {
            return 0;
        }
    }

    public void onViewHolderCreated(RViewHolder holder, View itemView) {

    }

    protected void setListener(final ViewGroup parent, final RViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mItemClickListener.onItemClick(v, viewHolder, mDatas.get(position), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mItemClickListener.onItemLongClick(v, viewHolder, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    protected boolean checkPositionAvailable(int position) {
        if (position >= 0 && position <= ((!mDatas.isEmpty()) ? mDatas.size() - 1 : 0)) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public RAdapter<T> addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public RAdapter<T> addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
