package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyOrderEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MyOrderRecordContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/20 15:42
 * 描述：
 */

public class MyOrderRecordModel implements MyOrderRecordContract.Model {
    @Override
    public Observable<MyOrderEntity> MyOrderRecordList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().UserOrderList(map);
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
