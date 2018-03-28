package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.Countryentity;
import com.idolmedia.yzy.ui.mvp.contract.SavePhoneContract;
import com.idolmedia.yzy.ui.mvp.model.SavePhoneModel;
import com.idolmedia.yzy.ui.mvp.presenter.SavePhonePresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
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
import rx.Subscription;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/20 15:35
 * 描述：
 */

public class SavePhoneActivity extends BaseActivity<SavePhonePresenter, SavePhoneModel> implements SavePhoneContract.View {
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
    int type;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.liner_nickname)
    LinearLayout linerNickname;
    @BindView(R.id.cy_number)
    TextView cyNumber;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.txt_code)
    TextView txtCode;
    @BindView(R.id.liner_code)
    LinearLayout linerCode;
    @BindView(R.id.liner_phone)
    LinearLayout linerPhone;
    String country_code = "86";
    Subscription subscription;
    String Phone, Code;
    boolean updatename;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_phone;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra("type", 0);
        updatename = getIntent().getBooleanExtra("updatename", false);
        tvTitle.setText("保存手机号");
        rightTxt.setText("保存");
        leftTxt.setText("取消");
        rightTxt.setVisibility(View.VISIBLE);
        rightImg.setVisibility(View.GONE);
        if (type == 1) {
            tvTitle.setText("认证昵称");
            String nickname = getIntent().getStringExtra("nickname");
            editName.setText(nickname);
        } else if (type == 2) {
            tvTitle.setText("认证手机号");
            linerPhone.setVisibility(View.VISIBLE);
            linerNickname.setVisibility(View.GONE);
            String phone = getIntent().getStringExtra("phone");
            editPhone.setText(phone);
        }/* else if (type == 1) {
            tvTitle.setText("填写手机号");
        }*/

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txt_code)
    public void onViewClicked1() {
        GetCode();
    }

    private void GetCode() {
        if (TextUtils.isEmpty(editPhone.getText().toString())) {
            ToastUitl.showLong(getString(R.string.please_phone_mail));
        } else {
            subscription = RxCountDown.countdown(60)
                    .doOnSubscribe(() -> {
                        txtCode.setEnabled(false);
                        txtCode.setBackground(getResources().getDrawable(R.drawable.save_code_f));
                    })
                    .subscribe(new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {
                            // appendLog("计时完成");
                            txtCode.setText("重新获取");
                            txtCode.setEnabled(true);
                            txtCode.setBackground(getResources().getDrawable(R.drawable.save_code));
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
            if (FormatUtil.isEmail(editPhone.getText().toString().trim())) {
                map.put("account_type", "1");
            } else {
                map.put("account_type", "0");
                map.put("country_code", country_code);
            }
            map.put("account_no", editPhone.getText().toString().trim());
            map.put("sms_type", "0");
            map.put("FKEY", PutUtils.FKEY());
            mPresenter.GetCode_p(map);
        }

    }



/*
    @OnClick(R.id.right_txt)
    public void onViewClicked() {
        if (updatename) {
        } else {
            if (type == 1) {
                Intent intent = new Intent();
                intent.putExtra("type", type);
                intent.putExtra("name", editName.getText().toString().trim());
                setResult(Activity.RESULT_OK, intent);
                finish();
            } else if (type == 2) {
                if (TextUtils.isEmpty(editPhone.getText().toString().trim()) || TextUtils.isEmpty(editCode.getText().toString().trim())) {
                    ToastUitl.showLong("请输入信息");
                    return;
                }
                Phone = editPhone.getText().toString().trim();
                Code = editCode.getText().toString().trim();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("FKEY", PutUtils.FKEY());
                hashMap.put("country_code", country_code);
                hashMap.put("account_no", Phone);
                hashMap.put("vf_code", Code);
                mPresenter.Code_p(hashMap);
            }


          */
/*  Intent intent = new Intent();
            intent.putExtra("type", type);
            intent.putExtra("phone", editPhone.getText().toString().trim());
            setResult(Activity.RESULT_OK, intent);
            finish();*//*


        }
    */
/*    Intent intent = new Intent();
        intent.putExtra("type", type);
        intent.putExtra("name", editName.getText().toString().trim());
        setResult(Activity.RESULT_OK, intent);
        finish();*//*

    }
*/

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
    public void code_v(String t) {
        int resultCode = JsonUtils.getIntValue(t, "resultCode");
        String msg = JsonUtils.getValue(t, "msg");
        if (resultCode == 1) {
            Intent intent = new Intent();
            intent.putExtra("phone", Phone);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            ToastUitl.show(msg, 1000);
        }
    }

    @Override
    public void GetCode_v(BaseResult t) {
        ToastUitl.show(t.getMsg(), 1000);
        if (t.getResultCode() == 1) {
        }
    }

    @OnClick({R.id.image_close, R.id.right_txt,R.id.cy_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case  R.id.cy_number:
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
                    cyNumber.setText(item.getCountryname()+"+"+item.getCountrycode());
                    country_code=String.valueOf(item.getCountrycode());
                });
                picker.show();
                break;

            case R.id.right_txt:
                if (updatename) {
                } else {
                    if (type == 1) {
                        Intent intent = new Intent();
                        intent.putExtra("type", type);
                        intent.putExtra("name", editName.getText().toString().trim());
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    } else if (type == 2) {
                        if (TextUtils.isEmpty(editPhone.getText().toString().trim()) || TextUtils.isEmpty(editCode.getText().toString().trim())) {
                            ToastUitl.showLong("请输入信息");
                            return;
                        }
                        Phone = editPhone.getText().toString().trim();
                        Code = editCode.getText().toString().trim();
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("FKEY", PutUtils.FKEY());
                        hashMap.put("country_code", country_code);
                        hashMap.put("account_no", Phone);
                        hashMap.put("vf_code", Code);
                        mPresenter.Code_p(hashMap);
                    }
                break;
        }
    }
}
}
