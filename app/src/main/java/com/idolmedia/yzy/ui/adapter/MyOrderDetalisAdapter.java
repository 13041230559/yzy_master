package com.idolmedia.yzy.ui.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MyOrderDetalisEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.MyOrderDetalisActivity;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.mumu.common.utils.FormatDate;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.JsonUtils;
import com.mumu.common.utils.ToastUitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/9 18:39
 * 描述：
 */

public class MyOrderDetalisAdapter extends RecyclerView.Adapter {

    private final int TYPE_TITLE = 0;//店铺头部布局
    private final int TYPE_ITEM = 1;//正常商品布局
    private static final String PARENT_POSITION = "parent_position";//标记存放父节点
    private static final String CHILD_POSITION = "child_position";//标记存放子节点

    List<MyOrderDetalisEntity.DatasBean> datasBeans;

    Context context;

    Link link;
    /**
     * 构造方法里面初始化一个空的数据列表 要想设置数据 需要通过方法 {@link #setdatasBeans(List)}
     */
    public MyOrderDetalisAdapter(Context context) {
        this.datasBeans = new ArrayList<>();
        this.context = context;

    }

    public void setdatasNewBeans(List<MyOrderDetalisEntity.DatasBean> datasBeans) {
        if (datasBeans != null) {
            this.datasBeans=datasBeans;
            notifyDataSetChanged();
        }
    }
    public void setdatasBeans(List<MyOrderDetalisEntity.DatasBean> datasBeans) {
        if (datasBeans != null) {
            this.datasBeans.addAll(datasBeans);
            notifyDataSetChanged();
        }
    }

    private String orderType(String type) {

        String[] title_name = new String[]{"待支付", "待发货", "待收货", "待评价", "已完成", "已退款", "交易关闭", "支付超时", "待付尾款"};
        String[] type_name = new String[]{"toPay", "waitingDelivery", "waitingGoods", "waitingApprise", "orderOver", "refunded", "cancel", "invalid", "waitingFinalPay"};
        String odertype = "";
        if (TextUtils.equals(type, type_name[0])) {

            odertype = title_name[0];

        } else if (TextUtils.equals(type, type_name[1])) {

            odertype = title_name[1];

        } else if (TextUtils.equals(type, type_name[2])) {

            odertype = title_name[2];

        } else if (TextUtils.equals(type, type_name[3])) {

            odertype = title_name[3];

        } else if (TextUtils.equals(type, type_name[4])) {

            odertype = title_name[4];

        } else if (TextUtils.equals(type, type_name[5])) {

            odertype = title_name[5];

        } else if (TextUtils.equals(type, type_name[5])) {

            odertype = title_name[5];

        } else if (TextUtils.equals(type, type_name[6])) {

            odertype = title_name[6];

        } else if (TextUtils.equals(type, type_name[7])) {

            odertype = title_name[7];

        } else if (TextUtils.equals(type, type_name[8])) {

            odertype = title_name[8];

        }
        return odertype;
    }

