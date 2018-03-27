package com.idolmedia.yzy.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/28 10:28
 * 描述：
 */

public class ShareAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {
    public ShareAdapter() {
        super(R.layout.share_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        Glide.with(mContext).load(item).apply(new RequestOptions().centerCrop()).into((ImageView) helper.getView(R.id.iamge_shaer));
    }
}
