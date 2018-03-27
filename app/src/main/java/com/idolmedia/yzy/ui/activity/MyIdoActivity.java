package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.ui.adapter.PageAdpater;
import com.idolmedia.yzy.ui.fragment.InformationFragment;
import com.idolmedia.yzy.ui.fragment.MyIdoFragment;
import com.mumu.common.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/30 17:52
 * 描述：
 */

public class MyIdoActivity extends BaseActivity {
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
    SegmentTabLayout tabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    PageAdpater pageAdpater;
    private ArrayList<Fragment> fragmentslist = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_myattention;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.my_ido));
        String[] title_name = new String[]{"爱豆", "官方认证"};
        tabLayout.setTabData(title_name);
        for(int i=0;i<title_name.length;i++){
            fragmentslist.add(MyIdoFragment.getInstance(i));
        }
        pageAdpater = new PageAdpater(getSupportFragmentManager(), fragmentslist, title_name);
        viewPage.setAdapter(pageAdpater);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPage.setCurrentItem(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
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

    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
    }
}
