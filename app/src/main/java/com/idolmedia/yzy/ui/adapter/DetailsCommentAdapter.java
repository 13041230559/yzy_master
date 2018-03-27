package com.idolmedia.yzy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mumu.common.base.BaseRecycleViewAdapter;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/5 16:30
 * 描述：商品评论详情
 */

public class DetailsCommentAdapter  extends BaseRecycleViewAdapter {

    private Context context;
    public DetailsCommentAdapter(Context context){
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
