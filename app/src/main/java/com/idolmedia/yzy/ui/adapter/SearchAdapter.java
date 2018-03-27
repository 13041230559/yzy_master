package com.idolmedia.yzy.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/13 13:51
 * 描述：
 */

public class SearchAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SearchAdapter() {
        super(R.layout.search_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.txt_search_name,item);
        helper.addOnClickListener(R.id.iamge_del);
    }
}
