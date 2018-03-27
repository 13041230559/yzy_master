package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.OldOrderEntity;
import com.idolmedia.yzy.ui.adapter.OldOrderAdapter;
import com.idolmedia.yzy.ui.mvp.contract.OldOrderContract;
import com.idolmedia.yzy.ui.mvp.model.OldOrderModel;
import com.idolmedia.yzy.ui.mvp.presenter.OldOrderPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/17 17:18
 * 描述：
 */

public class OldOrderActivity extends BaseActivity<OldOrderPresenter, OldOrderModel> implements OldOrderContract.View, OnRefreshListener, OnLoadmoreListener {
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    int pageNo = 1;
       OldOrderAdapter oldOrderAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_old_order;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText("历史订单");
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(OldOrderActivity.this).resumeRequests();
                } else {
                    Glide.with(OldOrderActivity.this).pauseRequests();
                }

            }
        });
        oldOrderAdapter= new OldOrderAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(oldOrderAdapter);
        oldOrderAdapter.setEmptyView(R.layout.empty_view,recyclerView);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("order_status", "");
        mPresenter.OldOrderList_p(hashMap);
        oldOrderAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.txt_logistics:
                    Bundle bundle = new Bundle();
                    bundle.putString("order_num",((OldOrderEntity.DatasBean)adapter.getData().get(position)).getOrder_num());
                    startActivity(LogisticsActivity.class, bundle);
                 break;


            }
        });

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
    public void OldOrderList(OldOrderEntity t) {
        if(t.getResultCode()==1){
            if(pageNo==1){
                oldOrderAdapter.setNewData(t.getDatas());
            }else {
                oldOrderAdapter.addData(t.getDatas());
            }

        }

    }
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo=1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("order_status", "");
        mPresenter.OldOrderList_p(hashMap);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("order_status", "");
        mPresenter.OldOrderList_p(hashMap);
        refreshlayout.finishLoadmore(2000);
        refreshlayout.finishRefresh(2000);
    }

    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
    }
}
