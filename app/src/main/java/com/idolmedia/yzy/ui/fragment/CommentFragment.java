package com.idolmedia.yzy.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommentTypeEntity;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.MessageEvent;
import com.idolmedia.yzy.ui.adapter.CommunityAdapter;
import com.idolmedia.yzy.ui.mvp.contract.CommentContract;
import com.idolmedia.yzy.ui.mvp.model.CommentModel;
import com.idolmedia.yzy.ui.mvp.presenter.CommentPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.view.EmptyRecyclerView;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.DisplayUtil;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称：com.idolmedia.yzy.ui.fragment
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/13 10:50
 * 描述：
 */

public class CommentFragment extends BaseFragment<CommentPresenter, CommentModel> implements CommentContract.View, OnRefreshListener, OnLoadmoreListener {

    int type;
    String shopcommon_id;
    int pageNo = 1, pageSize = 20;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;
    @BindView(R.id.comment_recyclerView)
    EmptyRecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.nested_scrollview)
    NestedScrollView nestedScrollview;
    CommunityAdapter communityAdapter;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    private View mEmptyView;
    public static CommentFragment getInstance(int type, String shopcommon_id) {
        CommentFragment commentFragment = new CommentFragment();
        commentFragment.type = type;
        commentFragment.shopcommon_id = shopcommon_id;
        return commentFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_comment_detalis;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    @Override
    protected void initView() {
        mEmptyView = rootView.findViewById(R.id.empty_no_view);
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        EventBus.getDefault().register(this);
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setMargins(0, DisplayUtil.getStatusBarHeight(getActivity())+ getResources().getDimensionPixelOffset(R.dimen.dp_50), 0, 0);  //设置间距
        smartLayout.setLayoutParams(layout);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", pageSize);
        hashMap.put("comment_from", "1");
        hashMap.put("satisfaction", "");
        hashMap.put("virtualuser_id",UserLoginUtil.IsUserId());
        mPresenter.CommentList_p(hashMap);
        communityAdapter = new CommunityAdapter(getActivity(), true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(communityAdapter);
        recyclerView.setEmptyView(mEmptyView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        communityAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemGoodClick(int position, int lickcount, int comment_id) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("virtualuser_id", UserLoginUtil.IsUserId());
                map.put("FKEY", PutUtils.FKEY());
                map.put("shopcommon_id", shopcommon_id);
                map.put("comment_id", comment_id);
                map.put("prise_from", "4");
                mPresenter.GoodClick_p(map, position, lickcount);
            }

        });
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMessage_type();
        if (TextUtils.equals("commenttype", event.getMessage_type())) {
            List<DepositEntity.CommentTypeBean> commentTypeBeanList = (List<DepositEntity.CommentTypeBean>) event.getObject();
          //  Log.e("onEventMainThread", commentTypeBeanList.get(0).getSatis_name());
            TagAdapter tagAdapter;

            tagAdapter=new TagAdapter(commentTypeBeanList) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tag_item, flowlayout, false);
                    tv.setTextSize(12);
                    tv.setText(commentTypeBeanList.get(position).getSatis_name() + "(" + commentTypeBeanList.get(position).getCount() + ")");
                    return tv;
                }
            };
            flowlayout.setAdapter(tagAdapter);
            tagAdapter.setSelectedList(0);
            flowlayout.setOnTagClickListener((view, position, parent) -> {
                pageNo=1;
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("FKEY", PutUtils.FKEY());
                hashMap.put("shopcommon_id", shopcommon_id);
                hashMap.put("pageNo", pageNo);
                hashMap.put("pageSize", pageSize);
                hashMap.put("comment_from", "1");
                hashMap.put("virtualuser_id",UserLoginUtil.IsUserId());
                hashMap.put("satisfaction", position == 0 ? "" : position);
                mPresenter.CommentList_p(hashMap);

                mPresenter.CommentList_p(hashMap);
                return true;
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
    public void CommentList_v(CommentEntity t) {
        if (t.getResultCode() == 1) {
            if(pageNo==1){
                communityAdapter.setDatas(t.getDatas());
            }else {
                communityAdapter.getDatas().addAll((t.getDatas()));
            }
            communityAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void CommentType_v(CommentTypeEntity t) {


    }

    @Override
    public void ReplyComment_v(BaseResult t) {

    }

    @Override
    public void GoodClick_v(BaseResult t, int position, int linkcount) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            ((CommentEntity.DatasBean)communityAdapter.getDatas().get(position)).setIsPrised(true);
            ((CommentEntity.DatasBean)communityAdapter.getDatas().get(position)).setPrise_count(linkcount+1);
            communityAdapter.notifyItemChanged(position);
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
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", pageSize);
        hashMap.put("comment_from", "1");
        hashMap.put("satisfaction", "");
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        mPresenter.CommentList_p(hashMap);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", pageSize);
        hashMap.put("comment_from", "1");
        hashMap.put("satisfaction", "");
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        mPresenter.CommentList_p(hashMap);
        refreshlayout.finishRefresh(2000);
    }
}
