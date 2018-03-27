package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommunityCommentDetalisEntity;
import com.idolmedia.yzy.ui.mvp.contract.CommunityDetalisContract;
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
 * 创建时间：2018/2/2 18:05
 * 描述：
 */

public class CommunityDetalisPresenter extends CommunityDetalisContract.Presenter {
    public String TAG = getClass().getSimpleName();

    @Override
    public void CommunityDetalisList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .CommunityDetalisList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CommunityCommentDetalisEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(CommunityCommentDetalisEntity s) {
                        mView.CommunityDetalisList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void ReplyComment_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .ReplyComment_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.ReplyComment_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void GoodClick_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .GoodClick_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.GoodClick_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }


}
