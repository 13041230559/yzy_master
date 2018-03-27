package com.idolmedia.yzy.ui.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.ui.activity.ReleaseActivity;
import com.idolmedia.yzy.ui.adapter.PageAdpater;
import com.idolmedia.yzy.ui.fragment.CommunityFagment;
import com.idolmedia.yzy.ui.fragment.InformationFragment;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.DisplayUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/31.
 */

public class StarFragment extends BaseFragment {
    @BindView(R.id.tile_view)
    View tileView;
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
    Unbinder unbinder;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    private ArrayList<Fragment> fragmentslist = new ArrayList<>();
    PageAdpater pageAdpater;
        private  final  int RELEASE=10000;
        int pageposition=0;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_star;
    }

    @Override
    public void initPresenter() {

    }
    @Override
    protected void initView() {
        tvTitle.setText("星 踪");
        imageClose.setVisibility(View.INVISIBLE);
        DisplayUtil.setWidgetHeight(tileView, DisplayUtil.getStatusBarHeight(getActivity()));
        tileView.setBackgroundColor(getResources().getColor(R.color.main_color));
        String[] title_name = new String[]{"资 讯", "社 区"};
        tabLayout.setTabData(title_name);
        fragmentslist.add(InformationFragment.getInstance((title_name[0]), 0));
        fragmentslist.add(CommunityFagment.getInstance());
        pageAdpater = new PageAdpater(getChildFragmentManager(), fragmentslist, title_name);
        viewPage.setAdapter(pageAdpater);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPage.setCurrentItem(position);
                pageposition=position;
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


       // floatingActionButton.setOnClickListener(view -> startActivityForResult({}ReleaseActivity.class,RELEASE));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Bundle bundle =new Bundle();
                  bundle.putBoolean("isRelease",true);
                  startActivityForResult(ReleaseActivity.class,bundle,RELEASE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
     //   super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RELEASE :
                    if(pageposition==0){
                        tabLayout.setCurrentTab(1);
                        viewPage.setCurrentItem(1);
                    }
                    break;


                
            }

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
}
