package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.PreferentiaEntity;
import com.idolmedia.yzy.entity.PreferentialHotEntity;
import com.idolmedia.yzy.ui.adapter.PreferentialAdapter;
import com.idolmedia.yzy.ui.adapter.PreferentialImageAdapter;
import com.idolmedia.yzy.ui.mvp.contract.PreferentialContract;
import com.idolmedia.yzy.ui.mvp.model.PreferentialModel;
import com.idolmedia.yzy.ui.mvp.presenter.PreferentialPresenter;
import com.idolmedia.yzy.utlis.DividerGridItemDecoration;
import com.idolmedia.yzy.utlis.GlideImageLoader;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/1.
 */

public class PreferentialActivity extends BaseActivity<PreferentialPresenter, PreferentialModel> implements PreferentialContract.View,OnRefreshListener, OnLoadmoreListener {
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
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.banner1)
    Banner banner1;
    @BindView(R.id.recyclerView_th)
    RecyclerView recyclerViewTh;
    @BindView(R.id.recyclerView_hot)
    RecyclerView recyclerViewHot;
    PreferentialImageAdapter imageAdapter;
    PreferentialAdapter preferentialAdapter;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    private List<String> bannerList = new ArrayList<>();
    private List<String> bannerList1 = new ArrayList<>();
    int pageNo=1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_preferential;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.preferentialarea));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", 1);
        hashMap.put("pageSize", 10);
        mPresenter.Preferential_p(hashMap);
        mPresenter.Preferentialhot_p(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("banner_type", "preference");
        hashMap.put("location", "0");
        mPresenter.Banner_p(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("banner_type", "preference");
        hashMap.put("location", "1");
        mPresenter.Banner1_p(hashMap);
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        preferentialAdapter = new PreferentialAdapter();
        recyclerViewHot.setNestedScrollingEnabled(false);
        recyclerViewHot.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHot.setAdapter(preferentialAdapter);
        imageAdapter = new PreferentialImageAdapter();
        recyclerViewTh.setAdapter(imageAdapter);
        recyclerViewTh.setNestedScrollingEnabled(false);
        recyclerViewTh.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewTh.addItemDecoration(new DividerGridItemDecoration(this));
        imageAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("shop_type", ((PreferentialHotEntity.DatasBean)adapter.getData().get(position)).getShop_type());
            bundle.putString("shopcommon_id", String.valueOf(((PreferentialHotEntity.DatasBean)adapter.getData().get(position)).getShopcommon_id()));
            startActivity(CommodityDetailsActivity.class, bundle);
        });
        preferentialAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("shop_type", ((PreferentiaEntity.DatasBean)adapter.getData().get(position)).getShop_type());
            bundle.putString("shopcommon_id", String.valueOf(((PreferentiaEntity.DatasBean)adapter.getData().get(position)).getShopcommon_id()));
            startActivity(CommodityDetailsActivity.class, bundle);
        });



       /* banner1.setOnBannerListener(position -> {
            Log.e("shop_type",t.getDatas().get(position).getShop_type());
            if(TextUtils.equals(t.getDatas().get(position).getSkip_type(),"shop")){
                if(TextUtils.equals(t.getDatas().get(position).getShop_type(),"information")){
                    Bundle bundle=new Bundle();
                    bundle.putString("shopcommon_id",t.getDatas().get(position).getShopcommon_id());
                    startActivity(InformationDetalisActivity.class,bundle);

                }else   if(TextUtils.equals(t.getDatas().get(position).getShop_type(),"support")){
                    Bundle bundle=new Bundle();
                    bundle.putString("shop_type", t.getDatas().get(position).getShop_type());
                    bundle.putString("shopcommon_id", String.valueOf(t.getDatas().get(position).getShopcommon_id()));
                    startActivity(SupportDetalisactivity.class,bundle);


                }else   if(TextUtils.equals(t.getDatas().get(position).getShop_type(),"active")||TextUtils.equals(t.getDatas().get(position).getShop_type(),"ordinary")||TextUtils.equals(t.getDatas().get(position).getShop_type(),"reserve")){
                    Bundle bundle=new Bundle();
                    bundle.putString("shop_type", t.getDatas().get(position).getShop_type());
                    bundle.putString("shopcommon_id", String.valueOf(t.getDatas().get(position).getShopcommon_id()));
                    startActivity(CommodityDetailsActivity.class,bundle);
                }

            }else if(TextUtils.equals(t.getDatas().get(position).getSkip_type(),"outsideUrl")){
                Bundle bundle =new Bundle();
                bundle.putString("title",t.getDatas().get(position).getOutside_title());
                bundle.putString("url",t.getDatas().get(position).getOutside_url());
                startActivity(WebActivity.class,bundle);

            }if(TextUtils.equals("skip_type","")){

            }

        });*/
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


    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void Preferential_v(PreferentiaEntity t) {
        if (t.getResultCode() == 1) {
            if(pageNo==1){
                preferentialAdapter.setNewData(t.getDatas());
            }else {
                preferentialAdapter.addData(t.getDatas());
            }

        }

    }

    @Override
    public void Preferentialhot_v(PreferentialHotEntity t) {
        if (t.getResultCode() == 1) {
            imageAdapter.setNewData(t.getDatas());
        } else {

            ToastUitl.show(t.getMsg(), 1000);
        }

    }

    @Override
    public void Banner_v(BannerEntity t) {
        if (t.getResultCode() == 1) {
            bannerList.clear();
            Iterator<BannerEntity.DatasBean> iterator = t.getDatas().iterator();
            while (iterator.hasNext()) {
                bannerList.add(iterator.next().getPic_url());
            }
            banner.setImages(bannerList);
            banner.setOnBannerListener(position -> {
                Log.e("shop_type", t.getDatas().get(position).getShop_type());
                if (TextUtils.equals(t.getDatas().get(position).getSkip_type(), "shop")) {
                    if (TextUtils.equals(t.getDatas().get(position).getShop_type(), "information")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("shopcommon_id", String.valueOf(t.getDatas().get(position).getShopcommon_id()));
                        startActivity(InformationDetalisActivity.class, bundle);

                    } else if (TextUtils.equals(t.getDatas().get(position).getShop_type(), "support")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("shop_type", t.getDatas().get(position).getShop_type());
                        bundle.putString("shopcommon_id", String.valueOf(t.getDatas().get(position).getShopcommon_id()));
                        startActivity(SupportDetalisactivity.class, bundle);

                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("shop_type", t.getDatas().get(position).getShop_type());
                        bundle.putString("shopcommon_id", String.valueOf(t.getDatas().get(position).getShopcommon_id()));
                        startActivity(CommodityDetailsActivity.class, bundle);
                    }

                } else if (TextUtils.equals(t.getDatas().get(position).getSkip_type(), "outsideUrl")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", t.getDatas().get(position).getOutside_title());
                    bundle.putString("url", t.getDatas().get(position).getOutside_url());
                    startActivity(WebActivity.class, bundle);

                }
                if (TextUtils.equals("skip_type", "")) {

                }

            });
            banner.setBannerAnimation(Transformer.Default);
            banner.setDelayTime(3000);
            banner.isAutoPlay(true);
            banner.setImageLoader(new GlideImageLoader());
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.start();
        }


    }

    @Override
    public void Banner_v1(BannerEntity t) {
        if (t.getResultCode() == 1) {
            bannerList1.clear();
            Iterator<BannerEntity.DatasBean> iterator = t.getDatas().iterator();
            while (iterator.hasNext()) {
                bannerList1.add(iterator.next().getPic_url());
            }
            banner1.setImages(bannerList1);
            banner1.setOnBannerListener(position -> {
                Log.e("shop_type",t.getDatas().get(position).getShop_type());
                if(TextUtils.equals(t.getDatas().get(position).getSkip_type(),"shop")){
                    if(TextUtils.equals(t.getDatas().get(position).getShop_type(),"information")){
                        Bundle bundle=new Bundle();
                        bundle.putString("shopcommon_id", String.valueOf(t.getDatas().get(position).getShopcommon_id()));
                        startActivity(InformationDetalisActivity.class,bundle);

                    }else   if(TextUtils.equals(t.getDatas().get(position).getShop_type(),"support")){
                        Bundle bundle=new Bundle();
                        bundle.putString("shop_type", t.getDatas().get(position).getShop_type());
                        bundle.putString("shopcommon_id", String.valueOf(t.getDatas().get(position).getShopcommon_id()));
                        startActivity(SupportDetalisactivity.class,bundle);


                    }else   if(TextUtils.equals(t.getDatas().get(position).getShop_type(),"active")||TextUtils.equals(t.getDatas().get(position).getShop_type(),"ordinary")||TextUtils.equals(t.getDatas().get(position).getShop_type(),"reserve")){
                        Bundle bundle=new Bundle();
                        bundle.putString("shop_type", t.getDatas().get(position).getShop_type());
                        bundle.putString("shopcommon_id", String.valueOf(t.getDatas().get(position).getShopcommon_id()));
                        startActivity(CommodityDetailsActivity.class,bundle);
                    }

                }else if(TextUtils.equals(t.getDatas().get(position).getSkip_type(),"outsideUrl")){
                    Bundle bundle =new Bundle();
                    bundle.putString("title",t.getDatas().get(position).getOutside_title());
                    bundle.putString("url",t.getDatas().get(position).getOutside_url());
                    startActivity(WebActivity.class,bundle);

                }if(TextUtils.equals("skip_type","")){

                }

            });
            banner1.setBannerAnimation(Transformer.Default);
            banner1.setDelayTime(3000);
            banner1.isAutoPlay(true);
            banner1.setImageLoader(new GlideImageLoader());
            banner1.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner1.setIndicatorGravity(BannerConfig.CENTER);
            banner1.start();

        }

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", 10);
        mPresenter.Preferential_p(hashMap);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
         pageNo=1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", 10);
        mPresenter.Preferential_p(hashMap);
        mPresenter.Preferentialhot_p(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("banner_type", "preference");
        hashMap.put("location", "0");
        mPresenter.Banner_p(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("banner_type", "preference");
        hashMap.put("location", "1");
        mPresenter.Banner1_p(hashMap);
        refreshlayout.finishRefresh(2000);
    }
}
