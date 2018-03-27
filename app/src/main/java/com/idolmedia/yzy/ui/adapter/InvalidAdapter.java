package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CartEntity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/4 13:59
 * 描述：
 */

public class InvalidAdapter extends BaseQuickAdapter<CartEntity.InvalidCartsDataBean,BaseViewHolder> {
    public InvalidAdapter() {
        super(R.layout.invalid_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, CartEntity.InvalidCartsDataBean item) {
        helper.setText(R.id.txt_name,item.getShop_name());
        Glide.with(mContext).load(item.getCatalog_img()).apply(new RequestOptions().centerCrop()).into((ImageView)(helper.getView(R.id.invalid_iamge)));
    }
}
