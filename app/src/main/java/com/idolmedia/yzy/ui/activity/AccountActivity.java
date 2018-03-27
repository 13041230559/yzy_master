package com.idolmedia.yzy.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.AccountEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.mvp.contract.AccountContract;
import com.idolmedia.yzy.ui.mvp.model.AccountModel;
import com.idolmedia.yzy.ui.mvp.presenter.AccountPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/20 9:50
 * 描述：  账号安全
 */

public class AccountActivity extends BaseActivity<AccountPresenter, AccountModel> implements UMAuthListener, AccountContract.View {
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
    @BindView(R.id.txt_id)
    TextView txtId;
    @BindView(R.id.txt_update_pass)
    TextView txtUpdatePass;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.txt_email)
    TextView txtEmail;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.txt_qq)
    TextView txtQq;
    @BindView(R.id.txt_sina)
    TextView txtSina;
    @BindView(R.id.txt_wx)
    TextView txtWx;
    @BindView(R.id.liner_qq)
    LinearLayout linerQq;
    @BindView(R.id.liner_wb)
    LinearLayout linerWb;
    @BindView(R.id.liner_wx)
    LinearLayout linerWx;
    AccountEntity.DatasBean AccountData;
    @BindView(R.id.liner_update_pass)
    LinearLayout linerUpdatePass;
    @BindView(R.id.liner_update_email)
    LinearLayout linerUpdateEmail;
    @BindView(R.id.liner_update_phone)
    LinearLayout linerUpdatePhone;
    private final  int UPDATE=1000;
    public int getLayoutId() {
        return R.layout.activity_id_security;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.id_and_security));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Account_p(hashMap);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
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
    public void Account_v(AccountEntity t) {
        if (t.getResultCode() == 1) {
            AccountData = t.getDatas();
            txtId.setText(String.valueOf(t.getDatas().getVirtualuser_id()));
            txtUpdatePass.setText(t.getDatas().getPassword());
            if (t.getDatas().getEmail().getNick_name() != null) {
                txtEmail.setText(t.getDatas().getEmail().getNick_name());
            } else {
                txtEmail.setText("未绑定");
            }
            if (t.getDatas().getPhone().getAccount_no() != null) {
                txtPhone.setText(t.getDatas().getPhone().getAccount_no());
            } else {
                txtPhone.setText("未绑定");
            }
            if (t.getDatas().getQq().getAccount_no() != null) {
                txtQq.setText(t.getDatas().getQq().getNick_name());
            } else {
                txtQq.setText("未绑定");
            }
            if (t.getDatas().getWb().getAccount_no() != null) {
                txtSina.setText(t.getDatas().getWb().getNick_name());
            } else {
                txtSina.setText("未绑定");
            }
            if (t.getDatas().getWx().getAccount_no() != null) {
                txtWx.setText(t.getDatas().getWx().getNick_name());
            } else {
                txtWx.setText("未绑定");
            }
        }else {
            ToastUitl.show(t.getMsg(), 1000);
        }

    }

    @Override
    public void BaseResultMsg_v(BaseResult t) {
        if (t.getResultCode() == 1) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            mPresenter.Account_p(hashMap);

        }else {
            ToastUitl.show(t.getMsg(),1000);
        }

    }

    private void umAuth(SHARE_MEDIA shareMedia) {
        if (!UMShareAPI.get(this).get(this).isInstall(this, shareMedia)) {
            ToastUitl.showShort("没有安装" + shareMedia.toString());
            return;
        }
        //YZYApplication.umShareAPI().doOauthVerify(this,shareMedia, umAuthListener);
        UMShareAPI.get(this).getPlatformInfo(this, shareMedia, this);
    }
    private void ShowAlertDialog (int type,String name){
        AlertDialog dialog;
        dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage(String.format(getString(R.string.ubing_thirdparty),name));
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialog1, which) -> {
            if(type==0){
                uBindThird(AccountData, "qq");
            }else if(type==1){
                uBindThird(AccountData, "wb");
            }else if(type==2){
                uBindThird(AccountData, "wx");
            }


        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialog12, which) -> {
            return;
        });
        dialog.show();



    };


    @OnClick({R.id.liner_qq, R.id.liner_wb, R.id.liner_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liner_qq:
                if (AccountData.getQq().getAccount_no() == null) {
                    umAuth(SHARE_MEDIA.QQ);
                } else {
                    ShowAlertDialog(0,"QQ");
                }
                break;
            case R.id.liner_wb:
                if (AccountData.getWb().getAccount_no() == null) {
                    umAuth(SHARE_MEDIA.SINA);
                } else {
                    ShowAlertDialog(1,"微博");
                }

                break;
            case R.id.liner_wx:
                if (AccountData.getWx().getAccount_no() == null) {
                    umAuth(SHARE_MEDIA.WEIXIN);
                } else {
                    ShowAlertDialog(2,"微信");
                }

                break;
        }
    }

    private void uBindThird(AccountEntity.DatasBean data, String third) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("from_type", third);
        if (third.equals("qq")) {
            hashMap.put("realuser_id", data.getQq().getRealuser_id());
            hashMap.put("account_no", data.getQq().getAccount_no());
        } else if (third.equals("wx")) {
            hashMap.put("realuser_id", data.getWx().getRealuser_id());
            hashMap.put("account_no", data.getWx().getAccount_no());
        } else if (third.equals("wb")) {
            hashMap.put("realuser_id", data.getWb().getRealuser_id());
            hashMap.put("account_no", data.getWb().getAccount_no());
        }
        mPresenter.UBindThird_p(hashMap);
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (share_media.toString().equals("QQ")) {
            hashMap.put("from_type", "qq");
            hashMap.put("uniqueness_id", map.get("unionid"));
        } else if (share_media.toString().equals("WEIXIN")) {
            hashMap.put("from_type", "wx");
            hashMap.put("uniqueness_id", map.get("uid"));
            hashMap.put("openid", map.get("uid"));
        } else if (share_media.toString().equals("SINA")) {
            hashMap.put("from_type", "wb");
            hashMap.put("uniqueness_id", map.get("uid"));

        }
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("nick_name", map.get("name"));
        hashMap.put("head_img", map.get("iconurl"));
        hashMap.putAll(PutUtils.Put(hashMap));
        mPresenter.BindThird_p(hashMap);
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.liner_update_pass, R.id.liner_update_email, R.id.liner_update_phone,R.id.txt_update_pass})
    public void onViewClicked1(View view) {
        switch (view.getId()) {
            case R.id.liner_update_pass:
                if(AccountData.getPassword()==null){
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isNo",true);
                    bundle.putString("type","pass");
                    bundle.putParcelable("bean",AccountData);
                    startActivityForResult(UpdateAccountActivity.class,bundle,UPDATE);
                }else {
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isNo",false);
                    bundle.putString("type","pass");
                    bundle.putParcelable("bean",AccountData);
                    startActivityForResult(UpdateAccountActivity.class,bundle,UPDATE);
                }
                break;
            case R.id.txt_update_pass:
                if(AccountData.getPassword()==null){
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isNo",true);
                    bundle.putString("type","pass");
                    bundle.putParcelable("bean",AccountData);
                    startActivityForResult(UpdateAccountActivity.class,bundle,UPDATE);
                }else {
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isNo",false);
                    bundle.putString("type","pass");
                    bundle.putParcelable("bean",AccountData);
                    startActivityForResult(UpdateAccountActivity.class,bundle,UPDATE);
                }
                break;
            case R.id.liner_update_email:
                if(AccountData.getEmail().getAccount_no()==null){
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isNo",true);
                    bundle.putString("type","email");
                    bundle.putParcelable("bean",AccountData);
                    startActivityForResult(UpdateAccountActivity.class,bundle,UPDATE);
                }else {
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isNo",false);
                    bundle.putString("type","email");
                    bundle.putParcelable("bean",AccountData);
                    startActivityForResult(UpdateAccountActivity.class,bundle,UPDATE);

                }
                break;
            case R.id.liner_update_phone:
                if(AccountData.getPhone().getAccount_no()==null){
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isNo",true);
                    bundle.putString("type","phone");
                    bundle.putParcelable("bean",AccountData);
                    startActivityForResult(UpdateAccountActivity.class,bundle,UPDATE);
                }else {
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isNo",false);
                    bundle.putString("type","phone");
                    bundle.putParcelable("bean",AccountData);
                    startActivityForResult(UpdateAccountActivity.class,bundle,UPDATE);

                }
                break;
        }
    }
}
