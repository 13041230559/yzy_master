package com.idolmedia.yzy.ui.adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.utlis.TitleTipUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/9 18:39
 * 描述：
 */

public class ConfirmOrderItemAdapter extends BaseQuickAdapter<ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean,BaseViewHolder> {
    public ConfirmOrderItemAdapter() {
        super(R.layout.order_detalis_item);
    }
   @Override
    protected void convert(BaseViewHolder helper, ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean item) {
        Glide.with(mContext).load(item.getCaralog_img()).apply(new RequestOptions().centerCrop().error(R.mipmap.default_bg)).into((ImageView) helper.getView(R.id.iamge_mainfigure));
         helper.setText(R.id.txt_attribute,item.getCatalog_title());
       helper.setText(R.id.txt_title,(item.getShop_label()==null?"":item.getShop_label())+item.getShop_name());
       helper.setText(R.id.txt_discount_price,"￥"+item.getUnint_price());
       helper.setText(R.id.txt_original_price,"￥"+item.getOriginal_price());
       ((TextView)helper.getView(R.id.txt_original_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
       helper.setText(R.id.txt_buy_number,"x"+item.getQuantity_count());
       if(item.getShop_label()!=null){
           if(item.getShop_label().equals("海外直邮")){
               helper.setText(R.id.txt_title, TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),14,14,35,R.color.violet));
           }else if(item.getShop_label().equals("娱自营")){
               helper.setText(R.id.txt_title,TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),14,14,35,R.color.main_color));

           }else {
               helper.setText(R.id.txt_title,TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),14,14,35,R.color.ffc244));
           }
       }else {
           helper.setText(R.id.txt_title,  item.getShop_name());
       }
    }

}
