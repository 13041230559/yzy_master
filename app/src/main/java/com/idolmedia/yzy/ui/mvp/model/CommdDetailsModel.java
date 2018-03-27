package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.CommodEntity;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.ui.mvp.contract.CommdDetailsContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/19 17:10
 * 描述：
 */

public class CommdDetailsModel implements CommdDetailsContract.Model {
    @Override
    public Observable<DepositEntity> CommdDetails_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CommodityDetails(map);
    }

    @Override
    public Observable<DepositEntity> DepositCommdDetails_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().DeCommdityDetails(map);
    }
}
