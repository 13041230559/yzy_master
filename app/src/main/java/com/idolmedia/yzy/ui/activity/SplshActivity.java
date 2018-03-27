package com.idolmedia.yzy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.utlis.RxCountDown;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.zhy.autolayout.AutoLayoutActivity;

import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by Administrator on 2017/9/10.
 */

public class SplshActivity extends AutoLayoutActivity {
    boolean isBoolean;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {  super.onCreate(savedInstanceState);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
           setContentView(R.layout.activity_splsh);
           RxCountDown.countdown(1)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        isBoolean=  SharedUtil.getBoolean("WelcomeActivity",false);
                        if(!isBoolean){
                            Intent intent=new Intent(SplshActivity.this,WelcomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent=new Intent(SplshActivity.this,AdvertisementActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                    }
                });


    }
}
