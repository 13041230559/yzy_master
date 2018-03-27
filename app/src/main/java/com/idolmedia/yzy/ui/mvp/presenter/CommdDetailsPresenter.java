package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.CommodEntity;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.CommdDetailsContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/19 17:16
 * 描述：
 */

public class CommdDetailsPresenter extends CommdDetailsContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void CommdDetails_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .CommdDetails_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<DepositEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(DepositEntity s) {
                        mView.CommdDetails_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void DepositCommdDetails_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .DepositCommdDetails_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<DepositEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(DepositEntity s) {
                        mView.CommdDetails_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