    @Override
    public int getItemViewType(int position) {
        if (isTitleItem(position) != -1) return TYPE_TITLE;
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.submit_order_group, parent, false);
                return new GroupViewHolder(view);
            //返回头部布局
            case TYPE_ITEM:
                //返回正常item布局
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_orderdetalis_recyclerview, parent, false);
                return new ChildViewHolder(view1);
        }
        return null;
    }

    private String onItemPirce(List<MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean> itemlist) {
        double count = 0;
        for (MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean catalogItemsBean : itemlist) {

            count += Double.parseDouble(catalogItemsBean.getCurrent_price()) * Integer.parseInt(catalogItemsBean.getShop_count());

        }

        return FormatUtil.FormatPirce(count);
    }


    private String RetainagePirce(List<MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean> listdata,TextView retainageType){
        double retainagePrice = 0;
        for(int i=0;i<listdata.size();i++){
            if(listdata.get(i).getIs_reserve().equals("2")){
                retainagePrice+=Double.parseDouble(listdata.get(i).getPay_money());
                retainageType.setText(listdata.get(i).getReserve_status().equals("havePay")?"已付款":"未付款");
            }
        }

        return FormatUtil.FormatPirce(retainagePrice);
    }

    private void isPayType(List<MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean> listdata,TextView retainageType,TextView depositType){
        for(int i=0;i<listdata.size();i++){
            if(listdata.get(i).getIs_reserve().equals("1")){
                depositType.setText(listdata.get(i).getReserve_status().equals("havePay")?"已付款":"未付款");
            }
            if(listdata.get(i).getIs_reserve().equals("2")){
                retainageType.setText(listdata.get(i).getReserve_status().equals("havePay")?"已付款":"未付款");
            }
        }
    }

    private String DepositPirce(List<MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean> listdata,TextView depositType){
        double depositPrice = 0;
        for(int i=0;i<listdata.size();i++){
            if(listdata.get(i).getIs_reserve().equals("1")){
                depositPrice+=Double.parseDouble(listdata.get(i).getPay_money());
                depositType.setText(listdata.get(i).getReserve_status().equals("havePay")?"已付款":"未付款");
            }
        }
        return FormatUtil.FormatPirce(depositPrice);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                int titlePosition = isTitleItem(position);
                MyOrderDetalisEntity.DatasBean datasBean = datasBeans.get(titlePosition);
                //设置头部数据
                Glide.with(context).load(datasBean.getHead_img()).apply(new RequestOptions().circleCrop()).into(((GroupViewHolder) holder).iamgeBusiness);
                ((GroupViewHolder) holder).txtBusinessName.setText(datasBean.getNick_name());
                ((GroupViewHolder) holder).ordr_type.setText(orderType(datasBean.getOrder_status()));
                ((GroupViewHolder) holder).ordr_type.setVisibility(View.VISIBLE);
                if(TextUtils.equals("waitingFinalPay",datasBean.getOrder_status())){
                    if(!TextUtils.isEmpty(datasBean.getFinal_end_time())){
                        long endtime = FormatDate.StrToDate(datasBean.getFinal_end_time()).getTime();
                        long thistime = FormatDate.StrToDate(datasBean.getNowTime()).getTime();
                        long startime = FormatDate.StrToDate(datasBean.getFinal_start_time()).getTime();
                        if((thistime<startime)||(thistime>endtime)){
                            if(thistime<startime){
                                ((GroupViewHolder) holder).ordr_type.setText(context.getResources().getString(R.string.payretainage_unopened));
                            }else if(thistime>endtime){
                                ((GroupViewHolder) holder).ordr_type.setText(context.getResources().getString(R.string.payretainage_over));
                            }
                        }else if(thistime<endtime&&thistime>startime){
                            ((GroupViewHolder) holder).ordr_type.setText(orderType(datasBean.getOrder_status()));
                        }
                    }else {

                        ((GroupViewHolder) holder).ordr_type.setText(context.getResources().getString(R.string.payretainage_unopened));

                    }
                }
                break;

            case TYPE_ITEM:
                //绑定正常布局item数据
                HashMap<String, Integer> map = isItem(position);
                if (map != null) {
                    int parentPosition = map.get(PARENT_POSITION);
                    int childPosition = map.get(CHILD_POSITION);
                    Log.e("parentPosition", parentPosition + "   " + childPosition);
                    MyOrderDetalisEntity.DatasBean mydatasBean = datasBeans.get(parentPosition);
                    MyOrderDetalisEntity.DatasBean.OrderItemsBean orderItemsBean = mydatasBean.getOrderItems().get(childPosition);
                    ((ChildViewHolder) holder).linerAddition.removeAllViews();

                    List<MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean> cartInfoBeanList = new ArrayList<>();
                    for (MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean catalogItemsBean : orderItemsBean.getCatalogItems()) {
                        catalogItemsBean.setShop_name(orderItemsBean.getShop_name());
                        catalogItemsBean.setShop_label(orderItemsBean.getShop_label());
                        catalogItemsBean.setOrder_status(mydatasBean.getOrder_status());
                        catalogItemsBean.setNowTime(mydatasBean.getNowTime());
                        catalogItemsBean.setFinal_end_time(mydatasBean.getFinal_end_time());
                        catalogItemsBean.setFinal_start_time(mydatasBean.getFinal_start_time());
                        cartInfoBeanList.add(catalogItemsBean);
                    }
                    MyOrderItemDetalisAdapter orderItemAdapter = new MyOrderItemDetalisAdapter();
                    ((ChildViewHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    ((ChildViewHolder) holder).recyclerView.setAdapter(orderItemAdapter);
                    orderItemAdapter.setNewData(orderItemsBean.getCatalogItems());
                    orderItemAdapter.setOnItemClickListener((adapter, view, position1) -> {
                       // MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean catalogItemsBean=(MyOrderDetalisEntity.DatasBean.OrderItemsBean.CatalogItemsBean)adapter.getData().get(position1);
                             Bundle bundle=new Bundle();
                        bundle.putString("shop_type",orderItemsBean.getShop_type());
                        bundle.putString("shopcommon_id",orderItemsBean.getShopcommon_id());
                        bundle.putString("ordernum",mydatasBean.getOrder_num());
                        ((MyOrderDetalisActivity)context).startActivity(CommodityDetailsActivity.class,bundle);
                    });

                    if (!TextUtils.isEmpty(orderItemsBean.getContent())) {
                        orderItemsBean.setAdditionBeanList((List<MyOrderDetalisEntity.DatasBean.OrderItemsBean.AdditionBean>) JsonUtils.fromJson(orderItemsBean.getContent(), new TypeToken<List<MyOrderDetalisEntity.DatasBean.OrderItemsBean.AdditionBean>>() {
                        }.getType()));
                        for (MyOrderDetalisEntity.DatasBean.OrderItemsBean.AdditionBean additionBean : orderItemsBean.getAdditionBeanList()) {
                            if (additionBean != null) {
                                ((ChildViewHolder) holder).linerAddition.setVisibility(View.VISIBLE);
                                TextView textView = new TextView(context);
                                textView.setTextColor(context.getResources().getColor(R.color.c3));
                                textView.setText(additionBean.getField() + ":" + additionBean.getAddition_value());
                                ((ChildViewHolder) holder).linerAddition.addView(textView);
                            }

                        }
         }

         if(mydatasBean.getOrderItems().get(0).getShop_type().equals("reserve")){
             ((ChildViewHolder) holder).linerDeposit.setVisibility(View.VISIBLE);
             ((ChildViewHolder) holder).linerRetainage.setVisibility(View.VISIBLE);
             ((ChildViewHolder) holder).txtDepositPirce.setText(String.format(context.getString(R.string.deposit),DepositPirce(cartInfoBeanList,((ChildViewHolder) holder).txtDepositPirce)));
             ((ChildViewHolder) holder).txtRetainagePrice.setText(String.format(context.getString(R.string.retainage),RetainagePirce(cartInfoBeanList,((ChildViewHolder) holder).txtRetainagePrice)));
               isPayType(cartInfoBeanList,((ChildViewHolder) holder).txtRetainageType, ((ChildViewHolder) holder).txtDepositType);
         }




                    ((ChildViewHolder) holder).txtSubtotal.setText(String.format(context.getString(R.string.pay_money),FormatUtil.FormatPirce(Double.parseDouble(mydatasBean.getTotal_pay_money()))));
                    link = new Link("￥"+FormatUtil.FormatPirce(Double.parseDouble(mydatasBean.getTotal_pay_money())))
                            .setTextColor(context.getResources().getColor(R.color.main_color))                  // optional, defaults to holo blue
                            .setUnderlined(false)
                            .setBold(false);
                    LinkBuilder.on(((ChildViewHolder) holder).txtSubtotal)
                            .addLink(link)
                            .build(); // creat*/
                    ((ChildViewHolder) holder).txtOrdernum.setText(mydatasBean.getOrder_num());
                    ((ChildViewHolder) holder).txtCreatTime.setText(mydatasBean.getCreat_time());
                    ((ChildViewHolder) holder).txtTotal.setText("￥" + onItemPirce(cartInfoBeanList));
                    ((ChildViewHolder) holder).txtExpressPrice.setText("￥" +FormatUtil.FormatPirce(Double.parseDouble( mydatasBean.getTotal_express_fee())));

                    ((ChildViewHolder) holder).txtCopyNumber.setOnClickListener(view -> {
                        ClipboardManager copy = (ClipboardManager) context
                                .getSystemService(Context.CLIPBOARD_SERVICE);
                        copy.setText(mydatasBean.getOrder_num());
                        ToastUitl.showShort("复制成功");
                    });
                }
                break;

        }
    }

    /**
     * 这个是是否显示商家
     * * @param position
     *
     * @return
     */
    public int isTitleItem(int position) {
        for (int i = 0; i < datasBeans.size(); i++) {
            if (position == getTitlePosition(i)) {
                return i;
            }
        }
        return -1;
    }

    public HashMap<String, Integer> isItem(int position) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < datasBeans.size(); i++) {
            if (position < getTitlePosition(i)) {
                int itemPosition = position - getTitlePosition(i - 1);
                map.put(PARENT_POSITION, i - 1);
                map.put(CHILD_POSITION, itemPosition - 1);
                return map;
            }
            if (i == datasBeans.size() - 1) {
                int itemPosition = position - getTitlePosition(i);
                map.put(PARENT_POSITION, i);
                map.put(CHILD_POSITION, itemPosition - 1);
                return map;
            }
        }
        return null;
    }

    public int getTitlePosition(int position) {
        return position + traverseChildListCount(position);
    }

    private int calGoodsSize() {
        int goodsSize = 0;
        for (MyOrderDetalisEntity.DatasBean shopDataBean : datasBeans) {

            goodsSize += shopDataBean.getOrderItems().size();
        }
        return goodsSize;
    }

    private int traverseChildListCount(int position) {
        int count = 0;
        for (int i = 0; i < position && position <= datasBeans.size(); i++) {
            count += datasBeans.get(i).getOrderItems() == null ? 0 : datasBeans.get(i).getOrderItems().size();
        }
        return count;
    }

    @Override
    public int getItemCount() {
        return datasBeans.size() + calGoodsSize();
    }


    static class GroupViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iamge_business)
        ImageView iamgeBusiness;
        @BindView(R.id.txt_business_name)
        TextView txtBusinessName;
        @BindView(R.id.txt_order_type)
        TextView ordr_type;
        @BindView(R.id.linear_business)
        LinearLayout linearBusiness;

        public GroupViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.liner_addition)
        LinearLayout linerAddition;
        @BindView(R.id.txt_total)
        TextView txtTotal;
        @BindView(R.id.txt_deposit_pirce)
        TextView txtDepositPirce;
        @BindView(R.id.txt_deposit_type)
        TextView txtDepositType;
        @BindView(R.id.liner_deposit)
        LinearLayout linerDeposit;
        @BindView(R.id.txt_retainage_price)
        TextView txtRetainagePrice;
        @BindView(R.id.txt_retainage_type)
        TextView txtRetainageType;
        @BindView(R.id.liner_retainage)
        LinearLayout linerRetainage;
        @BindView(R.id.txt_express_price)
        TextView txtExpressPrice;
        @BindView(R.id.liner_cost)
        LinearLayout linerCost;
        @BindView(R.id.txt_subtotal)
        TextView txtSubtotal;
        @BindView(R.id.txt_ordernum)
        TextView txtOrdernum;
        @BindView(R.id.txt_copy_number)
        TextView txtCopyNumber;
        @BindView(R.id.txt_creat_time)
        TextView txtCreatTime;
        @BindView(R.id.liner_order_msg)
        LinearLayout linerOrderMsg;
        @BindView(R.id.liner_oderitem)
        LinearLayout linerOderitem;


        public ChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}