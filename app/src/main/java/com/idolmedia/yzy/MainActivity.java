package com.idolmedia.yzy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.entity.UpdateVersionEntity;
import com.idolmedia.yzy.ui.activity.SelectStarActivity;
import com.idolmedia.yzy.ui.fragment.home.CartFragment;
import com.idolmedia.yzy.ui.fragment.home.EntertainmentlFragment;
import com.idolmedia.yzy.ui.fragment.home.MeFragment;
import com.idolmedia.yzy.ui.fragment.home.RecommendFragment;
import com.idolmedia.yzy.ui.fragment.home.StarFragment;
import com.idolmedia.yzy.ui.mvp.contract.MainContract;
import com.idolmedia.yzy.ui.mvp.model.MainModel;
import com.idolmedia.yzy.ui.mvp.presenter.MainPresenter;
import com.idolmedia.yzy.utlis.NotificationsUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.mumu.common.widget.StatusBarCompat;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ezy.boost.update.ICheckAgent;
import ezy.boost.update.IUpdateChecker;
import ezy.boost.update.IUpdateParser;
import ezy.boost.update.OnFailureListener;
import ezy.boost.update.UpdateError;
import ezy.boost.update.UpdateInfo;
import ezy.boost.update.UpdateManager;
import ezy.boost.update.UpdateUtil;
import io.reactivex.functions.Consumer;

import static com.mumu.common.base.BaseApplication.getContext;

