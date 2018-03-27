package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.ui.mvp.contract.RecommendContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/4 22:32
 * 描述：
 */

public class RecommendModel implements RecommendContract.Model {
    @Override
    public Observable<BannerEntity> Banner_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Banner(map);
    }

    @Override
    public Observable<CommodityEntity> RecommendList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().RecommendList(map);
    }

    @Override
    public Observable<String> Unreadmessage_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Unreadmessage(map);
    }
}
