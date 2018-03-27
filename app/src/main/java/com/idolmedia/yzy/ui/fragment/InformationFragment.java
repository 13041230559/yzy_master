package com.idolmedia.yzy.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.InformationEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.InformationDetalisActivity;
import com.idolmedia.yzy.ui.activity.SupportDetalisactivity;
import com.idolmedia.yzy.ui.activity.WebActivity;
import com.idolmedia.yzy.ui.adapter.InformationlAdapter;
import com.idolmedia.yzy.ui.mvp.contract.InformationContract;
import com.idolmedia.yzy.ui.mvp.model.InformationModel;
import com.idolmedia.yzy.ui.mvp.presenter.InformationPresenter;
import com.idolmedia.yzy.utlis.GlideImageLoader;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseFragment;
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
import butterknife.Unbinder;

/**
 * 项目名称：com.idolmedia.yzy.ui.fragment
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/12 11:07
 * 描述：资讯列表
 */

public class InformationFragment extends BaseFragment<InformationPresenter, InformationModel> implements InformationContract.View, OnRefreshListener, OnLoadmoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    Banner banner;
    TabLayout tabLayout;
    int type;
    int pageNo = 1;
    int tabPosition=0;
    InformationlAdapter informationlAdapter;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    private List<String> bannerList = new ArrayList<>();
    public static InformationFragment getInstance(String s, int type) {
        InformationFragment starDetailFragment = new InformationFragment();
        starDetailFragment.type = type;
        return starDetailFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.include_recyclerview;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

        View header_banner = LayoutInflater.from(getActivity()).inflate(R.layout.include_banner, (ViewGroup) rootView.findViewById(android.R.id.content), false);
        View header_tab = LayoutInflater.from(getActivity()).inflate(R.layout.include_tablayout, (ViewGroup) rootView.findViewById(android.R.id.content), false);
        banner = header_banner.findViewById(R.id.header_banner);
        tabLayout = header_tab.findViewById(R.id.tab_layout);
        String[] title_name = new String[]{"最新", "热门"};
        for (int i = 0; i < title_name.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
        }
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("banner_type", "information");
        hashMap.put("location", "0");
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Banner_p(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("sort", 1);
        mPresenter.InformationLis_p(hashMap,1);
        informationlAdapter = new InformationlAdapter();
        informationlAdapter.addHeaderView(header_banner);
        informationlAdapter.addHeaderView(header_tab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(informationlAdapter);
        informationlAdapter.setEmptyView(R.layout.empty_view,recyclerView);
        informationlAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle=new Bundle();
            bundle.putString("shopcommon_id",((InformationEntity.DatasBean)adapter.getData().get(position)).getShopcommon_id());
            bundle.putString("image_url",((InformationEntity.DatasBean)adapter.getData().get(position)).getPic_url());
            bundle.putString("shop_type","Information");
            startActivity(InformationDetalisActivity.class,bundle);
        });
      tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pageNo = 1;
                tabPosition=tab.getPosition();
                HashMap hashMap = new HashMap<>();
                hashMap.put("pageNo", pageNo);
                hashMap.put("pageSize", "10");
                hashMap.put("FKEY", PutUtils.FKEY());
                hashMap.put("sort", tab.getPosition() + 1);
                mPresenter.InformationLis_p(hashMap,pageNo);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    Glide.with(getActivity()).resumeRequests();
                }else{
                    Glide.with(getActivity()).pauseRequests();
                }

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
    public void Banner_v(BannerEntity t) {
        if (t.getResultCode() == 1) {
            bannerList.clear();
            Iterator<BannerEntity.DatasBean> iterator = t.getDatas().iterator();
            while (iterator.hasNext()) {
                bannerList.add(iterator.next().getPic_url());
            }
            banner.setImages(bannerList);
            banner.setBannerAnimation(Transformer.Default);
            banner.setDelayTime(3000);
            banner.isAutoPlay(true);
            banner.setImageLoader(new GlideImageLoader());
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.start();
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
                    }else {
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
        }
    }

    @Override
    public void InformationList_v(InformationEntity t, int PageNo) {
        if (t.getResultCode() == 1) {
            if(PageNo==1){
                informationlAdapter.setNewData(t.getDatas());
            }else {
                informationlAdapter.addData(t.getDatas());
            }
        }
    }

    @Override
    public void CommentList_v(CommentEntity t,int PageNo) {

    }

    @Override
    public void GoodClick_v(BaseResult t, int position, int linkcount) {

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap hashMap = new HashMap<>();
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("sort", tabPosition + 1);
        mPresenter.InformationLis_p(hashMap,pageNo);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap hashMap = new HashMap<>();
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("sort", tabPosition+ 1);
        mPresenter.InformationLis_p(hashMap,pageNo);
        refreshlayout.finishRefresh(2000);
    }
}
