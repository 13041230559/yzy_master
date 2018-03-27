package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.AuthenticationResultEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.AuthenticationContract;
import com.idolmedia.yzy.ui.mvp.contract.AuthenticationResultsContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/12 12:54
 * 描述：
 */

public class AuthenticationResultsPresenter extends AuthenticationResultsContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void resultMsg_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .resultMsg_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<AuthenticationResultEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(AuthenticationResultEntity s) {
                        mView.resultMsg_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
