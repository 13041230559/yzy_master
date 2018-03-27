package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.ClassEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.ClassificationContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/21 14:07
 * 描述：
 */

public class ClassificationPresenter extends ClassificationContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void Classification_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Classification_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ClassEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(ClassEntity s) {
                        mView.Classification_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
