package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.RetrievalPassContract;
import com.idolmedia.yzy.ui.mvp.model.RetrievalPassModel;
import com.idolmedia.yzy.ui.mvp.presenter.RetrievalPassPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.security.Md5Security;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.RxCountDown;
import com.mumu.common.utils.ToastUitl;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2017/10/31.
 */

public class RetrievalPassActivity extends BaseActivity<RetrievalPassPresenter,RetrievalPassModel> implements RetrievalPassContract.View {

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
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.txt_code)
    TextView txtCode;
    @BindView(R.id.liner_code)
    LinearLayout linerCode;
    @BindView(R.id.edit_pass)
    EditText editPass;
    @BindView(R.id.line_password)
    LinearLayout linePassword;
    @BindView(R.id.edit_confpass)
    EditText editConfpass;
    @BindView(R.id.line_confpass)
    LinearLayout lineConfpass;
    @BindView(R.id.login_btn)
    Button loginBtn;
    Subscription subscription;
    private  String countryCode="86";
    @Override
    public int getLayoutId() {
        return R.layout.activity_retrievalpass;
    }

    @Override
    public void initPresenter() {
     mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.passwordretrieval));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.txt_code, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_code:
                GetCode();
                break;
            case R.id.login_btn:
                String username=editName.getText().toString().trim();
                String pass=editPass.getText().toString().trim();
                String code=editCode.getText().toString().trim();
                String confpass=editConfpass.getText().toString().trim();
                Login(username,pass,confpass,code);
                break;
        }
    }
    private  void  GetCode(){
        if(TextUtils.isEmpty(editName.getText().toString())){
            ToastUitl.showLong(getString(R.string.please_phone_mail));
        }else {
            subscription=    RxCountDown.countdown(60)
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
            HashMap<String,Object> map =new HashMap<>();
            if(FormatUtil.isEmail(editName.getText().toString().trim())){
                map.put("account_type","1");
            }else {
                map.put("account_type","0");
                map.put("country_code",countryCode);
            }
            map.put("account_no",editName.getText().toString().trim());
            map.put("sms_type","2");
            map.put("FKEY", Md5Security.getMD5("api"+FormatUtil.dateToStrLong1(new java.util.Date())+"yizhiyu"));
            mPresenter.Code_p(map);
        }

    }
    private  void Login(String username,String pass,String confpass,String code){
        if(TextUtils.isEmpty(username)){
            ToastUitl.showLong(getString(R.string.please_phone_mail));
        }else if(TextUtils.isEmpty(code)){
            ToastUitl.showLong(getString(R.string.pleasecode));
        }else  if(TextUtils.isEmpty(pass)||TextUtils.isEmpty(confpass)){
            ToastUitl.showLong(getString(R.string.pleasepass));
        }else if(!FormatUtil.isSixAndrTwentyPass(pass)&&!FormatUtil.isSixAndrTwentyPass(confpass)) {
            ToastUitl.showLong(getString(R.string.please_six_twenty_pass));
        }else if(!TextUtils.equals(pass,confpass)) {
            ToastUitl.showLong("两次密码不一致");
        }else {
            HashMap map =new HashMap();
            map.put("account_no",username);
            map.put("password",Md5Security.getMD5(pass));
            map.put("vf_code",code);
            map.put("FKEY", Md5Security.getMD5("api"+ FormatUtil.dateToStrLong1(new java.util.Date())+"yizhiyu"));
            mPresenter.RetrievalPass_p(map);
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

    }

    @Override
    public void RetrievalPass_v(BaseResult t) {
        ToastUitl.showLong(t.getMsg());
        if(t.getResultCode()==1){
            finish();
        }

    }

    @Override
    public void Code_v(BaseResult t) {
        ToastUitl.showLong(t.getMsg());
    }
}
