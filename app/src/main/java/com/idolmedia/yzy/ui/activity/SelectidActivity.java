package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/4 21:06
 * 描述：
 */

public class SelectidActivity extends BaseActivity {
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
    @BindView(R.id.iamge_fans_team)
    ImageView iamgeFansTeam;
    @BindView(R.id.iamge_thirdparty)
    ImageView iamgeThirdparty;
    private  final  int SELECTID=100;
    @Override
    public int getLayoutId() {
        return R.layout.activity_select_id;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.select_id));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.iamge_fans_team, R.id.iamge_thirdparty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.iamge_fans_team:
                Bundle bundle=new Bundle();
                bundle.putInt("id",0);
                startActivityForResult(AuthenticationActivity.class,bundle,SELECTID);
                break;
            case R.id.iamge_thirdparty:
               bundle=new Bundle();
                bundle.putInt("id",1);
                startActivityForResult(AuthenticationActivity.class,bundle,SELECTID);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECTID:
                 boolean isSave= data.getBooleanExtra("isSave",false);
                       if(isSave){
                           Intent intent =new Intent();
                           intent.putExtra("isUpdate",true);
                           setResult(RESULT_OK,intent);
                           finish();
                       }
                    break;

            }
        }
    }
}
