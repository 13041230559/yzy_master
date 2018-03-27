package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.DepositEntity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/20 13:59
 * 描述：
 */

public class CommodityTagAdapter extends BaseQuickAdapter<DepositEntity.CataPdBean,BaseViewHolder> {
    public CommodityTagAdapter() {
        super(R.layout.select_commd_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepositEntity.CataPdBean item) {
        helper.setText(R.id.select_item,item.getCatalog_title());
        helper.addOnClickListener(R.id.select_item);
        if(item.isCheck()){
            helper.getView(R.id.select_item).setBackgroundResource(R.drawable.pop_item_shape);
            helper.setTextColor(R.id.select_item,mContext.getResources().getColor(R.color.white));
        }else {
            if(item.getSurplus_no()==0){
                helper.getView(R.id.select_item).setBackgroundResource(R.drawable.tv_tag1);
                helper.setTextColor(R.id.select_item,mContext.getResources().getColor(R.color.white));
            }else {
                helper.getView(R.id.select_item).setBackgroundResource(R.drawable.tv_bg);
                helper.setTextColor(R.id.select_item,mContext.getResources().getColor(R.color.c3));
            }
        }


        /*ViewGroup.LayoutParams lp = ((TextView)helper.getView(R.id.select_item)).getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
            flexboxLp.setFlexGrow(1.0f);
        }*/
    }
}
