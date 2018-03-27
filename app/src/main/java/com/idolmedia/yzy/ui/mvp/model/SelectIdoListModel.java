package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.IDoEntity;
import com.idolmedia.yzy.ui.mvp.contract.SelectIdoContract;
import com.idolmedia.yzy.ui.mvp.contract.SelectIdoListContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 15:58
 * 描述：
 */

public class SelectIdoListModel implements SelectIdoListContract.Model {

    @Override
    public Observable<IDoEntity> IdoSearch_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().IdoSearch(map);
    }

    @Override
    public Observable<BaseResult> FollowIdo_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().FollowIdo(map);
    }
}
