package com.mumu.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mumu.common.baserx.RxManager;
import com.mumu.common.utils.ToastUitl;
import com.mumu.common.utils.TypeUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {
    protected View rootView;
    public T mPresenter;
    public E mModel;
    public RxManager mRxManager;
    Unbinder unbinder;
    //是否可见
    protected boolean isVisible=false;
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)

            rootView = inflater.inflate(getLayoutResource(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mRxManager = new RxManager();
        mPresenter = TypeUtil.getType(this, 0);
        mModel = TypeUtil.getType(this, 1);
        if (mPresenter != null) {
            mPresenter.setContext(getActivity());
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
        initView();

    }

    //获取布局文件
    protected abstract int getLayoutResource();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    protected abstract void initView();


    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    public void showShortToast(String text) {
        ToastUitl.showShort((CharSequence) text);
    }

    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    public void showLongToast(String text) {
        ToastUitl.showLong((CharSequence) text);
    }

    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            isVisible = true;
            //lazyLoad();
        } else {
            isVisible = false;
        }
    }

    //protected abstract void lazyLoad();
}