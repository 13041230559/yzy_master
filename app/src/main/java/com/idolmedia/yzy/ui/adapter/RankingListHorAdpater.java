package com.idolmedia.yzy.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CommodEntity;
import com.idolmedia.yzy.entity.RankingEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/14 15:54
 * 描述：
 */

public class RankingListHorAdpater extends BaseQuickAdapter<CommodEntity.RecordPdBean,BaseViewHolder>{
    public RankingListHorAdpater() {
        super(R.layout.rank_horlist_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodEntity.RecordPdBean item) {
       Glide.with(mContext).load(item.getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into( (ImageView) helper.getView(R.id.iamge_end));
       helper.setText(R.id.txt_name,item.getNick_name());
        helper.setText(R.id.txt_price,String.valueOf(item.getMoney()));
    }
}
