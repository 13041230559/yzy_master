package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.MainActivity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.Countryentity;
import com.idolmedia.yzy.entity.HashMapEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.RegisterContract;
import com.idolmedia.yzy.ui.mvp.model.RegisterModel;
import com.idolmedia.yzy.ui.mvp.presenter.RegisterPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.security.Md5Security;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.LogUtil;
import com.mumu.common.utils.RxCountDown;
import com.mumu.common.utils.TimeUtil;
import com.mumu.common.utils.ToastUitl;
import com.umeng.message.PushAgent;
import com.umeng.socialize.sina.helper.MD5;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;
import rx.Subscriber;
import rx.functions.Action0;

import static com.mumu.common.base.BaseApplication.getAppContext;

/**
 * Created by Administrator on 2017/10/31.
 */

public class PhoneRegisterActivity extends BaseActivity<RegisterPresenter,RegisterModel>implements RegisterContract.View {
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
    Link link;
    @BindView(R.id.login_image)
    ImageView loginImage;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.liner_user)
    LinearLayout linerUser;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.txt_code)
    TextView txtCode;
    @BindView(R.id.liner_code)
    LinearLayout linerCode;
    @BindView(R.id.edit_pass)
    EditText editPass;
    /*  @BindView(R.id.select_pass)
      ImageView selectPass;*/
    @BindView(R.id.line_password)
    LinearLayout linePassword;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.txt_noid)
    TextView txtNoid;
    @BindView(R.id.txt_countrycode)
    TextView txtCountrycode;
    @BindView(R.id.liner_countrycode)
    LinearLayout linerCountrycode;
    private  boolean isMailRegister=true;
    private String countryCode="86";
    private String smsCode="";
    String registername="";
    HashMap<String, Object> hashMap;
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.registrmobile));
        rightTxt.setVisibility(View.VISIBLE);
        rightImg.setVisibility(View.GONE);
        rightTxt.setText(getString(R.string.registmail));
        link = new Link(getString(R.string.user_agreement))
                .setTextColor(getResources().getColor(R.color.fc4f4f))                  // optional, defaults to holo blue
                .setTextColorOfHighlightedLink(getResources().getColor(R.color.fc4f4f)) // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                                     // optional, defaults to .15f
                .setUnderlined(false)                                       // optional, defaults to true
                .setBold(false)
                .setOnClickListener(v -> startActivity(ServiceAgreementActivity.class));
        LinkBuilder.on(txtNoid)
                .addLink(link)
                .build(); // creat


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    @OnClick({R.id.image_close, R.id.right_txt, R.id.liner_countrycode, R.id.txt_code, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.right_txt:
                startActivity(MailRegisterActivity.class);
                break;
            case R.id.liner_countrycode:
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
            case R.id.txt_code:
                HashMap<String,Object>codemap=new HashMap();
                registername=    editName.getText().toString().trim();
                if(TextUtils.isEmpty(registername)){
                    ToastUitl.showLong(getString(R.string.please_phone_mail));
                }else{
                    RxCountDown.countdown(60)
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
                                    txtCode.setText(integer+"s");
                                }
                            });

                    if(FormatUtil.isEmail(registername)){
                        codemap.put("account_type","1");
                    }else {
                        codemap.put("account_type","0");
                        codemap.put("country_code",countryCode);
                    }
                    codemap.put("account_no",registername);
                    codemap.put("sms_type","0");
                    codemap.put("FKEY",PutUtils.FKEY());
                    mPresenter.code_p(codemap);
                }
                break;
            case R.id.login_btn:
                if(TextUtils.isEmpty(registername)){
                    ToastUitl.showLong("手机号不能为空");
                    return;
                }else if(TextUtils.isEmpty(editCode.getText().toString().trim())) {
                    ToastUitl.showLong("验证码不能为空");
                    return;
                }else if(TextUtils.isEmpty(editPass.getText().toString().trim())){

                    ToastUitl.showLong("密码不能为空");
                    return;
                }else {
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("account_type","0");
                    map.put("country_code",countryCode);
                    map.put("account_no",registername);
                    map.put("vf_code",smsCode);
                    map.put("country_code",countryCode);
                    map.put("password",Md5Security.getMD5(editPass.getText().toString().trim()));
                    mPresenter.register_p( PutUtils.Put(map));
                }
                break;
        }
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
    public void register_v(UserEntity userEntity) {
      ToastUitl.showLong(userEntity.getMsg());
       if (userEntity.getResultCode() == 1) {
            SharedUtil.saveObjecToString("UserEntity", userEntity);
           startActivity(MainActivity.class);
            finish();
        }
    }

    @Override
    public void code_v(BaseResult t) {
        ToastUitl.showLong(t.getMsg());
        if(t.getResultCode()==1){
            smsCode=t.getVf_code();
        }
    }
}
