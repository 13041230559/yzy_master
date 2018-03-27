package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.OldOrderEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;
import com.idolmedia.yzy.ui.mvp.contract.OldOrderContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:55
 * 描述：我的页面
 */

public class OldOrderPresenter extends OldOrderContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void OldOrderList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .OldOrderList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<OldOrderEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(OldOrderEntity s) {
                        mView.OldOrderList(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
