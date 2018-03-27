package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.AuthenticationEntity;
import com.idolmedia.yzy.entity.SettingEntity;
import com.mumu.common.base.BaseActivity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/22 17:45
 * 描述：
 */

public class AuthenticationAdapter extends BaseQuickAdapter<AuthenticationEntity,BaseViewHolder> {
    public AuthenticationAdapter() {
        super(R.layout.setting_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, AuthenticationEntity item) {
        helper.setText(R.id.txt_type,item.getKey());
        helper.setText(R.id.txt_kb,item.getValue());

    }
}
