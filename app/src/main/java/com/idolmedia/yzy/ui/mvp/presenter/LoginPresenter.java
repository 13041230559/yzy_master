package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.LoginContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/24 18:32
 * 描述：
 */

public class LoginPresenter extends LoginContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void Login_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Login_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UserEntity>(getContext(),true) {
                    @Override
                    protected void _onNext(UserEntity s) {
                        mView.Login_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void Code_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Code_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.code_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));

    }

    @Override
    public void CodeLogin_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .CodeLogin_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UserEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(UserEntity s) {
                        mView.Login_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void ThirdPartyLogin_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .ThirdPartyLogin_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UserEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(UserEntity s) {
                        mView.Login_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
