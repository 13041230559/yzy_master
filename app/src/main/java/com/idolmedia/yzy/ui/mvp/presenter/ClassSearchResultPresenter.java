package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.ui.mvp.contract.ClassSearchResultContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 12:07
 * 描述：
 */

public class ClassSearchResultPresenter extends ClassSearchResultContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void ClassSearchResult_p(HashMap<String, Object> map,int pageNo) {
        mRxManage.add((mModel)
                .ClassSearchResult_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CommodityEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(CommodityEntity s) {
                        mView.ClassSearchResult_v(s,pageNo);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
