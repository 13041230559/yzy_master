package com.idolmedia.yzy.ui.fragment;

import com.idolmedia.yzy.R;
import com.mumu.common.base.BaseFragment;

/**
 * 项目名称：com.idolmedia.yzy.ui.fragment
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/1 11:37
 * 描述：
 */

public class SeckillFragment extends BaseFragment {

    public static SeckillFragment getInstance(String s) {
        SeckillFragment seckillFragment = new SeckillFragment();
        return seckillFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_myorder;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

}
