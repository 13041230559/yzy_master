package com.mumu.common.base;

/**
 * Created by MuMu on 2016/12/18/0018.
 */

public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
