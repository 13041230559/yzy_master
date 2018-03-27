package com.idolmedia.yzy.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/3/26.
 */

public class CommunitysAdapter extends BaseQuickAdapter<CommentEntity.DatasBean,BaseViewHolder> {
    boolean isShowGoodLick;
    Context context;
    public CommunitysAdapter(Context context,boolean isShowGoodLick) {
        super(R.layout.comment_item);
        this.isShowGoodLick = isShowGoodLick;
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder holder, CommentEntity.DatasBean item) {
        Glide.with(mContext).load(item.getHead_img()).apply(new RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.e6).error(R.mipmap.default_bg)).into((ImageView) holder.getView(R.id.iamge_portrait));
       holder.setText(R.id.txt_nick_name,item.getNick_name());
        holder.setText(R.id.txt_release_time,item.getCreate_time());
        if(TextUtils.isEmpty(item.getContent())){
            holder.getView(R.id.txt_content).setVisibility(View.GONE);
        }else {
            holder.getView(R.id.txt_content).setVisibility(View.VISIBLE);
            holder.setText(R.id.txt_content,item.getContent());
        }
        holder.setText(R.id.txt_ll,String.valueOf(item.getLook_count()));
        holder.setText(R.id.txt_pl,String.valueOf(item.getComment_count()));
        holder.setText(R.id.txt_good,String.valueOf(item.getPrise_count()));
        holder.setText(R.id.txt_good_lick,String.valueOf(item.getPrise_count()));

        if(isShowGoodLick){
           holder.getView(R.id.comment_bottom).setVisibility(View.GONE);
            holder.getView(R.id.txt_good_lick).setVisibility(View.VISIBLE);
        }else {
            holder.getView(R.id.comment_bottom).setVisibility(View.VISIBLE);
            holder.getView(R.id.txt_good_lick).setVisibility(View.GONE);
        }

        if(item.getIsPrised()){
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.icon_zan_red_yzy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if(isShowGoodLick){
                ((TextView)holder.getView(R.id.txt_good_lick)).setCompoundDrawables(drawable,null,null,null);
            }else {
                ((TextView)holder.getView(R.id.txt_good)).setCompoundDrawables(drawable,null,null,null);
            }
        }else {
            if(isShowGoodLick){
                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.icon_zan_yzy);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                ((TextView)holder.getView(R.id.txt_good_lick)).setCompoundDrawables(drawable,null,null,null);
            }else {
                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.icon_zan_grey_yzy);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                ((TextView)holder.getView(R.id.txt_good)).setCompoundDrawables(drawable,null,null,null);
            }
        }
        MultiImageView multiImageView = (MultiImageView)holder.getView(R.id.multiImagView);
        if(multiImageView != null){
            List<PhotoInfo> photos = new ArrayList<>();
            List<LocalMedia> selectList = new ArrayList<>();
            for(int i=0;i<item.getCommentPics().size();i++){
                PhotoInfo photoInfo=new PhotoInfo();
                photoInfo.url=item.getCommentPics().get(i);
                photos.add(photoInfo);
                LocalMedia    localMedia=new LocalMedia();
                localMedia.setPath(item.getCommentPics().get(i));
                selectList.add(localMedia);
            }
            if (photos != null && photos.size() > 0) {
                multiImageView.setVisibility(View.VISIBLE);
                multiImageView.setList(photos);
                multiImageView.setOnItemClickListener((view, position1) -> PictureSelector.create((Activity) context).externalPicturePreview(position1, "/idolmedia",  selectList));
            } else {
                multiImageView.setVisibility(View.GONE);
            }

        }

    }
}
