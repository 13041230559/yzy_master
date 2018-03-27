package com.idolmedia.yzy.ui.adapter;

import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MyOrderDetalisEntity;
import com.idolmedia.yzy.entity.MyOrderEntity;
import com.idolmedia.yzy.utlis.RadiusBackgroundSpan;
import com.idolmedia.yzy.utlis.TitleTipUtils;
import com.mumu.common.utils.FormatDate;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/9 18:39
 * 描述：
 */

public class MyOrderItemDetalisAdapter extends BaseQuickAdapter<MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean,BaseViewHolder> {
    public MyOrderItemDetalisAdapter() {
        super(R.layout.order_detalis_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean item) {
        Glide.with(mContext).load(item.getCatalog_img()).apply(new RequestOptions().centerCrop().error(R.mipmap.default_bj)).into((ImageView) helper.getView(R.id.iamge_mainfigure));
        helper.setText(R.id.txt_attribute,item.getCatalog_name());
        helper.setText(R.id.txt_attribute,item.getOrder_status().equals("waitingFinalPay")?item.getCatalog_name()+"\n"+"尾款未开启":item.getCatalog_name());
        helper.getView(R.id.txt_un_opened).setVisibility(item.getOrder_status().equals("waitingFinalPay")? View.VISIBLE:View.INVISIBLE);
        if(TextUtils.equals("waitingFinalPay",item.getOrder_status())){

            Log.e("getFinal_end_time",String.valueOf(item.getFinal_end_time()));
            if(!TextUtils.isEmpty(item.getFinal_end_time())){
                long endtime = FormatDate.StrToDate(item.getFinal_end_time()).getTime();
                long thistime = FormatDate.StrToDate(item.getNowTime()).getTime();
                long startime = FormatDate.StrToDate(item.getFinal_start_time()).getTime();
                if((thistime<startime)||(thistime>endtime)){
                    if(thistime<startime){
                        helper.setText(R.id.txt_attribute,item.getOrder_status().equals("waitingFinalPay")?item.getCatalog_name()+"\n"+"尾款未开启":item.getCatalog_name());
                        helper.getView(R.id.txt_un_opened).setVisibility(item.getOrder_status().equals("waitingFinalPay")? View.VISIBLE:View.INVISIBLE);
                    }else if(thistime>endtime){
                        helper.setText(R.id.txt_attribute,item.getOrder_status().equals("waitingFinalPay")?item.getCatalog_name()+"\n"+"尾款已结束":item.getCatalog_name());
                    }
                }else if(thistime<endtime&&thistime>startime){
                    helper.setText(R.id.txt_attribute,item.getOrder_status().equals("waitingFinalPay")?item.getCatalog_name()+"\n"+"尾款结束时间"+"\n"+item.getFinal_end_time():item.getCatalog_name());
                    helper.getView(R.id.txt_un_opened).setVisibility(item.getOrder_status().equals("waitingFinalPay")? View.VISIBLE:View.INVISIBLE);

                    //  ((MyOrderListAdapter.GroupViewHolder) holder).ordr_type.setText(orderType(item.getOrder_status()));
                }
            }else {
                helper.setText(R.id.txt_attribute,item.getOrder_status().equals("waitingFinalPay")?item.getCatalog_name()+"\n"+"尾款未开启":item.getCatalog_name());
                helper.getView(R.id.txt_un_opened).setVisibility(item.getOrder_status().equals("waitingFinalPay")? View.VISIBLE:View.INVISIBLE);
            }
        }

        if(item.getShop_label()!=null){
            if(item.getShop_label().equals("海外直邮")){
                helper.setText(R.id.txt_title, TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),12,14,35,R.color.violet));
            }else if(item.getShop_label().equals("娱自营")){
                helper.setText(R.id.txt_title,TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),12,14,35,R.color.main_color));
            }else {
                helper.setText(R.id.txt_title,TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),12,14,35,R.color.ffc244));
            }
        }else {
            helper.setText(R.id.txt_title,  item.getShop_name());
        }
        helper.setText(R.id.txt_discount_price,"￥"+(item.getCurrent_price().equals("")?"0.0":item.getCurrent_price()));
        helper.setText(R.id.txt_original_price,"￥"+(item.getOriginal_price().equals("")?"0.0":item.getOriginal_price()));
        ((TextView)helper.getView(R.id.txt_original_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        helper.setText(R.id.txt_buy_number,"x"+item.getShop_count());
    }
}