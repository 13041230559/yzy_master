package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.LogisticsEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.LogisticsContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/21 17:06
 * 描述：
 */

public class LogisticsPresenter extends LogisticsContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void Logistics_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Logistics_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<LogisticsEntity>(getContext(),true) {
                    @Override
                    protected void _onNext(LogisticsEntity s) {
                        mView.Logistics_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
