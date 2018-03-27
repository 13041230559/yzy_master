package com.idolmedia.yzy.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.InformationEntity;
import com.idolmedia.yzy.entity.StarDetailEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/12 11:48
 * 描述：
 */

public class InformationlAdapter extends BaseQuickAdapter<InformationEntity.DatasBean,BaseViewHolder> {
    public InformationlAdapter() {
        super(R.layout.star_new_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, InformationEntity.DatasBean item) {
       /* Glide.with(mContext).load(item.getIamge()).apply(new RequestOptions().centerCrop()).into((ImageView) helper.getView(R.id.iamge_starview));
        helper.setText(R.id.txt_title,item.getTitle());
        helper.setText(R.id.txt_time,item.getTime());
        helper.setText(R.id.txt_lll,item.getNumber());
        helper.setText(R.id.txt_type,item.getType());*/
        helper.setText(R.id.txt_lll,String.valueOf(item.getPopularity_count()));
        helper.setText(R.id.txt_type,item.getInformation_type());
        helper.setText(R.id.txt_title,item.getShop_name());
        helper.setText(R.id.txt_time,item.getCreate_time());
        Glide.with(mContext).load(item.getPic_url()).apply(new RequestOptions().centerCrop().error(R.mipmap.default_bg)).into((ImageView) helper.getView(R.id.iamge_starview));
    }
}
