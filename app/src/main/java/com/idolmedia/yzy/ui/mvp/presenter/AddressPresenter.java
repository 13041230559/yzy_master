package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.AddressEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.AddressContract;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:55
 * 描述：我的页面
 */

public class AddressPresenter extends AddressContract.Presenter {
    public String TAG = getClass().getSimpleName();

    @Override
    public void AddressList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .AddressList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<AddressEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(AddressEntity s) {
                        mView.AddressList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void DelAddress_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .DelAddress_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.DelAddress_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
