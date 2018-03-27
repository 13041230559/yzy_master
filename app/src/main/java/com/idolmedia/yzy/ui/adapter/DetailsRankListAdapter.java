package com.idolmedia.yzy.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CommodEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 20:47
 * 描述：
 */

public class DetailsRankListAdapter extends BaseQuickAdapter<CommodEntity.RecordPdBean,BaseViewHolder> {
    public DetailsRankListAdapter() {
        super(R.layout.rank_horlist_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodEntity.RecordPdBean item) {

    }
}
