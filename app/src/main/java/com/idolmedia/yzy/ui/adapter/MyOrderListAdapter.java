package com.idolmedia.yzy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.reflect.TypeToken;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MyOrderEntity;
import com.mumu.common.utils.FormatDate;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/8 12:21
 * 描述： 我的订单列表
 */
/*

*/
public class MyOrderListAdapter extends RecyclerView.Adapter {

    private final int TYPE_TITLE = 0;//店铺头部布局
    private final int TYPE_ITEM = 1;//正常商品布局
    private static final String PARENT_POSITION = "parent_position";//标记存放父节点
    private static final String CHILD_POSITION = "child_position";//标记存放子节点
    List<MyOrderEntity.DatasBean> datasBeans;

    Context context;
    String[] type_name = new String[]{"toPay", "waitingDelivery", "waitingGoods", "waitingApprise", "orderOver", "refunded", "cancel", "invalid", "waitingFinalPay"};

    /**
     * 构造方法里面初始化一个空的数据列表 要想设置数据 需要通过方法 {@link #setdatasBeans(List)}
     */
    public MyOrderListAdapter(Context context) {
        this.datasBeans = new ArrayList<>();
        this.context = context;

    }

    public void setdatasBeans(List<MyOrderEntity.DatasBean> datasBeans) {
        if (datasBeans != null) {
            this.datasBeans.addAll(datasBeans);
            notifyDataSetChanged();
        }
    }


