package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.ui.mvp.contract.PayContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/12 18:04
 * 描述：
 */

public class PayModel implements PayContract.Model {
    @Override
    public Observable<String> Pay_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().PaySign(map);
    }


}
