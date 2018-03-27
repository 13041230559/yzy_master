package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.AddressDefaultEntity;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.PayEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
import com.idolmedia.yzy.ui.mvp.contract.ConfirmOrderContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 17:35
 * 描述：
 */

public class ConfirmOrderModel implements ConfirmOrderContract.Model {

    @Override
    public Observable<PayEntity> SubmitOrder_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().SubmitOrder(map);
    }

    @Override
    public Observable<ReturnFreightEntity> Freight_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Freight(map);
    }

    @Override
    public Observable<AddressDefaultEntity> DefaultAddress_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().DefaultAddress(map);
    }

    @Override
    public Observable<ConfirmOrderEntity> ConfirmOrder_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().ConfirmOrder(map);
    }
/*
    @Override
    public Observable<String> BuyAdditional_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BuyAdditional(map);
    }

    @Override
    public Observable<ReturnFreightEntity> BuyFreight_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BuyFreight(map);
    }

    @Override
    public Observable<String> BuyOrder_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BuyOrder(map);
    }*/
}
