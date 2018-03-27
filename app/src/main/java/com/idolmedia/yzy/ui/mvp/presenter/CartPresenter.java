package com.idolmedia.yzy.ui.mvp.presenter;

import android.view.View;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CartEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.CartContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 16:37
 * 描述：
 */

public class CartPresenter extends CartContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void CartList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .CartList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CartEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(CartEntity s) {
                        mView.CartList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));

    }

    @Override
    public void UpdateCart_p(HashMap<String, Object> map, int groupPosition, int childPosition, View showCountView,String number, String setNumber) {
        mRxManage.add((mModel)
                .UpdateCart_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.UpdateCart_v(s,groupPosition,childPosition,showCountView,number,setNumber);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }


    @Override
    public void DelCart_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .DelCart_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.DelCart_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void ClearInvalid_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .ClearInvalid_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.ClearInvalid_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void RecommendCartList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .RecommendCartList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CommodityEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(CommodityEntity s) {
                        mView.RecommendCartList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
