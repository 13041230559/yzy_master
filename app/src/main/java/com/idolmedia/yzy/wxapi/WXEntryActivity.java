package com.idolmedia.yzy.wxapi;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mumu.common.utils.ToastUitl;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity   {

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void onResp(BaseResp resp) {
        super.onResp(resp);

        Log.e("微信支付回调---->",resp.errCode+"");
        if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
          if(resp.errCode==0){
              ToastUitl.showShort("支付成功");
          }else if(resp.errCode==-1){
              ToastUitl.showShort("支付失败");
          }else if(resp.errCode==-2){
              ToastUitl.showShort("支付取消");
          }

        }
            }

    @Override
    public void onReq(BaseReq req) {
        super.onReq(req);
        Log.e("resp",req.toString()+"==="+req.getType());
    }
}
