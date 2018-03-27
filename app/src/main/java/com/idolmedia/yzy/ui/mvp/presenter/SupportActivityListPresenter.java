package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.ui.mvp.contract.SupportActivityListContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 11:06
 * 描述：
 */

public class SupportActivityListPresenter extends SupportActivityListContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void SupportList_p(HashMap<String, Object> map) {

        mRxManage.add((mModel)
                .SupportList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<SupportEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(SupportEntity s) {
                        mView.SupportList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
     /*   mRxManage.add((mModel)
                .SupportList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<SupportEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.SupportList_v(SupportEntity  s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));*/
    }
}
