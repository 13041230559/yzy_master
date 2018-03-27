package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.AddressDefaultEntity;
import com.idolmedia.yzy.entity.AddressEntity;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.PayEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
import com.idolmedia.yzy.ui.adapter.SubmitOrderAdapter;
import com.idolmedia.yzy.ui.mvp.contract.ConfirmOrderContract;
import com.idolmedia.yzy.ui.mvp.model.ConfirmOrderModel;
import com.idolmedia.yzy.ui.mvp.presenter.ConfirmOrderPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.JsonUtils;
import com.mumu.common.utils.ToastUitl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/4 11:58
 * 描述： 提交订单
 */

public class SubmitOrderCartActivity extends BaseActivity<ConfirmOrderPresenter, ConfirmOrderModel> implements ConfirmOrderContract.View, SubmitOrderAdapter.OnItemTotalListener {

    String cartitemid, freightData;
    String addressId;
    List<ReturnFreightEntity.FreightDataBean> freightlist;
    List<ConfirmOrderEntity.ShopDataBean> shopDataBeanList;
    SubmitOrderAdapter submitOrderAdapter;

    boolean isBuy;
    private static final int SELECTNUMBER = 10001;
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_img)
    ImageView rightImg;
    @BindView(R.id.liner_top)
    LinearLayout linerTop;
    @BindView(R.id.txt_addressname)
    TextView Consignee;
    @BindView(R.id.txt_addressphone)
    TextView ConsigneePhone;
    @BindView(R.id.txt_address)
    TextView ConsigneeAddress;
    @BindView(R.id.liner_address)
    LinearLayout linerAddress;
    @BindView(R.id.txt_add_address)
    TextView txtAddAddress;
    @BindView(R.id.relalayout_add_address)
    RelativeLayout relalayoutAddAddress;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txt_allprice)
    TextView txtAllprice;
    @BindView(R.id.txt_go)
    TextView txtGo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.ok_order));
        cartitemid = getIntent().getStringExtra("cartitemid");
        freightData = getIntent().getStringExtra("freightData");
        Log.e("cartitemid", cartitemid);
        Log.e("freightData", freightData);


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("cartitems_ids", cartitemid);
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.ConfirmOrder_p(hashMap);


    }


    public  boolean isAddtion(List<ConfirmOrderEntity.ShopDataBean> confirmlist){
        List<ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean> listbean;
        for(int i=0;i<confirmlist.size();i++){

            listbean = (List<ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean>) JsonUtils.fromJson(confirmlist.get(i).getShops().get(0).getAddition(), new TypeToken<List<ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean>>() {
            }.getType());
            for(int s=0;s<listbean.size();s++){
                if(TextUtils.isEmpty(listbean.get(s).getAddition_value())){
                    return  false;
                }
            }
        }
        // submitOrderAdapter.getGroups()
        return true;
    }
    private String ToJson(List<ConfirmOrderEntity.ShopDataBean> confirmlist) {

        JSONArray jsonArray = new JSONArray();
        try {
            //店家
            for(ConfirmOrderEntity.ShopDataBean shopDataBean:confirmlist){
                JSONObject  jsonFromid=new JSONObject();
                JSONArray cartInfojsonArray = new JSONArray();
                JSONArray  AdditionBeanArray=new JSONArray();

                //商品
                for(ConfirmOrderEntity.ShopDataBean.ShopsBean shops:shopDataBean.getShops()){


                    //购物车分类
                    for(ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean cartInfoBean:shops.getCartInfo()){


                        JSONObject cartInfo =new JSONObject();
                        cartInfo.put("cartitems_id",cartInfoBean.getCartitems_id());
                        cartInfojsonArray.put(cartInfo);


                    }


                }

                //商品
                for(ConfirmOrderEntity.ShopDataBean.ShopsBean shops:shopDataBean.getShops()){
                    JSONObject  shopsJsonObject=new JSONObject();
                    JSONArray additionArray = new JSONArray();
                    shopsJsonObject.put("shopcommon_id",shops.getShopcommon_id());

                    //商品附加信息
                    for(ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean additionBean:shops.getAdditionBeanList()){

                        JSONObject addition =new JSONObject();
                        addition.put("addition_id",additionBean.getAddition_id());
                        addition.put("addition_key",additionBean.getAddition_key());
                        addition.put("addition_value",additionBean.getAddition_value());
                        addition.put("field",additionBean.getField());
                        additionArray.put(addition);

                    }
                    shopsJsonObject.put("data",additionArray);
                    AdditionBeanArray.put(shopsJsonObject);
                }

                jsonFromid.put("additionData",AdditionBeanArray);
                jsonFromid.put("cartItems",cartInfojsonArray);
                jsonFromid.put("from_id",shopDataBean.getFrom_id());
                jsonArray.put(jsonFromid);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("toJson", jsonArray.toString());
        return  jsonArray.toString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.txt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_go:
                if (TextUtils.isEmpty(addressId)) {
                    ToastUitl.show("请去添加收货地址", 1000);
                    return;
                }  else if (!isAddtion(submitOrderAdapter.getGroups())) {
                    ToastUitl.show("请填写附加信息", 1000);
                    return;
                } else {
                    HashMap<String, Object> hashMap1 = new HashMap<>();
                    hashMap1.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap1.put("address_id", addressId);
                    hashMap1.put("app_from", "android");
                    hashMap1.put("FKEY", PutUtils.FKEY());
                    hashMap1.put("submitData",ToJson(submitOrderAdapter.getGroups()));
                    mPresenter.SubmitOrder_p(hashMap1);
                }
                break;
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showLong(msg);
    }

    private void DefaultAddress(int areaid,int addressid, String name, String Phone ,String city_name,String address) {
        addressId = String.valueOf(addressid);
        Consignee.setText(name);
        ConsigneePhone.setText(Phone);
        ConsigneeAddress.setText(city_name+address);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("freightData", freightData);
        hashMap.put("area_id", areaid);
        mPresenter.Freight_p(hashMap);

    }

    /**
     * @param t
     * @ 计算运费
     */
    @Override
    public void Freight_v(ReturnFreightEntity t) {
        if (t.getResultCode() == 1) {
            freightlist = t.getFreightData();
            if (submitOrderAdapter != null) {
                submitOrderAdapter.setFreightData(t.getFreightData());
            }
        }

    }

    /**
     * 默认地址
     *
     * @param t
     */
    @Override
    public void DefaultAddress_v(AddressDefaultEntity t) {
        if(t.getResultCode()==1){
            if(!TextUtils.isEmpty(t.getAddressData().getPhone())){
                linerAddress.setVisibility(View.VISIBLE);
                relalayoutAddAddress.setVisibility(View.GONE);
                DefaultAddress(t.getAddressData().getArea_id(), t.getAddressData().getAddress_id(), t.getAddressData().getConsignee(), t.getAddressData().getPhone(), t.getAddressData().getCity_name(),t.getAddressData().getDetail_address());
            }else {
                linerAddress.setVisibility(View.GONE);
                relalayoutAddAddress.setVisibility(View.VISIBLE);
            }
            linerAddress.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putBoolean("select", true);
                startActivityForResult(AddressListActivity.class, bundle, SELECTNUMBER);
            });
            txtAddAddress.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putBoolean("select", true);
                startActivityForResult(AddressListActivity.class, bundle, SELECTNUMBER);
            });

        }

    }

    /**
     * 确认订单
     *
     * @param t
     */
    @Override
    public void ConfirmOrder_v(ConfirmOrderEntity t) {
        if (t.getResultCode() == 1) {
            shopDataBeanList = t.getShopData();
            HashMap hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            mPresenter.DefaultAddress_p(hashMap);
            submitOrderAdapter = new SubmitOrderAdapter(this);
            submitOrderAdapter.setOnItemTotalListener(this);
            recyclerView.setAdapter(submitOrderAdapter);
            recyclerView.setNestedScrollingEnabled(false);
            submitOrderAdapter.setShopData(t.getShopData());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }


   /* *//**
     * 立即购买备注信息
     *
     * @param t
     *//*
    @Override
    public void BuyAdditional_v(String t) {

    }

    *//**
     * 立即购买计算运费
     *
     * @param t
     *//*
    @Override
    public void BuyFreight_v(ReturnFreightEntity t) {

    }

    *//**
     * 立即购买提交订单
     *
     * @param t
     *//*
    @Override
    public void BuyOrder_v(String t) {

    }
*/
    /**
     * 提交订单
     *
     * @param t
     */
    @Override
    public void SubmitOrder_v(PayEntity t) {

        if(t.getResultCode()==1){
            Bundle bundle=new Bundle();
            bundle.putString("create_time", t.getOrderData().getCreate_time());
            bundle.putString("totalMoney",t.getOrderData().getTotalMoney());
            bundle.putString("order_num",t.getOrderData().getOrder_num());
            bundle.putString("from_type",t.getOrderData().getFrom_type());
            android.util.Log.e("from_type",t.getOrderData().getFrom_type());
            startActivity(PayActivity.class,bundle);
            finish();
        }else {
            ToastUitl.show(t.getMsg(), 3000);
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECTNUMBER:
                    AddressEntity.AddressDataBean addressEntity = (AddressEntity.AddressDataBean) data.getParcelableExtra("address");
                    if(addressEntity==null){
                        relalayoutAddAddress.setVisibility(View.VISIBLE);
                        linerAddress.setVisibility(View.GONE);
                    }else {
                        relalayoutAddAddress.setVisibility(View.GONE);
                        linerAddress.setVisibility(View.VISIBLE);
                        DefaultAddress(addressEntity.getArea_id(), addressEntity.getAddress_id(), addressEntity.getConsignee(), addressEntity.getPhone(),addressEntity.getCity_name(), addressEntity.getDetail_address());
                    }
                    break;

            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void RefreshToTal(String total) {

        txtAllprice.setText(String.format(getString(R.string.total), total));
        Link     link = new Link("￥"+total)
                .setTextColor(getResources().getColor(R.color.main_color))
                .setUnderlined(false)
                .setBold(false);// optional, defaults to holo blue
        LinkBuilder.on(txtAllprice)
                .addLink(link)
                .build(); // creat
    }



}
