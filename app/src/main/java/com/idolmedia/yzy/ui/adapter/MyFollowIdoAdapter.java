package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MyFollowIdoEntity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/1 12:32
 * 描述：
 */

public class MyFollowIdoAdapter extends BaseQuickAdapter<MyFollowIdoEntity.DatasBean,BaseViewHolder> {
    public MyFollowIdoAdapter() {
        super(R.layout.my_followido_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFollowIdoEntity.DatasBean item) {
        helper.addOnClickListener(R.id.iamge_like);
        Glide.with(mContext).load(item.getIdo_head_img()).apply(new RequestOptions().circleCrop()).into((ImageView)helper.getView(R.id.head_iamge));
          helper.setText(R.id.tv_nick_name,item.getIdo_name());
        helper.setText(R.id.tv_fans_count,String.format(mContext.getString(R.string.fans_count),item.getFans_count()));
        if(item.isAtted()){
            ((ImageView) helper.getView(R.id.iamge_like)).setImageResource(R.mipmap.icon_attention_heart_yzy);
        }else {
            ((ImageView) helper.getView(R.id.iamge_like)).setImageResource(R.mipmap.icon_attention_add_yzy);
        }

    }
}
