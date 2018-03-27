package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.MyReleaseInfoEntity;
import com.idolmedia.yzy.ui.adapter.PageAdpater;
import com.idolmedia.yzy.ui.fragment.ReleaseFragment;
import com.idolmedia.yzy.ui.mvp.contract.MyReleaseListContract;
import com.idolmedia.yzy.ui.mvp.model.MyReleaseListModel;
import com.idolmedia.yzy.ui.mvp.presenter.MyReleaseListPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/6 12:19
 * 描述： 我的发布
 */

public class MyReleaseActivity extends BaseActivity <MyReleaseListPresenter,MyReleaseListModel>implements MyReleaseListContract.View{

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
    @BindView(R.id.iamge_head)
    ImageView iamgeHead;
    @BindView(R.id.iamge_grade)
    ImageView iamgeGrade;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_content)
    TextView txtContent;
    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.txt_certification)
    TextView txtCertification;
    private List<Fragment> fragmentslist;
    PageAdpater pageAdpater;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_release;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);

    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.all_release));
        String[] title_name = new String[]{"商品", "社区", "活动"};
        tabLayout.setTabData(title_name);
        fragmentslist = new ArrayList<>();
        for (int i = 0; i < title_name.length; i++) {
            fragmentslist.add(ReleaseFragment.getInstance(i));
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
        viewPage.setCurrentItem(0);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        mPresenter.MyReleaseInfo_p(hashMap);

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
        ToastUitl.show(msg,1000);

    }

    @Override
    public void MyReleaseList_v(String t) {

    }

    @Override
    public void MyReleaseInfo_v(MyReleaseInfoEntity t) {
        if(t.getResultCode()==1){
            txtName.setText(t.getDatas().getNick_name());
            Glide.with(this).load(t.getDatas().getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bj)).into(iamgeHead);
           //商品 235 |社区 9900 |活动1.1万
            txtContent.setText(String.format(getString(R.string.my_release_content),String.valueOf(t.getDatas().getProductCount()),String.valueOf(t.getDatas().getCommunityCount()),String.valueOf(t.getDatas().getActivityCount())));
        }

    }
}
