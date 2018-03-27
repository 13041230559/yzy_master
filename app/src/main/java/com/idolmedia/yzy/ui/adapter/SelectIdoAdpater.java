package com.idolmedia.yzy.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.IDoEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/15 10:46
 * 描述：
 */

public class SelectIdoAdpater  extends BaseQuickAdapter<IDoEntity.DatasBean,BaseViewHolder>{
    public SelectIdoAdpater() {
        super(R.layout.selectstar_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, IDoEntity.DatasBean item) {
        Glide.with(mContext).load(item.getIdo_head_img()).apply(new RequestOptions().circleCrop()).into((ImageView)helper.getView(R.id.iamg_starhead));
        helper.addOnClickListener(R.id.iamge_like);
     if(item.isIsAtted()){
         ((ImageView) helper.getView(R.id.iamge_like)).setBackgroundResource(R.mipmap.icon_like_like);
         //  Glide.with(mContext).load(R.mipmap.icon_like_like).into((ImageView)helper.getView(R.id.iamge_like));
         }else {
         ((ImageView) helper.getView(R.id.iamge_like)).setBackgroundResource(R.mipmap.icon_like_add);
             //Glide.with(mContext).load(R.mipmap.icon_like_add).into((ImageView)helper.getView(R.id.iamge_like));
         }
    }
}
