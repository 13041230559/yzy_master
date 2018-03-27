package com.idolmedia.yzy.ui.adapter;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.AddressEntity;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/11 15:58
 * 描述： 地址管理
 */

public class AddressAdapter extends BaseQuickAdapter<AddressEntity.AddressDataBean,BaseViewHolder> {
    public AddressAdapter() {
        super(R.layout.address_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressEntity.AddressDataBean item) {

         helper.setText(R.id.txt_addressname,item.getConsignee());
        helper.setText(R.id.txt_addressphone,item.getPhone());

        helper.setText(R.id.txt_content,item.getCity_name().replaceAll("YZY","")+item.getDetail_address());
        if(item.getIs_default()==0){
//改变字体颜色
            //先构造SpannableString
            SpannableString spanString = new SpannableString(mContext.getString(R.string.default_address)+item.getCity_name().replaceAll("YZY","")+item.getDetail_address());
            //再构造一个改变字体颜色的Span
            ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.ff5757));
            //将这个Span应用于指定范围的字体
            spanString.setSpan(span, 0, mContext.getString(R.string.default_address).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            helper.setText(R.id.txt_content,spanString);
        }

        helper.addOnClickListener(R.id.iamge_update).addOnClickListener(R.id.iamge_del);


    }
}
