package com.idolmedia.yzy.app;


import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.request.target.ViewTarget;
import com.idolmedia.yzy.MainActivity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.LogisticsActivity;
import com.idolmedia.yzy.ui.activity.MyOrderListActivity;
import com.idolmedia.yzy.ui.activity.SupportDetalisactivity;
import com.idolmedia.yzy.ui.activity.WebActivity;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.mumu.common.base.BaseApplication;
import com.mumu.common.utils.JsonUtils;
import com.mumu.common.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.Map;

/**
 * Created by MuMu on 2016/9/6.
 */
public class YzyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Log
        LogUtil.logInit();
        AutoLayoutConifg.getInstance().useDeviceSize();
        SharedUtil.init(this);
        Config.DEBUG=true;
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
        String RegistrationId=  PushAgent.getInstance(this).getRegistrationId();
        Log.e("RegistrationId",RegistrationId+"");
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.setNotificaitonOnForeground(true);
        mPushAgent.setDisplayNotificationNumber(Integer.MAX_VALUE);
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //声音
        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SERVER);//呼吸灯
        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SERVER);//振动

        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            @Override
            public Notification getNotification(Context context, UMessage msg) {
             /*   String  androidPush= msg.extra.get("androidPush");
                String  type=JsonUtils.getValue(androidPush,"type");
                Intent intent;
                for (Map.Entry<String, String> entry : msg.extra.entrySet()) {
                    if(TextUtils.equals(type,"detail")) {
                        String param = JsonUtils.getValue(androidPush, "param");
                        String shop_type = JsonUtils.getValue(param, "shop_type");
                        String shopcommon_id = JsonUtils.getValue(param, "shopcommon_id");
                        Log.e("JsonUtils",""+shop_type);
                        if (TextUtils.equals(shop_type, "support")) {

                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("shopcommon_id", shopcommon_id);
                            bundle.putString("shop_type", shop_type);
                            intent.setClass(context, SupportDetalisactivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else if (TextUtils.equals(shop_type, "activity")) {
                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("shopcommon_id", shopcommon_id);
                            bundle.putString("shop_type", shop_type);
                            intent.setClass(context, SupportDetalisactivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else if (TextUtils.equals(shop_type, "ordinary")) {
                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("shopcommon_id", shopcommon_id);
                            bundle.putString("shop_type", shop_type);
                            intent.setClass(context, CommodityDetailsActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else if (TextUtils.equals(shop_type, "reserve")) {
                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("shopcommon_id", shopcommon_id);
                            bundle.putString("shop_type", shop_type);
                            intent.setClass(context, CommodityDetailsActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }else if (TextUtils.equals(type, "url")) {
                        String outsideUrl = JsonUtils.getValue(JsonUtils.getValue(entry.getValue(), "param"), "outsideUrl");
                        String outsideTitle = JsonUtils.getValue(JsonUtils.getValue(entry.getValue(), "param"), "outsideTitle");
                        intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("url", outsideUrl);
                        bundle.putString("title", outsideTitle + "");
                        intent.setClass(context, WebActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else if(TextUtils.equals(type, "express")){
                        Log.e("JsonUtils",""+JsonUtils.getValue(entry.getValue(), "param"));
                        Log.e("JsonUtils_",""+JsonUtils.getValue(JsonUtils.getValue(entry.getValue(), "param"), "order_num"));
                        String order_num = JsonUtils.getValue(JsonUtils.getValue(entry.getValue(), "param"), "order_num");
                        String express_no = JsonUtils.getValue(JsonUtils.getValue(entry.getValue(), "param"), "express_no");
                        String shippercode = JsonUtils.getValue(JsonUtils.getValue(entry.getValue(), "param"), "shippercode");
                        intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("order_num", order_num);
                        bundle.putString("express_no", express_no );
                        bundle.putString("shippercode", shippercode);
                        intent.setClass(context, LogisticsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                }*/
                return super.getNotification(context, msg);

            }

        };
        mPushAgent.setMessageHandler(messageHandler);
        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                String  androidPush= msg.extra.get("androidPush");
                String  type=JsonUtils.getValue(androidPush,"type");
                Intent intent;
                for (Map.Entry<String, String> entry : msg.extra.entrySet()) {
                    if(TextUtils.equals(type,"detail")) {
                        String param = JsonUtils.getValue(androidPush, "param");
                        String shop_type = JsonUtils.getValue(param, "shop_type");
                        String shopcommon_id = JsonUtils.getValue(param, "shopcommon_id");

                        if (TextUtils.equals(shop_type, "support")) {
                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("shopcommon_id", shopcommon_id);
                            bundle.putString("shop_type", shop_type);
                            intent.setClass(context, SupportDetalisactivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else if (TextUtils.equals(shop_type, "activity")) {
                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("shopcommon_id", shopcommon_id);
                            bundle.putString("shop_type", shop_type);
                            intent.setClass(context, SupportDetalisactivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else if (TextUtils.equals(shop_type, "ordinary")) {
                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("shopcommon_id", shopcommon_id);
                            bundle.putString("shop_type", shop_type);
                            intent.setClass(context, CommodityDetailsActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else if (TextUtils.equals(shop_type, "reserve")) {
                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("shopcommon_id", shopcommon_id);
                            bundle.putString("shop_type", shop_type);
                            intent.setClass(context, CommodityDetailsActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }else if (TextUtils.equals(type, "url")) {
                       String outsideUrl = JsonUtils.getValue(JsonUtils.getValue(entry.getValue(), "param"), "outsideUrl");
                        String outsideTitle = JsonUtils.getValue(JsonUtils.getValue(entry.getValue(), "param"), "outsideTitle");
                        intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("url", outsideUrl);
                        bundle.putString("title", outsideTitle + "");
                        intent.setClass(context, WebActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else if(TextUtils.equals(type, "express")){
                       String order_num = JsonUtils.getValue(androidPush, "order_num");
                        String express_no = JsonUtils.getValue(androidPush, "express_no");
                        String shippercode = JsonUtils.getValue(androidPush, "shippercode");
                        intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("order_num", order_num);
                        bundle.putString("express_no", express_no );
                        bundle.putString("shippercode", shippercode);
                        intent.setClass(context, LogisticsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtras(bundle);
                        startActivity(intent);

                   }else if(TextUtils.equals(type, "reserveStatus")){


                        String shippercode = JsonUtils.getValue(androidPush, "shippercode");
                        intent = new Intent();
                        intent.setClass(context, MyOrderListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("toPay",1);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtras(bundle);
                        startActivity(intent);
                      /*  String order_num = JsonUtils.getValue(androidPush, "order_num");
                        String express_no = JsonUtils.getValue(androidPush, "express_no");
                        String shippercode = JsonUtils.getValue(androidPush, "shippercode");
                        intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("order_num", order_num);
                        bundle.putString("express_no", express_no );
                        bundle.putString("shippercode", shippercode);
                        intent.setClass(context, LogisticsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtras(bundle);
                        startActivity(intent);*/
                    }


                }
            }
        };

        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        mPushAgent.register(new IUmengRegisterCallback() {
                @Override
                public void onSuccess(String deviceToken) {
                    //注册成功会返回device token
                    Log.e("deviceToken",deviceToken);
                    //sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
                }
                @Override
                public void onFailure(String s, String s1) {
                    Log.e("register failed",s+"   "+s1);
                }
            });
            {
                PlatformConfig.setWeixin("wx2d80c6a71ca20ebc","1c31cd9e056a0de771bd12aefdb6e016");
                PlatformConfig.setQQZone("1106213827","4gxieXqDmciHMpkA");
                PlatformConfig.setSinaWeibo("2938706775","90e20ec802a7c38ce898c733a7ba1a1e","https://sns.whalecloud.com/sina2/callback");
            }

        }

        //static 代码段可以防止内存泄露
        static {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
                layout.setPrimaryColorsId(R.color.white, R.color.main_color);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            });
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            });
        }

    }
