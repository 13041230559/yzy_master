package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;
import com.idolmedia.yzy.ui.mvp.contract.ReleaseContract;
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
 * 创建时间：2017/11/27 11:55
 * 描述：发布页面
 */

public class ReleasePresenter extends ReleaseContract.Presenter {
    public String TAG = getClass().getSimpleName();
   /* @Override
    public void AutoLogin_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .AutoLogin_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UserEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(UserEntity s) {
                        mView.AutoLogin_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }*/

    @Override
    public void ReleaseComment_p(List<MultipartBody.Part> partList) {
        mRxManage.add((mModel)
                .ReleaseComment_m(partList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.ReleaseComment_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
