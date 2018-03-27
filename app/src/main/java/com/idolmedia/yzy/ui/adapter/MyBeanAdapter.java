package com.idolmedia.yzy.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MyBeanEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/21 15:09
 * 描述：我的娱豆
 */

public class MyBeanAdapter extends BaseQuickAdapter<MyBeanEntity.DatasBean,BaseViewHolder> {
    public MyBeanAdapter() {
        super(R.layout.mybean_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBeanEntity.DatasBean item) {

        helper.setText(R.id.txt_type,item.getDescription());
        helper.setText(R.id.txt_time,item.getCreat_time());
        helper.setText(R.id.txt_price,item.getMoney_type()+item.getMoney_no());
    }
}
