package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MessageClassEntity;
import com.idolmedia.yzy.entity.MessageEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MessageContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/23 18:29
 * 描述：
 */

public class MessagePresenter extends MessageContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void MessageList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .MessageList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MessageEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(MessageEntity s) {
                        mView.MessageList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void QueryMessageList_p(HashMap<String, Object> map) {
        mRxManage.add((mModel)
                .QueryMessageList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MessageClassEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(MessageClassEntity s) {
                        mView.QueryMessageList_v(s);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void ToRead_p(HashMap<String, Object> map,int position ,int have_read) {
        mRxManage.add((mModel)
                .ToRead_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s ) {
                        mView.ToRead_v(s,position,have_read);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
