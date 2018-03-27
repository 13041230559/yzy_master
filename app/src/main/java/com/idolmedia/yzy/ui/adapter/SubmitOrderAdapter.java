package com.idolmedia.yzy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
import com.idolmedia.yzy.ui.activity.ServiceAgreementActivity;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/29 11:50
 * 描述：确认订单
 */

public class SubmitOrderAdapter extends RecyclerView.Adapter {
    private final int TYPE_TITLE = 0;//店铺头部布局
    private final int TYPE_ITEM = 1;//正常商品布局
    private static final String PARENT_POSITION = "parent_position";//标记存放父节点
    private static final String CHILD_POSITION = "child_position";//标记存放子节点

    List<ConfirmOrderEntity.ShopDataBean> shopData;

    Context context;
    Gson gson = new Gson();

    /**
     * 构造方法里面初始化一个空的数据列表 要想设置数据 需要通过方法 {@link #setShopData(List)}
     */
    public SubmitOrderAdapter(Context context) {
        this.shopData = new ArrayList<>();
        this.context = context;

    }

    public List<ConfirmOrderEntity.ShopDataBean> getGroups() {

        return shopData;
    }

    List<ReturnFreightEntity.FreightDataBean> freightDataBeanList;

    //单价
    public String subtotal(List<ConfirmOrderEntity.ShopDataBean.ShopsBean> shop,int parentPosition, int childPosition) {
        double total = 0;
        double freightTotal = 0;
           for(int s=0;s<shop.get(childPosition).getCartInfo().size();s++){
               total+= shop.get(childPosition).getCartInfo().get(s).getUnint_price()*  shop.get(childPosition).getCartInfo().get(s).getQuantity_count();

           }
        if (freightDataBeanList != null && freightDataBeanList.size() > 0) {
            freightTotal=freightDataBeanList.get(parentPosition).getFreight_money();

    }

        return FormatUtil.FormatPirce(total+freightTotal);
    }

    public void setFreightData(List<ReturnFreightEntity.FreightDataBean> freigth) {
        this.freightDataBeanList = freigth;
        if (mOnItemTotalListener != null) {
            mOnItemTotalListener.RefreshToTal(total());
        }
        notifyDataSetChanged();
    }

    //总价
    public String total() {
        double total = 0;
        double freightTotal = 0;
        for (int i = 0; i < shopData.size(); i++) {
            Iterator<ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean> cartInfoIterator;
            Iterator<ConfirmOrderEntity.ShopDataBean.ShopsBean> shopIterator;
            for (shopIterator = shopData.get(i).getShops().iterator(); shopIterator.hasNext(); ) {
                ConfirmOrderEntity.ShopDataBean.ShopsBean shopsBean = shopIterator.next();
                for (cartInfoIterator = shopsBean.getCartInfo().iterator(); cartInfoIterator.hasNext(); ) {
                    ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean cartInfoBean = cartInfoIterator.next();
                    total += cartInfoBean.getUnint_price() * cartInfoBean.getQuantity_count();
                }

            }

        }

        if (freightDataBeanList != null && freightDataBeanList.size() > 0) {
            for (int i = 0; i < freightDataBeanList.size(); i++) {
                freightTotal += freightDataBeanList.get(i).getFreight_money();
            }
            // freightTotal += freightDataBeanList.get(groupPosition).getFreight_money();
        }
        return FormatUtil.FormatPirce(total + freightTotal);
    }

    private int parentItemSize(List<ConfirmOrderEntity.ShopDataBean.ShopsBean> shop, int childPosition){
        int itemSize=0;
        for(ConfirmOrderEntity.ShopDataBean.ShopsBean shopsBean:shop){

            for(ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean cartInfoBean: shopsBean.getCartInfo()){
                itemSize+=cartInfoBean.getQuantity_count();
            }
        }
        return itemSize;
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
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_conrirm_recyclerview, parent, false);
                return new ChildViewHolder(view1);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                //绑定头部数据
                int titlePosition = isTitleItem(position);
                ConfirmOrderEntity.ShopDataBean shopDataBean = shopData.get(titlePosition);
                ((GroupViewHolder) holder).txtBusinessName.setText(shopDataBean.getFrom_name());
                Glide.with(context).load(shopDataBean.getHead_img()).apply(new RequestOptions().circleCrop()).into(((GroupViewHolder) holder).iamgeBusiness);

