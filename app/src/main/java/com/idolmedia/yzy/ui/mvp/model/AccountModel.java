package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.AccountEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.mvp.contract.AccountContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/17 15:15
 * 描述：
 */

public class AccountModel implements AccountContract.Model {
    @Override
    public Observable<AccountEntity> Account_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().AccountList(map);
    }

    @Override
    public Observable<BaseResult> BindThird_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BindThirdAccount(map);
    }

    @Override
    public Observable<BaseResult> BindPhoneEmail_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BindPhoneEmailAccount(map);
    }

    @Override
    public Observable<BaseResult> UBindThird_m(HashMap<String, Object> map) {
        return  RetrofitHttp.getInstance().UBindThirdAccount(map);
    }

    @Override
    public Observable<BaseResult> ChangeMain_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().ChangeMain(map);
    }
}
