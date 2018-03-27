package com.idolmedia.yzy.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.idolmedia.yzy.api.retrofit.AppConstant;
import com.idolmedia.yzy.entity.MessageEvent;
import com.idolmedia.yzy.ui.activity.SuccessPayActivity;
import com.mumu.common.utils.ToastUitl;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/8/31.
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, AppConstant.APP_ID);
        api.handleIntent(getIntent(), this);
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Log.e("微信支付回调---->",resp.errCode+"");
        if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            if(resp.errCode==0){
            //    ToastUitl.showShort("支付成功");
                MessageEvent messageEvent=new MessageEvent();
                messageEvent.setMessage_type("colsePay");
                EventBus.getDefault().post(messageEvent);
                Intent intent=new Intent(WXPayEntryActivity.this,SuccessPayActivity.class);
                startActivity(intent);
            }else if(resp.errCode==-1){
                //ToastUitl.showShort("支付失败");

            }else if(resp.errCode==-2){
             //   ToastUitl.showShort("支付取消");
            }
        }
        finish();
    }
}
