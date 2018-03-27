package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.ui.mvp.contract.RecommendContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/4 22:33
 * 描述：
 */

public class RecommendPresenter extends RecommendContract.Presenter {
    public String TAG = getClass().getSimpleName();
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
    public void RecommendList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                . RecommendList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CommodityEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(CommodityEntity s) {
                        mView.RecommendList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void Unreadmessage_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Unreadmessage_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.Unreadmessage_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
