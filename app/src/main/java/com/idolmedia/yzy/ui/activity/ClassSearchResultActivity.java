package com.idolmedia.yzy.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.RecommendEntity;
import com.idolmedia.yzy.ui.adapter.EntertainmentlAdapter;
import com.idolmedia.yzy.ui.adapter.RecommendAdapter;
import com.idolmedia.yzy.ui.mvp.contract.ClassSearchResultContract;
import com.idolmedia.yzy.ui.mvp.model.ClassSearchResultModel;
import com.idolmedia.yzy.ui.mvp.presenter.ClassSearchResultPresenter;
import com.idolmedia.yzy.utlis.DividerGridItemDecoration;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.TabLayout.MODE_FIXED;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 14:12
 * 描述：
 */

public class ClassSearchResultActivity extends BaseActivity<ClassSearchResultPresenter, ClassSearchResultModel> implements ClassSearchResultContract.View, OnRefreshListener, OnLoadmoreListener {
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
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ImageView price_sort, check_sort;
    String dictionaries_id;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout SmartRefreshLayout;
    private RecommendAdapter adapter;
    private EntertainmentlAdapter entertainmentlAdapter;
    private boolean isBoolean, isCheckSort;
    int Position;
    int pageNo = 1;
    String title;
    @Override
    public int getLayoutId() {
        return R.layout.activity_calsssearch_result;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        dictionaries_id = getIntent().getStringExtra("dictionaries_id");
        title=  getIntent().getStringExtra("title");
        Log.e("dictionaries_id", dictionaries_id + "");
        tvTitle.setText(TextUtils.isEmpty(title)?"搜索结果":title);
        String[] title_name = new String[]{"人气", "限量", "最新", "价格"};
        tabLayout.setTabMode(MODE_FIXED);
        for (int i = 0; i < title_name.length - 1; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
        }
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.price_sort));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.check_sort));
        price_sort = findViewById(R.id.image_price);
        check_sort = findViewById(R.id.image_price_sort);
        adapter = new RecommendAdapter(R.layout.recommend_item);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setAdapter(adapter);
        adapter.setEmptyView(R.layout.empty_view,recyclerView);
        entertainmentlAdapter = new EntertainmentlAdapter();
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("shop_type", ((CommodityEntity.DatasBean) adapter.getData().get(position)).getShop_type());
            bundle.putString("shopcommon_id", String.valueOf(((CommodityEntity.DatasBean) adapter.getData().get(position)).getShopcommon_id()));
            startActivity(CommodityDetailsActivity.class, bundle);
        });
        entertainmentlAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("shop_type", ((CommodityEntity.DatasBean) adapter.getData().get(position)).getShop_type());
            bundle.putString("shopcommon_id", String.valueOf(((CommodityEntity.DatasBean) adapter.getData().get(position)).getShopcommon_id()));
            startActivity(CommodityDetailsActivity.class, bundle);
        });
        entertainmentlAdapter.setEmptyView(R.layout.empty_view,recyclerView);

        SmartRefreshLayout.setOnRefreshListener(this);
        SmartRefreshLayout.setOnLoadmoreListener(this);
        mPresenter.ClassSearchResult_p(PostMap(1, 0, 1), 1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pageNo=1;
                Drawable drawable;
                Position = tab.getPosition();
                if (tab.getPosition() == 3) {
                    if (isBoolean) {
                        drawable = getResources().getDrawable(R.mipmap.icon_up_down_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 1), 1);
                    } else {
                        drawable = getResources().getDrawable(R.mipmap.icon_up_up_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 2), 1);
                    }

                } else if (tab.getPosition() == 4) {
                    if (isCheckSort) {
                        drawable = getResources().getDrawable(R.mipmap.icon_anyway_l_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        check_sort.setImageDrawable(drawable);
                        recyclerView.setLayoutManager(new GridLayoutManager(ClassSearchResultActivity.this, 2));
                        recyclerView.setAdapter(adapter);

                    } else {
                        drawable = getResources().getDrawable(R.mipmap.icon_anyway_s_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        check_sort.setImageDrawable(drawable);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ClassSearchResultActivity.this));
                        recyclerView.setAdapter(entertainmentlAdapter);
                    }

                } else {
                    mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 1), 1);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                pageNo=1;
                Position = tab.getPosition();
                if (tab.getPosition() == 3) {
                    if (!isBoolean) {
                        Drawable drawable = getResources().getDrawable(R.mipmap.icon_up_down_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 1), 1);
                    } else {
                        Drawable drawable = getResources().getDrawable(R.mipmap.icon_up_up_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_sort.setImageDrawable(drawable);
                        mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 2), 1);
                    }
                    isBoolean = !isBoolean;
                } else if (tab.getPosition() == 4) {
                    Drawable drawable;
                    if (!isCheckSort) {
                        drawable = getResources().getDrawable(R.mipmap.icon_anyway_l_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        check_sort.setImageDrawable(drawable);
                        recyclerView.setLayoutManager(new GridLayoutManager(ClassSearchResultActivity.this, 2));
                        recyclerView.setAdapter(adapter);

                    } else {
                        drawable = getResources().getDrawable(R.mipmap.icon_anyway_s_yzy);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        check_sort.setImageDrawable(drawable);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ClassSearchResultActivity.this));
                        recyclerView.setAdapter(entertainmentlAdapter);

                    }
                    isCheckSort = !isCheckSort;
                } else {

                    mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 1), 1);

                }

            }
        });


    }

    private HashMap PostMap(int pageNo, int sort, int order_type) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("dictionaries_id", dictionaries_id);
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageSize", "10");
        hashMap.put("pageNo", pageNo);
        hashMap.put("sort", sort);
        hashMap.put("order_type", order_type);
        return hashMap;
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
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        mPresenter.ClassSearchResult_p(PostMap(pageNo, Position, isBoolean ? 1 : 2), pageNo);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        mPresenter.ClassSearchResult_p(PostMap(pageNo, Position, isBoolean ? 1 : 2), pageNo);
        refreshlayout.finishRefresh(2000);
    }

    @Override
    public void ClassSearchResult_v(CommodityEntity t, int pageN1o) {
        if (t.getResultCode() == 1) {
               if (pageNo == 1) {
                   adapter.setNewData(t.getDatas());
               } else {
                   adapter.addData(t.getDatas());
               }

            List<CommodityEntity.DatasBean> list=new ArrayList<>();
               list.addAll(t.getDatas());
            if (pageNo == 1) {
                entertainmentlAdapter.setNewData(list);
            } else {
                entertainmentlAdapter.addData(list);
            }
        }
    }
}
