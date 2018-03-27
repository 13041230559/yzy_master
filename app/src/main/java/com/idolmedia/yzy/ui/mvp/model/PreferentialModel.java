package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.PreferentiaEntity;
import com.idolmedia.yzy.entity.PreferentialHotEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;
import com.idolmedia.yzy.ui.mvp.contract.PreferentialContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:47
 * 描述：
 */

public class PreferentialModel implements PreferentialContract.Model {

    @Override
    public Observable<PreferentiaEntity> Preferential_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().PreferentialList(map);
    }

    @Override
    public Observable<PreferentialHotEntity> Preferentialhot_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().PreferentialhotList(map);
    }

    @Override
    public Observable<BannerEntity> Banner_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Banner(map);
    }

    @Override
    public Observable<BannerEntity> Banner1_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Banner(map);
    }
}
