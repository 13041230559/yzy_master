package com.idolmedia.yzy.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyFollowIdoEntity;
import com.idolmedia.yzy.entity.MyFollowOfficialEntity;
import com.idolmedia.yzy.ui.adapter.MyFollowIdoAdapter;
import com.idolmedia.yzy.ui.adapter.MyFollowOfficialAdapter;
import com.idolmedia.yzy.ui.mvp.contract.MyIdoContract;
import com.idolmedia.yzy.ui.mvp.model.MyIdoModel;
import com.idolmedia.yzy.ui.mvp.presenter.MyIdoPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseFragment;
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
 * 创建时间：2018/2/1 11:48
 * 描述：
 */

public class MyIdoFragment extends BaseFragment<MyIdoPresenter, MyIdoModel> implements MyIdoContract.View, OnRefreshListener, OnLoadmoreListener {

    int type;
    int pageNo = 1;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    Unbinder unbinder;
    MyFollowIdoAdapter followIdoAdapter;
    MyFollowOfficialAdapter  followOfficialAdapter;
    public static MyIdoFragment getInstance(int type) {
        MyIdoFragment fragment = new MyIdoFragment();
        fragment.type = type;
        return fragment;
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
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        if (type == 0) {
            mPresenter.MyIdoList_p(PostMap(type, pageNo), pageNo);
            followIdoAdapter=new MyFollowIdoAdapter();
            recyclerView.setAdapter(followIdoAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(followIdoAdapter);
            followIdoAdapter.setEmptyView(R.layout.empty_view,recyclerView);
            followIdoAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()){
                    case  R.id.iamge_like:
                        MyFollowIdoEntity.DatasBean followIdo=(MyFollowIdoEntity.DatasBean)adapter.getData().get(position);
                        HashMap<String,Object> hashMap=new HashMap<>();
                        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                        hashMap.put("FKEY",PutUtils.FKEY());
                        hashMap.put("idol_id",followIdo.getIdol_id());
                        hashMap.put("attention_type",followIdo.isAtted()?1:0);
                        mPresenter.FollowIdo_p(hashMap,position,followIdo.isAtted(),followIdo.getFans_count());
                        break;
                }
            });
        } else {
            mPresenter.MyAuthList_p(PostMap(type, pageNo), pageNo);
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
        }

    }

    private HashMap PostMap(int type, int pageNo) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (type == 0) {
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("pageNo", pageNo);
            hashMap.put("pageSize", 10);
        } else {
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("pageNo", pageNo);
            hashMap.put("pageSize", 10);
        }

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
    public void MyIdoList_v(MyFollowIdoEntity t, int pageNo) {
        if(t.getResultCode()==1){
            if(pageNo==1){
                followIdoAdapter.setNewData(t.getDatas());
            }else {
                followIdoAdapter.addData(t.getDatas());
            }

        }


    }

    @Override
    public void MyAuthList_v(MyFollowOfficialEntity t, int pageNo) {
        if(t.getResultCode()==1){
            if(pageNo==1){
                followOfficialAdapter.setNewData(t.getDatas());
            }else {
                followOfficialAdapter.addData(t.getDatas());
            }

        }

    }

    @Override
    public void FollowIdo_v(BaseResult t, int position, boolean isfollow,int fanscount) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            followIdoAdapter.getData().get(position).setAtted(!isfollow);
            followIdoAdapter.getData().get(position).setFans_count(!isfollow?++fanscount:fanscount==0?fanscount:--fanscount);
            followIdoAdapter.notifyItemChanged(position);
        }

    }

    @Override
    public void FollowThirdParty_v(BaseResult t, int position, boolean isfollow,int fanscount) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            followOfficialAdapter.getData().get(position).setIsAttend(!isfollow);
            followOfficialAdapter.getData().get(position).setFans_count(!isfollow?++fanscount:fanscount==0?fanscount:--fanscount);
            followOfficialAdapter.notifyItemChanged(position);
        }
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
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        if(type==0){
            mPresenter.MyIdoList_p(PostMap(type,pageNo),pageNo);
        }else {
            mPresenter.MyAuthList_p(PostMap(type,pageNo),pageNo);
        }
        refreshlayout.finishLoadmore(1000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo=1;
        if(type==0){
            mPresenter.MyIdoList_p(PostMap(type,pageNo),pageNo);
        }else {
            mPresenter.MyAuthList_p(PostMap(type,pageNo),pageNo);
        }
        refreshlayout.finishRefresh(1000);
    }
}
