package com.idolmedia.yzy.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MyFollowOfficialEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/1 12:32
 * 描述：
 */

public class MyFollowOfficialAdapter extends BaseQuickAdapter<MyFollowOfficialEntity.DatasBean,BaseViewHolder> {
    public MyFollowOfficialAdapter() {
        super(R.layout.my_followido_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFollowOfficialEntity.DatasBean item) {
        helper.addOnClickListener(R.id.iamge_like);
        Glide.with(mContext).load(R.mipmap.icon_v_gfv_yzy).apply(new RequestOptions().circleCrop()).into((ImageView)helper.getView(R.id.iamge_grade));

        Glide.with(mContext).load(item.getHead_img()).apply(new RequestOptions().circleCrop()).into((ImageView)helper.getView(R.id.head_iamge));
          helper.setText(R.id.tv_nick_name,item.getNick_name());
        helper.setText(R.id.tv_fans_count,String.format(mContext.getString(R.string.fans_attention_count),item.getFans_count(),item.getAttention_count()));
        if(item.getIsAttend()){
            ((ImageView) helper.getView(R.id.iamge_like)).setImageResource(R.mipmap.icon_attention_heart_yzy);
        }else {
            ((ImageView) helper.getView(R.id.iamge_like)).setImageResource(R.mipmap.icon_attention_add_yzy);
        }

    }
}
