package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.ui.mvp.contract.AdvertisementContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/2 19:13
 * 描述：
 */

public class AdvertisementModel implements AdvertisementContract.Model {
    @Override
    public Observable<BannerEntity> Banner_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Banner(map);
    }
}
