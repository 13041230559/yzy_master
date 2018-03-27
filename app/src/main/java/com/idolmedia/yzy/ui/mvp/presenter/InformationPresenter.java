package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.InformationEntity;
import com.idolmedia.yzy.ui.mvp.contract.InformationContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/30 12:18
 * 描述：
 */

public class InformationPresenter extends InformationContract.Presenter {

    public String TAG = getClass().getSimpleName();
    @Override
    public void Banner_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .Banner_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BannerEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(BannerEntity s) {
                        mView.Banner_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void InformationLis_p(HashMap<String, Object> map,int pageno) {
        mRxManage.add((mModel)
                .InformationList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<InformationEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(InformationEntity s) {
                        mView.InformationList_v(s,pageno);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void CommentList_p(HashMap<String, Object> map,int PageNo) {
        mRxManage.add((mModel)
                .CommentList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CommentEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(CommentEntity s) {
                        mView.CommentList_v(s,PageNo);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void GoodClick_p(HashMap<String, Object> map, int position,int linkcount) {
        mRxManage.add((mModel)
                .GoodClick_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.GoodClick_v(s,position,linkcount);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
