package com.idolmedia.yzy.ui.adapter;

import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.utlis.RadiusBackgroundSpan;
import com.idolmedia.yzy.utlis.TitleTipUtils;
import com.mumu.common.utils.FormatUtil;

/**
 * Created by Administrator on 2017/11/13.
 */

public class EntertainmentlAdapter extends BaseQuickAdapter<CommodityEntity.DatasBean,BaseViewHolder> {


    public EntertainmentlAdapter() {
        super(R.layout.commd_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, CommodityEntity.DatasBean item) {
           helper.addOnClickListener(R.id.iamg_addcart);
           if(item.getShop_type().equals("reserve")){
               helper.getView(R.id.iamge_isdeposit).setVisibility(View.VISIBLE);
           }else {
               helper.getView(R.id.iamge_isdeposit).setVisibility(View.GONE);
           }
        Glide.with(mContext).load(item.getPic_url()).apply(new RequestOptions().error(R.mipmap.default_bg)).into((ImageView) helper.getView(R.id.iamge_figure));
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


       /*if(item.getShop_label()!=null){
            TitleTipUtils.titleTipUtils(mContext,(TextView)helper.getView(R.id.txt_title),item.getShop_label(),item.getShop_name(),12,14,35,R.color.ffc244);
          //  helper.setText(R.id.txt_title, Spannable(item.getShop_label(),item.getShop_name()));
        }else {
            helper.setText(R.id.txt_title,  item.getShop_name());
        }
       //*/
        helper.setText(R.id.txt_original_price," ￥"+ FormatUtil.FormatPirce(item.getOrigin_price()));
        ((TextView)helper.getView(R.id.txt_original_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        helper.setText(R.id.txt_pirce, item.getShop_type().equals("reserve")?"定金":"价格");
        helper.setText(R.id.txt_discount,String.valueOf(" ￥"+FormatUtil.FormatPirce(item.getCurrent_price())));
        helper.setText(R.id.txt_sold,String.format(mContext.getString(R.string.sold_out_),item.getSale_no()));
       /* if(item.getShop_type().equals("reserve")){
            helper.setText(R.id.txt_sold,String.format(mContext.getString(R.string.have_reserved_),item.getSale_no()));
        }else {

        }*/

    }
   /* @Override
    protected void convert(BaseViewHolder helper, CommodEntity1 item) {
        Glide.with(mContext).load(item.getMian_iamge()).apply(new RequestOptions().centerCrop()).into((ImageView) helper.getView(R.id.iamge_figure));
        helper.setText(R.id.txt_pircen,"￥"+item.getY_price());
      //  ((TextView) helper.getView(R.id.txt_originalprice)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        helper.setText(R.id.txt_sold,"已售出"+item.getY_price());
        helper.addOnClickListener(R.id.iamg_addcart);
        if(item.getShop_type()!=null) {
            if (item.getShop_type().equals("海外直邮")) {
                SpannableStringBuilder spannbulider = new SpannableStringBuilder(item.getShop_type() + " " + item.getContext());
                spannbulider.setSpan(new RadiusBackgroundSpan(mContext.getResources().getColor(R.color.violet), mContext.getResources().getColor(R.color.white), 30, 35, 0), 0, item.getShop_type().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                helper.setText(R.id.txt_title, spannbulider);
            } else {
                SpannableStringBuilder spannbulider = new SpannableStringBuilder(item.getShop_type() + " " + item.getContext());
                spannbulider.setSpan(new RadiusBackgroundSpan(mContext.getResources().getColor(R.color.main_color), mContext.getResources().getColor(R.color.white), 30, 35, 0), 0, item.getShop_type().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                helper.setText(R.id.txt_title, spannbulider);

            }
        }
    }*/

}

