package com.idolmedia.yzy.ui.fragment.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.ui.activity.ClassificationActivity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.HotSearchActivity;
import com.idolmedia.yzy.ui.activity.InformationDetalisActivity;
import com.idolmedia.yzy.ui.activity.MessageListActivity;
import com.idolmedia.yzy.ui.activity.PreferentialActivity;
import com.idolmedia.yzy.ui.activity.SeckillActivity;
import com.idolmedia.yzy.ui.activity.SupportDetalisactivity;
import com.idolmedia.yzy.ui.activity.SupportListactivity;
import com.idolmedia.yzy.ui.activity.WebActivity;
import com.idolmedia.yzy.ui.adapter.RecommendAdapter;
import com.idolmedia.yzy.ui.mvp.contract.RecommendContract;
import com.idolmedia.yzy.ui.mvp.model.RecommendModel;
import com.idolmedia.yzy.ui.mvp.presenter.RecommendPresenter;
import com.idolmedia.yzy.utlis.DensityUtil;
import com.idolmedia.yzy.utlis.DividerGridItemDecoration;
import com.idolmedia.yzy.utlis.GlideImageLoader;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.view.popwindow.ObservableScrollView;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.DisplayUtil;
import com.mumu.common.utils.JsonUtils;
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
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/30.
 */

public class RecommendFragment extends BaseFragment<RecommendPresenter, RecommendModel> implements RecommendContract.View, OnRefreshListener, OnLoadmoreListener, ObservableScrollView.OnObservableScrollViewListener {

