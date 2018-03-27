package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.AddressDefaultEntity;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.PayBuyEntity;
import com.idolmedia.yzy.entity.PayEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
import com.idolmedia.yzy.ui.mvp.contract.ConfirmOrderBuyContract;
import com.idolmedia.yzy.ui.mvp.contract.ConfirmOrderContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 17:35
 * 描述：
 */

public class ConfirmOrderBuyModel implements ConfirmOrderBuyContract.Model {


    @Override
    public Observable<AddressDefaultEntity> DefaultAddress_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().DefaultAddress(map);
    }
    @Override
    public Observable<String> BuyAddition_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BuyAdditional(map);
    }

    @Override
    public Observable<String> BuyFreight_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BuyFreight(map);
    }

    @Override
    public Observable<PayBuyEntity> BuyOrder_m(HashMap<String, Object> map) {
            return RetrofitHttp.getInstance().BuyOrder(map);
    }

    @Override
    public Observable<PayBuyEntity> RetainageOrder_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().RetainageOrder(map);
    }
}
