package com.idolmedia.yzy.ui.mvp.presenter;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.SearchResultContract;
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

public class SearchResultPresenter extends SearchResultContract.Presenter {
    public String TAG = getClass().getSimpleName();
    @Override
    public void SearchResult_p(HashMap<String, Object> map,int pageNo,int search_type) {
        mRxManage.add((mModel)
                .SearchResult_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>(getContext(),false) {
                    @Override
                    protected void _onNext(String s) {
                        mView.SearchResult_v(s,pageNo, search_type);
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
    public void FollowThirdParty_p(HashMap<String, Object> map, int position, boolean isfollow, int fanscount) {
        mRxManage.add((mModel)
                .FollowThirdParty_m(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseResult>(getContext(),false) {
                    @Override
                    protected void _onNext(BaseResult s) {
                        mView.FollowThirdParty_v(s,position,isfollow,fanscount);
                    }
                    protected void _onError(String message) {
                        LogUtil.logd(TAG, message);
                        mView.showErrorTip(message);
                    }
                }));
    }
}