    Unbinder unbinder;
    @BindView(R.id.tile_view)
    View tileView;
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txt_search)
    EditText txtSearch;
    @BindView(R.id.cleartxt_iamge)
    ImageView cleartxtIamge;
    @BindView(R.id.liner_homesearch)
    LinearLayout linerHomesearch;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_iamge)
    ImageView rightIamge;
    @BindView(R.id.rl_bar)
    RelativeLayout rlBar;
    @BindView(R.id.top_tab_layout)
    TabLayout topTabLayout;
    @BindView(R.id.liner_search)
    LinearLayout linerSearch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_shopclass)
    TextView txtShopclass;
    @BindView(R.id.txt_preferential)
    TextView txtPreferential;
    @BindView(R.id.txt_activity)
    TextView txtActivity;
    @BindView(R.id.txt_seckill)
    TextView txtSeckill;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.shop_class)
    LinearLayout shopClass;
    @BindView(R.id.ob_scrolview)
    ObservableScrollView obScrolview;
    @BindView(R.id.liner_scrollView)
    LinearLayout linerScrollView;
    @BindView(R.id.liner_shop_class)
    LinearLayout linerShopClass;
    @BindView(R.id.liner_preferential)
    LinearLayout linerPreferential;
    @BindView(R.id.liner_activity)
    LinearLayout linerActivity;
    @BindView(R.id.liner_seckill)
    LinearLayout linerSeckill;


    private List<String> bannerList = new ArrayList<>();
    RecommendAdapter recommendAdapter;
    private int pageNo = 1;
    private boolean isBoolean;
    private ImageView price_sort, top_price_sort;
    int Position;
    View HeaderBannerView;
    private int bannerViewTopMargin, bannerViewHeight; // 广告视图距离顶部的距离
    private View itemHeaderFilterView; // 从ListView获取的筛选子View
    private int filterViewTopMargin; // 筛选视图距离顶部的距离
    private boolean isStickyTop = false; // 是否吸附在顶部

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        DisplayUtil.setWidgetHeight(tileView, DisplayUtil.getStatusBarHeight(getActivity()));
        tileView.setBackgroundColor(getResources().getColor(R.color.main_color));
        String[] title_name = new String[]{"新品", "限量", "人气"};
        for (int i = 0; i < title_name.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
            topTabLayout.addTab(topTabLayout.newTab().setText(title_name[i]));
        }
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.price_sort));
        topTabLayout.addTab(topTabLayout.newTab().setCustomView(R.layout.price_sort));
        price_sort = rootView.findViewById(R.id.image_price);
        top_price_sort = rootView.findViewById(R.id.image_price);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(getActivity()).resumeRequests();
                } else {
                    Glide.with(getActivity()).pauseRequests();
                }

            }
        });
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        obScrolview.setOnObservableScrollViewListener(this);
        recommendAdapter = new RecommendAdapter(R.layout.recommend_item);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        recyclerView.setAdapter(recommendAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recommendAdapter.setEmptyView(R.layout.empty_view, recyclerView);
        switchTab();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("banner_type", "recommend");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("location", 0);
        mPresenter.Banner_p(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("sort", 0);
        hashMap.put("order_type", "1");
        mPresenter.RecommendList_p(hashMap);
        recommendAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("shop_type", ((CommodityEntity.DatasBean) adapter.getData().get(position)).getShop_type());
            bundle.putString("shopcommon_id", String.valueOf(((CommodityEntity.DatasBean) adapter.getData().get(position)).getShopcommon_id()));
            startActivity(CommodityDetailsActivity.class, bundle);
        });
        if (!TextUtils.isEmpty(UserLoginUtil.IsUserId())) {
            hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            mPresenter.Unreadmessage_p(hashMap);
        }
    }

    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {

        if (HeaderBannerView == null) {
            HeaderBannerView = linerScrollView.getChildAt(1);
        }
        if (HeaderBannerView != null) {
            bannerViewTopMargin = DensityUtil.px2dip(getContext(), HeaderBannerView.getTop());
            bannerViewHeight = DensityUtil.px2dip(getContext(), HeaderBannerView.getHeight());
        }
        if (itemHeaderFilterView == null) {
            itemHeaderFilterView = linerScrollView.getChildAt(2);
        }
        if (itemHeaderFilterView != null) {
            filterViewTopMargin = DensityUtil.px2dip(getContext(), itemHeaderFilterView.getTop());
        }
        //     Log.e("filterViewTopMargin",t +"==="+ filterViewTopMargin + "===" +bannerViewTopMargin);
        // 处理筛选是否吸附在顶部
        if (t > (filterViewTopMargin + bannerViewTopMargin + 200)) {
            isStickyTop = true; // 吸附在顶部
            topTabLayout.setVisibility(View.VISIBLE);
        } else {
            isStickyTop = false; // 没有吸附在顶部
            topTabLayout.setVisibility(View.GONE);
        }
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            rlBar.setBackgroundColor(getResources().getColor(R.color.main_color));
        } else if (t > 0 && t < 200) {
            //滑动过程中，渐变
            float scale = (float) t / 60;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度
            alpha = (float) 0.5;
            rlBar.setBackgroundColor(getResources().getColor(R.color.main_color));

        } else {
            //过顶部图区域，标题栏定色
            rlBar.setBackgroundColor(getResources().getColor(R.color.main_color));
            //  relaClose.setBackground(getResources().getDrawable(R.drawable.yuan_b));
        }
        //  handleTitleBarColorEvaluate();
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


    private void switchTab() {

        topTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Position = tab.getPosition();
                tabLayout.setScrollPosition(tab.getPosition(), 0, true);
                Drawable drawable;
                pageNo = 1;
                HashMap hashMap = new HashMap<>();
                if (tab.getPosition() == 3) {
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", "10");
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("sort", tab.getPosition());
                    if (isBoolean) {
                        drawable = getResources().getDrawable(R.mipmap.icon_up_down_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        top_price_sort.setImageDrawable(drawable);
                        hashMap.put("order_type", "1");
                    } else {
                        drawable = getResources().getDrawable(R.mipmap.icon_up_up_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        top_price_sort.setImageDrawable(drawable);
                        hashMap.put("order_type", "2");
                    }
                    mPresenter.RecommendList_p(hashMap);
                } else {
                    drawable = getResources().getDrawable(R.mipmap.icon_down_yzy);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    price_sort.setImageDrawable(drawable);
                    top_price_sort.setImageDrawable(drawable);
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", "10");
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("sort", tab.getPosition());
                    hashMap.put("order_type", "1");
                    mPresenter.RecommendList_p(hashMap);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 3) {
                    HashMap hashMap = new HashMap<>();
                    pageNo = 1;
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", "10");
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("sort", tab.getPosition());
                    if (!isBoolean) {
                        Drawable drawable = getResources().getDrawable(R.mipmap.icon_up_down_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        top_price_sort.setImageDrawable(drawable);
                        hashMap.put("order_type", "1");
                    } else {
                        Drawable drawable = getResources().getDrawable(R.mipmap.icon_up_up_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        top_price_sort.setImageDrawable(drawable);
                        hashMap.put("order_type", "2");
                    }
                    mPresenter.RecommendList_p(hashMap);
                    isBoolean = !isBoolean;
                }

            }
        });
        //假tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                topTabLayout.setScrollPosition(tab.getPosition(), 0, true);
                Drawable drawable;
                pageNo = 1;
                Position = tab.getPosition();
                HashMap hashMap = new HashMap<>();
                if (tab.getPosition() == 3) {
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", "10");
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("sort", tab.getPosition());
                    if (isBoolean) {
                        drawable = getResources().getDrawable(R.mipmap.icon_up_down_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        top_price_sort.setImageDrawable(drawable);
                        hashMap.put("order_type", "1");
                    } else {
                        drawable = getResources().getDrawable(R.mipmap.icon_up_up_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        top_price_sort.setImageDrawable(drawable);
                        hashMap.put("order_type", "2");
                    }
                    mPresenter.RecommendList_p(hashMap);
                } else {
                    drawable = getResources().getDrawable(R.mipmap.icon_down_yzy);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    price_sort.setImageDrawable(drawable);
                    top_price_sort.setImageDrawable(drawable);
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", "10");
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("sort", tab.getPosition());
                    hashMap.put("order_type", "1");
                    mPresenter.RecommendList_p(hashMap);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                if (tab.getPosition() == 3) {
                    HashMap hashMap = new HashMap<>();
                    pageNo = 1;
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", "10");
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("sort", tab.getPosition());
                    if (!isBoolean) {
                        Drawable drawable = getResources().getDrawable(R.mipmap.icon_up_down_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        top_price_sort.setImageDrawable(drawable);
                        hashMap.put("order_type", "1");
                    } else {
                        Drawable drawable = getResources().getDrawable(R.mipmap.icon_up_up_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        top_price_sort.setImageDrawable(drawable);
                        hashMap.put("order_type", "2");
                    }
                    mPresenter.RecommendList_p(hashMap);
                    isBoolean = !isBoolean;
                }

            }
        });


    }




    @OnClick(R.id.txt_search)
    public void onViewClicked() {
        startActivity(HotSearchActivity.class);
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

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
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();

        //结束轮播
        banner.stopAutoPlay();
    }


    @Override
    public void RecommendList_v(CommodityEntity t) {
        if (t.getResultCode() == 1) {
            if (pageNo == 1) {
                recommendAdapter.setNewData(t.getDatas());
            } else {
                recommendAdapter.addData(t.getDatas());
            }

        } else {
            ToastUitl.show(t.getMsg(), 1000);
        }

    }

    @Override
    public void Unreadmessage_v(String t) {
        int resultCode = JsonUtils.getIntValue(t, "resultCode");
        String msg = JsonUtils.getValue(t, "msg");
        if (resultCode == 1) {
            int notReadCount = JsonUtils.getIntValue(t, "notReadCount");
            if (notReadCount > 0) {
                rightIamge.setImageResource(R.mipmap.icon_news_more_yzy);
            } else {
                rightIamge.setImageResource(R.mipmap.home_icon_messsage_yzy);

            }
        } else {
            ToastUitl.show(msg, 1000);
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("sort", Position);
        hashMap.put("order_type", isBoolean ? 1 : 2);
        mPresenter.RecommendList_p(hashMap);
        refreshlayout.finishLoadmore(1000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("banner_type", "recommend");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("location", 0);
        mPresenter.Banner_p(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("sort", Position);
        hashMap.put("order_type", isBoolean ? 1 : 2);
        mPresenter.RecommendList_p(hashMap);
        if (!TextUtils.isEmpty(UserLoginUtil.IsUserId())) {
            hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            mPresenter.Unreadmessage_p(hashMap);
        }
        refreshlayout.finishRefresh(1000);
    }


    @OnClick({R.id.right_iamge, R.id.liner_shop_class, R.id.liner_preferential, R.id.liner_activity, R.id.liner_seckill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_iamge:
                startActivity(MessageListActivity.class);
                break;
            case R.id.liner_shop_class:
                startActivity(ClassificationActivity.class);
                break;
            case R.id.liner_preferential:
                startActivity(PreferentialActivity.class);
                break;
            case R.id.liner_activity:
                startActivity(SupportListactivity.class);
                break;
            case R.id.liner_seckill:
                startActivity(SeckillActivity.class);
                break;

        }
    }


     /*   @OnClick({R.id.txt_shopclass, R.id.txt_preferential, R.id.txt_activity, R.id.txt_seckill, R.id.right_iamge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_shopclass:
                startActivity(ClassificationActivity.class);
                break;
            case R.id.txt_preferential:
                startActivity(PreferentialActivity.class);
                break;
            case R.id.txt_activity:
                startActivity(SupportListactivity.class);
                break;
            case R.id.txt_seckill:
                startActivity(SeckillActivity.class);
                break;
            case R.id.right_iamge:
                startActivity(MessageListActivity.class);
                break;
        }
    }
*/
}
