package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.MyReleaseInfoEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MyReleaseListContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/27 17:30
 * 描述：
 */

public class MyReleaseListPresenter extends MyReleaseListContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void MyReleaseList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .MyReleaseList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.MyReleaseList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void MyReleaseInfo_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .MyReleaseInfo_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MyReleaseInfoEntity>(getContext(),true) {
                    @Override
                    protected void _onNext(MyReleaseInfoEntity s) {
                        mView.MyReleaseInfo_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
