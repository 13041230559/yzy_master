package com.idolmedia.yzy.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.alipay.AlipayRequest;
import com.idolmedia.yzy.alipay.PayResult1;
import com.idolmedia.yzy.api.retrofit.AppConstant;
import com.idolmedia.yzy.entity.AliPayEntity;
import com.idolmedia.yzy.entity.MessageEvent;
import com.idolmedia.yzy.entity.WxPayEntity;
import com.idolmedia.yzy.ui.mvp.contract.PayContract;
import com.idolmedia.yzy.ui.mvp.model.PayModel;
import com.idolmedia.yzy.ui.mvp.presenter.PayPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.FormatDate;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.JsonUtils;
import com.mumu.common.utils.NetWorkUtil;
import com.mumu.common.utils.ToastUitl;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/4 11:11
 * 描述：  支付页面
 */

public class PayActivity extends BaseActivity<PayPresenter, PayModel> implements PayContract.View {
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_img)
    ImageView rightImg;
    @BindView(R.id.liner_top)
    LinearLayout linerTop;
    @BindView(R.id.txt_pay_price)
    TextView txtPayPrice;
    @BindView(R.id.alipay)
    CheckBox alipay;
    @BindView(R.id.liner_alipay)
    LinearLayout linerAlipay;
    @BindView(R.id.wxpay)
    CheckBox wxpay;
    @BindView(R.id.liner_wxpay)
    LinearLayout linerWxpay;
    @BindView(R.id.txt_go)
    TextView txtGo;
    String totalMoney;
    String order_num;
    String from_type;
    String create_time;
    boolean GoBuy;
    @BindView(R.id.countdownView)
    CountdownView countdownView;
    long starTime;
    Date  date;
    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        tvTitle.setText(getString(R.string.pay_order));
        create_time=getIntent().getStringExtra("create_time");
        totalMoney = getIntent().getStringExtra("totalMoney");
        order_num = getIntent().getStringExtra("order_num");
        from_type = getIntent().getStringExtra("from_type");
        txtPayPrice.setText("￥" + FormatUtil.FormatPirce(Double.parseDouble(totalMoney)));
        long startime = FormatDate.StrToDate(create_time).getTime();
        // countdownView.start((startime+15*60*1000)- );
        new Thread(() -> {
            try {
                URL url = null;//取得资源对象
                url = new URL("https://www.baidu.com");
                URLConnection uc = url.openConnection();//生成连接对象
                uc.connect(); //发出连接
                long  ld = uc.getDate(); //取得网站日期时间
                date=new Date(ld); //转换为标准时间对象
                Log.i("this_time","ld---->>>>"+ld);
                Log.i("data","ld---->>>>"+date);
                Log.i("data","ld---->>>>"+FormatUtil.dateToStrLong(date));
                Log.i("data","ld---->>>>"+(date.getTime()-FormatDate.StrToDate(create_time).getTime())+"");
                Message msg=new Message();
                msg.what=1;
                msg.obj=(15*60*1000)-(date.getTime()-FormatDate.StrToDate(create_time).getTime());
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        countdownView.setOnCountdownEndListener(cv -> startActivity(MyOrderListActivity.class));
    }

    private void reduction() {
        alipay.setChecked(false);
        wxpay.setChecked(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMessage_type();
        Log.e("onEventMainThread",msg);
        if (TextUtils.equals("colsePay", event.getMessage_type())) {
               finish();

        }
     /*   if (TextUtils.equals("commenttype", event.getMessage_type())) {
            List<DepositEntity.CommentTypeBean> commentTypeBeanList = (List<DepositEntity.CommentTypeBean>) event.getObject();
            Log.e("onEventMainThread", commentTypeBeanList.get(0).getSatis_name());
            flowlayout.setAdapter(new TagAdapter(commentTypeBeanList) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tag_item, flowlayout, false);
                    tv.setTextSize(12);
                    tv.setText(commentTypeBeanList.get(position).getSatis_name() + "(" + commentTypeBeanList.get(position).getCount() + ")");
                    return tv;
                }
            });
            flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("shopcommon_id", shopcommon_id);
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", pageSize);
                    hashMap.put("comment_from", "1");
                    hashMap.put("satisfaction", position == 0 ? "" : position);
                    mPresenter.CommentList_p(hashMap);
                    return true;
                }
            });*/

    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    long time= (long) msg.obj;
                    countdownView.start(time);
                    break;
            }

        }
    };




    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void ShowDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setMessage("请您在15分钟内完成订单支付，否则自动取消，请您尽快支付");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "继续支付", (dialog12, which) -> {
            return;
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认离开", (dialog1, which) -> {
            finish();
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        ShowDialog();
    }

    @OnClick({R.id.image_close, R.id.alipay, R.id.liner_alipay, R.id.wxpay, R.id.liner_wxpay, R.id.txt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                ShowDialog();
                break;
            case R.id.alipay:
                reduction();
                alipay.setChecked(true);
                break;
            case R.id.liner_alipay:
                reduction();
                alipay.setChecked(true);
                break;
            case R.id.wxpay:
                reduction();
                wxpay.setChecked(true);
                break;
            case R.id.liner_wxpay:
                reduction();
                wxpay.setChecked(true);
                break;
            case R.id.txt_go:
                if (wxpay.isChecked()) {
                    mPresenter.Pay_p(Pay(2));
                } else if (alipay.isChecked()) {
                    mPresenter.Pay_p(Pay(1));

                }
                break;
        }
    }


    public HashMap Pay(int pay_type) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pay_type", pay_type);
        hashMap.put("pay_money",totalMoney);
        hashMap.put("order_no", order_num);
        hashMap.put("app_type", "2");
        hashMap.put("from_type", from_type);
        hashMap.put("app_ip", NetWorkUtil.getHostIP());
        return hashMap;
    }

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        create_time=intent.getStringExtra("create_time");
        totalMoney = intent.getStringExtra("totalMoney");
        order_num = intent.getStringExtra("order_num");
        from_type = intent.getStringExtra("from_type");

    }

    @Override
    public void Pay_v(String t) {
        if (alipay.isChecked()) {
            AliPayEntity aliPayEntity = (AliPayEntity) JsonUtils.fromJson(t, new TypeToken<AliPayEntity>() {
            }.getType());
            if (aliPayEntity.getResultCode() == 1) {
                Log.e("aliPayEntity", aliPayEntity.getOrder_no());
                Log.e("aliPayEntity", aliPayEntity.getOrder_info());
                AlipayRequest.StartAlipay(this, aliPayEntity.getOrder_info(), result -> {
                    Log.e("result", result);
                    PayResult1 result1 = new PayResult1(result);
                    String resultStatus = result1.getResultStatus();
                    Looper.prepare();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUitl.show("支付成功", 1000);
                        startActivity(SuccessPayActivity.class);
                        finish();
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUitl.show("等待支付结果确认", 1000);
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            ToastUitl.showShort("取消支付");
                        }
                        Looper.loop();

                    }
                });
            } else {
                ToastUitl.show(aliPayEntity.getMsg(), 3000);
                finish();
            }

        } else if (wxpay.isChecked()) {
            WxPayEntity wxPay = (WxPayEntity) JsonUtils.fromJson(t, new TypeToken<WxPayEntity>() {
            }.getType());
            if (wxPay.getResultCode() == 1) {
                Log.e("wxPayEntity", wxPay.getOrder_no());
                Log.e("wxPayEntity", wxPay.getSign());
                final IWXAPI api = WXAPIFactory.createWXAPI(PayActivity.this, null);
                api.registerApp(AppConstant.APP_ID);
                PayReq request = new PayReq();
                request.appId = wxPay.getAppid();
                request.partnerId = wxPay.getPartnerid();
                request.prepayId = wxPay.getPrepayid();
                request.packageValue = wxPay.getPackageX();
                request.nonceStr = wxPay.getNoncestr();
                request.timeStamp = wxPay.getTimestamp();
                request.sign = wxPay.getSign();
                api.sendReq(request);
            } else {
                ToastUitl.show(wxPay.getMsg(), 3000);
                finish();
            }


        }

     /*   //{"msg":"获取签名成功","order_no":"yzy201801131445543810001","resultCode":"01","order_info":"charset\u003dutf-8\u0026biz_content\u003d%7B%22timeout_express%22%3A%2216m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%2285.0%22%2C%22subject%22%3A%22%E6%97%A0%E9%99%84%E5%8A%A0%E4%BF%A1%E6%81%AF%E4%BC%98%E6%83%A0%E5%95%86%E5%93%81%22%2C%22body%22%3A%22%E6%97%A0%E9%99%84%E5%8A%A0%E4%BF%A1%E6%81%AF%E4%BC%98%E6%83%A0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%22yzy201801131445543810001%22%2C%22passback_params%22%3A%22cartOrder%22%7D\u0026method\u003dalipay.trade.app.pay\u0026notify_url\u003dhttp%3A%2F%2F59.110.67.175%3A1081%2FFHADMINM%2FappPay%2Falipay_notify_url\u0026app_id\u003d2017070307629190\u0026sign_type\u003dRSA2\u0026version\u003d1.0\u0026timestamp\u003d2018-01-13+14%3A45%3A58\u0026sign\u003ddMXrUHiFeQY86xRBvo7OBTuiDxwHgeZq4uHkDUaLb4%2FrHo5pH%2FpAIgN5SwqH0Ix75RWg36tnUdjAyPNL9Bx96IENF%2BEyWzfm6d%2FWrNQWyyoj41Z1lQKRM%2FkpwpQhOseIFqPCWFgc6MAvY9BPfqz56nVKvTNrJU8C7qD7kgaabBRiXhb64MVrxpEogtby%2F18tlpzRFBHJOc364rldGFIaBeQmztHeWYdqpKztflXOf6zJ6XD1Gd%2Bq27LtMHjS6r9Do%2FSrBmNauYEvJynwlZWVFG8Hz3z7RTP%2BaWHGAMSRlAczAeT3ynLbNWetsGkBxqp7J0uKbYxnBcJg2HZewf1sJg%3D%3D"}
        //   {"order_no":"yzy201801131445543810001","msg":"生成签名数据成功","package":"Sign\u003dWXPay","appid":"wx2d80c6a71ca20ebc","sign":"A81F4E826DE983B476D0EDCE3721FFF1","resultCode":"01","partnerid":"1486210522","prepayid":"wx2018011314460716a1d5ae890730994212","noncestr":"tR0czKZyaO7SBDQK6zpPGL83rxABTz","timestamp":"1515825967"}
        String resultCode = JsonUtils.getValue("resultCode",t);
        String msg= JsonUtils.getValue("msg",t);
        if(resultCode.equals("01")){
            if(alipay.isChecked()){
                //(List<MyOrderEntity.DatasBean.OrderItemsBean.AdditionBean>) JsonUtils.fromJson(orderItemsBean.getContent(), new TypeToken<List<MyOrderEntity.DatasBean.OrderItemsBean.AdditionBean>>() {
         //   }.getType()));
                AliPayEntity aliPayEntity= (AliPayEntity) JsonUtils.fromJson(t, new TypeToken<AliPayEntity>(){}.getType());
                Log.e("aliPayEntity",aliPayEntity.getOrder_no());
                Log.e("aliPayEntity",aliPayEntity.getOrder_info());
                AlipayRequest.StartAlipay(this, aliPayEntity.getOrder_info(), result -> {
                    Log.e("result", result);
                    PayResult1 result1 = new PayResult1(result);
                    String    resultStatus = result1.getResultStatus();
                    Looper.prepare();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUitl.show("支付成功",1000);
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUitl.show("等待支付结果确认",1000);
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            ToastUitl.showShort("取消支付");
                        }
                        Looper.loop();

                    }
                });

            }else if(wxpay.isChecked()){
                WxPayEntity wxPayEntity= (WxPayEntity) JsonUtils.fromJson(t, new TypeToken<WxPayEntity>(){}.getType());
                Log.e("wxPayEntity",wxPayEntity.getOrder_no());
                Log.e("wxPayEntity",wxPayEntity.getSign());

            }


        }else {

            ToastUitl.show(msg,1000);
        }*/

    }
}
