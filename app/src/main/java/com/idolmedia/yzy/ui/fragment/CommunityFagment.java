package com.idolmedia.yzy.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.InformationEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.CommunityDetalisActivity;
import com.idolmedia.yzy.ui.activity.InformationDetalisActivity;
import com.idolmedia.yzy.ui.activity.SupportDetalisactivity;
import com.idolmedia.yzy.ui.activity.WebActivity;
import com.idolmedia.yzy.ui.adapter.CommunityAdapter;
import com.idolmedia.yzy.ui.mvp.contract.InformationContract;
import com.idolmedia.yzy.ui.mvp.model.InformationModel;
import com.idolmedia.yzy.ui.mvp.presenter.InformationPresenter;
import com.idolmedia.yzy.utlis.GlideImageLoader;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.view.EmptyRecyclerView;
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
 * 创建时间：2018/2/2 16:16
 * 描述：
 */

public class CommunityFagment extends BaseFragment<InformationPresenter, InformationModel> implements InformationContract.View, OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.header_banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    Unbinder unbinder;
    int pageNo = 1;
    int tabPosition;
    private View mEmptyView;
    private CommunityAdapter communityAdapter;
    private List<String> bannerList = new ArrayList<>();
    public static CommunityFagment getInstance() {
        CommunityFagment communityFagment = new CommunityFagment();
        return communityFagment;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_community;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        mEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.empty_view,null, false);
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        communityAdapter   =new CommunityAdapter(getActivity(),false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(communityAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setEmptyView(mEmptyView);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("banner_type", "community");
        hashMap.put("location", "0");
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Banner_p(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("comment_from", "4");
        mPresenter.CommentList_p(hashMap,1);


        communityAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("bean",((CommentEntity.DatasBean)communityAdapter.getDatas().get(position)));
                bundle.putString("comment_id",String.valueOf(((CommentEntity.DatasBean)communityAdapter.getDatas().get(position)).getComment_id()));
                startActivity(CommunityDetalisActivity.class,bundle);
            }
            @Override
            public void onItemGoodClick(int position, int lickcount, int comment_id) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("virtualuser_id", UserLoginUtil.IsUserId());
                map.put("FKEY",PutUtils.FKEY());
                map.put("comment_id",comment_id);
                map.put("prise_from","4");
                mPresenter.GoodClick_p(map,position,lickcount);
            }
        });
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
    public void Banner_v(BannerEntity t) {
        if (t.getResultCode() == 1) {
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


                    }else   {
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
            banner.setImageLoader(new GlideImageLoader());
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.start();

        }

    }

    @Override
    public void InformationList_v(InformationEntity t,int PageNo) {

    }

    @Override
    public void CommentList_v(CommentEntity t,int PageNo) {
        if(t.getResultCode()==1){
            if(pageNo==1){
                communityAdapter.setDatas(t.getDatas());
            }else {
                communityAdapter.getDatas().addAll(t.getDatas());
            }
            communityAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void GoodClick_v(BaseResult t, int position, int linkcount) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            // communityCommentAdapter.getData().get(position).setIsAtted(!adpater.getData().get(onClickPosition).isIsAtted());
            //adpater.notifyItemChanged(onClickPosition);
            //  communityCommentAdapter.getDatas().get(position).
            ((CommentEntity.DatasBean)communityAdapter.getDatas().get(position)).setIsPrised(true);
            ((CommentEntity.DatasBean)communityAdapter.getDatas().get(position)).setPrise_count(linkcount+1);
            communityAdapter.notifyItemChanged(position);
        }


    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo",pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("sort", tabPosition+ 1);
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("comment_from", "4");
        mPresenter.CommentList_p(hashMap,pageNo);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("sort", tabPosition+ 1);
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("comment_from", "4");
        mPresenter.CommentList_p(hashMap,pageNo);
        refreshlayout.finishRefresh(2000);
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
}
