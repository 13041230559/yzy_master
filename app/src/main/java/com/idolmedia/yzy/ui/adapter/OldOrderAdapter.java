package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.OldOrderEntity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/17 17:45
 * 描述：
 */

public class OldOrderAdapter extends BaseQuickAdapter<OldOrderEntity.DatasBean ,BaseViewHolder> {
    public OldOrderAdapter() {
        super(R.layout.old_order_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, OldOrderEntity.DatasBean item) {
        helper.getView(R.id.txt_logistics).setVisibility(View.INVISIBLE);
        if(item.getOrder_status().equals("orderOver")){
            helper.setText(R.id.txt_s_type,"已完成");
            helper.getView(R.id.txt_logistics).setVisibility(View.VISIBLE);
        }else if(item.getOrder_status().equals("waitingDelivery")){
            helper.setText(R.id.txt_s_type,"待发货");
        }else if(item.getOrder_status().equals("waitingGoods")){
            helper.setText(R.id.txt_s_type,"待收货");
            helper.getView(R.id.txt_logistics).setVisibility(View.VISIBLE);
        }
        Glide.with(mContext).load(R.mipmap.logo).apply(new RequestOptions().circleCrop()).into((ImageView) helper.getView(R.id.iamge_s_logo));
        Glide.with(mContext).load(item.getTitle_img()).apply(new RequestOptions().error(R.mipmap.default_bg)).into((ImageView) helper.getView(R.id.iamge_mainfigure));
        helper.setText(R.id.txt_title,item.getProduct_title());
        helper.setText(R.id.txt_attribute,item.getTitle()+"\n"+"订单编号:\n"+item.getOrder_num());
        helper.setText(R.id.txt_discount_price,"￥"+item.getPrice());
        helper.setText(R.id.txt_buy_number,"x"+item.getNum());
        helper.setText(R.id.txt_subtotal,"合计:"+item.getPayMoney());
        helper.setText(R.id.txt_freight,String.format(mContext.getString(R.string.freight),item.getExpress_money()));
        helper.addOnClickListener(R.id.txt_logistics);

    }
}
