package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.utlis.Windowheight;
import com.idolmedia.yzy.view.popwindow.PopItemAction;
import com.idolmedia.yzy.view.popwindow.PopWindow;
import com.jakewharton.rxbinding.view.RxView;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.mumu.common.widget.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.mumu.common.base.BaseApplication.getContext;

/**
 * Created by Administrator on 2017/9/5.
 */

public class AboutusActivity extends BaseActivity {
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lin_wb)
    LinearLayout linWb;
    @BindView(R.id.lin_emali)
    LinearLayout linEmali;
    @BindView(R.id.txt_code)
    TextView txtCode;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_img)
    ImageView rightImg;
    @BindView(R.id.liner_top)
    LinearLayout linerTop;
    @BindView(R.id.imageView6)
    ImageView imageView6;

    @Override
    public int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    public void initPresenter() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
        tvTitle.setText("关于一直娱");
    }

    @Override
    public void initView() {
        try {
            txtCode.setText("一直娱Android版" + getVersionName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getContext().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.lin_wb, R.id.lin_emali})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.lin_wb:
                Bundle bundle = new Bundle();
                bundle.putString("title", "一直娱微博");
                bundle.putString("url", "https://weibo.com/p/1006066297598713/home?from=page_100606&mod=TAB&is_all=1#place");
                startActivity(WebActivity.class, bundle);
                break;
            case R.id.lin_emali:
                ShowPoP();
                break;
        }
    }

    private void ShowPoP() {
        View customView = View.inflate(this, R.layout.pop_eamli, null);
        final TextView txt_copy, dismiss, callphone, txt_email;
        final PopWindow.Builder popWindow = new PopWindow.Builder((Activity) this);
        popWindow.setStyle(PopWindow.PopWindowStyle.PopAlert)
                .setPopWindowMargins(100, Windowheight.getDpi(this) / 3, 100, 50)
                .addItemAction(new PopItemAction("选项"))
                .setView(customView)
                .create()
                .show();
        txt_email = (TextView) customView.findViewById(R.id.txt_email);
        callphone = (TextView) customView.findViewById(R.id.txt_dismiss);
        txt_copy = (TextView) customView.findViewById(R.id.txt_copy);
        RxView.clicks(callphone).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                popWindow.create().dismiss();
            }
        });
        RxView.clicks(txt_copy).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(txt_email.getText());
                popWindow.create().dismiss();
                ToastUitl.showShort("已复制");
            }
        });
    }
}
