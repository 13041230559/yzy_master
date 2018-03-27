package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.LogisticsEntity;
import com.idolmedia.yzy.ui.mvp.contract.LogisticsContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/21 17:03
 * 描述：
 */

public class LogisticsModel implements LogisticsContract.Model {
    @Override
    public Observable<LogisticsEntity> Logistics_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().LogisticsList(map);
    }
}
