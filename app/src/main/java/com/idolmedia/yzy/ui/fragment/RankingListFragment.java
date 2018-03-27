package com.idolmedia.yzy.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.RankingEntity;
import com.idolmedia.yzy.ui.adapter.RankingListAdpater;
import com.idolmedia.yzy.ui.mvp.contract.RankingContract;
import com.idolmedia.yzy.ui.mvp.model.RankingModel;
import com.idolmedia.yzy.ui.mvp.presenter.RankingPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.DisplayUtil;
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
 * 创建时间：2017/12/14 15:51
 * 描述：  排行榜/评论
 */

public class RankingListFragment extends BaseFragment<RankingPresenter, RankingModel> implements RankingContract.View, OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    int type;
    String shopcommon_id;
    int pageNo = 1;
    RankingListAdpater rankingListAdpater;
    Unbinder unbinder;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;

    public static RankingListFragment getInstance(int type, String shopcommon_id) {
        RankingListFragment rankingListFragment = new RankingListFragment();
        rankingListFragment.type = type;
        rankingListFragment.shopcommon_id = shopcommon_id;
        return rankingListFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.include_no_recyclerview;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
      LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setMargins(0, DisplayUtil.getStatusBarHeight(getActivity()) + getResources().getDimensionPixelOffset(R.dimen.dp_40), 0,  0);  //设置间距
        smartLayout.setLayoutParams(layout);
         /*
        recyclerView.setPadding(0,0,0, getResources().getDimensionPixelOffset(R.dimen.dp_30));*/
        // if(type==2){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "20");
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Ranking_p(hashMap);
        rankingListAdpater = new RankingListAdpater();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rankingListAdpater);
        rankingListAdpater.setEmptyView(R.layout.empty_view, recyclerView);
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);

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

    }

    @Override
    public void Ranking_v(RankingEntity t) {
        if (t.getResultCode() == 1) {
            if (pageNo == 1) {
                rankingListAdpater.setNewData(t.getDatas());
            } else {
                rankingListAdpater.addData(t.getDatas());
            }

            //  rankingListAdpater.setOnItemClickListener((adapter, view, position) -> ToastUitl.showLong(""+position));
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "20");
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Ranking_p(hashMap);
        refreshlayout.finishLoadmore(1000);
    }
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "20");
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Ranking_p(hashMap);
        refreshlayout.finishRefresh(1000);
    }

/*    //定义如下方法给rc设置点击事件
    public interface OnSwitchPageListener {
        void switchPage();

    }
    private   OnSwitchPageListener onSwitchPageListener = null;
    public void setOnswitchPageListener(OnSwitchPageListener listener) {
        this.onSwitchPageListener = listener;
    }*/
}
