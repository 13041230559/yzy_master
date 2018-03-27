package com.idolmedia.yzy.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.PhotoInfo;
import com.idolmedia.yzy.ui.viewholder.CommentViewHolder;
import com.idolmedia.yzy.ui.viewholder.ImageViewHolder;
import com.idolmedia.yzy.utlis.MultiImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.mumu.common.base.BaseRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/2 10:48
 * 描述：  社区评论
 */

public class CommunityAdapter extends BaseRecycleViewAdapter {
    boolean isShowGoodLick;
    private Context context;
    public CommunityAdapter(Context context, boolean isShowGoodLick){
        this.context = context;
        this.isShowGoodLick = isShowGoodLick;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder ;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_comment_item, parent, false);
        viewHolder= new ImageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final CommentViewHolder holder = (CommentViewHolder) viewHolder;
        CommentEntity.DatasBean datasBean= (CommentEntity.DatasBean) datas.get(position);
        String name = datasBean.getNick_name();
        String headImg = datasBean.getHead_img();
        String createTime = datasBean.getCreate_time();
        holder.nameTv.setText(name);
        holder.timeTv.setText(createTime);
        Glide.with(context).load(headImg).apply(new RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.e6).error(R.mipmap.default_bg)).into(holder.headIv);
        if(isShowGoodLick){
            ((CommentViewHolder) viewHolder).relativeComment.setVisibility(View.GONE);
            ((CommentViewHolder) viewHolder).goodLickBtn.setVisibility(View.VISIBLE);
        }else {
            ((CommentViewHolder) viewHolder).relativeComment.setVisibility(View.VISIBLE);
            ((CommentViewHolder) viewHolder).goodLickBtn.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(datasBean.getContent())){
            holder.content.setText(datasBean.getContent());
            holder.content.setVisibility(View.VISIBLE);
        }else {
            holder.content.setVisibility(View.GONE);
        }
        holder.goodBtn.setText(String.valueOf(datasBean.getPrise_count()));
        holder.goodLickBtn.setText(String.valueOf(datasBean.getPrise_count()));
        holder.browseBtn.setText(String.valueOf(datasBean.getLook_count()));
        holder.commentBtn.setText(String.valueOf(datasBean.getComment_count()));
        if(datasBean.getIsPrised()){
            Log.e("IsPrised",String.valueOf(datasBean.getIsPrised()));
            Drawable drawable = context.getResources().getDrawable(R.mipmap.icon_zan_red_yzy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if(isShowGoodLick){
                holder.goodLickBtn.setCompoundDrawables(drawable,null,null,null);
            }else {
                holder.goodBtn.setCompoundDrawables(drawable,null,null,null);
            }
        }else {
            if(isShowGoodLick){
                Drawable drawable = context.getResources().getDrawable(R.mipmap.icon_zan_yzy);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                holder.goodLickBtn.setCompoundDrawables(drawable,null,null,null);
            }else {
                Drawable drawable = context.getResources().getDrawable(R.mipmap.icon_zan_grey_yzy);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                holder.goodBtn.setCompoundDrawables(drawable,null,null,null);
            }
        }
        holder.commentBtn.setOnClickListener(view -> {
            if(mItemClickListener!=null){
                mItemClickListener.onItemClick(position);
            }
        });
        holder.itemView.setOnClickListener(view -> {
            if(mItemClickListener!=null){
                mItemClickListener.onItemClick(position);
            }
        });
        holder.goodLickBtn.setOnClickListener(view -> {
            if(mItemClickListener!=null){
                mItemClickListener.onItemGoodClick(position,datasBean.getPrise_count(),datasBean.getComment_id());
            }
        });
        holder.goodBtn.setOnClickListener(view -> {
            if(mItemClickListener!=null){
                mItemClickListener.onItemGoodClick(position,datasBean.getPrise_count(),datasBean.getComment_id());
            }
        });
        List<PhotoInfo> photos = new ArrayList<>();
        List<LocalMedia> selectList = new ArrayList<>();
        for(int i=0;i<datasBean.getCommentPics().size();i++){
            PhotoInfo photoInfo=new PhotoInfo();
            photoInfo.url=datasBean.getCommentPics().get(i);
            photos.add(photoInfo);
            LocalMedia    localMedia=new LocalMedia();
            localMedia.setPath(datasBean.getCommentPics().get(i));
            selectList.add(localMedia);
        }
        if (photos != null && photos.size() > 0) {
            ((ImageViewHolder)holder).multiImageView.setVisibility(View.VISIBLE);
            ((ImageViewHolder)holder).multiImageView.setList(photos);
        ((ImageViewHolder)holder).multiImageView.setOnItemClickListener((view, position1) -> PictureSelector.create((Activity) context).externalPicturePreview(position1, "/idolmedia",  selectList));
        } else {
            ((ImageViewHolder)holder).multiImageView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemGoodClick(int position,int lickcount,int commentid);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
