package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MyBeanEntity;
import com.idolmedia.yzy.ui.adapter.MyBeanAdapter;
import com.idolmedia.yzy.ui.mvp.contract.MyBeanContract;
import com.idolmedia.yzy.ui.mvp.model.MyBeanModel;
import com.idolmedia.yzy.ui.mvp.presenter.MyBeanPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/15 18:40
 * 描述：
 */

public class MyBeanActivity extends BaseActivity<MyBeanPresenter, MyBeanModel> implements MyBeanContract.View {
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_img)
    ImageView rightImg;
    @BindView(R.id.liner_top)
    LinearLayout linerTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.txt_my_bean)
    TextView txtMyBean;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int pageNo = 1;
    private int pageSize = 10;
    private int Selectposition=0;
    MyBeanAdapter myBeanAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_mybean;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.my_bean));
        String[] title_name = new String[]{"交易记录", "返还记录"};
        tabLayout.setTabData(title_name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("virtualuser_id", UserLoginUtil.IsUserId());
        map.put("yd_type", 0);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        map.put("FKEY", PutUtils.FKEY());
        mPresenter.MyBeanList_p(map);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pageNo = 1;
                Selectposition=position;
                HashMap<String, Object> map = new HashMap<>();
                map.put("virtualuser_id", UserLoginUtil.IsUserId());
                map.put("yd_type", position);
                map.put("pageNo", pageNo);
                map.put("pageSize", pageSize);
                map.put("FKEY", PutUtils.FKEY());
                mPresenter.MyBeanList_p(map);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            pageNo=1;
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("virtualuser_id", UserLoginUtil.IsUserId());
            map1.put("yd_type", Selectposition);
            map1.put("pageNo", pageNo);
            map1.put("pageSize", pageSize);
            map1.put("FKEY", PutUtils.FKEY());
            mPresenter.MyBeanList_p(map1);
            refreshlayout.finishRefresh(1000);
        });
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            pageNo++;
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("virtualuser_id", UserLoginUtil.IsUserId());
            map1.put("yd_type", Selectposition);
            map1.put("pageNo", pageNo);
            map1.put("pageSize", pageSize);
            map1.put("FKEY", PutUtils.FKEY());
            mPresenter.MyBeanList_p(map1);
            refreshlayout.finishLoadmore(1000);
        });
        myBeanAdapter=new MyBeanAdapter();
        recyclerView.setAdapter(myBeanAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myBeanAdapter.setEmptyView(R.layout.empty_view,recyclerView);
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
    public void MyBeanList_v(MyBeanEntity data) {
        if (data.getResultCode() == 1) {
            txtMyBean.setText(String.format(getString(R.string.bean), data.getMoney_no()));
            if(pageNo==1){
                myBeanAdapter.setNewData(data.getDatas());
            }else {
                myBeanAdapter.addData(data.getDatas());
            }
        } else {
            ToastUitl.show(data.getMsg(), 1000);
        }

    }
}
