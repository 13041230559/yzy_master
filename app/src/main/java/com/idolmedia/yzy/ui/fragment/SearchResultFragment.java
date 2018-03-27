package com.idolmedia.yzy.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.InformationEntity;
import com.idolmedia.yzy.entity.MyFollowIdoEntity;
import com.idolmedia.yzy.entity.MyFollowOfficialEntity;
import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.InformationDetalisActivity;
import com.idolmedia.yzy.ui.activity.SupportDetalisactivity;
import com.idolmedia.yzy.ui.adapter.EntertainmentlAdapter;
import com.idolmedia.yzy.ui.adapter.InformationlAdapter;
import com.idolmedia.yzy.ui.adapter.MyFollowIdoAdapter;
import com.idolmedia.yzy.ui.adapter.MyFollowOfficialAdapter;
import com.idolmedia.yzy.ui.adapter.RecommendAdapter;
import com.idolmedia.yzy.ui.adapter.SupportAdapter;
import com.idolmedia.yzy.ui.mvp.contract.SearchResultContract;
import com.idolmedia.yzy.ui.mvp.model.SearchResultModel;
import com.idolmedia.yzy.ui.mvp.presenter.SearchResultPresenter;
import com.idolmedia.yzy.utlis.DividerGridItemDecoration;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.view.EmptyRecyclerView;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.JsonUtils;
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
import butterknife.Unbinder;

import static android.support.design.widget.TabLayout.MODE_FIXED;

/**
 * 项目名称：com.idolmedia.yzy.ui.fragment
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/13 10:53
 * 描述：
 */

