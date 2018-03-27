package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.MainActivity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MessageEvent;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/3 14:05
 * 描述：
 */

public class SuccessPayActivity extends BaseActivity implements UMShareListener {
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
    @BindView(R.id.txt_oder)
    TextView txtOder;
    @BindView(R.id.txt_returnindex)
    TextView txtReturnindex;
    @BindView(R.id.txt_share)
    TextView txtShare;
    public static final String SHARE_URL = "http://www.withfans.com/webProgress/webPage/zxDetail.html?id=%s&picUrl=%s";
    @Override
    public int getLayoutId() {
        return R.layout.activity_success_pay;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvTitle.setText("支付成功");
        rightImg.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MessageEvent msgEvent= new MessageEvent();
        msgEvent.setMessage_type("colsePay");
        EventBus.getDefault().post(msgEvent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.txt_oder, R.id.txt_returnindex, R.id.txt_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_oder:
                startActivity(MyOrderListActivity.class);
                finish();
                break;
            case R.id.txt_returnindex:
                startActivity(MainActivity.class);
                finish();
                break;
            case R.id.txt_share:
                break;

        }
    }

    public void ShareWeb(int thumb_img) {
        UMImage thumb = new UMImage(this, thumb_img);
        UMWeb web = new UMWeb("http://www.withfans.com");
        web.setThumb(thumb);
        web.setDescription(getResources().getString(R.string.app_name));
        web.setTitle("一直娱");
        new ShareAction(this)
                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(this)
                .withMedia(web)
                .withMedia(web)
                .open();
    }

    /**
     * @param platform 平台类型
     * @descrption 分享开始的回调
     */
    @Override
    public void onStart(SHARE_MEDIA platform) {
    }

    /**
     * @param platform 平台类型
     * @descrption 分享成功的回调
     */

    @Override
    public void onResult(SHARE_MEDIA platform) {
        ToastUitl.showShort("分享成功");
    }

    /**
     * @param platform 平台类型
     * @param t        错误原因
     * @descrption 分享失败的回调
     */
    @Override
    public void onError(SHARE_MEDIA platform, Throwable t) {
        Log.e("分享", t.getMessage());
        ToastUitl.showShort("分享失败" + t.getMessage());

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }
}
