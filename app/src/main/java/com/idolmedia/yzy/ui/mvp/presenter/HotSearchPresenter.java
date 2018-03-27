package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.SearchHotEntity;
import com.idolmedia.yzy.ui.mvp.contract.HotSearchContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 11:16
 * 描述：
 */

public class HotSearchPresenter extends HotSearchContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void HotSearchList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .HotSearchList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<SearchHotEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(SearchHotEntity s) {
                        mView.HotSearchList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
