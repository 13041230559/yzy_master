package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.ProductclassEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.CommodityDetailsContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 11:29
 * 描述：
 */

public class CommodityDetailsPresenter extends CommodityDetailsContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void ProductclassList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .ProductclassList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ProductclassEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(ProductclassEntity s) {
                        mView.ProductclassList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void AddCart_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .AddCart_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.AddCart_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void Deposit_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Deposit_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<DepositEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(DepositEntity s) {
                        mView.Deposit_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void GoodClick_p(HashMap<String, Object> map,int linkcount) {
        mRxManage.add((mModel)
                .GoodClick_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.GoodClick_v(s,linkcount);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
