package com.idolmedia.yzy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.ui.adapter.PageAdpater;
import com.idolmedia.yzy.ui.fragment.SearchResultFragment;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.FormatUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.TabLayout.MODE_FIXED;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/12 17:59
 * 描述：  搜索_结果
 */

public class SearchResultActivity extends BaseActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txt_search)
    EditText txtSearch;
    @BindView(R.id.cleartxt_iamge)
    ImageView cleartxtIamge;
    @BindView(R.id.liner_homesearch)
    LinearLayout linerHomesearch;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_iamge)
    ImageView rightIamge;
    @BindView(R.id.liner_search)
    LinearLayout linerSearch;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.image_close)
    ImageView imageClose;
    private ArrayList<Fragment> fragmentslist;
    PageAdpater pageAdpater;
    String keywords;
    int tabposition;
    @Override
    public int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tabposition=getIntent().getIntExtra("tabposition",0);
        Log.e("tabposition",String.valueOf(tabposition));
        keywords = getIntent().getStringExtra("keywords");
        txtSearch.setText(String.valueOf(keywords));
        txtSearch.setFocusable(false);
        txtSearch.setFocusableInTouchMode(false);
        fragmentslist = new ArrayList<>();
        String[] title_name = new String[]{"商品", "认证", "爱豆", "资讯", "活动"};
        for (int i = 0; i < title_name.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
            fragmentslist.add(SearchResultFragment.getInstance(i, keywords));
        }
        rightIamge.setVisibility(View.INVISIBLE);
        rightTxt.setVisibility(View.GONE);
        imageClose.setVisibility(View.VISIBLE);
        rightTxt.setText("取消");
        rightTxt.setTextSize(13);
        pageAdpater = new PageAdpater(getSupportFragmentManager(), fragmentslist, title_name);
        viewPage.setAdapter(pageAdpater);
        tabLayout.setupWithViewPager(viewPage);
        tabLayout.setTabsFromPagerAdapter(pageAdpater);
        tabLayout.setTabMode(MODE_FIXED);
        tabLayout.setScrollPosition(tabposition, 0, true);
        viewPage.setCurrentItem(tabposition);
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
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
       /* keywords =intent.getStringExtra("keywords");
        txtSearch.setText(String.valueOf(keywords));*/
    }

    @OnClick({R.id.image_close, R.id.txt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_search:
                startActivity(HotSearchActivity.class);
                finish();
                break;
        }
    }
}

