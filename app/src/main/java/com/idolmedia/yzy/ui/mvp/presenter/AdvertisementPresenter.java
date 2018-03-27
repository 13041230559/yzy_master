package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.AdvertisementContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/2 19:14
 * 描述：
 */

public class AdvertisementPresenter extends AdvertisementContract.Presenter {
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
}
