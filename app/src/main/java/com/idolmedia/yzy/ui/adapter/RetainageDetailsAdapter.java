package com.idolmedia.yzy.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.view.popwindow.NumberButton;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/22 20:53
 * 描述：
 */

public class RetainageDetailsAdapter extends BaseQuickAdapter<DepositEntity.CataPdBean,BaseViewHolder> {
    public RetainageDetailsAdapter() {
        super(R.layout.retainage_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepositEntity.CataPdBean item) {
        helper.setText(R.id.select_item,item.getCatalog_title());
        helper.addOnClickListener(R.id.button_sub).addOnClickListener(R.id.button_add).addOnClickListener(R.id.select_item);
        helper.setText(R.id.text_count,String.valueOf(item.getBuyNumber()));
        if(item.isCheck()){
            helper.getView(R.id.select_item).setBackgroundResource(R.drawable.pop_item_shape);
            helper.setTextColor(R.id.select_item,mContext.getResources().getColor(R.color.white));
        }else {
            helper.getView(R.id.select_item).setBackgroundResource(R.drawable.tv_bg);
            helper.setTextColor(R.id.select_item,mContext.getResources().getColor(R.color.c3));
        }
        // ((NumberButton)helper.getView(R.id.number_button)).setCurrentNumber(0).setInventory(item.getSurplus_no());
    }
}
