package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.AddressDefaultEntity;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.PayBuyEntity;
import com.idolmedia.yzy.entity.PayEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
import com.idolmedia.yzy.ui.mvp.contract.ConfirmOrderBuyContract;
import com.idolmedia.yzy.ui.mvp.contract.ConfirmOrderContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 17:40
 * 描述：
 */

public class ConfirmOrderBuyPresenter extends ConfirmOrderBuyContract.Presenter {
    public String TAG = getClass().getSimpleName();

    @Override
    public void DefaultAddress_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .DefaultAddress_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<AddressDefaultEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(AddressDefaultEntity s) {
                        mView.DefaultAddress_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }


    @Override
    public void BuyAddition_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .BuyAddition_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.BuyAddition_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void BuyFreight_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .BuyFreight_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.BuyFreight_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void BuyOrder_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .BuyOrder_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PayBuyEntity>(getContext(),true) {
                    @Override
                    protected void _onNext(PayBuyEntity s) {
                        mView.BuyOrder_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void RetainageOrder_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .RetainageOrder_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PayBuyEntity>(getContext(),true) {
                    @Override
                    protected void _onNext(PayBuyEntity s) {
                        mView.RetainageOrder_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
