package com.idolmedia.yzy.ui.adapter;


import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CommunityCommentDetalisEntity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/7 13:58
 * 描述： 社区评论
 */

public class CommunityDetalisAdapter extends BaseQuickAdapter<CommunityCommentDetalisEntity.DatasBean,BaseViewHolder> {
    public CommunityDetalisAdapter() {
        super(R.layout.community_comment_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityCommentDetalisEntity.DatasBean item) {
        Glide.with(mContext).load(item.getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into((ImageView)helper.getView(R.id.iamge_portrait));
        helper.setText(R.id.txt_nick_name,item.getNick_name());
        helper.setText(R.id.txt_release_time,item.getCreate_time());
        helper.setText(R.id.txt_content,item.getReply_content());
        helper.getView(R.id.comment_bottom).setVisibility(View.GONE);
        helper.getView(R.id.txt_good_lick).setVisibility(View.GONE);
    }
}


