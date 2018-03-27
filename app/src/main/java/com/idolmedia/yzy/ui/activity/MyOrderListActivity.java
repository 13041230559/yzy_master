package com.idolmedia.yzy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.ui.adapter.PageAdpater;
import com.idolmedia.yzy.ui.fragment.MyOrderListFragment;
import com.mumu.common.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.TabLayout.MODE_FIXED;
import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/21 11:53
 * 描述：  我的订单
 */

public class MyOrderListActivity extends BaseActivity{
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
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
    @BindView(R.id.view_page)
    ViewPager viewPage;
    private ArrayList<Fragment> fragmentslist = new ArrayList<>();
    PageAdpater pageAdpater;
    int selectType;
    @Override
    public int getLayoutId() {
        return R.layout.activity_myorder;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.myorder));
        selectType=getIntent().getIntExtra("toPay",0);
        String[] title_name = new String[]{"全部", "待支付", "待发货", "待收货", "待评价","已完成"};
        String[] type_name = new String[]{"", "toPay", "waitingDelivery", "waitingGoods", "waitingApprise","orderOver"};
        tabLayout.setTabMode(MODE_SCROLLABLE);
        for (int i = 0; i < title_name.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
            fragmentslist.add(MyOrderListFragment.getInstance(type_name[i]));
        }
        pageAdpater = new PageAdpater(getSupportFragmentManager(), fragmentslist, title_name);
        viewPage.setAdapter(pageAdpater);
        tabLayout.setupWithViewPager(viewPage);
       // tabLayout.setTabsFromPagerAdapter(pageAdpater);
        tabLayout.setScrollPosition(selectType, 0, true);
        viewPage.setCurrentItem(selectType);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        selectType=getIntent().getIntExtra("toPay",0);
    }

    /*    @Override
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
    public void MyOrderRecord_v(MyOrderEntity t) {

    }*/
}
