package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.mumu.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/30 17:45
 * 描述：  我的粉丝
 */

public class MyFansActivity extends BaseActivity {
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
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.iamge_no_view)
    ImageView iamgeNoView;
    @BindView(R.id.txt_no_data)
    TextView txtNoData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_myfans;
    }

    @Override
    public void initPresenter() {

    }
    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.my_fans));

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
}
