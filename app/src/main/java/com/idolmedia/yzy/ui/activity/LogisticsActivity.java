package com.idolmedia.yzy.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.LogisticsEntity;
import com.idolmedia.yzy.ui.mvp.contract.LogisticsContract;
import com.idolmedia.yzy.ui.mvp.model.LogisticsModel;
import com.idolmedia.yzy.ui.mvp.presenter.LogisticsPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/21 16:59
 * 描述： 物流信息
 */

public class LogisticsActivity extends BaseActivity<LogisticsPresenter, LogisticsModel> implements LogisticsContract.View {
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
    @BindView(R.id.txt_logistics)
    TextView txtLogistics;
    @BindView(R.id.txt_ordernum)
    TextView txtOrdernum;
    @BindView(R.id.txt_copy_logistics)
    TextView txtCopyLogistics;
    @BindView(R.id.txt_copy_order)
    TextView txtCopyOrder;
    @BindView(R.id.txt_logistics_time)
    TextView txtLogisticsTime;
    @BindView(R.id.txt_logistics_content)
    TextView txtLogisticsContent;
    @BindView(R.id.txt_select_logistics)
    TextView txtSelectLogistics;
    private String order_num;
   private final String WEBURL="https://m.kuaidi100.com/index_all.html?type=%s&postid=%s#result";
    @Override
    public int getLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.logistics_msg));
        order_num = getIntent().getStringExtra("order_num");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("order_num", order_num);
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Logistics_p(hashMap);

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
    public void Logistics_v(LogisticsEntity t) {
        if (t.getResultCode() == 1) {
            txtLogistics.setText(t.getDatas().getShipperName() + ":" + t.getDatas().getExpress_no());
            txtOrdernum.setText("订单编号：" + t.getDatas().getOrder_num());
            txtLogisticsTime.setText(t.getDatas().getExpress_time());
            txtLogisticsContent.setText(t.getExpressInfo());
            txtCopyLogistics.setOnClickListener(view -> {
                ClipboardManager copy = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(t.getDatas().getExpress_no());
                ToastUitl.showShort("复制成功");
            });
        }
        txtCopyOrder.setOnClickListener(view -> {
            ClipboardManager copy = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            copy.setText(t.getDatas().getOrder_num());
            ToastUitl.showShort("复制成功");
        });
        txtSelectLogistics.setOnClickListener(view -> {
            Intent intent=new Intent(Intent.ACTION_VIEW);

            String url=String.format(WEBURL,t.getDatas().getShippercode(),t.getDatas().getExpress_no());
            intent.setData(Uri.parse(url));
           startActivity(intent);
        });
    }

    }

   /* @OnClick({R.id.image_close, R.id.txt_copy_logistics, R.id.txt_copy_order, R.id.txt_select_logistics})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_copy_logistics:
                ClipboardManager copy = getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(mydatasBean.getOrder_num());
                ToastUitl.showShort("复制成功");
                break;
            case R.id.txt_copy_order:
                ClipboardManager copy = (ClipboardManager) context
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(mydatasBean.getOrder_num());
                ToastUitl.showShort("复制成功");
                break;
            case R.id.txt_select_logistics:
                break;
        }
    }*/
