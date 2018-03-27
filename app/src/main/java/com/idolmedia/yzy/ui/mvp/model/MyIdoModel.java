package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyFollowIdoEntity;
import com.idolmedia.yzy.entity.MyFollowOfficialEntity;
import com.idolmedia.yzy.ui.mvp.contract.MyIdoContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:47
 * 描述：
 */

public class MyIdoModel implements MyIdoContract.Model {

    @Override
    public Observable<MyFollowIdoEntity> MyIdoList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().MyIdoList(map);
    }

    @Override
    public Observable<MyFollowOfficialEntity> MyAuthList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().MyAuthList(map);
    }

    @Override
    public Observable<BaseResult> FollowIdo_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().FollowIdo(map);
    }

    @Override
    public Observable<BaseResult> FollowThirdParty_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().FollowThirdParty(map);
    }
}
