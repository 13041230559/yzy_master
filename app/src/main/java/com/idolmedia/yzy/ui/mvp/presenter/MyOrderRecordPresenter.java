package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyOrderEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MyOrderRecordContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/20 15:43
 * 描述：
 */

public class MyOrderRecordPresenter extends MyOrderRecordContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void MyOrderRecordList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .MyOrderRecordList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MyOrderEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(MyOrderEntity s) {
                        mView.MyOrderRecordList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void CancelOrder_p(HashMap<String, Object> map,int  position) {
        mRxManage.add((mModel)
                .CancelOrder_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.CancelOrder_v(s,position);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void ConfirmReceipt_p(HashMap<String, Object> map,int  position) {
        mRxManage.add((mModel)
                .ConfirmReceipt_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.ConfirmReceipt_v(s,position);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void DeleteOrder_p(HashMap<String, Object> map,int  position) {
        mRxManage.add((mModel)
                .DeleteOrder_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.DeleteOrder_v(s,position);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

}
