package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.PreferentialHotEntity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 19:10
 * 描述：
 */

public class PreferentialImageAdapter extends BaseQuickAdapter<PreferentialHotEntity.DatasBean,BaseViewHolder> {
    public PreferentialImageAdapter() {
        super(R.layout.pref_item_discount);
    }

    @Override
    protected void convert(BaseViewHolder helper, PreferentialHotEntity.DatasBean item) {
        Glide.with(mContext).load(item.getPic_url()).apply(new RequestOptions().error(R.mipmap.default_bg)).into((ImageView) helper.getView(R.id.iamge_backg));

    }
}
