package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.UpdateVersionEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.SettingContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/7 14:08
 * 描述：
 */

public class SettingPresenter extends SettingContract.Presenter {
    public String TAG = getClass().getSimpleName();

    @Override
    public void UpdateVersion_p() {
        mRxManage.add((mModel)
                .UpdateVersion_m()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UpdateVersionEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(UpdateVersionEntity s) {
                        mView.UpdateVersion_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
