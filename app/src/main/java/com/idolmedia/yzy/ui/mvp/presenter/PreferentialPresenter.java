package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.PreferentiaEntity;
import com.idolmedia.yzy.entity.PreferentialHotEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;
import com.idolmedia.yzy.ui.mvp.contract.PreferentialContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:55
 * 描述：特惠Presenter
 */

public class PreferentialPresenter extends PreferentialContract.Presenter {
    public String TAG = getClass().getSimpleName();

    @Override
    public void Preferential_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Preferential_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PreferentiaEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(PreferentiaEntity s) {
                        mView.Preferential_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void Preferentialhot_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Preferentialhot_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PreferentialHotEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(PreferentialHotEntity s) {
                        mView.Preferentialhot_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void Banner_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Banner_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BannerEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(BannerEntity s) {
                        mView.Banner_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void Banner1_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Banner1_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BannerEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(BannerEntity s) {
                        mView.Banner_v1(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
