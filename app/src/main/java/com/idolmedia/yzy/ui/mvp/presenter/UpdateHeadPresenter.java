package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.IDoEntity;
import com.idolmedia.yzy.ui.mvp.contract.UpdateHeadContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Part;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/5 9:13
 * 描述：
 */

public class UpdateHeadPresenter extends UpdateHeadContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void UpdateHead_p(@Part List<MultipartBody.Part> partList) {
        mRxManage.add((mModel)
                .UpdateHead_m(partList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),true) {
                    @Override
                    protected void _onNext(String s) {
                        mView.UpdateHead_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
/*
    @Override
    public void UpdateNickName_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .UpdateNickName_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.UpdateNickName_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }*/
}
