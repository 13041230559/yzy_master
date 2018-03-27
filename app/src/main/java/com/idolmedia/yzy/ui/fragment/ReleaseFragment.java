package com.idolmedia.yzy.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.MyReleaseInfoEntity;
import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.CommunityDetalisActivity;
import com.idolmedia.yzy.ui.activity.SupportDetalisactivity;
import com.idolmedia.yzy.ui.adapter.CommunityAdapter;
import com.idolmedia.yzy.ui.adapter.RecommendAdapter;
import com.idolmedia.yzy.ui.adapter.SupportAdapter;
import com.idolmedia.yzy.ui.mvp.contract.MyReleaseListContract;
import com.idolmedia.yzy.ui.mvp.model.MyReleaseListModel;
import com.idolmedia.yzy.ui.mvp.presenter.MyReleaseListPresenter;
import com.idolmedia.yzy.utlis.DividerGridItemDecoration;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.view.EmptyRecyclerView;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.JsonUtils;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称：com.idolmedia.yzy.ui.fragment
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/13 15:35
 * 描述： 发布
 */

public class ReleaseFragment extends BaseFragment<MyReleaseListPresenter, MyReleaseListModel> implements MyReleaseListContract.View , OnRefreshListener, OnLoadmoreListener {
    int type = 0;
    @BindView(R.id.recyclerView)
    EmptyRecyclerView recyclerView;
    Unbinder unbinder;
    int pageNo = 1;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    RecommendAdapter recommendAdapter;
    CommunityAdapter commentAdapter;
    SupportAdapter supportAdapter;
    private View mEmptyView;
    public static ReleaseFragment getInstance(int type) {
        ReleaseFragment releaseFragment = new ReleaseFragment();
        releaseFragment.type = type;
        return releaseFragment;
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
        mEmptyView =rootView.findViewById(R.id.empty_no_view);
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageNo", "1");
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("publish_type", type);
        mPresenter.MyReleaseList_p(hashMap);
        if(type==0){
            recommendAdapter = new RecommendAdapter(R.layout.recommend_item);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setAdapter(recommendAdapter);
            recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
            recommendAdapter.setOnItemClickListener((adapter, view, position) -> {
                Bundle bundle = new Bundle();
                bundle.putString("shop_type", ((CommodityEntity.DatasBean) adapter.getData().get(position)).getShop_type());
                bundle.putString("shopcommon_id", String.valueOf(((CommodityEntity.DatasBean) adapter.getData().get(position)).getShopcommon_id()));
                startActivity(CommodityDetailsActivity.class, bundle);
            });
        }else if(type==1){
            commentAdapter   =new CommunityAdapter(getActivity(),false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(commentAdapter);
            commentAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("bean",((CommentEntity.DatasBean)commentAdapter.getDatas().get(position)));
                    bundle.putString("comment_id",String.valueOf(((CommentEntity.DatasBean)commentAdapter.getDatas().get(position)).getComment_id()));
                    startActivity(CommunityDetalisActivity.class,bundle);
                }
                @Override
                public void onItemGoodClick(int position, int lickcount, int commentid) {
                }
            });

        }else if(type==2){
            supportAdapter = new SupportAdapter();
            recyclerView.setAdapter(supportAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        }
        recyclerView.setEmptyView(mEmptyView);
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
        ToastUitl.show(msg,1000);
    }

    @Override
    public void MyReleaseList_v(String t) {
        int resultCode= JsonUtils.getIntValue(t,"resultCode");
        String msg=JsonUtils.getValue(t,"msg");
        if(resultCode==1){
            if(type==0){
                CommodityEntity commodityEntity= (CommodityEntity) JsonUtils.fromJson(t, new TypeToken<CommodityEntity>() {
                }.getType());
                if(pageNo==1){
                    recommendAdapter.setNewData(commodityEntity.getDatas());
                }else {
                    recommendAdapter.addData(commodityEntity.getDatas());
                }

            }else if(type==1){
                CommentEntity commentEntity= (CommentEntity) JsonUtils.fromJson(t, new TypeToken<CommentEntity>() {
                }.getType());
                if(pageNo==1){
                    commentAdapter.setDatas(commentEntity.getDatas());
                }else {
                    commentAdapter.getDatas().addAll(commentEntity.getDatas());
                }
                commentAdapter.notifyDataSetChanged();
            }else if(type==2){
                SupportEntity  supportEntity= (SupportEntity) JsonUtils.fromJson(t, new TypeToken<SupportEntity>() {
                }.getType());
                if(pageNo==1){
                    supportAdapter.setNewData(supportEntity.getDatas());
                }else {
                    supportAdapter.addData(supportEntity.getDatas());
                }
            }



        }else {

            ToastUitl.show(msg,1000);
        }


    }

    @Override
    public void MyReleaseInfo_v(MyReleaseInfoEntity t) {


    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("publish_type", type);
        mPresenter.MyReleaseList_p(hashMap);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo=1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("publish_type", type);
        mPresenter.MyReleaseList_p(hashMap);
        refreshlayout.finishRefresh(2000);
    }
}
