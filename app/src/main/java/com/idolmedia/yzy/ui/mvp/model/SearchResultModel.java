package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.ui.mvp.contract.SearchResultContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 12:04
 * 描述：
 */

public class SearchResultModel implements SearchResultContract.Model {
    @Override
    public Observable<String> SearchResult_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().SearchList(map);
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
