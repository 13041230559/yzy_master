package com.idolmedia.yzy.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.SeckillEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/17 11:13
 * 描述：  秒杀商品适配器
 */

public class SeckillAdapter extends BaseQuickAdapter<SeckillEntity,BaseViewHolder> {
    public SeckillAdapter() {
        super(R.layout.seckill_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, SeckillEntity item) {
        Glide.with(mContext).load(item.getIamge()).apply(new RequestOptions().centerCrop()).into((ImageView) helper.getView(R.id.iamge_main_figure));
        helper.setText(R.id.txt_title_name,item.getTitle());
        helper.setText(R.id.txt_price,item.getPrice());
        helper.setText(R.id.txt_people_number,item.getContext());
        helper.addOnClickListener(R.id.txt_immediately).addOnClickListener(R.id.txt_inviting_friends);
    }
}
