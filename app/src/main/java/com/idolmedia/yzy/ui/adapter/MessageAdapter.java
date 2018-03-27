package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MessageEntity;
import com.idolmedia.yzy.entity.MsgEntity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/14 11:23
 * 描述： 消息列表
 */

public class MessageAdapter extends BaseQuickAdapter<MessageEntity.DatasBean,BaseViewHolder> {
    public MessageAdapter() {
        super(R.layout.msg_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity.DatasBean item) {
        //  helper.setText(R.id.txt_type,item.getType());
        //   Glide.with(mContext).load(item.getPic_url()).into((ImageView)(helper.getView(R.id.image_type)));
        helper.setText(R.id.txt_msg_title,item.getTitle());
        helper.setText(R.id.txt_msg_time,item.getCreate_time());
        helper.setText(R.id.txt_msg_content,item.getContent());
        Log.e("helper",String.valueOf(item.getMessage_type_id()));
        if(item.getNot_read_count()==0){
            helper.getView(R.id.red_view).setVisibility(View.INVISIBLE);
        }else {
            helper.getView(R.id.red_view).setVisibility(View.VISIBLE);
        }
        if(item.getMessage_type_id()==1){
            Glide.with(mContext).load(R.mipmap.icon_news_star_yzy).into((ImageView)(helper.getView(R.id.image_type)));

        }else  if(item.getMessage_type_id()==2){
            Glide.with(mContext).load(R.mipmap.icon_news_logistics_yzy).into((ImageView)(helper.getView(R.id.image_type)));

        }else  if(item.getMessage_type_id()==3){
            Glide.with(mContext).load(R.mipmap.icon_news_update_yzy).into((ImageView)(helper.getView(R.id.image_type)));

        }else  if(item.getMessage_type_id()==4){
            Glide.with(mContext).load(R.mipmap.icon_news_yu_yzy).into((ImageView)(helper.getView(R.id.image_type)));

        }else  if(item.getMessage_type_id()==5){
           Glide.with(mContext).load(R.mipmap.icon_news_new).into((ImageView)(helper.getView(R.id.image_type)));

        }
    }
       /* if(helper.getAdapterPosition()==1){
            helper.setText(R.id.txt_type,"递");
        }  else if(helper.getAdapterPosition()==2){

            helper.setText(R.id.txt_type,"娱");
        } else if(helper.getAdapterPosition()==3){

            helper.setText(R.id.txt_type,"豆");
        }

    }*/
}
