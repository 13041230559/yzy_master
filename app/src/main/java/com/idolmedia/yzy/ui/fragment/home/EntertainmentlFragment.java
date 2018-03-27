package com.idolmedia.yzy.ui.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.HotSearchActivity;
import com.idolmedia.yzy.ui.activity.InformationDetalisActivity;
import com.idolmedia.yzy.ui.activity.SelectStarListActivity;
import com.idolmedia.yzy.ui.activity.SupportDetalisactivity;
import com.idolmedia.yzy.ui.activity.WebActivity;
import com.idolmedia.yzy.ui.adapter.DataAdapter;
import com.idolmedia.yzy.ui.adapter.EntertainmentlAdapter;
import com.idolmedia.yzy.ui.mvp.contract.EntertainmentContract;
import com.idolmedia.yzy.ui.mvp.model.EntertainmentModel;
import com.idolmedia.yzy.ui.mvp.presenter.EntertainmentPresenter;
import com.idolmedia.yzy.utlis.GlideImageLoaderRadius;
import com.idolmedia.yzy.utlis.Transform.Transformer;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.DisplayUtil;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/31.
 */

public class EntertainmentlFragment extends BaseFragment<EntertainmentPresenter, EntertainmentModel> implements EntertainmentContract.View, OnRefreshListener, OnLoadmoreListener {

    Unbinder unbinder;
    int pageNo = 1;
    DataAdapter dataAdapter;
    EntertainmentlAdapter entertainmentlAdapter;
    @BindView(R.id.tile_view)
    View tileView;
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
    @BindView(R.id.yu_recyclerview)
    RecyclerView yuRecyclerview;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    private List<String> bannerList = new ArrayList<>();
    RecyclerView carouselRecyclerView;
    Banner banner;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_entertainmentl;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        tvTitle.setText("娱");
        imageClose.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).load(R.mipmap.icon_add_heart_yzy).into(imageClose);
        Glide.with(getActivity()).load(R.mipmap.icon_search_yzy).into(rightImg);
        DisplayUtil.setWidgetHeight(tileView, DisplayUtil.getStatusBarHeight(getActivity()));
        tileView.setBackgroundColor(getResources().getColor(R.color.main_color));
        rightImg.setVisibility(View.VISIBLE);
        View head_view= LayoutInflater.from(getActivity()).inflate(R.layout.entertainmentl_head, (ViewGroup) rootView.findViewById(android.R.id.content), false);
        banner=head_view.findViewById(R.id.banner);
        carouselRecyclerView=head_view.findViewById(R.id.carousel_recyclerView);
        entertainmentlAdapter = new EntertainmentlAdapter();
        yuRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        yuRecyclerview.setNestedScrollingEnabled(false);
        yuRecyclerview.setAdapter(entertainmentlAdapter);
        entertainmentlAdapter.addHeaderView(head_view);
        entertainmentlAdapter.setEmptyView(R.layout.empty_view,yuRecyclerview);
        dataAdapter = new DataAdapter();
        carouselRecyclerView.setAdapter(dataAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        carouselRecyclerView.setLayoutManager(linearLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(carouselRecyclerView);
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageSize", "10");
        map.put("sort", "0");
        map.put("virtualuser_id", UserLoginUtil.IsUserId());
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Entertainment_p(map);
        map = new HashMap<>();
        map.put("banner_type", "yu");
        map.put("location", 0);
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Banner_p(map, 0);
        map = new HashMap<>();
        map.put("banner_type", "yu");
        map.put("location", 1);
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Banner_p(map, 1);

        entertainmentlAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("shop_type", ((CommodityEntity.DatasBean)adapter.getData().get(position)).getShop_type());
            bundle.putString("shopcommon_id", String.valueOf(((CommodityEntity.DatasBean)adapter.getData().get(position)).getShopcommon_id()));
            startActivity(CommodityDetailsActivity.class, bundle);
        });
          // 一行代码搞定（默认为渐显效果）
        entertainmentlAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        dataAdapter.setOnItemClickListener((adapter, view, position) -> {
            BannerEntity.DatasBean bannerEntity=  ((BannerEntity.DatasBean)adapter.getData().get(position));
            Log.e("BannerEntity",bannerEntity.getShop_type());
            if(TextUtils.equals(bannerEntity.getSkip_type(),"shop")){
                if(TextUtils.equals(bannerEntity.getShop_type(),"information")){
                    Bundle bundle=new Bundle();
                    bundle.putString("shopcommon_id", String.valueOf(bannerEntity.getShopcommon_id()));
                    startActivity(InformationDetalisActivity.class,bundle);

                }else  if(TextUtils.equals(bannerEntity.getShop_type(),"support")){
                    Bundle bundle=new Bundle();
                    bundle.putString("shop_type",bannerEntity.getShop_type());
                    bundle.putString("shopcommon_id", String.valueOf(bannerEntity.getShopcommon_id()));
                    startActivity(SupportDetalisactivity.class,bundle);
                }else {
                    Bundle bundle=new Bundle();
                    bundle.putString("shop_type", bannerEntity.getShop_type());
                    bundle.putString("shopcommon_id", String.valueOf(bannerEntity.getShopcommon_id()));
                    startActivity(CommodityDetailsActivity.class,bundle);
                }

            }else if(TextUtils.equals(bannerEntity.getSkip_type(),"outsideUrl")){
                Bundle bundle =new Bundle();
                bundle.putString("title",bannerEntity.getOutside_title());
                bundle.putString("url",bannerEntity.getOutside_url());
                startActivity(WebActivity.class,bundle);

            }if(TextUtils.equals("skip_type","")){

            }

        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.image_close, R.id.right_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                startActivity(SelectStarListActivity.class);
                break;
            case R.id.right_img:
                startActivity(HotSearchActivity.class);
                break;
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
    public void Entertainment_v(CommodityEntity entertainmentEntity) {
        if (pageNo == 1) {
            entertainmentlAdapter.setNewData(entertainmentEntity.getDatas());
        } else {
            entertainmentlAdapter.addData(entertainmentEntity.getDatas());
        }
    }

    @Override
    public void Banner_v(BannerEntity t, int type) {
        if (t.getResultCode() == 1) {
            if (type == 0) {
                dataAdapter.setNewData(t.getDatas());
            } else if (type == 1) {
                bannerList.clear();
                Iterator<BannerEntity.DatasBean> iterator = t.getDatas().iterator();
                while (iterator.hasNext()) {
                    bannerList.add(iterator.next().getPic_url());
                }
                banner.setImages(bannerList);
                banner.setOnBannerListener(position -> {
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


                        }else  {
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
                banner.setBannerAnimation(Transformer.Default);
                banner.setDelayTime(3000);
                banner.isAutoPlay(true);
                banner.setImageLoader(new GlideImageLoaderRadius());
                banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.start();

            }

        } else {
            ToastUitl.show(t.getMsg(), 1000);
        }
    }




    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageSize", "10");
        map.put("sort", "0");
        map.put("virtualuser_id", UserLoginUtil.IsUserId());
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Entertainment_p(map);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageSize", "10");
        map.put("sort", "0");
        map.put("virtualuser_id", UserLoginUtil.IsUserId());
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Entertainment_p(map);
        map = new HashMap<>();
        map.put("banner_type", "yu");
        map.put("location", 0);
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Banner_p(map, 0);
        map = new HashMap<>();
        map.put("banner_type", "yu");
        map.put("location", 1);
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.Banner_p(map, 1);
        refreshlayout.finishRefresh(2000);
    }
}
