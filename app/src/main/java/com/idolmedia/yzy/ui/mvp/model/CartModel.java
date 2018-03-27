package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CartEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.ui.mvp.contract.CartContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 16:33
 * 描述：购物车
 */

public class CartModel implements CartContract.Model {
    @Override
    public Observable<CartEntity> CartList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CartList(map);
    }

    @Override
    public Observable<BaseResult> UpdateCart_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().UpdateCart(map);
    }

    @Override
    public Observable<BaseResult> DelCart_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().DelCart(map);
    }

    @Override
    public Observable<BaseResult> ClearInvalid_m(HashMap<String, Object> map) {
        return  RetrofitHttp.getInstance().Invalid(map);
    }

    @Override
    public Observable<CommodityEntity> RecommendCartList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().RecommendCartList(map);
    }
}
