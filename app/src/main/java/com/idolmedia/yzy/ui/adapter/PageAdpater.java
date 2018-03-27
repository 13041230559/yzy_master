package com.idolmedia.yzy.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/1 11:12
 * 描述：
 */

public class PageAdpater extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    String [] title;
    public PageAdpater(FragmentManager fm, List<Fragment> fragmentList,String [] title) {
        super(fm);
        this.fragmentList=fragmentList;
        this.title=title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  title[position];
    }
}
