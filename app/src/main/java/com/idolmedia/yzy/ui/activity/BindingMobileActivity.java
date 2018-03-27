package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.idolmedia.yzy.ui.mvp.contract.BindingMobileContract;
import com.idolmedia.yzy.ui.mvp.model.BindingMobileModel;
import com.idolmedia.yzy.ui.mvp.presenter.BindingMobilePresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.security.Md5Security;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.JsonUtils;
import com.mumu.common.utils.RxCountDown;
import com.mumu.common.utils.ToastUitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/11/8.
 * 绑定手机号
 */

public class BindingMobileActivity extends BaseActivity<BindingMobilePresenter,BindingMobileModel> implements BindingMobileContract.View {
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
    @BindView(R.id.txt_countrycode)
    TextView txtCountrycode;
    @BindView(R.id.liner_countrycode)
    LinearLayout linerCountrycode;
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
    @BindView(R.id.txt_noid)
    TextView txtNoid;
    @BindView(R.id.txt_newwork_law)
    TextView txtNewworkLaw;
    @BindView(R.id.login_btn)
    Button loginBtn;
    HashMap<String, Object> hashMap;
    String countryCode="86";

    @Override
    public int getLayoutId() {
        return R.layout.activity_bindingmobile;
    }

    @Override
    public void initPresenter() {
      mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.bind_mobile));
        Bundle bundle = getIntent().getExtras();
        HashMapEntity mapEntity = (HashMapEntity) bundle.get("hashMap");
        hashMap = new HashMap<>();
        hashMap.putAll(mapEntity.getHashMap());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.liner_countrycode, R.id.login_btn,R.id.txt_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
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
            case R.id.login_btn:
                Login();
                break;
            case  R.id.txt_code:
                GetCode();
                break;

        }
    }

    private  void GetCode(){
        if(TextUtils.isEmpty(editName.getText().toString().trim())){
            ToastUitl.showLong(getString(R.string.please_phone_mail));
            return;
        }else {
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
        }
        HashMap<String,Object> map =new HashMap<>();
        if(FormatUtil.isEmail(editName.getText().toString().trim())){
            map.put("account_type","1");
        }else {
            map.put("account_type","0");
            map.put("country_code",countryCode);
        }
        map.put("account_no",editName.getText().toString().trim());
        map.put("sms_type","0");
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Code_p(map);

    }
    private void Login(){
    if(TextUtils.isEmpty(editName.getText().toString().trim())){
        ToastUitl.showLong(getString(R.string.please_phone_mail));
    }else if(TextUtils.isEmpty(editCode.getText().toString().trim())){
        ToastUitl.showLong(getString(R.string.pleasecode));
    }else {
                hashMap.put("country_code",countryCode);
                hashMap.put("account_no",editName.getText().toString().trim());
                hashMap.put("vf_code",editCode.getText().toString().trim());
                mPresenter.BindLogin_p(hashMap);
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
    public void BindLogin_v(UserEntity userEntity) {
    /*    int resultCode = JsonUtils.getIntValue(t, "resultCode");
        String msg = JsonUtils.getValue(t, "msg");
  */
        ToastUitl.show(userEntity.getMsg(), 1000);
        if(userEntity.getResultCode()==1){
            SharedUtil.saveObjecToString("UserEntity", userEntity);
            startActivity(MainActivity.class);
            finish();

        }
       /* if (resultCode == 1) {
            finish();
        }*/

    }

    @Override
    public void Code_v(BaseResult t) {
       ToastUitl.show(t.getMsg(),1000);
    }
}
