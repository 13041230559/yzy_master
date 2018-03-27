package com.idolmedia.yzy.ui.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyOrderDetalisEntity;
import com.idolmedia.yzy.ui.adapter.MyOrderDetalisAdapter;
import com.idolmedia.yzy.ui.mvp.contract.MyOrderDetalisContract;
import com.idolmedia.yzy.ui.mvp.model.MyOrderDetalisModel;
import com.idolmedia.yzy.ui.mvp.presenter.MyOrderDetalisPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.FormatDate;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/1 15:25
 * 描述：订单详情
 */

public class MyOrderDetalisActivity extends BaseActivity<MyOrderDetalisPresenter, MyOrderDetalisModel> implements MyOrderDetalisContract.View {


    MyOrderDetalisEntity myOrderEntity;
    private static final int COMMODITYEVA = 1;
    MyOrderDetalisAdapter orderDetalisAdapter;
    String[] type_name;
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
    @BindView(R.id.countdownView)
    CountdownView countdownView;
    @BindView(R.id.liner_pay_time)
    LinearLayout linerPayTime;
    @BindView(R.id.txt_logistics_msg)
    TextView txtLogisticsMsg;
    @BindView(R.id.txt_logistics_time)
    TextView txtLogisticsTime;
    @BindView(R.id.liner_logistics)
    LinearLayout linerLogistics;
    @BindView(R.id.txt_addressname)
    TextView txtConsignee;
    @BindView(R.id.txt_addressphone)
    TextView txtConsigneePhone;
    @BindView(R.id.txt_address)
    TextView txtConsigneeAddress;
    @BindView(R.id.liner_address)
    LinearLayout linerAddress;
    @BindView(R.id.txt_add_address)
    TextView txtAddAddress;
    @BindView(R.id.relalayout_add_address)
    RelativeLayout relalayoutAddAddress;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.txt_canceloder)
    TextView txtCanceloder;
    @BindView(R.id.txt_cancel)
    TextView txtCancel;
    @BindView(R.id.txt_pay)
    TextView txtPay;
    @BindView(R.id.txt_remind)
    TextView txtRemind;
    @BindView(R.id.txt_logistics)
    TextView txtLogistics;
    @BindView(R.id.txt_goodreceipt)
    TextView txtGoodreceipt;
    @BindView(R.id.txt_waitingApprise)
    TextView txtGoEvaluate;
    @BindView(R.id.txt_retainage)
    TextView txtRetainage;
    @BindView(R.id.txt_deloder)
    TextView txtDeloder;
    @BindView(R.id.txt_close_order)
    TextView txtCloseOrder;
    @BindView(R.id.liner_orderset)
    LinearLayout linerOrderset;
    String order_num;
    @Override
    public int getLayoutId() {
        return R.layout.activity_my_orderdetalis;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText("订单详情");
        order_num = getIntent().getStringExtra("order_num");
        Log.e("order", order_num);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("order_num", order_num);
        mPresenter.OrderDetalis_p(hashMap);
        orderDetalisAdapter = new MyOrderDetalisAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        linerLogistics.setVisibility(View.GONE);

        type_name = new String[]{"toPay", "waitingDelivery", "waitingGoods", "waitingApprise", "orderOver", "refunded", "cancel", "invalid", "waitingFinalPay"};
    }
    private void getNetTime(String create_time) {
        new Thread(() -> {
            try {
                URL url = null;//取得资源对象
                url = new URL("https://www.baidu.com");
                URLConnection uc = url.openConnection();//生成连接对象
                uc.connect(); //发出连接
                long ld = uc.getDate(); //取得网站日期时间
                Date date = new Date(ld); //转换为标准时间对象
                Log.i("this_time", "ld---->>>>" + ld);
                Log.i("data", "ld---->>>>" + date);
                Log.i("data", "ld---->>>>" + FormatUtil.dateToStrLong(date));
                Log.i("data", "ld---->>>>" + (date.getTime() - FormatDate.StrToDate(create_time).getTime()) + "");
                Message msg = new Message();
                msg.what = 1;
                msg.obj = (15 * 60 * 1000) - (date.getTime() - FormatDate.StrToDate(create_time).getTime());
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    long time = (long) msg.obj;
                    countdownView.start(time);
                    countdownView.setOnCountdownEndListener(cv -> {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("FKEY", PutUtils.FKEY());
                        hashMap.put("order_num", order_num);
                        mPresenter.OrderDetalis_p(hashMap);
                    });
                    break;
            }

        }
    };


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.show(msg, 1000);
    }

    @Override
    public void OrderDetalis_v(MyOrderDetalisEntity t) {
        if (t.getResultCode() == 1) {
            if (t.getDatas().getOrder_status().equals(type_name[0])) {
                getNetTime(t.getDatas().getCreat_time());
                linerPayTime.setVisibility(View.VISIBLE);
            }
            linerAddress.setVisibility(View.VISIBLE);
            relalayoutAddAddress.setVisibility(View.GONE);
            txtConsignee.setText(t.getDatas().getConsignee());
            txtConsigneePhone.setText(t.getDatas().getPhone());
            txtConsigneeAddress.setText(t.getDatas().getConsignee_address().replaceAll("YZY", ""));
            // myOrderEntity = t;
            recyclerView.setAdapter(orderDetalisAdapter);
            List<MyOrderDetalisEntity.DatasBean> list = new ArrayList<>();
            list.clear();
            list.add(t.getDatas());
            orderDetalisAdapter.setdatasNewBeans(list);

            if (t.getDatas().getOrder_status().equals(type_name[0])) {
                // ((ChildViewHolder) holder).countdownView.setVisibility(View.VISIBLE);
                txtCanceloder.setVisibility(View.VISIBLE);
                txtPay.setVisibility(View.VISIBLE);
            } else if (t.getDatas().getOrder_status().equals(type_name[1])) {
                txtRemind.setVisibility(View.VISIBLE);
            } else if (t.getDatas().getOrder_status().equals(type_name[2])) {
                txtLogistics.setVisibility(View.VISIBLE);
                txtGoodreceipt.setVisibility(View.VISIBLE);
            } else if (t.getDatas().getOrder_status().equals(type_name[3])) {
                txtLogistics.setVisibility(View.VISIBLE);
                txtGoEvaluate.setVisibility(View.VISIBLE);
            } else if (t.getDatas().getOrder_status().equals(type_name[4])) {
                txtLogistics.setVisibility(View.VISIBLE);

            } else if (t.getDatas().getOrder_status().equals(type_name[8])) {
                txtRetainage.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(t.getDatas().getFinal_end_time())) {
                    long endtime = FormatDate.StrToDate(t.getDatas().getFinal_end_time()).getTime();
                    long thistime = FormatDate.StrToDate(t.getDatas().getNowTime()).getTime();
                    long startime = FormatDate.StrToDate(t.getDatas().getFinal_start_time()).getTime();
                    if ((thistime < startime) || (thistime > endtime)) {
                        if (thistime < startime) {
                            txtRetainage.setText(getString(R.string.payretainage_unopened));
                        } else if (thistime > endtime) {
                            txtRetainage.setText(getResources().getString(R.string.payretainage_over));
                        }
                        txtRetainage.setBackground(getResources().getDrawable(R.drawable.order_btnitem));
                        txtRetainage.setTextColor(getResources().getColor(R.color.c3));
                    } else if (thistime < endtime && thistime > startime) {
                        txtRetainage.setBackground(getResources().getDrawable(R.drawable.order_btnitem1));
                        txtPay.setFocusable(true);
                        txtRetainage.setTextColor(getResources().getColor(R.color.ff8282));
                    }
                } else {
                    txtRetainage.setBackground(getResources().getDrawable(R.drawable.order_btnitem));
                    txtRetainage.setTextColor(getResources().getColor(R.color.c3));
                    txtRetainage.setText(getResources().getString(R.string.payretainage_unopened));
                }

            } else if (t.getDatas().getOrder_status().equals(type_name[6]) || t.getDatas().getOrder_status().equals(type_name[7])) {
                //((ChildViewHolder) holder).txtCloseOrder.setVisibility(View.VISIBLE);
                txtDeloder.setVisibility(View.VISIBLE);
            } else if (t.getDatas().getOrder_status().equals(type_name[4])) {
                txtLogistics.setVisibility(View.VISIBLE);
            }
            OnClick(t);

        } else {
            ToastUitl.show(t.getMsg(), 2000);
        }
    }

    @Override
    public void CancelOrder_v(BaseResult t) {
        if(t.getResultCode()==1){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("order_num", order_num);
            mPresenter.OrderDetalis_p(hashMap);
        }

    }

    @Override
    public void ConfirmReceipt_v(BaseResult t) {
        if(t.getResultCode()==1){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("order_num", order_num);
            mPresenter.OrderDetalis_p(hashMap);
        }
    }

    @Override
    public void DeleteOrder_v(BaseResult t) {
        if(t.getResultCode()==1){
            setResult(RESULT_OK);
            finish();
            /*HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("order_num", order_num);
            mPresenter.OrderDetalis_p(hashMap);*/
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void OnClick(MyOrderDetalisEntity t) {
        txtCanceloder.setOnClickListener(view -> ShowDialog("确认取消订单？", t.getDatas().getOrder_num(), 0));
        txtCancel.setOnClickListener(view -> {

        });
        txtPay.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("totalMoney", String.valueOf(t.getDatas().getTotal_pay_money()));
            bundle.putString("order_num", t.getDatas().getOrder_num());
            bundle.putString("from_type", TextUtils.equals(t.getDatas().getOrderItems().get(0).getShop_type(),"reserve")?"reserveOrder":"commonOrder");
            bundle.putString("create_time", t.getDatas().getCreate_time());
            startActivity(PayActivity.class, bundle);
        });
        txtRemind.setOnClickListener(view -> ToastUitl.show("已提醒卖家发货!", 1000));
        txtLogistics.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("order_num", t.getDatas().getOrder_num());
         /*   bundle.putString("iamge", t.getDatas().getOrderItems().get(0).getCatalogItems().get(0).getCatalog_img());
            bundle.putString("shippercode", t.getDatas().getShippercode());
            bundle.putString("express_no", t.getDatas().getExpress_no());*/
            startActivity(LogisticsActivity.class, bundle);
        });
        txtGoodreceipt.setOnClickListener(view -> ShowDialog("确定收货？", t.getDatas().getOrder_num(), 1));
        txtGoEvaluate.setOnClickListener(view -> {
          Bundle bundle = new Bundle();
            bundle.putString("order_no", t.getDatas().getOrder_num());
            bundle.putString("iamge", t.getDatas().getOrderItems().get(0).getCatalogItems().get(0).getCatalog_img());
            bundle.putString("name", t.getDatas().getOrderItems().get(0).getShop_name());
             bundle.putString("shopcommon_id", String.valueOf(t.getDatas().getOrderItems().get(0).getShopcommon_id()));
            startActivityForResult(CommodityEvaluationActivity.class, bundle, COMMODITYEVA);

        });
        txtRetainage.setOnClickListener(view -> {
            if(!TextUtils.isEmpty(t.getDatas().getFinal_end_time())){
                long endtime = FormatDate.StrToDate(t.getDatas().getFinal_end_time()).getTime();
                long thistime = FormatDate.StrToDate(t.getDatas().getNowTime()).getTime();
                long startime = FormatDate.StrToDate(t.getDatas().getFinal_start_time()).getTime();
                if((thistime<startime)||(thistime>endtime)){
                    if(thistime<startime){
                        ToastUitl.show(getString(R.string.payretainage_unopened),1000);
                    }else if(thistime>endtime){
                        ToastUitl.show(getString(R.string.payretainage_over),1000);
                    }
                }else if(thistime<endtime&&thistime>startime){
                    Bundle bundle = new Bundle();
                    bundle.putString("ordernum", t.getDatas().getOrder_num());
                    bundle.putString("shop_type", t.getDatas().getOrderItems().get(0).getShop_type());
                    bundle.putString("shopcommon_id", String.valueOf(t.getDatas().getOrderItems().get(0).getShopcommon_id()));
                    startActivity(CommodityDetailsActivity.class, bundle);
                }
            }else {
                ToastUitl.show(getString(R.string.payretainage_unopened),1000);
            }


        });
        txtDeloder.setOnClickListener(view -> ShowDialog("确定删除订单？", t.getDatas().getOrder_num(), 2));
        txtCloseOrder.setOnClickListener(view -> {

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case COMMODITYEVA:
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("order_num", order_num);
                    mPresenter.OrderDetalis_p(hashMap);
                    break;
            }
        }
    }

    private void ShowDialog(String msg, String ordernum, int type) {
        AlertDialog dialog = new AlertDialog.Builder(MyOrderDetalisActivity.this).create();
        dialog.setMessage(msg);
        // dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialog12, which) -> {
            return;
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialog1, which) -> {
            HashMap<String, Object> hashMap = new HashMap<>();

            switch (type) {
                case 0:
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("order_num", ordernum);
                    mPresenter.CancelOrder_p(hashMap);
                    break;
                case 1:
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("order_num", ordernum);
                    mPresenter.ConfirmReceipt_p(hashMap);
                    break;
                case 2:
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("order_num", ordernum);
                    mPresenter.DeleteOrder_p(hashMap);
                    break;


            }

        });
        dialog.show();
    }

    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
    }

}