public class MainActivity extends BaseActivity<MainPresenter,MainModel> implements MainContract.View {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.tab_recommend_iamge)
    ImageView tabRecommendIamge;
    @BindView(R.id.tab_recommend_txt)
    TextView tabRecommendTxt;
    @BindView(R.id.tab_recommend)
    LinearLayout tabRecommend;
    @BindView(R.id.tab_star_iamge)
    ImageView tabStarIamge;
    @BindView(R.id.tab_star_txt)
    TextView tabStarTxt;
    @BindView(R.id.tab_star)
    LinearLayout tabStar;
    @BindView(R.id.tab_iamge_zy)
    ImageView iamgeOverMail;
    @BindView(R.id.tab_shopcart_iamge)
    ImageView tabShopcartIamge;
    @BindView(R.id.tab_shopcart_txt)
    TextView tabShopcartTxt;
    @BindView(R.id.tab_shopcart)
    LinearLayout tabShopcart;
    @BindView(R.id.tab_me_iamge)
    ImageView tabMeIamge;
    @BindView(R.id.tab_me_txt)
    TextView tabMeTxt;
    @BindView(R.id.tab_me)
    LinearLayout tabMe;
    @BindView(R.id.tab_zy_txt)
    TextView tabZyTxt;
    @BindView(R.id.view2)
    View view2;
    //按下时间
    private long exitTime = 0;
    private Fragment fragments[];
    private ImageView[] iv_tabs;
    private TextView[] tv_tabs;
    private int index = 2;
    private int currentTabIndex = 2;
    private PushAgent mPushAgent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //  setContentView(R.layout.activity_main);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
   mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();//在这个Activity里，我们可以通过getIntent()，来获取外部跳转传过来的信息。
        String data = intent.getDataString();//接收到网页传过来的数据：sharetest://data/http://www.huxiu.com/
        Log.e("data===",data+"");
        StatusBarCompat.translucentStatusBar(this);
        requestPermissions();
         if(!NotificationsUtils.isNotificationEnabled(this)){

             ToastUitl.show("您的推送通知已关闭,点击设置-应用管理-允许推送通知 即可",3000);

         }
        if(UserLoginUtil.loginEntity()!=null){
            if(UserLoginUtil.IsSelectIdo()){
                startActivity(SelectStarActivity.class);
            }
        }


         mPresenter.UpdateVersion_p();
        //Log.e("TongZhi",NotificationsUtils.isNotificationEnabled(this)+"");
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
        RecommendFragment recommend = new RecommendFragment();
        StarFragment star = new StarFragment();
        EntertainmentlFragment overseasDirectMail = new EntertainmentlFragment();
        CartFragment shopCartFragment = new CartFragment();
        MeFragment meFragment = new MeFragment();
        iamgeOverMail.setSelected(true);
        tabZyTxt.setSelected(true);
        // Glide.with(this).load(R.mipmap.ic_launcher_round).apply(new RequestOptions().circleCrop()).into(iamgeOverMail);
        iv_tabs = new ImageView[]{
                tabRecommendIamge, tabStarIamge, iamgeOverMail, tabShopcartIamge, tabMeIamge

        };
        tv_tabs = new TextView[]{
                tabRecommendTxt, tabStarTxt, tabZyTxt, tabShopcartTxt, tabMeTxt

        };
        fragments = new Fragment[]{
                recommend, star, overseasDirectMail, shopCartFragment, meFragment
        };
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, fragments[currentTabIndex]).
                show(fragments[currentTabIndex]).commitAllowingStateLoss();

    }

    public void switchTabs(int index) {
        if (currentTabIndex != index) {//currentTab unSelected
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);//hide currentTab
            if (!fragments[index].isAdded())
                trx.add(R.id.frame_layout, fragments[index], index + "");
            //  Log.e("Mainindex", index + "");
            trx.show(fragments[index]).commitAllowingStateLoss();
        }

        iv_tabs[currentTabIndex].setSelected(false);
        iv_tabs[index].setSelected(true);
        tv_tabs[currentTabIndex].setSelected(false);
        tv_tabs[index].setSelected(true);
        currentTabIndex = index;
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (event.getAction() == KeyEvent.ACTION_UP) {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    ToastUitl.showLong("再按一次退出程序");
                    // 将系统当前的时间赋值给exitTime
                    exitTime = System.currentTimeMillis();
                    return true;
                } else {
                    // finish();
                    System.exit(0);
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @OnClick({R.id.tab_recommend, R.id.tab_star, R.id.tab_iamge_zy, R.id.tab_shopcart, R.id.tab_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_recommend:
                index = 0;
                break;
            case R.id.tab_star:
                index = 1;
                break;
            case R.id.tab_iamge_zy:
                index = 2;
                break;
            case R.id.tab_shopcart:
                if(!UserLoginUtil.Islogin(this)){return;}
                index = 3;
                break;
            case R.id.tab_me:
                if(!UserLoginUtil.Islogin(this)){return;}
                index = 4;
                break;
        }
        switchTabs(index);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        int tag = intent.getIntExtra("tag", 0);
        Log.e("tag", tag + "");
        if (tag == 2) {
            switchTabs(2);
            //  bottomNavigation.setCurrentItem(2, true);
        }else if(tag==0){
            switchTabs(0);
        }else if(tag==1){
            switchTabs(1);
        }
        super.onNewIntent(intent);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("4");
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.requestEach(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_LOGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.SET_DEBUG_APP,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.WRITE_APN_SETTINGS
        ).subscribe(permission -> {
            if (permission.granted) {
                // 用户已经同意该权限
                Log.d("permission", permission.name + " 同意.");
            } else if (permission.shouldShowRequestPermissionRationale) {
                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                Log.d("permission", permission.name + " 下次请求");
            } else {
                // 用户拒绝了该权限，并且选中『不再询问』
                //  ToastUitl.showShort("可能造成APP无法使用请打开权限");
                Log.d("permission", permission.name + " is denied.");
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
        ToastUitl.showLong(msg);

    }
    void check(boolean isManual,final int notifyId,final UpdateVersionEntity t) {
        UpdateManager.create(this).setChecker(new IUpdateChecker() {
            @Override
            public void check(ICheckAgent agent, String url) {
                Log.e("agent",agent.toString()+"==="+url);
                Log.e("ezy.update", "checking");
                agent.setInfo("");
            }
        }).setUrl(t.getDatas().getMCheckUrl()).setManual(isManual).setNotifyId(notifyId).setParser(new IUpdateParser() {
            @Override
            public UpdateInfo parse(String source) throws Exception {
                UpdateInfo info = new UpdateInfo();
                info.hasUpdate = Boolean.getBoolean(t.getDatas().getHasUpdate());
                //info.updateContent = "• 支持文字、贴纸、背景音乐，尽情展现欢乐气氛；\n• 两人视频通话支持实时滤镜，丰富滤镜，多彩心情；\n• 图片编辑新增艺术滤镜，一键打造文艺画风；\n• 资料卡新增点赞排行榜，看好友里谁是魅力之王。";
                info.updateContent=t.getDatas().getContent();
                info.versionCode = t.getDatas().getVersionCode();
                info.versionName = t.getDatas().getVersionNo();
                info.url = t.getDatas().getUpdateUrl();
                info.md5 = t.getDatas().getMd5();
                info.size = Long.parseLong(t.getDatas().getVersionSize());
                info.isForce = Boolean.getBoolean(t.getDatas().getIsForce());
                info.isIgnorable =  Boolean.getBoolean(t.getDatas().getIsIgnorable());
                info.isSilent = Boolean.getBoolean(t.getDatas().getIsSilent());
                return info;
            }
        }).setOnFailureListener(error -> ToastUitl.showLong(error.toString())).check();
    }
    @Override
    public void UpdateVersion_v(UpdateVersionEntity t) {
        if(t.getResultCode()==1){
            if(t.getDatas().getVersionCode()> UpdateUtil.getVersionCode(this)){
                check(true,998,t);
            }
              /*  UpdateAppUtils.from(this)
                        .checkBy(UpdateAppUtils.CHECK_BY_VERSION_NAME) //更新检测方式，默认为VersionCode
                        .serverVersionCode(t.getResultCode())  //服务器versionCode
                        .serverVersionName(t.getDatas().getVersionNo()) //服务器versionName
                        .apkPath(t.getDatas().getUpdateUrl()) //最新apk下载地址
                        .downloadBy(UpdateAppUtils.DOWNLOAD_BY_BROWSER) //下载方式：app下载、手机浏览器下载。默认app下载
                        .isForce(Boolean.parseBoolean(t.getDatas().getIsIgnorable())) //是否强制更新，默认false 强制更新情况下用户不同意更新则不能使用app
                        .update();*/
        }

    }


}
