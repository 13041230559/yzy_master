package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.ui.mvp.contract.EntertainmentContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/18 14:26
 * 描述：  娱
 */

public class EntertainmentModel implements EntertainmentContract.Model {
    @Override
    public Observable<CommodityEntity> Entertainment_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().YuList(map);
    }

    @Override
    public Observable<BannerEntity> Banner_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Banner(map);
    }
}
