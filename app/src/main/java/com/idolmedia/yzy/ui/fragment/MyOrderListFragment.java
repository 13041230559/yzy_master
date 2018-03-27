package com.idolmedia.yzy.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyOrderEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.CommodityEvaluationActivity;
import com.idolmedia.yzy.ui.activity.LogisticsActivity;
import com.idolmedia.yzy.ui.activity.MyOrderDetalisActivity;
import com.idolmedia.yzy.ui.activity.PayActivity;
import com.idolmedia.yzy.ui.adapter.MyOrderListAdapter;
import com.idolmedia.yzy.ui.mvp.contract.MyOrderRecordContract;
import com.idolmedia.yzy.ui.mvp.model.MyOrderRecordModel;
import com.idolmedia.yzy.ui.mvp.presenter.MyOrderRecordPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.view.EmptyRecyclerView;
import com.idolmedia.yzy.view.RecyclerViewForEmpty;
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

import static android.app.Activity.RESULT_OK;


/**
 * 项目名称：com.idolmedia.yzy.ui.fragment
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/1 10:52
 * 描述： 订单列表
 */

public class MyOrderListFragment extends BaseFragment<MyOrderRecordPresenter, MyOrderRecordModel> implements MyOrderRecordContract.View, OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.recyclerView)
    EmptyRecyclerView recyclerView;
    Unbinder unbinder;
    String typeName;
    MyOrderListAdapter myOrderAdapter;
    int pageNo = 1;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;

    private static final int COMMODITYEVA = 1;

    @BindView(R.id.iamge_no_view)
    ImageView iamgeNoView;
    @BindView(R.id.txt_no_data)
    TextView txtNoData;
    @BindView(R.id.empty_no_view)
    RelativeLayout mEmptyView;
    public static MyOrderListFragment getInstance(String s) {
        MyOrderListFragment orderFragment = new MyOrderListFragment();
        orderFragment.typeName = s;
        return orderFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_myorder;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("order_status", typeName);
        mPresenter.MyOrderRecordList_p(hashMap);
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        myOrderAdapter = new MyOrderListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.setOnItemClickListener(new MyOrderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String ordernum) {
                Bundle bundle = new Bundle();
                bundle.putString("order_num", ordernum);
                startActivityForResult(MyOrderDetalisActivity.class, bundle, COMMODITYEVA);
            }

            @Override
            public void onItemPayClick(int position, String ordernum, boolean isDeposit, int Shopcommon_id, String shop_type, double paymoney, String create_time, String form_type) {
                if (isDeposit) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ordernum", ordernum);
                    bundle.putString("shop_type", shop_type);
                    bundle.putString("shopcommon_id", String.valueOf(Shopcommon_id));
                    startActivity(CommodityDetailsActivity.class, bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("totalMoney", String.valueOf(paymoney));
                    bundle.putString("order_num", ordernum);
                    bundle.putString("from_type", TextUtils.equals(form_type, "reserve") ? "reserveOrder" : "commonOrder");
                    bundle.putString("create_time", create_time);
                    startActivity(PayActivity.class, bundle);

                }
            }

            @Override
            public void onItemCanceloderClick(int position, String ordernum) {
                ShowDialog("确认取消订单？", ordernum, 0, position);

            }

            @Override
            public void onItemGoodreceiptClick(int position, String ordernum) {
                ShowDialog("确定收货？", ordernum, 1, position);
            }

            @Override
            public void onItemLogisticsClick(int position, String ordernum, String iamge, String shippercode, String express_no) {
                Bundle bundle = new Bundle();
                bundle.putString("order_num", ordernum);
               /* bundle.putString("iamge", iamge);
                bundle.putString("shippercode", shippercode);
                bundle.putString("express_no", express_no);*/
                startActivity(LogisticsActivity.class, bundle);
            }

            @Override
            public void onItemRemindClick(int position, String ordernum) {
                ToastUitl.show("已提醒卖家发货!", 1000);
            }

            @Override
            public void onItemDelClick(int position, String ordernum) {
                ShowDialog("确定删除订单？", ordernum, 2, position);

            }

            @Override
            public void onItemGoEvaluateClick(int position, String ordernum, String iamge, String commodityName, int shopcommon_id) {
                // ToastUitl.show("去评价",1000);
                Bundle bundle = new Bundle();
                bundle.putString("order_no", ordernum);
                bundle.putString("iamge", iamge);
                bundle.putString("name", commodityName);
                bundle.putString("shopcommon_id", String.valueOf(shopcommon_id));
                startActivityForResult(CommodityEvaluationActivity.class, bundle, COMMODITYEVA);
            }

            @Override
            public void onItemRetainagePayClick(String ordernum, String shop_type, int shopcommon_id, int type) {
                if (type == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ordernum", ordernum);
                    bundle.putString("shop_type", shop_type);
                    bundle.putString("shopcommon_id", String.valueOf(shopcommon_id));
                    startActivity(CommodityDetailsActivity.class, bundle);
                } else if (type == 1) {
                    ToastUitl.show(getString(R.string.payretainage_unopened), 1000);
                } else if (type == 2) {
                    ToastUitl.show(getString(R.string.payretainage_over), 1000);
                }


            }
        });
    }

    private void ShowDialog(String msg, String ordernum, int type, int position) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setMessage(msg);
        dialog.setTitle("提示");
        // dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialog12, which) -> {
            return;
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialog1, which) -> {
            HashMap<String, Object> hashMap = new HashMap<>();

            switch (type) {
                case 0:
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("order_num", ordernum);
                    mPresenter.CancelOrder_p(hashMap, position);
                    break;
                case 1:
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("order_num", ordernum);
                    mPresenter.ConfirmReceipt_p(hashMap, position);
                    break;
                case 2:
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("order_num", ordernum);
                    mPresenter.DeleteOrder_p(hashMap, position);
                    break;


            }

        });
        dialog.show();
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
        ToastUitl.showLong(msg);
    }

    @Override
    public void MyOrderRecordList_v(MyOrderEntity t) {
        if (t.getResultCode() == 1) {
            if (pageNo == 1) {
                myOrderAdapter.setNewBeans(t.getDatas());
            } else {
                myOrderAdapter.setdatasBeans(t.getDatas());
            }
           if(myOrderAdapter.getItemCount()==0){
               mEmptyView.setVisibility(View.VISIBLE);
               recyclerView.setVisibility(View.GONE);
           }else {
               mEmptyView.setVisibility(View.GONE);
               recyclerView.setVisibility(View.VISIBLE);
           }
        }

    }

    @Override
    public void CancelOrder_v(BaseResult t, int position) {
        if (t.getResultCode() == 1) {
            pageNo = 1;
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("pageNo", pageNo);
            hashMap.put("pageSize", "10");
            hashMap.put("order_status", typeName);
            mPresenter.MyOrderRecordList_p(hashMap);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case COMMODITYEVA:
                    pageNo = 1;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", "10");
                    hashMap.put("order_status", typeName);
                    mPresenter.MyOrderRecordList_p(hashMap);
                    break;
            }
        }
    }

    @Override
    public void ConfirmReceipt_v(BaseResult t, int position) {
        if (t.getResultCode() == 1) {
            pageNo = 1;
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("pageNo", pageNo);
            hashMap.put("pageSize", "10");
            hashMap.put("order_status", typeName);
            mPresenter.MyOrderRecordList_p(hashMap);
        }
    }

    @Override
    public void DeleteOrder_v(BaseResult t, int position) {
        if (t.getResultCode() == 1) {
            pageNo = 1;
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("pageNo", pageNo);
            hashMap.put("pageSize", "10");
            hashMap.put("order_status", typeName);
            mPresenter.MyOrderRecordList_p(hashMap);
        }

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("order_status", typeName);
        mPresenter.MyOrderRecordList_p(hashMap);
        refreshlayout.finishLoadmore(1000);

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("order_status", typeName);
        mPresenter.MyOrderRecordList_p(hashMap);
        refreshlayout.finishRefresh(1000);
    }
}
