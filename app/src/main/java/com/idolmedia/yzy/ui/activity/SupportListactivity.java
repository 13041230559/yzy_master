package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.ui.adapter.SupportAdapter;
import com.idolmedia.yzy.ui.mvp.contract.SupportActivityListContract;
import com.idolmedia.yzy.ui.mvp.model.SupportActivityListModel;
import com.idolmedia.yzy.ui.mvp.presenter.SupportActivityListPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
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
 * Created by Administrator on 2017/11/7.
 */

public class SupportListactivity extends BaseActivity<SupportActivityListPresenter, SupportActivityListModel> implements SupportActivityListContract.View, OnRefreshListener, OnLoadmoreListener {
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
    SupportAdapter supportAdapter;
    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    int pageNo = 1;
    int type = 0;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_support;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        SetStatusBarColor(ContextCompat.getColor(this, R.color.fc4f4f));
        tvTitle.setText(getString(R.string.support_activity));
        String[] title_name = new String[]{"进行中", "已结束"};
        tabLayout.setTabData(title_name);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        mPresenter.SupportList_p(HttpHashMap(1, 0));
        supportAdapter = new SupportAdapter();
        recyclerView.setAdapter(supportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        supportAdapter.setEmptyView(R.layout.empty_view,recyclerView);
       /* supportAdapter.setOnItemClickListener((adapter, view, position) -> {
            startActivity(SupportDetalisactivity.class)});*/
        supportAdapter.setOnItemClickListener((adapter, view, position) -> {
         SupportEntity.DatasBean datasBean=  (SupportEntity.DatasBean)adapter.getData().get(position);
         if(datasBean.getShop_type().equals("support")){
             Bundle bundle=new Bundle();
             bundle.putString("shopcommon_id",String.valueOf(datasBean.getShopcommon_id()));
             bundle.putString("shop_type",datasBean.getShop_type());
             startActivity(SupportDetalisactivity.class,bundle);
         }else {
             Bundle bundle=new Bundle();
             bundle.putString("shopcommon_id",String.valueOf(datasBean.getShopcommon_id()));
             bundle.putString("shop_type",datasBean.getShop_type());
             startActivity(CommodityDetailsActivity.class,bundle);
         }

        });
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                type = position;
                mPresenter.SupportList_p(HttpHashMap(1, position));
            }
            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    private HashMap HttpHashMap(int pageNo, int type) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", 10);
        hashMap.put("activity_type", type);
        return hashMap;
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
        ToastUitl.show(msg, 1000);
    }

    @Override
    public void SupportList_v(SupportEntity t) {
        if (t.getResultCode() == 1) {
            if (pageNo == 1) {
                supportAdapter.setNewData(t.getDatas());
            } else {
                supportAdapter.addData(t.getDatas());
            }

        }

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo=1;
        mPresenter.SupportList_p(HttpHashMap(1, type));
        refreshlayout.finishRefresh(2000);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        mPresenter.SupportList_p(HttpHashMap(pageNo, type));
        refreshlayout.finishLoadmore(2000);
    }


}
