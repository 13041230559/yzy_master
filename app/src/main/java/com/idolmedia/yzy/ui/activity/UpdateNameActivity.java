package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.mvp.contract.UpdateNameContract;
import com.idolmedia.yzy.ui.mvp.model.UpdateNameModel;
import com.idolmedia.yzy.ui.mvp.presenter.UpdateNamePresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/25 12:16
 * 描述：
 */

public class UpdateNameActivity extends BaseActivity<UpdateNamePresenter, UpdateNameModel> implements UpdateNameContract.View {
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
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.liner_nickname)
    LinearLayout linerNickname;

    @Override
    public int getLayoutId() {
        return R.layout.activity_updatename;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.edit_name));
        rightTxt.setText(getString(R.string.save));
        rightTxt.setVisibility(View.VISIBLE);
        rightImg.setVisibility(View.GONE);
        editName.setText(UserLoginUtil.IsUserName());
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
    public void UpdateName_v(BaseResult t) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            Intent intent=new Intent();
            intent.putExtra("isUpdate",true);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.right_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                Intent intent=new Intent();
                intent.putExtra("isUpdate",false);
                setResult(Activity.RESULT_OK,intent);
                finish();
                break;
            case R.id.right_txt:
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                hashMap.put("nick_name",editName.getText().toString().trim());
                hashMap.put("FKEY", PutUtils.FKEY());
                mPresenter.UpdateName_p(hashMap);
                break;
        }
    }
}
