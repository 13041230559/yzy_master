package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.mvp.contract.SavePhoneContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/22 15:27
 * 描述：
 */

public class  SavePhoneModel implements SavePhoneContract.Model {
    @Override
    public Observable<String> CodeLogin_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().AuthenticationPhone(map);
    }

    @Override
    public Observable<BaseResult> GetCode_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GetCode(map);
}
}
