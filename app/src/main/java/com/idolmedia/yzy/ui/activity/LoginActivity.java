package com.idolmedia.yzy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.Countryentity;
import com.idolmedia.yzy.entity.HashMapEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.adapter.ShareAdapter;
import com.idolmedia.yzy.ui.mvp.contract.LoginContract;
import com.idolmedia.yzy.ui.mvp.model.LoginModel;
import com.idolmedia.yzy.ui.mvp.presenter.LoginPresenter;
import com.idolmedia.yzy.utlis.AndroidBug54971Workaround;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.security.Md5Security;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.RxCountDown;
import com.mumu.common.utils.ToastUitl;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2017/10/30.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements UMAuthListener, LoginContract.View {

//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖镇楼                 BUG辟易

    Link link;
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
    @BindView(R.id.login_image)
    ImageView loginImage;
    @BindView(R.id.view_isphoneregister)
    View viewIsphoneregister;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.liner_user)
    LinearLayout linerUser;
    @BindView(R.id.edit_pass)
    EditText editPass;
    @BindView(R.id.select_pass)
    ImageView selectPass;
    @BindView(R.id.line_password)
    LinearLayout linePassword;
    @BindView(R.id.txt_forgetpass)
    TextView txtForgetpass;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.txt_noid)
    TextView txtNoid;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.txt_code)
    TextView txtCode;
    @BindView(R.id.liner_code)
    LinearLayout linerCode;
    @BindView(R.id.recyclerView_share)
    RecyclerView recyclerViewShare;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.iamge_user)
    ImageView iamgeUser;
    @BindView(R.id.txt_countrycode)
    TextView txtCountrycode;
    @BindView(R.id.liner_countrycode)
    LinearLayout linerCountrycode;
    private boolean isShowpass = true;
    private boolean isCodeLogin = true;
    Subscription subscription;
    private String name, pass, code;
    private String countryCode = "86";
    ShareAdapter shareAdapter;
    HashMap<String, Object> hashMap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        AndroidBug54971Workaround.assistActivity(findViewById(android.R.id.content));
      /*  if (UserLoginUtil.loginEntity() != null) {
            if (!TextUtils.isEmpty(UserLoginUtil.loginEntity().getDatas().getAccount_no())) {
                editName.setText(UserLoginUtil.loginEntity().getDatas().getAccount_no());
            }
            if (!TextUtils.isEmpty(UserLoginUtil.loginEntity().getDatas().getPassword())) {
                editPass.setText(UserLoginUtil.loginEntity().getDatas().ge());
            }
        }
*/
        // editPass.setText("123456789");
        tvTitle.setText(getString(R.string.login));
        rightImg.setVisibility(View.GONE);
        rightTxt.setVisibility(View.VISIBLE);
        rightTxt.setText(getString(R.string.smslogin));
        link = new Link(getString(R.string.imregistr))
                .setTextColor(getResources().getColor(R.color.fc4f4f))                  // optional, defaults to holo blue
                .setTextColorOfHighlightedLink(getResources().getColor(R.color.fc4f4f)) // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                                     // optional, defaults to .15f
                .setUnderlined(false)
                .setBold(false)
                .setOnClickListener(v -> startActivity(PhoneRegisterActivity.class));
        LinkBuilder.on(txtNoid)
                .addLink(link)
                .build(); // creat
        List<Integer> sharelist = new ArrayList<>();
        sharelist.add(R.mipmap.icon_qq_yzy);
        sharelist.add(R.mipmap.icon_whchat_yzy);
        sharelist.add(R.mipmap.icon_sina_yzy);
        shareAdapter = new ShareAdapter();
        recyclerViewShare.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewShare.setAdapter(shareAdapter);
        shareAdapter.setNewData(sharelist);
        shareAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == 0) {
                umAuth(SHARE_MEDIA.QQ);

            } else if (position == 2) {
                umAuth(SHARE_MEDIA.SINA);
            } else if (position == 1) {
                umAuth(SHARE_MEDIA.WEIXIN);
            }/*else if(position==3){
                umAuth(SHARE_MEDIA.FACEBOOK);
            }else if(position==4){
                umAuth(SHARE_MEDIA.TWITTER);
            }else if(position==5){
                umAuth(SHARE_MEDIA.INSTAGRAM);
            }*/

        });
    }

    private void umAuth(SHARE_MEDIA shareMedia) {
        // if(!YZYApplication.umShareAPI().get(this).isInstall(this,shareMedia)){ToastUitl.showShort("没有安装"+shareMedia.toString());return;}
        //YZYApplication.umShareAPI().doOauthVerify(this,shareMedia, umAuthListener);
        UMShareAPI.get(this).getPlatformInfo(this, shareMedia, this);
    }

    /**
     * @param platform 平台类型
     * @descrption 分享开始的回调
     */
    @Override
    public void onStart(SHARE_MEDIA platform) {
    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        hashMap = new HashMap<>();
        hashMap.put("nick_name", map.get("name"));
        hashMap.put("head_img", map.get("iconurl"));
        hashMap.putAll(PutUtils.Put(hashMap));
        if (share_media.toString().equals("QQ")) {
            hashMap.put("login_type", "qq");
            hashMap.put("uniqueness_id", map.get("unionid"));
            Log.e("QQ_UID", map.get("unionid").toString());
        } else if (share_media.toString().equals("WEIXIN")) {
            hashMap.put("login_type", "wx");
            hashMap.put("uniqueness_id", map.get("uid"));
            hashMap.put("openid", map.get("uid"));
        } else if (share_media.toString().equals("SINA")) {
            hashMap.put("login_type", "wb");
            hashMap.put("uniqueness_id", map.get("uid"));
        }
        mPresenter.ThirdPartyLogin_p(hashMap);
    }

    /**
     * @param share_media 平台类型
     * @param throwable   错误原因
     * @descrption 分享失败的回调
     */
    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        ToastUitl.showShort("登录失败" + throwable.getMessage());
    }

    /**
     * @param share_media 平台类型
     * @descrption 分享取消的回调
     */
    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        ToastUitl.showShort("登录取消");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.right_txt, R.id.select_pass, R.id.login_btn, R.id.txt_forgetpass, R.id.txt_code,R.id.txt_countrycode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                if (isCodeLogin) {
                    isCodeLogin = !isCodeLogin;
                    finish();
                } else {
                    linerCountrycode.setVisibility(View.INVISIBLE);
                    txtForgetpass.setVisibility(View.VISIBLE);
                    iamgeUser.setImageResource(R.mipmap.icon_log_me_yzy);
                    recyclerViewShare.setVisibility(View.VISIBLE);
                    tvTitle.setText(getString(R.string.login));
                    rightTxt.setVisibility(View.VISIBLE);
                    linerCode.setVisibility(View.GONE);
                    linePassword.setVisibility(View.VISIBLE);
                    isCodeLogin = !isCodeLogin;
                    if (subscription != null) {
                        subscription.unsubscribe();
                    }
                    txtCode.setText("获取验证码");
                    txtCode.setEnabled(true);
                    txtCode.setBackground(getResources().getDrawable(R.drawable.code_backround));
                    //  finish();
                }
                break;
            case R.id.right_txt:
                if (isCodeLogin) {
                    linerCountrycode.setVisibility(View.VISIBLE);
                    txtForgetpass.setVisibility(View.INVISIBLE);
                    iamgeUser.setImageResource(R.mipmap.icon_log_phone_yzy);
                    recyclerViewShare.setVisibility(View.INVISIBLE);
                    tvTitle.setText(getString(R.string.smslogin));
                    isCodeLogin = !isCodeLogin;
                    rightTxt.setVisibility(View.INVISIBLE);
                    linerCode.setVisibility(View.VISIBLE);
                    linePassword.setVisibility(View.GONE);
                } else {
                    isCodeLogin = !isCodeLogin;
                    linerCode.setVisibility(View.GONE);
                    linePassword.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.select_pass:
                if (isShowpass) {
                    selectPass.setImageResource(R.mipmap.icon_eyeing_yzy);
                    editPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isShowpass = !isShowpass;

                } else {
                    selectPass.setImageResource(R.mipmap.icon_eye_yzy);
                    editPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isShowpass = !isShowpass;
                }
                break;
            case R.id.login_btn:
                String username = editName.getText().toString().trim();
                String password = editPass.getText().toString().trim();
                String code = editCode.getText().toString().trim();
                Login(username, password, code);
                break;
            case R.id.txt_code:
                GetCode();
                break;
            case R.id.txt_forgetpass:
                startActivity(RetrievalPassActivity.class);
                break;

            case R.id.txt_countrycode:
                List<Countryentity> data = new ArrayList<>();
                data.add(new Countryentity(86,"中国大陆"));
                data.add(new Countryentity( 886,"中国台湾"));
                data.add(new Countryentity(852 ,"中国香港"));
                data.add(new Countryentity(853,"中国澳门"));
                data.add(new Countryentity(60,"马来西亚"));
                data.add(new Countryentity(82,"韩国"));
                data.add(new Countryentity(61,"澳大利亚"));
                data.add(new Countryentity(55,"巴西"));
                data.add(new Countryentity(1,"加拿大"));
                data.add(new Countryentity(1,"美国"));
                data.add(new Countryentity( 81 ,"日本"));
                SinglePicker<Countryentity> picker = new SinglePicker<>(this, data);
                picker.setCanceledOnTouchOutside(false);
                picker.setSelectedIndex(0);
                picker.setTextSize(18);
                picker.setTextColor(getResources().getColor(R.color.c3));
                picker.setCycleDisable(false);
                picker.setOnItemPickListener((index, item) -> {
                    txtCountrycode.setText(item.getCountryname()+"+"+item.getCountrycode());
                    countryCode=String.valueOf(item.getCountrycode());
                });
                picker.show();
                break;
        }
    }

    private void Login(String username, String pass, String code) {
        if (!isCodeLogin) {
            if (TextUtils.isEmpty(editName.getText().toString().trim())) {
                ToastUitl.showLong(getString(R.string.please_phone_mail));
            } else if (TextUtils.isEmpty(editCode.getText().toString().trim())) {
                ToastUitl.showLong(getString(R.string.pleasecode));
            } else {
                HashMap map = new HashMap();
                map.put("account_no", username);
                map.put("vf_code", code);
                map.put("country_code",countryCode);
                mPresenter.CodeLogin_p(PutUtils.Put(map));
            }
        } else {
            if (TextUtils.isEmpty(editName.getText().toString().trim())) {
                ToastUitl.showLong(getString(R.string.please_phone_mail));
            } else if (TextUtils.isEmpty(editPass.getText().toString().trim())) {
                ToastUitl.showLong(getString(R.string.pleasepass));
            } else {
                HashMap map = new HashMap();
                map.put("account_no", username);
                if (pass.length() == 32) {
                    map.put("password", pass);
                } else {
                    map.put("password", Md5Security.getMD5(pass));
                }
                //  map.put("password", Md5Security.getMD5(pass));
                mPresenter.Login_p(PutUtils.Put(map));
            }

        }

    }

    private void GetCode() {

        if (TextUtils.isEmpty(editName.getText().toString())) {
            ToastUitl.showLong(getString(R.string.please_phone_mail));
        } else {
            subscription = RxCountDown.countdown(60)
                    .doOnSubscribe(() -> {
                        txtCode.setEnabled(false);
                        txtCode.setBackground(getResources().getDrawable(R.drawable.code_backroundf));
                    })
                    .subscribe(new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {
                            // appendLog("计时完成");
                            txtCode.setText("重新获取");
                            txtCode.setEnabled(true);
                            txtCode.setBackground(getResources().getDrawable(R.drawable.code_backround));

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Integer integer) {
                            txtCode.setText(integer + "s");
                        }
                    });
            HashMap<String, Object> map = new HashMap<>();
            if (FormatUtil.isEmail(editName.getText().toString().trim())) {
                map.put("account_type", "1");
            } else {
                map.put("account_type", "0");
                map.put("country_code", countryCode);
            }
            map.put("account_no", editName.getText().toString().trim());
            map.put("sms_type", "1");
            map.put("FKEY", Md5Security.getMD5("api" + FormatUtil.dateToStrLong1(new Date()) + "yizhiyu"));
            mPresenter.Code_p(map);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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


    @Override
    public void Login_v(UserEntity userEntity) {
        ToastUitl.showLong(userEntity.getMsg());
        if (userEntity.getResultCode() == 1) {
            SharedUtil.saveObjecToString("UserEntity", userEntity);
            //  startActivity(MainActivity.class);
            finish();
        } else if (userEntity.getResultCode() == 2) {
            Bundle bundle = new Bundle();
            HashMapEntity hashMapEntity = new HashMapEntity();
            hashMapEntity.setHashMap(hashMap);
            bundle.putSerializable("hashMap", hashMapEntity);
            startActivity(BindingMobileActivity.class, bundle);
        }


    }

    @Override
    public void code_v(BaseResult t) {
        if (t.getResultCode() == 1) {
            ToastUitl.showLong("验证码已发送");
        } else {
            ToastUitl.showLong(t.getMsg());
        }

    }



}


