package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.AuthenticationContract;
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
 * 创建时间：2017/12/22 17:06
 * 描述：
 */
public class AuthenticationPresenter extends AuthenticationContract.Presenter {
    public String TAG = getClass().getSimpleName();

    @Override
    public void Authentication_p(List<MultipartBody.Part> partList) {
        mRxManage.add((mModel)
                .Authentication_m(partList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.Authentication_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