public class SearchResultFragment extends BaseFragment<SearchResultPresenter, SearchResultModel> implements SearchResultContract.View, OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.recyclerView)
    EmptyRecyclerView recyclerView;
    Unbinder unbinder;
    int type = 0;
    int pageNo = 1;
    int order_type = 1;
    int Position;
    boolean isBoolean, isCheckSort;
    String keywords;
    ImageView price_sort, check_sort;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    InformationlAdapter informationlAdapter;
    SupportAdapter supportAdapter;
    MyFollowIdoAdapter followIdoAdapter;
    MyFollowOfficialAdapter followOfficialAdapter;
    @BindView(R.id.image_lier_sort)
    ImageView imageLierSort;
    @BindView(R.id.liner_tab)
    LinearLayout linerTab;
    @BindView(R.id.iamge_no_view)
    ImageView iamgeNoView;
    @BindView(R.id.txt_no_data)
    TextView txtNoData;
    private View mEmptyView;
    List<CommodityEntity.DatasBean>  commodityList=new ArrayList<>();
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    private RecommendAdapter recommendAdapter;
    private EntertainmentlAdapter entertainmentlAdapter;

    public static SearchResultFragment getInstance(int type, String keywords) {
        SearchResultFragment resultFragment = new SearchResultFragment();
        resultFragment.type = type;
        resultFragment.keywords = keywords;
        return resultFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search_result;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        mEmptyView = rootView.findViewById(R.id.empty_no_view);
        if (type == 0) {
            linerTab.setVisibility(View.VISIBLE);
            tabLayout.removeAllTabs();
            imageLierSort.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            String[] title_name = new String[]{"人气", "限量", "最新", "价格"};
            tabLayout.setTabMode(MODE_FIXED);
            for (int i = 0; i < title_name.length - 1; i++) {
                tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
            }
            smartLayout.setOnRefreshListener(this);
            smartLayout.setOnLoadmoreListener(this);
            tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.price_sort));
            //价格切换
            price_sort = rootView.findViewById(R.id.image_price);

            recommendAdapter = new RecommendAdapter(R.layout.recommend_item);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
            recyclerView.setAdapter(recommendAdapter);
            entertainmentlAdapter = new EntertainmentlAdapter();
            mPresenter.SearchResult_p(PostHttp(keywords, 1, 0, 1, type), 1, type);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Drawable drawable;
                    Position = tab.getPosition();
                    if (tab.getPosition() == 3) {
                        if (isBoolean) {
                            drawable = getResources().getDrawable(R.mipmap.icon_up_down_yzy);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            price_sort.setImageDrawable(drawable);
                            mPresenter.SearchResult_p(PostHttp(keywords, 1, tab.getPosition(), 1, type), 1, type);
                            //  mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 1), 1);
                        } else {
                            drawable = getResources().getDrawable(R.mipmap.icon_up_up_yzy);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            price_sort.setImageDrawable(drawable);
                            mPresenter.SearchResult_p(PostHttp(keywords, 1, tab.getPosition(), 2, type), 1, type);
                            //  mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 2), 1);
                        }

                    } /*else if (tab.getPosition() == 4) {
                        if (isCheckSort) {
                            drawable = getResources().getDrawable(R.mipmap.icon_anyway_l_yzy);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            check_sort.setImageDrawable(drawable);
                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            recyclerView.setAdapter(recommendAdapter);
                        } else {
                            drawable = getResources().getDrawable(R.mipmap.icon_anyway_s_yzy);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            check_sort.setImageDrawable(drawable);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(entertainmentlAdapter);
                        }

                    }*/ else {
                        mPresenter.SearchResult_p(PostHttp(keywords, 1, tab.getPosition(), 1, type), 1, type);
                        //  mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 1), 1);
                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    Position = tab.getPosition();
                    if (tab.getPosition() == 3) {
                        if (!isBoolean) {
                            Drawable drawable = getResources().getDrawable(R.mipmap.icon_up_down_yzy);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            price_sort.setImageDrawable(drawable);
                            mPresenter.SearchResult_p(PostHttp(keywords, 1, tab.getPosition(), 1, type), 1, type);
                            //  mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 1), 1);
                        } else {
                            Drawable drawable = getResources().getDrawable(R.mipmap.icon_up_up_yzy);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            price_sort.setImageDrawable(drawable);
                            mPresenter.SearchResult_p(PostHttp(keywords, 1, tab.getPosition(), 2, type), 1, type);
                        }
                        isBoolean = !isBoolean;
                    } /*else if (tab.getPosition() == 4) {
                        Drawable drawable;
                        if (!isCheckSort) {
                            drawable = getResources().getDrawable(R.mipmap.icon_anyway_l_yzy);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            check_sort.setImageDrawable(drawable);
                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            recyclerView.setAdapter(recommendAdapter);

                        } else {
                            drawable = getResources().getDrawable(R.mipmap.icon_anyway_s_yzy);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            check_sort.setImageDrawable(drawable);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(entertainmentlAdapter);

                        }
                        isCheckSort = !isCheckSort;
                    } */ else {
                        mPresenter.SearchResult_p(PostHttp(keywords, 1, tab.getPosition(), 1, type), 1, type);
                        //  mPresenter.ClassSearchResult_p(PostMap(1, tab.getPosition(), 1), 1);

                    }

                }
            });
            entertainmentlAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("shop_type", ((CommodityEntity.DatasBean) adapter.getData().get(position)).getShop_type());
                    bundle.putString("shopcommon_id", String.valueOf(((CommodityEntity.DatasBean) adapter.getData().get(position)).getShopcommon_id()));
                    startActivity(CommodityDetailsActivity.class, bundle);
                }
            });
            recommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("shop_type", ((CommodityEntity.DatasBean) adapter.getData().get(position)).getShop_type());
                    bundle.putString("shopcommon_id", String.valueOf(((CommodityEntity.DatasBean) adapter.getData().get(position)).getShopcommon_id()));
                    startActivity(CommodityDetailsActivity.class, bundle);
                }
            });
        } else if (type == 1) {
            mPresenter.SearchResult_p(PostHttp(keywords, 1, 0, 1, type), 1, type);
            followOfficialAdapter=new MyFollowOfficialAdapter();
            recyclerView.setAdapter(followOfficialAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(followOfficialAdapter);
            followOfficialAdapter.setEmptyView(R.layout.empty_view,recyclerView);
            followOfficialAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()){
                    case  R.id.iamge_like:
                        MyFollowOfficialEntity.DatasBean followofficial=(MyFollowOfficialEntity.DatasBean)adapter.getData().get(position);
                        HashMap<String,Object> hashMap=new HashMap<>();
                        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                        hashMap.put("FKEY",PutUtils.FKEY());
                        hashMap.put("by_virtualuser_id",followofficial.getBy_virtualuser_id());
                        hashMap.put("attention_type",followofficial.getIsAttend()?1:0);
                        mPresenter.FollowThirdParty_p(hashMap,position,followofficial.getIsAttend(),followofficial.getFans_count());
                        break;
                }
            });
        } else if (type == 2) {
            mPresenter.SearchResult_p(PostHttp(keywords, 1, 0, 1, type), 1, type);
            followIdoAdapter = new MyFollowIdoAdapter();
            recyclerView.setAdapter(followIdoAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            followIdoAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()) {
                    case R.id.iamge_like:
                        MyFollowIdoEntity.DatasBean followIdo = (MyFollowIdoEntity.DatasBean) adapter.getData().get(position);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                        hashMap.put("FKEY", PutUtils.FKEY());
                        hashMap.put("idol_id", followIdo.getIdol_id());
                        hashMap.put("attention_type", followIdo.isAtted() ? 1 : 0);
                        mPresenter.FollowIdo_p(hashMap, position, followIdo.isAtted(),followIdo.getFans_count());
                        break;

                }
            });
        } else if (type == 3) {
            mPresenter.SearchResult_p(PostHttp(keywords, 1, 0, 1, type), 1, type);
            informationlAdapter = new InformationlAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(informationlAdapter);
            informationlAdapter.setOnItemClickListener((adapter, view, position) -> {
                Bundle bundle = new Bundle();
                bundle.putString("shopcommon_id", ((InformationEntity.DatasBean) adapter.getData().get(position)).getShopcommon_id());
                bundle.putString("image_url", ((InformationEntity.DatasBean) adapter.getData().get(position)).getPic_url());
                bundle.putString("shop_type", "Information");
                startActivity(InformationDetalisActivity.class, bundle);
            });
        } else if (type == 4) {
            mPresenter.SearchResult_p(PostHttp(keywords, 1, 0, 1, type), 1, type);
            supportAdapter = new SupportAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(supportAdapter);
            supportAdapter.setOnItemClickListener((adapter, view, position) -> {
                SupportEntity.DatasBean datasBean = (SupportEntity.DatasBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("shopcommon_id", datasBean.getShopcommon_id());
                bundle.putString("shop_type", datasBean.getShop_type());
                startActivity(SupportDetalisactivity.class, bundle);
            });

        }
        recyclerView.setEmptyView(mEmptyView);

    }

    private HashMap PostHttp(String keywords, int pageNo, int sort, int order_type, int search_type) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("keywords", keywords);
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("sort", sort);
        hashMap.put("order_type", String.valueOf(order_type));
        hashMap.put("search_type", String.valueOf(search_type));
        return hashMap;
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
    public void SearchResult_v(String t, int pageNo, int search_type) {
        int resultCode = JsonUtils.getIntValue(t, "resultCode");
        String msg = JsonUtils.getValue(t, "msg");
        if (resultCode == 1) {
            if (search_type == 0) {
                CommodityEntity commodityEntity = (CommodityEntity) JsonUtils.fromJson(t, new TypeToken<CommodityEntity>() {
                }.getType());
                if (pageNo == 1) {
                    recommendAdapter.setNewData(commodityEntity.getDatas());
                } else {
                    recommendAdapter.addData(commodityEntity.getDatas());
                }
                commodityList=commodityEntity.getDatas();
                if (pageNo == 1) {
                    entertainmentlAdapter.setNewData(commodityList);
                } else {
                    entertainmentlAdapter.addData(commodityList);
                }
            } else if (search_type == 1) {
                MyFollowOfficialEntity myFollowIdoEntity = (MyFollowOfficialEntity) JsonUtils.fromJson(t, new TypeToken<MyFollowOfficialEntity>() {
                }.getType());
                if (pageNo == 1) {
                    followOfficialAdapter.setNewData(myFollowIdoEntity.getDatas());
                } else {
                    followOfficialAdapter.addData(myFollowIdoEntity.getDatas());
                }

            } else if (search_type == 2) {
                MyFollowIdoEntity idoEntity = (MyFollowIdoEntity) JsonUtils.fromJson(t, new TypeToken<MyFollowIdoEntity>() {
                }.getType());

                if (pageNo == 1) {
                    followIdoAdapter.setNewData(idoEntity.getDatas());
                } else {
                    followIdoAdapter.addData(idoEntity.getDatas());
                }


            } else if (search_type == 3) {
                InformationEntity informationEntity = (InformationEntity) JsonUtils.fromJson(t, new TypeToken<InformationEntity>() {
                }.getType());
                if (pageNo == 1) {
                    informationlAdapter.setNewData(informationEntity.getDatas());
                } else {
                    informationlAdapter.addData(informationEntity.getDatas());
                }

            } else if (search_type == 4) {
                SupportEntity supportEntity = (SupportEntity) JsonUtils.fromJson(t, new TypeToken<SupportEntity>() {
                }.getType());
                if (pageNo == 1) {
                    supportAdapter.setNewData(supportEntity.getDatas());
                } else {
                    supportAdapter.addData(supportEntity.getDatas());
                }

            }
        } else {
            ToastUitl.show(msg, 1000);
        }

    }

    @Override
    public void FollowIdo_v(BaseResult t, int position, boolean isfollow,int fanscount) {
        ToastUitl.show(t.getMsg(), 1000);
        if (t.getResultCode() == 1) {
            followIdoAdapter.getData().get(position).setAtted(!isfollow);
            followIdoAdapter.getData().get(position).setFans_count(!isfollow?++fanscount:fanscount==0?fanscount:--fanscount);
            followIdoAdapter.notifyItemChanged(position);
        }

    }

    @Override
    public void FollowThirdParty_v(BaseResult t, int position, boolean isfollow, int fanscount) {
        ToastUitl.show(t.getMsg(), 1000);
        if (t.getResultCode() == 1) {
            followOfficialAdapter.getData().get(position).setIsAttend(!isfollow);
            followOfficialAdapter.getData().get(position).setFans_count(!isfollow?++fanscount:fanscount==0?fanscount:--fanscount);
            followOfficialAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        if (type == 0) {
            mPresenter.SearchResult_p(PostHttp(keywords, pageNo, Position, isBoolean ? 1 : 2, type), pageNo, type);
        } else {
            mPresenter.SearchResult_p(PostHttp(keywords, pageNo, 0, 0, type), pageNo, type);
        }
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        if (type == 0) {
            mPresenter.SearchResult_p(PostHttp(keywords, pageNo, Position, isBoolean ? 1 : 2, type), pageNo, type);
        } else {
            mPresenter.SearchResult_p(PostHttp(keywords, pageNo, 0, 1, type), pageNo, type);
        }
        refreshlayout.finishRefresh(2000);
    }

    @OnClick(R.id.image_lier_sort)
    public void onViewClicked() {
        isCheckSort = !isCheckSort;
        if (!isCheckSort) {
            imageLierSort.setImageResource(R.mipmap.icon_anyway_l_yzy);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setAdapter(recommendAdapter);
        } else {
            imageLierSort.setImageResource(R.mipmap.icon_anyway_s_yzy);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(entertainmentlAdapter);

        }
    }
}
