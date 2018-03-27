package com.idolmedia.yzy.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.SettingEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/15 14:42
 * 描述：  设置页面
 */

public class SettingAdapter extends BaseQuickAdapter<SettingEntity,BaseViewHolder> {
    public SettingAdapter() {
        super(R.layout.setting_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingEntity item) {
           helper.setText(R.id.txt_type,item.getName());
        helper.setText(R.id.txt_kb,item.getContext());
        if(TextUtils.isEmpty(item.getContext())){

            helper.setText(R.id.txt_kb,item.getContext());
        }
    }
}