    public void setNewBeans(List<MyOrderEntity.DatasBean> datasBeans) {
        if (datasBeans != null) {
            this.datasBeans = datasBeans;
            notifyDataSetChanged();
        }
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
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_order_recyclerview, parent, false);
                return new ChildViewHolder(view1);
        }
        return null;
    }

    private int orderItemNuber(int parentPosition) {
        int count = 0;
        MyOrderEntity.DatasBean mydatasBean = datasBeans.get(parentPosition);
        for (MyOrderEntity.DatasBean.OrderItemsBean orderItemsBean : mydatasBean.getOrderItems()) {

            for (MyOrderEntity.DatasBean.OrderItemsBean.CatalogItemsBean catalogItemsBean : orderItemsBean.getCatalogItems()) {

                count += catalogItemsBean.getShop_count();
            }
        }
        return count;
    }

    private String orderType(String type) {

        String[] title_name = new String[]{"待支付", "待发货", "待收货", "待评价", "已完成", "已退款", "订单取消", "订单超时", "待付尾款"};
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                //绑定头部数据
                int titlePosition = isTitleItem(position);
                MyOrderEntity.DatasBean datasBean = datasBeans.get(titlePosition);
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
                //设置头部数据
                break;
            case TYPE_ITEM:
                //绑定正常布局item数据
                HashMap<String, Integer> map = isItem(position);
                if (map != null) {
                    int parentPosition = map.get(PARENT_POSITION);
                    int childPosition = map.get(CHILD_POSITION);
                    Log.e("parentPosition", parentPosition + "   " + childPosition);
                    MyOrderEntity.DatasBean mydatasBean = datasBeans.get(parentPosition);
                    MyOrderEntity.DatasBean.OrderItemsBean orderItemsBean = mydatasBean.getOrderItems().get(childPosition);
                    ((ChildViewHolder) holder).linerAddition.removeAllViews();

                    List<MyOrderEntity.DatasBean.OrderItemsBean.CatalogItemsBean> cartInfoBeanList = new ArrayList<>();
                    for (MyOrderEntity.DatasBean.OrderItemsBean.CatalogItemsBean catalogItemsBean : orderItemsBean.getCatalogItems()) {
                        catalogItemsBean.setShop_name(orderItemsBean.getShop_name());
                        catalogItemsBean.setShop_label(orderItemsBean.getShop_label());
                        catalogItemsBean.setOrder_status(mydatasBean.getOrder_status());
                        catalogItemsBean.setNowTime(mydatasBean.getNowTime());
                        catalogItemsBean.setFinal_end_time(mydatasBean.getFinal_end_time());
                        catalogItemsBean.setFinal_start_time(mydatasBean.getFinal_start_time());
                        cartInfoBeanList.add(catalogItemsBean);
                    }
                    ((ChildViewHolder) holder).txtRetainage.setVisibility(View.GONE);
                    ((ChildViewHolder) holder).txtCanceloder.setVisibility(View.GONE);
                    ((ChildViewHolder) holder).txtDeloder.setVisibility(View.GONE);
                    ((ChildViewHolder) holder).txtPay.setVisibility(View.GONE);
                    ((ChildViewHolder) holder).txtRemind.setVisibility(View.GONE);
                    ((ChildViewHolder) holder).txtLogistics.setVisibility(View.GONE);
                    ((ChildViewHolder) holder).txtGoodreceipt.setVisibility(View.GONE);
                    ((ChildViewHolder) holder).txtGoEvaluate.setVisibility(View.GONE);
                    ((ChildViewHolder) holder).txtCloseOrder.setVisibility(View.GONE);
                    if (childPosition + 1 == mydatasBean.getOrderItems().size()) {
                        ((ChildViewHolder) holder).linerTotal.setVisibility(View.VISIBLE);
                        ((ChildViewHolder) holder).linerOrderset.setVisibility(View.VISIBLE);
                        ((ChildViewHolder) holder).txtSubtotal.setText(String.format(context.getString(R.string.total), FormatUtil.FormatPirce(mydatasBean.getTotal_pay_money())));
                        ((ChildViewHolder) holder).txtFreight.setText(String.format(context.getString(R.string.freight),  FormatUtil.FormatPirce(mydatasBean.getTotal_express_fee())));
                        ((ChildViewHolder) holder).txtShopNumber.setText(String.format(context.getString(R.string.all_number), orderItemNuber(parentPosition)));
                        if (mydatasBean.getOrder_status().equals(type_name[0])) {
                            // ((ChildViewHolder) holder).countdownView.setVisibility(View.VISIBLE);
                            ((ChildViewHolder) holder).txtCanceloder.setVisibility(View.VISIBLE);
                            ((ChildViewHolder) holder).txtPay.setVisibility(View.VISIBLE);
                        } else if (mydatasBean.getOrder_status().equals(type_name[1])) {
                            ((ChildViewHolder) holder).txtRemind.setVisibility(View.VISIBLE);
                        } else if (mydatasBean.getOrder_status().equals(type_name[2])) {
                            ((ChildViewHolder) holder).txtGoodreceipt.setVisibility(View.VISIBLE);
                            ((ChildViewHolder) holder).txtLogistics.setVisibility(View.VISIBLE);
                        } else if (mydatasBean.getOrder_status().equals(type_name[3])) {
                            ((ChildViewHolder) holder).txtGoEvaluate.setVisibility(View.VISIBLE);
                        }else if(mydatasBean.getOrder_status().equals(type_name[4])){
                            ((ChildViewHolder) holder).txtLogistics.setVisibility(View.VISIBLE);

                        } else if (mydatasBean.getOrder_status().equals(type_name[8])) {
                            ((ChildViewHolder) holder).txtRetainage.setVisibility(View.VISIBLE);
                            if(!TextUtils.isEmpty(mydatasBean.getFinal_end_time())){
                                long endtime = FormatDate.StrToDate(mydatasBean.getFinal_end_time()).getTime();
                                long thistime = FormatDate.StrToDate(mydatasBean.getNowTime()).getTime();
                                long startime = FormatDate.StrToDate(mydatasBean.getFinal_start_time()).getTime();
                                if((thistime<startime)||(thistime>endtime)){
                                    if(thistime<startime){
                                        ((ChildViewHolder) holder).txtRetainage.setText(context.getResources().getString(R.string.payretainage_unopened));
                                    }else if(thistime>endtime){
                                        ((ChildViewHolder) holder).txtRetainage.setText(context.getResources().getString(R.string.payretainage_over));
                                    }
                                    ((ChildViewHolder) holder).txtRetainage.setBackground(context.getResources().getDrawable(R.drawable.order_btnitem));
                                    ((ChildViewHolder) holder).txtRetainage.setTextColor(context.getResources().getColor(R.color.c3));
                                }else if(thistime<endtime&&thistime>startime){
                                    ((ChildViewHolder) holder).txtRetainage.setBackground(context.getResources().getDrawable(R.drawable.order_btnitem1));((ChildViewHolder) holder).txtPay.setFocusable(true);
                                    ((ChildViewHolder) holder).txtRetainage.setTextColor(context.getResources().getColor(R.color.ff8282));
                                }
                            }else {
                                ((ChildViewHolder) holder).txtRetainage.setBackground(context.getResources().getDrawable(R.drawable.order_btnitem));
                                ((ChildViewHolder) holder).txtRetainage.setTextColor(context.getResources().getColor(R.color.c3));
                                ((ChildViewHolder) holder).txtRetainage.setText(context.getResources().getString(R.string.payretainage_unopened));
                            }
                            ((ChildViewHolder) holder).txtRetainage.setOnClickListener(view -> {
                                if (mOnItemClickListener != null) {
                                    int type=1;
                                    if(!TextUtils.isEmpty(mydatasBean.getFinal_end_time())){
                                    long endtime = FormatDate.StrToDate(mydatasBean.getFinal_end_time()).getTime();
                                    long thistime = FormatDate.StrToDate(mydatasBean.getNowTime()).getTime();
                                    long startime = FormatDate.StrToDate(mydatasBean.getFinal_start_time()).getTime();
                                        if((thistime<startime)||(thistime>endtime)){
                                            if(thistime<startime){
                                                type=1;
                                            }else if(thistime>endtime) {
                                                type=2;
                                            }
                                        }else if(thistime<endtime&&thistime>startime){
                                            type=0;
                                        }
                                    }else {
                                        type=1;
                                    }
                                    mOnItemClickListener.onItemRetainagePayClick(mydatasBean.getOrder_num(),orderItemsBean.getShop_type(),orderItemsBean.getShopcommon_id(),type);
                                }
                            });
                        } else if (mydatasBean.getOrder_status().equals(type_name[6])||mydatasBean.getOrder_status().equals(type_name[7])) {
                            //((ChildViewHolder) holder).txtCloseOrder.setVisibility(View.VISIBLE);
                            ((ChildViewHolder) holder).txtDeloder.setVisibility(View.VISIBLE);
                        }else if (mydatasBean.getOrder_status().equals(type_name[4])) {
                            ((ChildViewHolder) holder).txtLogistics.setVisibility(View.VISIBLE);
                        }

                        ((ChildViewHolder) holder).itemView.setOnClickListener(view -> {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick(position, mydatasBean.getOrder_num());
                            }
                        });
                        ((ChildViewHolder) holder).txtPay.setOnClickListener(view -> {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemPayClick(position,String.valueOf(mydatasBean.getOrder_num()),mydatasBean.getOrder_status().equals(type_name[8]),orderItemsBean.getShopcommon_id(),orderItemsBean.getShop_type(),mydatasBean.getTotal_pay_money(),mydatasBean.getCreate_time(),orderItemsBean.getShop_type());
                            }
                        });


                        ((ChildViewHolder) holder).txtCanceloder.setOnClickListener(view -> {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemCanceloderClick(position, mydatasBean.getOrder_num());
                            }
                        });

                        ((ChildViewHolder) holder).txtGoodreceipt.setOnClickListener(view -> {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemGoodreceiptClick(position, mydatasBean.getOrder_num());
                            }
                        });
                        ((ChildViewHolder) holder).txtLogistics.setOnClickListener(view -> {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemLogisticsClick(position, mydatasBean.getOrder_num(),mydatasBean.getOrderItems().get(0).getCatalogItems().get(0).getCatalog_img(),mydatasBean.getShippercode(),mydatasBean.getExpress_no());
                            }
                        });
                        ((ChildViewHolder) holder).txtRemind.setOnClickListener(view -> {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemRemindClick(position, mydatasBean.getOrder_num());
                            }
                        });
                        ((ChildViewHolder) holder).txtDeloder.setOnClickListener(view -> {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemDelClick(position, mydatasBean.getOrder_num());
                            }
                        });
                        ((ChildViewHolder) holder).txtGoEvaluate.setOnClickListener(view -> {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemGoEvaluateClick(position, mydatasBean.getOrder_num(),mydatasBean.getOrderItems().get(0).getCatalogItems().get(0).getCatalog_img(),mydatasBean.getOrderItems().get(0).getShop_name(),orderItemsBean.getShopcommon_id());
                            }
                        });

                    } else {
                        ((ChildViewHolder) holder).linerTotal.setVisibility(View.GONE);
                        ((ChildViewHolder) holder).linerOrderset.setVisibility(View.GONE);
                    }

                    MyOrderItemAdapter orderItemAdapter = new MyOrderItemAdapter();
                    ((ChildViewHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    ((ChildViewHolder) holder).recyclerView.setAdapter(orderItemAdapter);
                    orderItemAdapter.setNewData(cartInfoBeanList);

                    orderItemAdapter.setOnItemClickListener((adapter, view, position1) -> {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(position1, mydatasBean.getOrder_num());
                        }
                    });
                    if (!TextUtils.isEmpty(orderItemsBean.getContent())) {
                        orderItemsBean.setAdditionBeanList((List<MyOrderEntity.DatasBean.OrderItemsBean.AdditionBean>) JsonUtils.fromJson(orderItemsBean.getContent(), new TypeToken<List<MyOrderEntity.DatasBean.OrderItemsBean.AdditionBean>>() {
                        }.getType()));
                        for (MyOrderEntity.DatasBean.OrderItemsBean.AdditionBean additionBean : orderItemsBean.getAdditionBeanList()) {
                            if (additionBean != null) {
                                ((ChildViewHolder) holder).linerAddition.setVisibility(View.VISIBLE);
                                View remarks = LayoutInflater.from(context).inflate(R.layout.linearlayout_addtion_msg, (ViewGroup) ((ChildViewHolder) holder).itemView.findViewById(android.R.id.content), false);
                                TextView txt_type = (TextView) remarks.findViewById(R.id.txt_type);
                                TextView txt_content = (TextView) remarks.findViewById(R.id.txt_content);
                                txt_type.setText(additionBean.getField()+":");
                                txt_content.setText(additionBean.getAddition_value());
                                ((ChildViewHolder) holder).linerAddition.addView(remarks);

                            }

                        }
                    }


                    //设置头部数据

                    break;
                }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isTitleItem(position) != -1) return TYPE_TITLE;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return datasBeans.size() + calGoodsSize();
    }


    private int calGoodsSize() {
        int goodsSize = 0;
        for (MyOrderEntity.DatasBean shopDataBean : datasBeans) {

            goodsSize += shopDataBean.getOrderItems().size();
        }
        return goodsSize;
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

    private int traverseChildListCount(int position) {
        int count = 0;
        for (int i = 0; i < position && position <= datasBeans.size(); i++) {
            count += datasBeans.get(i).getOrderItems() == null ? 0 : datasBeans.get(i).getOrderItems().size();
        }
        return count;
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
        @BindView(R.id.txt_subtotal)
        TextView txtSubtotal;
        @BindView(R.id.txt_freight)
        TextView txtFreight;
        @BindView(R.id.txt_shop_number)
        TextView txtShopNumber;
        @BindView(R.id.liner_total)
        LinearLayout linerTotal;
        @BindView(R.id.liner_orderset)
        LinearLayout linerOrderset;
        @BindView(R.id.liner_oderitem)
        LinearLayout linerOderitem;

        @BindView(R.id.countdownView)
        CountdownView countdownView;
        @BindView(R.id.txt_canceloder)
        TextView txtCanceloder;
        @BindView(R.id.txt_deloder)
        TextView txtDeloder;
        @BindView(R.id.txt_cancel)
        TextView txtCancel;
        @BindView(R.id.txt_pay)
        TextView txtPay;
        @BindView(R.id.txt_remind)
        TextView txtRemind;
        @BindView(R.id.txt_goodreceipt)
        TextView txtGoodreceipt;
        @BindView(R.id.txt_logistics)
        TextView txtLogistics;
        @BindView(R.id.txt_retainage)
        TextView txtRetainage;
        @BindView(R.id.txt_waitingApprise)
        TextView txtGoEvaluate;
        @BindView(R.id.txt_close_order)
        TextView txtCloseOrder;
        public ChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    private OnItemClickListener mOnItemClickListener = null;

    //定义如下方法给rc设置点击事件
    public interface OnItemClickListener {
        void onItemClick(int position, String ordernum);

        void onItemPayClick(int position, String ordernum,boolean isDeposit,int Shopcommon_id,String shop_type,double paymoney,String create_time,String form_type);

        void onItemCanceloderClick(int position, String ordernum);

        void onItemGoodreceiptClick(int position, String ordernum);
        void onItemLogisticsClick(int position, String ordernum,String iamge,String  shippercode,String express_no);
        void onItemRemindClick(int position, String ordernum);
        void onItemDelClick(int position, String ordernum);
        void onItemGoEvaluateClick(int position, String ordernum,String iamge,String name,int Shopcommon_id);

        void onItemRetainagePayClick(String ordernum,String shop_type,int shopcommon_id,int type);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
