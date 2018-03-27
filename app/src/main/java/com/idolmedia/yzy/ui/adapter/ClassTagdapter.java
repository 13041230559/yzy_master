package com.idolmedia.yzy.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.ClassEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.UpdateHeadActivity;
import com.mumu.common.utils.ToastUitl;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/7 14:23
 * 描述： 产品分类
 */

public class ClassTagdapter extends BaseQuickAdapter<ClassEntity.DatasBean,BaseViewHolder> {
    public ClassTagdapter() {
        super(R.layout.classification_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, ClassEntity.DatasBean item) {
         helper.setText(R.id.txt_title,item.getName());
        ((TagFlowLayout)helper.getView(R.id.flowlayout)).setAdapter(new TagAdapter(item.getSub_dicts()) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.class_item, (TagFlowLayout)helper.getView(R.id.flowlayout), false);
                tv.setText(item.getSub_dicts().get(position).getName());
                return tv;
            }
        });
        ((TagFlowLayout)helper.getView(R.id.flowlayout)).setOnTagClickListener((view, position, parent) -> {
            if(itemOnclickListener!=null){
                itemOnclickListener.OnItemOnclick(item.getSub_dicts().get(position).getDictionaries_id(),item.getSub_dicts().get(position).getName());
            }
            return true;
        });
    }

    public interface OnItemOnclickListener {
        void OnItemOnclick(String i,String title);

    }
    private OnItemOnclickListener itemOnclickListener = null;
    public void setitemOnclickListener(OnItemOnclickListener itemOnclickListener) {
        this.itemOnclickListener=itemOnclickListener;
    }
}
