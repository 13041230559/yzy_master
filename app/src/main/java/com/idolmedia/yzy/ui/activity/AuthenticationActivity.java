package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.AuthenticationEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.HashMapEntity;
import com.idolmedia.yzy.ui.adapter.AuthenticationAdapter;
import com.idolmedia.yzy.ui.mvp.contract.AuthenticationContract;
import com.idolmedia.yzy.ui.mvp.model.AuthenticationModel;
import com.idolmedia.yzy.ui.mvp.presenter.AuthenticationPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/20 17:12
 * 描述：  实名认证
 */

public class AuthenticationActivity extends BaseActivity<AuthenticationPresenter, AuthenticationModel> implements AuthenticationContract.View {

    String AuthenticationType;
    HashMapEntity hashMapEntity;
    public static final int SAVEMSSAGE = 2;
    public static final int SAVECARDID = 3;
    public static final int SAVEPHONE = 4;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    HashMap<String, Object> hashMap = new HashMap<>();
    List<AuthenticationEntity> entityList;
    AuthenticationAdapter adapter;
    int type;
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
    @BindView(R.id.txt_go)
    TextView txtGo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.authentication));
        type = getIntent().getIntExtra("id", 0);
        entityList = new ArrayList<>();
        entityList.add(new AuthenticationEntity("认证类型", type == 1 ? "第三方认证" : "粉丝团认证"));
        entityList.add(new AuthenticationEntity("认证昵称", ""));
        entityList.add(new AuthenticationEntity(type == 1 ? "认证企业信息" : "认证身份证信息", ""));
        entityList.add(new AuthenticationEntity("认证手机号", ""));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AuthenticationAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNewData(entityList);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == 1) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putString("nickname", entityList.get(1).getValue());
                startActivityForResult(SavePhoneActivity.class, bundle, SAVEMSSAGE);
            } else if (position == 2) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", type);
                bundle.putSerializable("hashMap", hashMapEntity);
                startActivityForResult(UploadAuthenActivity.class, bundle, SAVECARDID);
            } else if (position == 3) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);
                bundle.putString("phone", entityList.get(2).getValue());
                startActivityForResult(SavePhoneActivity.class, bundle, SAVEPHONE);
            }
        });

      /*
        int i = getIntent().getIntExtra("id", 0);
        if (i == 0) {
            AuthenticationType = "第三方认证";
        } else {
            AuthenticationType = "粉丝团认证";
        }
        List<SettingEntity> list = new ArrayList<>();
        SettingEntity set1 = new SettingEntity("认证类型", AuthenticationType);
        SettingEntity set2 = new SettingEntity("认证昵称", "");
        //SettingEntity set3 = new SettingEntity("本人身份证信息", "");
        SettingEntity set4 = new SettingEntity("手机号", "");
        SettingEntity set3 = new SettingEntity(i == 0 ? "身份证" : "企业信息", "");
        list.add(set1);
        list.add(set2);
        list.add(set4);
        list.add(set3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SettingAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNewData(list);


        adapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == 1) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putString("nickname", list.get(1).getContext());
                startActivityForResult(SavePhoneActivity.class, bundle, SAVEMSSAGE);
            } else if (position == 3) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", i);
                startActivityForResult(UploadAuthenActivity.class, bundle, SAVECARDID);
            } else if (position == 2) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);
                bundle.putString("phone", list.get(2).getContext());
                startActivityForResult(SavePhoneActivity.class, bundle, SAVEPHONE);
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SAVEMSSAGE:
                    int type = data.getIntExtra("type", 1);
                    String nickname = data.getStringExtra("name");
                    Log.e("type+name", type + "" + nickname);
                    adapter.getData().get(type).setValue(nickname);
                    adapter.notifyDataSetChanged();
                    hashMap.put("nickname", nickname);
                    break;
                case SAVECARDID:
                    hashMapEntity = (HashMapEntity) data.getSerializableExtra("hashMap");
                    if (hashMapEntity != null) {
                        if (hashMapEntity.getHashMap().size() > 0) {
                            hashMap.putAll(hashMapEntity.getHashMap());
                            Log.e("hashMapEntity", hashMapEntity.getHashMap().size() + "");
                            adapter.getData().get(3).setValue("已上传");
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.getData().get(3).setValue("");
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        adapter.getData().get(3).setValue("");
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case SAVEPHONE:
                    String phone = data.getStringExtra("phone");
                    adapter.getData().get(2).setValue(phone);
                    adapter.notifyDataSetChanged();
                    hashMap.put("phone", phone);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    @OnClick(R.id.txt_go)
    public void onViewClicked2() {
            List<String> updatelist = new ArrayList<>();
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("attestation_type", type);
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            updatelist.add(hashMap.get("positiveImage").toString());
            updatelist.add(hashMap.get("reverseSideImage").toString());
            mPresenter.Authentication_p(RetrofitHttp.onUpload(hashMap, updatelist));
            ToastUitl.show("请勾选认证经营协议", 1000);
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
    public void Authentication_v(BaseResult t) {
        ToastUitl.show(t.getMsg(), 1000);
        if (t.getResultCode() == 1) {
            Intent intent = new Intent();
            intent.putExtra("isSave", true);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}
