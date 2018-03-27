package com.idolmedia.yzy.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.MainActivity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.ui.mvp.contract.AdvertisementContract;
import com.idolmedia.yzy.ui.mvp.model.AdvertisementModel;
import com.idolmedia.yzy.ui.mvp.presenter.AdvertisementPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.idolmedia.yzy.utlis.Transform.CornersTransform;
import com.jakewharton.rxbinding.view.RxView;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.RxCountDown;
import com.mumu.common.utils.ToastUitl;
import com.mumu.common.widget.StatusBarCompat;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/2 19:12
 * 描述： 广告页面
 */

public class AdvertisementActivity extends BaseActivity<AdvertisementPresenter, AdvertisementModel> implements AdvertisementContract.View {
    @BindView(R.id.txt_advertisement)
    TextView txtAdvertisement;
    @BindView(R.id.iamge_advertisement)
    ImageView iamgeAdvertisement;
    @BindView(R.id.rela_layout)
    RelativeLayout relaLayout;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.txt_logoname)
    TextView txtLogoname;
    @BindView(R.id.txt_logojs)
    TextView txtLogojs;
    Subscription subscription;
    boolean isBoolean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_advertisement;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //全屏
        StatusBarCompat.translucentStatusBar(this);
       // Glide.with(this).load(R.mipmap.logo).apply(new RequestOptions().transform()).into(logo);
        txtLogoname.setTypeface(Typeface.createFromAsset(getAssets(),"yy.otf"));
        txtLogojs.setTypeface(Typeface.createFromAsset(getAssets(),"yy.otf"));
        txtAdvertisement.bringToFront();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("banner_type", "spread");
        hashMap.put("location", "0");
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Banner_p(hashMap);
        subscription = RxCountDown.countdown(5)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        txtAdvertisement.setText("跳过" + 5+"s");
                        //appendLog("开始计时");
                        // sendCode.setText("获取验证码");
                        // InspectCode();
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        isBoolean=  SharedUtil.getBoolean("WelcomeActivity",false);
                        if(!isBoolean){
                            Intent intent=new Intent(AdvertisementActivity.this,WelcomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent=new Intent(AdvertisementActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        txtAdvertisement.setText("跳过" + integer+"s");
                    }
                });
        RxView.clicks(txtAdvertisement).subscribe(aVoid -> {
            isBoolean=  SharedUtil.getBoolean("WelcomeActivity",false);
            if(!isBoolean){
                Intent intent=new Intent(AdvertisementActivity.this,WelcomeActivity.class);
                startActivity(intent);
                finish();
            }else {
                Intent intent=new Intent(AdvertisementActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
    public void Banner_v(BannerEntity t) {
        if(t.getResultCode()==1){
        Glide.with(this).load(t.getDatas().get(0).getPic_url()).apply(new RequestOptions().centerCrop()).transition(new DrawableTransitionOptions().crossFade(2000)).into(iamgeAdvertisement);
        }
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription!=null){
            subscription.unsubscribe();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
