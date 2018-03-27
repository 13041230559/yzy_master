package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.IDoEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.SelectIdoContract;
import com.idolmedia.yzy.ui.mvp.contract.SelectIdoListContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 15:58
 * 描述：
 */

public class SelectIdoListPresenter extends SelectIdoListContract.Presenter {
    public String TAG = getClass().getSimpleName();

   /* @Override
    public void IdoAll_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .IdoAll_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.IdoAll_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }*/

    @Override
    public void IdoSearch_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .IdoSearch_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<IDoEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(IDoEntity s) {
                        mView.IdoSearch_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void FollowIdo_p(HashMap<String, Object> map,int position,boolean isIsAtted) {
        mRxManage.add((mModel)
                .FollowIdo_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.FollowIdo_v(s,position,isIsAtted);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
