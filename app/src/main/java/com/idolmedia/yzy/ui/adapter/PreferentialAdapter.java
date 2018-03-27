package com.idolmedia.yzy.ui.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
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
import com.idolmedia.yzy.entity.PreferentiaEntity;
import com.idolmedia.yzy.entity.SeckillEntity;
import com.idolmedia.yzy.utlis.RadiusBackgroundSpan;
import com.idolmedia.yzy.utlis.TitleTipUtils;
import com.mumu.common.utils.FormatUtil;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/28 11:41
 * 描述：
 */

public class PreferentialAdapter extends BaseQuickAdapter<PreferentiaEntity.DatasBean, BaseViewHolder> {
    public PreferentialAdapter() {
        super(R.layout.commd_item);
    }



    @Override
    protected void convert(BaseViewHolder helper, PreferentiaEntity.DatasBean item) {
        Glide.with(mContext).load(item.getPic_url()).apply(new RequestOptions().error(R.mipmap.default_bj)).into((ImageView) helper.getView(R.id.iamge_figure));
        if(item.getShop_label()!=null){
            if(item.getShop_label().equals("海外直邮")){
                helper.setText(R.id.txt_title,TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),12,14,35,R.color.violet));
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

        ((TextView)helper.getView(R.id.txt_original_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        helper.setText(R.id.txt_pirce, item.getShop_type().equals("reserve")?"定金":"价格");
        helper.setText(R.id.txt_discount,String.valueOf(" ￥"+ FormatUtil.FormatPirce(item.getCurrent_price())));
        helper.setText(R.id.txt_original_price,String.valueOf(" ￥"+FormatUtil.FormatPirce(item.getOriginal_price())));
        helper.setText(R.id.txt_sold,String.format(mContext.getString(R.string.sold_out_),item.getSale_no()));
      /*  if(item.getShop_type().equals("reserve")){
            helper.setText(R.id.txt_sold,String.format(mContext.getString(R.string.have_reserved_),item.getSale_no()));
        }else {
            helper.setText(R.id.txt_sold,String.format(mContext.getString(R.string.sold_out_),item.getSale_no()));
        }
*/
    }
}
