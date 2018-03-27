package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.AccountEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.AccountContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/17 15:15
 * 描述：
 */

public class AccountPresenter extends AccountContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void Account_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Account_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<AccountEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(AccountEntity s) {
                        mView.Account_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void BindThird_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .BindThird_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.BaseResultMsg_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void BindPhoneEmail_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .BindPhoneEmail_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.BaseResultMsg_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void UBindThird_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .UBindThird_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.BaseResultMsg_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void ChangeMain_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .ChangeMain_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.BaseResultMsg_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
