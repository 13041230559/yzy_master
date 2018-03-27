package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.idolmedia.yzy.entity.MessageEvent;
import com.idolmedia.yzy.entity.PayBuyEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
import com.idolmedia.yzy.ui.adapter.SubmitOrderAdapter;
import com.idolmedia.yzy.ui.mvp.contract.ConfirmOrderBuyContract;
import com.idolmedia.yzy.ui.mvp.model.ConfirmOrderBuyModel;
import com.idolmedia.yzy.ui.mvp.presenter.ConfirmOrderBuyPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.JsonUtils;
import com.mumu.common.utils.ToastUitl;
import com.umeng.socialize.utils.Log;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/4 20:28
 * 描述：
 */

public class SubmitOrderBuyactivity extends BaseActivity<ConfirmOrderBuyPresenter, ConfirmOrderBuyModel> implements ConfirmOrderBuyContract.View, SubmitOrderAdapter.OnItemTotalListener {


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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txt_allprice)
    TextView txtAllprice;
    @BindView(R.id.txt_go)
    TextView txtGo;
    ConfirmOrderEntity confirmOrderEntity;
    SubmitOrderAdapter submitOrderAdapter;
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
    String addressId;
    boolean isRetainage,isFreight;
    private static final int SELECTNUMBER = 10001;
    String order_no;
    @Override
    public int getLayoutId() {
        return R.layout.activity_confirmorder_buy;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.ok_order));
        confirmOrderEntity = (ConfirmOrderEntity) getIntent().getParcelableExtra("buy");
        order_no=getIntent().getStringExtra("ordernum");
        isRetainage = getIntent().getBooleanExtra("isRetainage", false);
         isFreight= getIntent().getBooleanExtra("isFreight", true);
        Log.e("confirmOrderEntity", confirmOrderEntity.getShopData().get(0).getFrom_name());
        submitOrderAdapter = new SubmitOrderAdapter(this);
        // submitOrderAdapter.setOnItemTotalListener(this);
        recyclerView.setAdapter(submitOrderAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        submitOrderAdapter.setShopData(confirmOrderEntity.getShopData());
        submitOrderAdapter.setOnItemTotalListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Drawable drawable = getResources().getDrawable(R.mipmap.icon_adress_add_yzy);
        //设置drawable的位置,宽高
        drawable.setBounds(0, 0, 70, 70);
        txtAddAddress.setCompoundDrawables(null, drawable, null, null);

        //获取默认地址
        HashMap hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.DefaultAddress_p(hashMap);

        //获取商品备注信息
        hashMap = new HashMap<>();
        hashMap.put("shopcommon_id", confirmOrderEntity.getShopData().get(0).getShops().get(0).getShopcommon_id());
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.BuyAddition_p(hashMap);


    }


    private String ToJson() {
        int  i=0;
        JSONObject jsonObject = new JSONObject();
          try {
              JSONArray shop_items = new JSONArray();
              JSONArray additionBeanArray = new JSONArray();
              for (ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean additionBean:confirmOrderEntity.getShopData().get(0).getShops().get(0).getAdditionBeanList()){
                  JSONObject addtionobject = new JSONObject();
                  addtionobject.put("addition_id", additionBean.getAddition_id());
                  addtionobject.put("field", additionBean.getField());
                  addtionobject.put("addition_key", additionBean.getAddition_key());
                  addtionobject.put("addition_value", additionBean.getAddition_value());
                  additionBeanArray.put(addtionobject);
              }
              for(ConfirmOrderEntity.ShopDataBean shopdatabean:confirmOrderEntity.getShopData()){

               for(ConfirmOrderEntity.ShopDataBean.ShopsBean shopsBean:shopdatabean.getShops()){

                  for(ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean cartInfoBean:shopsBean.getCartInfo()){
                      JSONObject shopitem = new JSONObject();
                      shopitem.put("ssc_id", cartInfoBean.getSsc_id());
                      shopitem.put("count", cartInfoBean.getQuantity_count());
                      shop_items.put(shopitem);
                     i++;
                  }
               }
              }

              jsonObject.put("shopcommon_id", confirmOrderEntity.getShopData().get(0).getShops().get(0).getShopcommon_id());
              jsonObject.put("shop_items", shop_items);
              jsonObject.put("additionData", additionBeanArray);
          }catch (JSONException e){
              e.printStackTrace();
          }
        Log.e("shop_items",String.valueOf(i++));
        Log.e("ToJson", jsonObject.toString());
        return jsonObject.toString();

    }

  private String ToShopItems(){
        JSONObject jsonObject = new JSONObject();
        JSONArray shop_items = new JSONArray();
        for(ConfirmOrderEntity.ShopDataBean shopDataBean:confirmOrderEntity.getShopData()){

            for(ConfirmOrderEntity.ShopDataBean.ShopsBean shops:shopDataBean.getShops()){

                for(ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean cartInfoBean:shops.getCartInfo()){

                    JSONObject cartInfoObject = new JSONObject();
                    try {
                        cartInfoObject.put("ssc_id",cartInfoBean.getSsc_id());
                        cartInfoObject.put("count",cartInfoBean.getQuantity_count());
                        shop_items.put(cartInfoObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }



            }


        }
        Log.e("ToShopItems", shop_items.toString());
        return shop_items.toString();
    }
    private int buyNuber(ConfirmOrderEntity t) {
        int count = 0;
        for (ConfirmOrderEntity.ShopDataBean shopDataBean : t.getShopData()) {

            for (ConfirmOrderEntity.ShopDataBean.ShopsBean shopsBean : shopDataBean.getShops()) {

                for (ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean cartInfoBean : shopsBean.getCartInfo()) {
                    count += cartInfoBean.getQuantity_count();
                }
            }

        }
        return count;
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

    private void DefaultAddress(int area_id, int addressid, String name, String Phone,String city_name, String address) {
        addressId = String.valueOf(addressid);
        Consignee.setText(name);
        ConsigneePhone.setText(Phone);
        ConsigneeAddress.setText(city_name+address);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("area_id", area_id);
            hashMap.put("shopcommon_id", confirmOrderEntity.getShopData().get(0).getShops().get(0).getShopcommon_id());
            hashMap.put("shop_items", ToShopItems());
            mPresenter.BuyFreight_p(hashMap);

    }
    @Override
    public void DefaultAddress_v(AddressDefaultEntity t) {
             if(t.getResultCode()==1){

                 if(!TextUtils.isEmpty(t.getAddressData().getPhone())){
                     linerAddress.setVisibility(View.VISIBLE);
                     relalayoutAddAddress.setVisibility(View.GONE);
                     DefaultAddress(t.getAddressData().getArea_id(), t.getAddressData().getAddress_id(), t.getAddressData().getConsignee(), t.getAddressData().getPhone(),t.getAddressData().getCity_name(), t.getAddressData().getDetail_address());
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

    @Override
    public void BuyAddition_v(String t) {
        int resultCode = JsonUtils.getIntValue(t, "resultCode");
        String msg = JsonUtils.getValue(t, "msg");
        if (resultCode == 1) {
            String data = JsonUtils.getValue(t, "datas");
            if (data != null) {
                confirmOrderEntity.getShopData().get(0).getShops().get(0).setAddition(data);
                confirmOrderEntity.getShopData().get(0).getShops().get(0).setAdditionBeanList((List<ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean>) JsonUtils.fromJson(data, new TypeToken<List<ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean>>() {
                }.getType()));
                submitOrderAdapter.notifyDataSetChanged();
            }

        } else {
            ToastUitl.show(msg, 1000);
        }
    }

    @Override
    public void BuyFreight_v(String t) {
        int resultCode = JsonUtils.getIntValue(t, "resultCode");
        String msg = JsonUtils.getValue(t, "msg");
        if (resultCode == 1) {
            double fee_money = JsonUtils.getDoubleValue(t, "fee_money");
            if (submitOrderAdapter != null) {
                List<ReturnFreightEntity.FreightDataBean> list = new ArrayList<>();
                ReturnFreightEntity.FreightDataBean freightData = new ReturnFreightEntity.FreightDataBean();
                freightData.setFreight_money(fee_money);
                list.add(freightData);
                submitOrderAdapter.setFreightData(list);
            }

        } else {
            ToastUitl.show(msg, 1000);

        }
    }

    @Override
    public void BuyOrder_v(PayBuyEntity t) {
        if (t.getResultCode() == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("create_time", t.getCreate_time());
            bundle.putString("totalMoney", t.getTotalMoney());
            bundle.putString("order_num", t.getOrder_num());
            bundle.putString("from_type", t.getFrom_type());
            android.util.Log.e("from_type",t.getFrom_type());
            startActivity(PayActivity.class, bundle);
            finish();
        } else {
            ToastUitl.show(t.getMsg(), 3000);
        }
    }
    @Override
    public void RetainageOrder_v(PayBuyEntity t) {
        if (t.getResultCode() == 1) {
            MessageEvent msgEvent= new MessageEvent();
            msgEvent.setMessage_type("onRefresh");
            EventBus.getDefault().post(msgEvent);
            Bundle bundle = new Bundle();
            bundle.putString("create_time", t.getCreate_time());
            bundle.putString("totalMoney", t.getTotalMoney());
            bundle.putString("order_num", t.getOrder_num());
            bundle.putString("from_type", t.getFrom_type());
            startActivity(PayActivity.class, bundle);
            finish();
        } else {
            ToastUitl.show(t.getMsg(), 1000);
        }

    }


    public  boolean isAddtion(List<ConfirmOrderEntity.ShopDataBean> confirmlist){
      for(ConfirmOrderEntity.ShopDataBean shopDataBean:confirmlist){
          for(ConfirmOrderEntity.ShopDataBean.ShopsBean shopsBean:shopDataBean.getShops()){
              for(ConfirmOrderEntity.ShopDataBean.ShopsBean.AdditionBean additionBean:shopsBean.getAdditionBeanList()){
                  if(TextUtils.isEmpty(additionBean.getAddition_value())){
                      return  false;
                  }
              }
          }
      }
      return  true;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void RefreshToTal(String total) {
        txtAllprice.setText(String.format(getString(R.string.total), total));
        Link link = new Link("￥"+total)
                .setTextColor(getResources().getColor(R.color.main_color))
                .setUnderlined(false)
                .setBold(false);// optional, defaults to holo blue
        LinkBuilder.on(txtAllprice)
                .addLink(link)
                .build(); // creat
    }

    @OnClick({R.id.image_close,R.id.txt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_go:
                if (TextUtils.isEmpty(addressId)) {
                    ToastUitl.show("请添加收货地址", 1000);
                    return;
                }else if (!isAddtion(submitOrderAdapter.getGroups())) {
                    ToastUitl.show("请填写附加信息", 1000);
                    return;
                } else {
                    if(isRetainage){
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("virtualuser_id", UserLoginUtil.IsUserId());
                        map.put("order_num", order_no);
                        map.put("address_id", addressId);
                        map.put("app_from", "android");
                        map.put("FKEY", PutUtils.FKEY());
                        map.put("shopcommon_id", confirmOrderEntity.getShopData().get(0).getShops().get(0).getShopcommon_id());
                        map.put("submitData",ToJson());
                        mPresenter.RetainageOrder_p(map);
                    }else {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("virtualuser_id", UserLoginUtil.IsUserId());
                        map.put("address_id", addressId);
                        map.put("app_from", "android");
                        map.put("FKEY", PutUtils.FKEY());
                        map.put("submitData", ToJson());
                        mPresenter.BuyOrder_p(map);
                    }

                }



            /*    if (!TextUtils.isEmpty(addressId)) {
                    if(isRetainage){
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("virtualuser_id", IsCheckLogin.IsUserId());
                        map.put("ord41er_num", order_no);
                        map.put("address_id", addressId);
                        map.put("app_from", "android");
                        map.put("FKEY", PutUtils.FKEY());
                        map.put("shopcommon_id", confirmOrderEntity.getShopData().get(0).getShops().get(0).getShopcommon_id());
                        map.put("submitData",ToJson());
                        mPresenter.RetainageOrder_p(map);
                    }else {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("virtualuser_id", IsCheckLogin.IsUserId());
                        map.put("address_id", addressId);
                        map.put("app_from", "android");
                        map.put("FKEY", PutUtils.FKEY());
                        map.put("submitData", ToJson());
                        mPresenter.BuyOrder_p(map);
                    }
                } else {
                    ToastUitl.show("请选择收货地址", 1000);
                }
*/
                break;
        }
    }
}
