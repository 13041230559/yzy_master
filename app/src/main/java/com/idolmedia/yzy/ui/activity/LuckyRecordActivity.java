package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.ui.adapter.PageAdpater;
import com.idolmedia.yzy.ui.fragment.SeckillFragment;
import com.mumu.common.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/24 11:01
 * 描述： 幸运记录
 */

public class LuckyRecordActivity extends BaseActivity {
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
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    private ArrayList<Fragment> fragmentslist = new ArrayList<>();
    PageAdpater pageAdpater;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lucky;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.lucky_record));
        String[] title_name = new String[]{"全部秒杀", "进行中", "已揭晓", "待领取"};
        for (int i = 0; i < title_name.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
            fragmentslist.add(SeckillFragment.getInstance(title_name[i]));
        }
        pageAdpater = new PageAdpater(getSupportFragmentManager(), fragmentslist, title_name);
        viewPage.setAdapter(pageAdpater);
        tabLayout.setupWithViewPager(viewPage);
        tabLayout.setTabsFromPagerAdapter(pageAdpater);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
}
