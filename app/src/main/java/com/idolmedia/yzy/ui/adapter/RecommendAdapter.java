package com.idolmedia.yzy.ui.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
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
import com.idolmedia.yzy.utlis.DensityUtil;
import com.idolmedia.yzy.utlis.RadiusBackgroundSpan;
import com.idolmedia.yzy.utlis.TitleTipUtils;
import com.mumu.common.utils.FormatUtil;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/25 13:37
 * 描述：推荐商品列表
 */

public class RecommendAdapter extends BaseQuickAdapter<CommodityEntity.DatasBean,BaseViewHolder> {
    public RecommendAdapter(int layoutResId) {
        super(layoutResId);
    }


    public static boolean isOdd(int i){
        return i % 2!=0;//如果一个数是偶数，就算是负数整除2余数也为0
    }
    @Override
    protected void convert(BaseViewHolder helper, CommodityEntity.DatasBean item) {


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
        if(item.getShop_type().equals("reserve")){
            helper.getView(R.id.iamge_isdeposit).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.iamge_isdeposit).setVisibility(View.GONE);
        }
        helper.setText(R.id.txt_discount,"￥"+ FormatUtil.FormatPirce(item.getCurrent_price()));
        helper.setText(R.id.txt_original_price,"￥"+FormatUtil.FormatPirce(item.getOrigin_price()));
        ((TextView)helper.getView(R.id.txt_original_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线

        helper.setText(R.id.txt_sold,String.format(mContext.getString(R.string.sold_out),item.getSale_no()));
        helper.addOnClickListener(R.id.iamg_addcart);
    }
}
