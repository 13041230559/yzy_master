package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyFollowIdoEntity;
import com.idolmedia.yzy.entity.MyFollowOfficialEntity;
import com.idolmedia.yzy.ui.mvp.contract.MyIdoContract;
import com.mumu.common.baserx.RxSubscriber;
import com.mumu.common.utils.LogUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.presenter
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:55
 * 描述：我的页面
 */

public class MyIdoPresenter extends MyIdoContract.Presenter {
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
    public void MyIdoList_p(HashMap<String, Object> map, int pageno) {
        mRxManage.add((mModel)
                .MyIdoList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MyFollowIdoEntity>(getContext(),true) {
                    @Override
                    protected void _onNext(MyFollowIdoEntity s) {
                        mView.MyIdoList_v(s,pageno);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void MyAuthList_p(HashMap<String, Object> map, int pageno) {
        mRxManage.add((mModel)
                .MyAuthList_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MyFollowOfficialEntity>(getContext(),false) {
                    @Override
                    protected void _onNext(MyFollowOfficialEntity s) {
                        mView.MyAuthList_v(s,pageno);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void FollowIdo_p(HashMap<String, Object> map, int postion, boolean isfollow,int fanscount) {

        mRxManage.add((mModel)
                .FollowIdo_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.FollowIdo_v(s,postion,isfollow,fanscount);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }

    @Override
    public void FollowThirdParty_p(HashMap<String, Object> map, int postion, boolean isfollow,int fanscount) {
        mRxManage.add((mModel)
                .FollowThirdParty_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.FollowThirdParty_v(s,postion,isfollow,fanscount);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
