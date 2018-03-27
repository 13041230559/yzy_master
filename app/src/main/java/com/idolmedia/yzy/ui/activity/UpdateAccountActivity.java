package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.AccountEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.mvp.contract.UpdateAccountContract;
import com.idolmedia.yzy.ui.mvp.model.UpdateAccountModel;
import com.idolmedia.yzy.ui.mvp.presenter.UpdateAccountPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
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
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/27 11:53
 * 描述： 账户安全,添加修改
 */

public class UpdateAccountActivity extends BaseActivity<UpdateAccountPresenter, UpdateAccountModel> implements UpdateAccountContract.View {
    boolean isNo;
    String type;
    AccountEntity.DatasBean bean;
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
    @BindView(R.id.txt_country)
    TextView txtCountry;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_phone_code)
    EditText editPhoneCode;
    @BindView(R.id.txt_code)
    TextView txtCode;
    @BindView(R.id.liner_code)
    LinearLayout linerCode;
    @BindView(R.id.txt_this_phone)
    TextView txtThisPhone;
    @BindView(R.id.linear_layout_phone)
    LinearLayout linearLayoutPhone;
    @BindView(R.id.edit_y_pass)
    EditText editYPass;
    @BindView(R.id.select_y_pass)
    ImageView selectYPass;
    @BindView(R.id.edit_n_pass)
    EditText editNPass;
    @BindView(R.id.select_n_pass)
    ImageView selectNPass;
    @BindView(R.id.edit_nn_pass)
    EditText editNnPass;
    @BindView(R.id.select_nn_pass)
    ImageView selectNnPass;
    @BindView(R.id.linear_layout_pass)
    LinearLayout linearLayoutPass;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_email_code)
    EditText editEmailCode;
    @BindView(R.id.txt_email_code)
    TextView txtEmailCode;
    @BindView(R.id.linear_layout_email)
    LinearLayout linearLayoutEmail;
    @BindView(R.id.txt_go)
    TextView txtGo;
    @BindView(R.id.liner_old_pass)
    LinearLayout linerOldPass;
    Subscription subscription;
    String code = "";
       boolean isShowypass,isShownpass,isShownnypass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_account;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }
    @Override
    public void initView() {
        isNo = getIntent().getBooleanExtra("isNo", false);
        type = getIntent().getStringExtra("type");
        bean = getIntent().getParcelableExtra("bean");
        if (TextUtils.equals("pass", type)) {
            if (!isNo) {
                tvTitle.setText("修改密码");
            } else {
                tvTitle.setText("新增密码");
                linerOldPass.setVisibility(View.GONE);
            }
            linearLayoutPass.setVisibility(View.VISIBLE);

        } else if (TextUtils.equals("phone", type)) {
            if (!isNo) {
                tvTitle.setText("修改手机号");
                txtThisPhone.setText("当前手机号:" + bean.getPhone().getNick_name());
            } else {
                tvTitle.setText("新增手机号");
                txtThisPhone.setVisibility(View.GONE);
            }
            linearLayoutPhone.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals("email", type)) {
            if (!isNo) {
                tvTitle.setText("修改邮箱");
            } else {
                tvTitle.setText("新增邮箱");
            }
            linearLayoutEmail.setVisibility(View.VISIBLE);
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
        ToastUitl.show(msg, 1000);
    }

    @Override
    public void baseResult_v(BaseResult t) {
        if (t.getResultCode() == 1) {
            ToastUitl.show(t.getMsg(), 1000);

        } else {
            ToastUitl.show(t.getMsg(), 1000);
        }

    }

    @Override
    public void updatePass_v(BaseResult t) {
        if (t.getResultCode() == 1) {
            ToastUitl.show(t.getMsg(), 1000);
            finish();
            startActivity(LoginActivity.class);

        } else {
            ToastUitl.show(t.getMsg(), 1000);
        }
    }

    @Override
    public void code_v(BaseResult t) {
        if (t.getResultCode() == 1) {
            ToastUitl.show("验证码已发出", 1000);
        } else {
            ToastUitl.show(t.getMsg(), 1000);
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void GetCode(int sms_type, String account_no, int account_type) {

        if (account_type == 0) {
            subscription = RxCountDown.countdown(60)
                    .doOnSubscribe(() -> {
                        txtCode.setEnabled(false);
                        txtCode.setBackground(getResources().getDrawable(R.drawable.code_circular_backround_no));
                    })
                    .subscribe(new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {
                            // appendLog("计时完成");
                            txtCode.setText("重新获取");
                            txtCode.setEnabled(true);
                            txtCode.setBackground(getResources().getDrawable(R.drawable.code_circular_backround));
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Integer integer) {
                            txtCode.setText(integer + "s");
                        }
                    });
        } else if (account_type == 1) {
            subscription = RxCountDown.countdown(60)
                    .doOnSubscribe(() -> {
                        txtEmailCode.setEnabled(false);
                        txtEmailCode.setBackground(getResources().getDrawable(R.drawable.code_circular_backround_no));
                    })
                    .subscribe(new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {
                            // appendLog("计时完成");
                            txtEmailCode.setText("重新获取");
                            txtEmailCode.setEnabled(true);
                            txtEmailCode.setBackground(getResources().getDrawable(R.drawable.code_circular_backround));

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Integer integer) {
                            txtEmailCode.setText(integer + "s");
                        }
                    });
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("account_type", account_type);
        map.put("account_no", account_no);
        map.put("country_code", "86");
        map.put("sms_type", sms_type);
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Code_p(map);
    }

    @OnClick({R.id.image_close, R.id.txt_code, R.id.txt_email_code, R.id.txt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_code:
                if (TextUtils.equals("phone", type)) {
                    if (!TextUtils.isEmpty(editPhone.getText().toString().trim())) {

                        if (isNo) {
                            GetCode(isNo ? 0 : 1, editPhone.getText().toString().trim(), 0);
                        } else {
                            if (!TextUtils.isEmpty(bean.getPhone().getNick_name())) {
                                if (!TextUtils.equals(bean.getPhone().getNick_name(), editPhone.getText().toString().trim())) {
                                    GetCode(isNo ? 0 : 1, editPhone.getText().toString().trim(), 0);
                                } else {
                                    ToastUitl.show("与原手机号相通", 1000);
                                }
                            }

                        }
                    } else {
                        ToastUitl.show("手机号码不能为空", 1000);
                    }
                }
                break;
            case R.id.txt_email_code:
                if (TextUtils.equals("email", type)) {
                    if (!TextUtils.isEmpty(editEmail.getText().toString().trim())) {
                        if (FormatUtil.isEmail(editEmail.getText().toString().trim())) {
                            GetCode(isNo ? 0 : 1, editEmail.getText().toString().trim(), 1);
                        } else {
                            ToastUitl.show("邮箱格式不正确", 1000);
                        }
                    } else {
                        ToastUitl.show("邮箱不能为空", 1000);
                    }

                }
                break;
            case R.id.txt_go:
                if (TextUtils.equals("pass", type)) {
                    if (!isNo) {
                        if (!TextUtils.isEmpty(editYPass.getText().toString().trim())) {
                            if (editNPass.getText().toString().trim() != null && editNnPass.getText().toString().trim() != null) {

                                if (TextUtils.equals(Md5Security.getMD5(editYPass.getText().toString().trim()), bean.getPassword())) {
                                    if (TextUtils.isEmpty(editNnPass.getText().toString().trim()) || TextUtils.isEmpty(editNPass.getText().toString().trim())) {

                                        ToastUitl.show("新密码或确认密码为空", 1000);
                                    } else {

                                          if(  FormatUtil.isSixAndrTwentyPass(editNPass.getText().toString().trim())&&FormatUtil.isSixAndrTwentyPass(editNnPass.getText().toString().trim())){
                                        //if (editNPass.getText().toString().trim().length() > 6 && editNPass.getText().toString().trim().length() < 20 && editNnPass.getText().toString().trim().length() > 6 && editNnPass.getText().toString().trim().length() < 20) {

                                            if (editNPass.getText().toString().trim().equals(editNnPass.getText().toString().trim())) {
                                                if(!editNPass.getText().toString().trim().equals(editYPass.getText().toString().trim())){
                                                HashMap<String, Object> hashMap = new HashMap<>();
                                                hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                                                hashMap.put("FKEY", PutUtils.FKEY());
                                                hashMap.put("old_password", Md5Security.getMD5(editYPass.getText().toString().trim()));
                                                hashMap.put("new_password", Md5Security.getMD5(editNPass.getText().toString().trim()));
                                                mPresenter.updatePass_p(hashMap);
                                                }else {
                                                    ToastUitl.show("和原密码相同", 1000);
                                                }
                                            } else {
                                                ToastUitl.show("二次密码输入不一致", 1000);
                                            }

                                        } else {
                                            ToastUitl.show("请输入6-20位密码", 1000);
                                        }
                                    }


                                } else {
                                    ToastUitl.show("旧密码输入不一致", 1000);
                                }

                            } else {
                                ToastUitl.show("新密码或确认密码为空", 1000);
                            }
                        } else {
                            ToastUitl.show("原密码不能为空", 1000);
                        }
                    } else {
                        if (editNPass.getText().toString().trim() != null && editNnPass.getText().toString().trim() != null) {
                            if (FormatUtil.isSixAndrTwentyPass(editNnPass.getText().toString().trim()) || FormatUtil.isSixAndrTwentyPass(editNPass.getText().toString().trim())) {
                                if (!TextUtils.equals(editNPass.getText().toString().trim(), editNnPass.getText().toString().toString())) {
                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                                    hashMap.put("FKEY", PutUtils.FKEY());
                                    hashMap.put("password", Md5Security.getMD5(editNPass.getText().toString().trim()));
                                    mPresenter.addPass_p(hashMap);
                                } else {
                                    ToastUitl.show("二次密码输入不一致", 1000);
                                }

                            } else {
                                ToastUitl.show("请输入6-20位密码", 1000);
                            }
                        } else {
                            ToastUitl.show("新密码或确认密码为空", 1000);
                        }
                    }
                } else if (TextUtils.equals("phone", type)) {
                    if (!isNo) {
                        if (!TextUtils.isEmpty(editPhone.getText().toString().trim()) || !TextUtils.isEmpty(editPhoneCode.getText().toString().trim())) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                            hashMap.put("FKEY", PutUtils.FKEY());
                            hashMap.put("realuser_id", bean.getPhone().getRealuser_id());
                            hashMap.put("old_account_no", bean.getPhone().getNick_name());
                            hashMap.put("new_account_no", editPhone.getText().toString().trim());
                            hashMap.put("vf_code", editPhoneCode.getText().toString().trim());
                            hashMap.put("from_type", "phone");
                            hashMap.put("country_code", "86");
                            mPresenter.changePhoneEmail_p(hashMap);
                        } else {
                            ToastUitl.show("手机号或验证码为空", 1000);
                        }
                    } else {
                        if (!TextUtils.isEmpty(editPhone.getText().toString().trim()) || !TextUtils.isEmpty(editPhoneCode.getText().toString().trim())) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                            hashMap.put("FKEY", PutUtils.FKEY());
                            hashMap.put("from_type", "phone");
                            hashMap.put("account_no", editPhone.getText().toString().trim());
                            hashMap.put("vf_code", editPhoneCode.getText().toString().trim());
                            hashMap.put("device_type", "1");
                            hashMap.put("country_code", "86");
                            mPresenter.bindPhoneEmail_p(hashMap);
                        } else {
                            ToastUitl.show("手机号或验证码为空", 1000);
                        }

                    }
                } else if (TextUtils.equals("email", type)) {
                    if (!isNo) {
                        if (!TextUtils.isEmpty(editEmail.getText().toString().trim()) || !TextUtils.isEmpty(editEmailCode.getText().toString().trim())) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                            hashMap.put("FKEY", PutUtils.FKEY());
                            hashMap.put("realuser_id", bean.getEmail().getRealuser_id());
                            hashMap.put("old_acoount_no", bean.getEmail().getAccount_no());
                            hashMap.put("new_account_no", editEmail.getText().toString().trim());
                            hashMap.put("vf_code", editEmailCode.getText().toString().trim());
                            hashMap.put("from_type", "email");
                            hashMap.put("country_code", "86");
                            mPresenter.changePhoneEmail_p(hashMap);
                        } else {
                            ToastUitl.show("手机号或验证码为空", 1000);
                        }
                    } else {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                        hashMap.put("FKEY", PutUtils.FKEY());
                        hashMap.put("from_type", "email");
                        hashMap.put("account_no", editEmail.getText().toString().trim());
                        hashMap.put("vf_code", editEmailCode.getText().toString().trim());
                        hashMap.put("device_type", "1");
                        hashMap.put("country_code", "86");
                        mPresenter.bindPhoneEmail_p(hashMap);
                    }
                }

                break;
        }
    }

    @OnClick({R.id.select_y_pass, R.id.select_n_pass, R.id.select_nn_pass})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.select_y_pass:
                if (isShowypass) {
                    selectYPass.setImageResource(R.mipmap.icon_eyeing_yzy);
                    isShowypass = !isShowypass;
                    editYPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    selectYPass.setImageResource(R.mipmap.icon_eye_yzy);
                    isShowypass = !isShowypass;
                    editYPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.select_n_pass:
                if (isShownpass) {
                    selectNPass.setImageResource(R.mipmap.icon_eyeing_yzy);
                    isShownpass = !isShownpass;
                    editNPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    selectNPass.setImageResource(R.mipmap.icon_eye_yzy);
                    isShownpass = !isShownpass;
                    editNPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.select_nn_pass:
                if (isShownnypass) {
                    selectNnPass.setImageResource(R.mipmap.icon_eyeing_yzy);
                    isShownnypass = !isShownnypass;
                    editNnPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    selectNnPass.setImageResource(R.mipmap.icon_eye_yzy);
                    isShownnypass = !isShownnypass;
                    editNnPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
        }
    }
}
