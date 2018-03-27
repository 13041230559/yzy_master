package com.idolmedia.yzy.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.SeckillEntity;
import com.idolmedia.yzy.entity.SupportEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/28 14:32
 * 描述：活动应援
 */

public class SupportAdapter extends BaseQuickAdapter<SupportEntity.DatasBean,BaseViewHolder> {
    public SupportAdapter() {
        super(R.layout.support_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupportEntity.DatasBean item) {
        Glide.with(mContext).load(item.getPic_url()).apply(new RequestOptions()).into((ImageView) helper.getView(R.id.iamge_find));
            helper.setText(R.id.txt_sm,item.getShop_name());
            helper.setText(R.id.txt_label,item.getShop_label());
            if(TextUtils.equals(item.getShop_type(),"activity")){
                Glide.with(mContext).load(R.mipmap.icon_startrace_active_yzy).apply(new RequestOptions()).into((ImageView) helper.getView(R.id.iamge_type));
            }else {
                Glide.with(mContext).load(R.mipmap.icon_startrace_assistance_yzy).apply(new RequestOptions()).into((ImageView) helper.getView(R.id.iamge_type));
            }
    }
}
