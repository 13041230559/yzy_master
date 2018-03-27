package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MessageClassEntity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/7 15:21
 * 描述：
 */

public class QueryMessageAdapter extends BaseQuickAdapter<MessageClassEntity.DatasBean,BaseViewHolder> {

    public QueryMessageAdapter() {
        super(R.layout.query_message_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageClassEntity.DatasBean item) {
        helper.setText(R.id.txt_shop_name,item.getTitle()).setText(R.id.txt_shop_time,item.getCreate_time()).setText(R.id.txt_shop_content,item.getContent());
         if(item.getHave_read()==0){
             helper.getView(R.id.red_view).setVisibility(View.VISIBLE);
         }else {
             helper.getView(R.id.red_view).setVisibility(View.INVISIBLE);
         }

    }
}
