package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.AddressDefaultEntity;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.FreightEntity;
import com.idolmedia.yzy.entity.PayEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
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

public class ConfirmOrderPresenter extends ConfirmOrderContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void SubmitOrder_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .SubmitOrder_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PayEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(PayEntity s) {
                        mView.SubmitOrder_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void Freight_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Freight_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ReturnFreightEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(ReturnFreightEntity s) {
                        mView.Freight_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

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
    public void ConfirmOrder_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .ConfirmOrder_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ConfirmOrderEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(ConfirmOrderEntity s) {
                        mView.ConfirmOrder_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

/*    @Override
    public void BuyAdditional_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .BuyAdditional_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.BuyAdditional_v(s);
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
                .subscribe(new RxSubscriber<ReturnFreightEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(ReturnFreightEntity s) {
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
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.BuyOrder_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }*/
}
