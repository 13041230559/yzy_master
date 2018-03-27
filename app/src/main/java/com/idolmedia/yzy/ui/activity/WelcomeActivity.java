package com.idolmedia.yzy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idolmedia.yzy.MainActivity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.widget.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/12 10:36
 * 描述：  引导页
 */

public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.view_page)
    ViewPager viewPage;
    private List<View> viewList = new ArrayList<View>();
    PageAdapter pageAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        StatusBarCompat.translucentStatusBar(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        viewList.add(inflater.inflate(R.layout.w1,null));
        viewList.add(inflater.inflate(R.layout.w2,null));
        viewList.add(inflater.inflate(R.layout.w3,null));
        viewList.add(inflater.inflate(R.layout.w4,null));
        viewList.add(inflater.inflate(R.layout.w5,null));
        viewList.get(viewList.size()-1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUtil.setBoolean("WelcomeActivity",true);
                Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        pageAdapter=new PageAdapter();
        viewPage.setAdapter(pageAdapter);
        //  viewPage.setPageTransformer(true, new DepthPageTransformer());
        viewPage.setOffscreenPageLimit(viewList.size());// 加载缓存的页面个数
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class PageAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return viewList.size();
        }

        // 销毁某个元素时候调用
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        // 更新视图
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
        // 判断是否是生成的对象
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
