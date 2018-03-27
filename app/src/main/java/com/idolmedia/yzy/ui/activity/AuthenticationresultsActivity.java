package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.AuthenticationResultEntity;
import com.idolmedia.yzy.ui.mvp.contract.AuthenticationResultsContract;
import com.idolmedia.yzy.ui.mvp.model.AuthenticationResultsModel;
import com.idolmedia.yzy.ui.mvp.presenter.AuthenticationResultsPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/12 12:18
 * 描述：
 */

public class AuthenticationresultsActivity extends BaseActivity<AuthenticationResultsPresenter, AuthenticationResultsModel> implements AuthenticationResultsContract.View {
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
    @BindView(R.id.iamge_fans_team)
    ImageView iamgeFansTeam;
    @BindView(R.id.txt_auth_results)
    TextView txtAuthResults;
    @BindView(R.id.txt_no_authen_msg)
    TextView txtNoAuthenMsg;
    @BindView(R.id.txt_fans_team)
    TextView txtFansTeam;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication_results;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText("申请结果");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.resultMsg_p(hashMap);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
    public void resultMsg_v(AuthenticationResultEntity t) {
        if (t.getResultCode() == 1) {
            if (t.getDatas().getAttestation_type() == 0) {
                iamgeFansTeam.setImageResource(R.mipmap.img_certification_company_yzy);
                txtFansTeam.setText("我是第三方");
            } else if (t.getDatas().getAttestation_type() == 1) {
                iamgeFansTeam.setImageResource(R.mipmap.img_certification_team_yzy);
                txtFansTeam.setText("我是粉丝团");
            } else if (t.getDatas().getAttestation_type() == 2) {

            }
            txtNoAuthenMsg.setText(t.getDatas().getDescription());

        }

    }
}
