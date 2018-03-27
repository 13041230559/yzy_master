package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyOrderDetalisEntity;
import com.idolmedia.yzy.ui.mvp.contract.MyOrderDetalisContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 10:43
 * 描述：
 */

public class MyOrderDetalisModel implements MyOrderDetalisContract.Model {
    @Override
    public Observable<MyOrderDetalisEntity> OrderDetalis_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().OrderDetailsList(map);
    }

    @Override
    public Observable<BaseResult> CancelOrder_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CancelOrder(map);
    }

    @Override
    public Observable<BaseResult> ConfirmReceipt_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().ConfirmReceipt(map);
    }

    @Override
    public Observable<BaseResult> DeleteOrder_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().DeleteOrder(map);
    }
}
