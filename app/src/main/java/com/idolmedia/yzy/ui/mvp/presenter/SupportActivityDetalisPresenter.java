package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.SupportDetalisEntity;
import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.SupportActivityDetalisContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/12 16:46
 * 描述：
 */

public class SupportActivityDetalisPresenter extends SupportActivityDetalisContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void SupportActivityDetalis_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .SupportActivityDetalis_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<SupportDetalisEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(SupportDetalisEntity s) {
                        mView.SupportActivityDetalis_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));

    }


}