                //设置头部数据
                break;
            case TYPE_ITEM:
                //绑定正常布局item数据
                HashMap<String, Integer> map = isItem(position);
                if (map != null) {
                    int parentPosition = map.get(PARENT_POSITION);
                    int childPosition = map.get(CHILD_POSITION);
                    Log.e("parentPosition", parentPosition + "   " + childPosition);
                    ConfirmOrderEntity.ShopDataBean shop = shopData.get(parentPosition);
                    ConfirmOrderEntity.ShopDataBean.ShopsBean shopsBean = shop.getShops().get(childPosition);

                    List<ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean> cartInfoBeanList = new ArrayList<>();
                    for (ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean cartInfoBean : shopsBean.getCartInfo()) {
                        cartInfoBean.setShop_label(shopsBean.getShop_label());
                        cartInfoBean.setShop_name(shopsBean.getShop_name());
                        cartInfoBeanList.add(cartInfoBean);
                    }
                    if (freightDataBeanList != null) {
                        if (childPosition + 1 == shop.getShops().size()) {
                            ((ChildViewHolder) holder).linerKdf.setVisibility(View.VISIBLE);
                            ((ChildViewHolder) holder).txtKdf.setText("￥" + freightDataBeanList.get(parentPosition).getFreight_money());

                        }
                    }

                    if (childPosition + 1 == shop.getShops().size()) {
                        ((ChildViewHolder) holder).txtSubtotal.setVisibility(View.VISIBLE);
                        ((ChildViewHolder) holder).txtSubtotal.setText(String.format(context.getString(R.string.order_group_itempirce), parentItemSize(shop.getShops(),childPosition), subtotal(shop.getShops(),parentPosition, childPosition)));
                      Link  link = new Link("￥"+subtotal(shop.getShops(),parentPosition, childPosition))
                                .setTextColor(context.getResources().getColor(R.color.fc4f4f))                  // optional, defaults to holo blue
                                .setUnderlined(false)                                       // optional, defaults to true
                                .setBold(false);
                        LinkBuilder.on(((ChildViewHolder) holder).txtSubtotal)
                                .addLink(link)
                                .build(); // creat
                    }else {
                        ((ChildViewHolder) holder).txtSubtotal.setVisibility(View.GONE);
                    }
                    if (mOnItemTotalListener != null) {
                        mOnItemTotalListener.RefreshToTal(total());
                    }
                    Log.e("shopsBean", shop.getShops().size() + "====" + childPosition);

                    ((ChildViewHolder) holder).linerRemarks.removeAllViews();
                    ConfirmOrderItemAdapter confirmOrderItemAdapter = new ConfirmOrderItemAdapter();
                    ((ChildViewHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    ((ChildViewHolder) holder).recyclerView.setAdapter(confirmOrderItemAdapter);
                    confirmOrderItemAdapter.setNewData(cartInfoBeanList);
                    if (!TextUtils.isEmpty(shopsBean.getAddition())) {
                        shopsBean.setAdditionBeanList((List<ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean>) JsonUtils.fromJson(shopsBean.getAddition(), new TypeToken<List<ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean>>() {
                        }.getType()));
                        for (ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean additionBean : shopsBean.getAdditionBeanList()) {
                            if (shopsBean == null) {
                                ((ChildViewHolder) holder).linerAddition.setVisibility(View.GONE);
                            } else {
                                ((ChildViewHolder) holder).linerAddition.setVisibility(View.VISIBLE);
                                View remarks = LayoutInflater.from(context).inflate(R.layout.remarks_item, (ViewGroup) ((ChildViewHolder) holder).itemView.findViewById(android.R.id.content), false);
                                TextView txt_type = (TextView) remarks.findViewById(R.id.txt_type);
                                EditText txt_content = (EditText) remarks.findViewById(R.id.txt_content);
                                txt_type.setText(additionBean.getField());
                                txt_content.setHint(additionBean.getPlacehold());
                                ((ChildViewHolder) holder).linerRemarks.addView(remarks);
                                txt_content.setText(additionBean.getAddition_value());
                                txt_content.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                    }
                                    @Override
                                    public void onTextChanged(CharSequence charSequence, int t, int i1, int i2) {
                                        additionBean.setAddition_value(charSequence.toString().trim());
                                        // Log.e("additionBean1",additionBean.getValue());
                                        shopsBean.setAddition(gson.toJson(shopsBean.getAdditionBeanList()));
                                        //   Log.e("getAddition",group.getShops().get(0).getAddition()+"");
                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {
                                    }
                                });
                            }
                        }
                    } else {
                        ((ChildViewHolder) holder).linerAddition.setVisibility(View.GONE);
                    }


                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return shopData.size() + calGoodsSize();
    }

    @Override
    public int getItemViewType(int position) {
        if (isTitleItem(position) != -1) return TYPE_TITLE;
        return TYPE_ITEM;
    }

    private int calGoodsSize() {
        int goodsSize = 0;
        for (ConfirmOrderEntity.ShopDataBean shopDataBean : shopData) {

            goodsSize += shopDataBean.getShops().size();
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
        for (int i = 0; i < shopData.size(); i++) {
            if (position == getTitlePosition(i)) {
                return i;
            }
        }
        return -1;
    }

    public HashMap<String, Integer> isItem(int position) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < shopData.size(); i++) {
            if (position < getTitlePosition(i)) {
                int itemPosition = position - getTitlePosition(i - 1);
                map.put(PARENT_POSITION, i - 1);
                map.put(CHILD_POSITION, itemPosition - 1);
                return map;
            }
            if (i == shopData.size() - 1) {
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
        for (int i = 0; i < position && position <= shopData.size(); i++) {
            count += shopData.get(i).getShops() == null ? 0 : shopData.get(i).getShops().size();
        }
        return count;
    }


    public void setShopData(List<ConfirmOrderEntity.ShopDataBean> shopData) {
        if (shopData != null) {
            this.shopData.addAll(shopData);
            notifyDataSetChanged();
        }
    }
/*    public void setAddtion(){

        for(int i=0;i<shopData.size();i++){


            for(int s=0;s<shopData.get(i).getShops().size();s++){

                shopData.get(i).getShops().get(s).getAddition();
            }
        }


    }*/


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
        @BindView(R.id.liner_remarks)
        LinearLayout linerRemarks;
        @BindView(R.id.liner_addition)
        LinearLayout linerAddition;
        @BindView(R.id.txt_kdf)
        TextView txtKdf;
        @BindView(R.id.liner_kdf)
        LinearLayout linerKdf;
        @BindView(R.id.txt_subtotal)
        TextView txtSubtotal;

        public ChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private OnItemTotalListener mOnItemTotalListener = null;

    //定义如下方法给rc设置点击事件
    public interface OnItemTotalListener {
        void RefreshToTal(String Total);

        //    void onItemAdditionBean(List<ConfirmOrderEntity.ShopDataBean> list);
    }

    public void setOnItemTotalListener(OnItemTotalListener listener) {
        this.mOnItemTotalListener = listener;
    }

}
