package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.MainActivity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.SettingEntity;
import com.idolmedia.yzy.entity.UpdateVersionEntity;
import com.idolmedia.yzy.ui.adapter.SettingAdapter;
import com.idolmedia.yzy.ui.mvp.contract.SettingContract;
import com.idolmedia.yzy.ui.mvp.model.SettingModel;
import com.idolmedia.yzy.ui.mvp.presenter.SettingPresenter;
import com.idolmedia.yzy.utlis.CleanMessageUtil;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.idolmedia.yzy.view.popwindow.PopItemAction;
import com.idolmedia.yzy.view.popwindow.PopWindow;
import com.jakewharton.rxbinding.view.RxView;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ezy.boost.update.ICheckAgent;
import ezy.boost.update.IUpdateChecker;
import ezy.boost.update.IUpdateParser;
import ezy.boost.update.UpdateInfo;
import ezy.boost.update.UpdateManager;
import ezy.boost.update.UpdateUtil;
import rx.functions.Action1;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/15 14:29
 * 描述：  设置
 */

public class SettingActivity extends BaseActivity<SettingPresenter,SettingModel> implements SettingContract.View {
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SettingAdapter adapter;
    @BindView(R.id.txt_go)
    TextView txtGo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        List<SettingEntity> list = new ArrayList<>();
        SettingEntity set1 = new SettingEntity("账号与安全", "");
        SettingEntity set2 = new SettingEntity("意见反馈", "");
        SettingEntity set3 = new SettingEntity("服务协议", "");
        SettingEntity set4 = null;
        SettingEntity set5 = new SettingEntity("关于一直娱", "");
        SettingEntity set6 = new SettingEntity("版本更新", "");

        try {
            set4 = new SettingEntity("清理缓存", CleanMessageUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add(set1);
        list.add(set2);
        list.add(set3);
        list.add(set4);
        list.add(set5);
        list.add(set6);
        tvTitle.setText(getString(R.string.Setting));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SettingAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNewData(list);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if(position==0){
                startActivity(AccountActivity.class);
            }else if (position==3){
                CleanMessageUtil.clearAllCache(this);
                try {
                    list.get(position).setContext(CleanMessageUtil.getTotalCacheSize(this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ToastUitl.show("清理成功",1000);
                adapter.notifyItemChanged(position);
            }else if(position==1){
                startActivity(FeedbackActivity.class);
            }else if(position==2){
                startActivity(ServiceAgreementActivity.class);
            }else if(position==5){
                 mPresenter.UpdateVersion_p();
            }else if(position==4){
               startActivity(AboutusActivity.class);
            }
        });
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
                // ToastUitl.showLong("退出登录");
                ShowPopWind();
                break;
        }
    }

    private  void ShowPopWind(){
        TextView txt_yes,txt_no;
        final PopWindow.Builder popWindow = new PopWindow.Builder(this);
        View customView = View.inflate(this, R.layout.pop_signout, null);
        popWindow.setStyle(PopWindow.PopWindowStyle.PopUp)
                .addItemAction(new PopItemAction("选项"))
                .setView(customView)
                .show();
        txt_yes= (TextView) customView.findViewById(R.id.txt_yes);
        txt_no= (TextView) customView.findViewById(R.id.txt_no);
        RxView.clicks(txt_yes).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                popWindow.create().dismiss();
                SharedUtil.setString("UserEntity", "");
                Bundle bundle =new Bundle();
                bundle.putInt("tag", 2);
                startActivity(MainActivity.class,bundle);
                finish();
            }
        });
        RxView.clicks(txt_no).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                popWindow.create().dismiss();
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
        ToastUitl.show(msg,1000);
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
            }else {
                ToastUitl.show("已经是最新版了",3000);
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
