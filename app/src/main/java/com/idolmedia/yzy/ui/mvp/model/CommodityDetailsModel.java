package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommodEntity;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.ProductclassEntity;
import com.idolmedia.yzy.ui.mvp.contract.CommdDetailsContract;
import com.idolmedia.yzy.ui.mvp.contract.CommodityDetailsContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 11:28
 * 描述：
 */

public class CommodityDetailsModel implements CommodityDetailsContract.Model {

    @Override
    public Observable<ProductclassEntity> ProductclassList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().ProductclassList(map);
    }

    @Override
    public Observable<DepositEntity> Deposit_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().DeCommdityDetails(map);
    }

    @Override
    public Observable<BaseResult> AddCart_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().AddCart(map);
    }

    @Override
    public Observable<BaseResult> GoodClick_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GoodClick(map);
    }
}
