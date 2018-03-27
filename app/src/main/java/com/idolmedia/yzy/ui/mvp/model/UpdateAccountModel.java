package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.mvp.contract.UpdateAccountContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/29 10:42
 * 描述：
 */

public class UpdateAccountModel implements UpdateAccountContract.Model {
    @Override
    public Observable<BaseResult> bindPhoneEmail_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BindPhoneEmailAccount(map);
    }

    @Override
    public Observable<BaseResult> changePhoneEmail_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().ChangeMain(map);
    }

    @Override
    public Observable<BaseResult> addPass_m(HashMap<String, Object> map) {
        return  RetrofitHttp.getInstance().AddPass(map);
    }

    @Override
    public Observable<BaseResult> updatePass_m(HashMap<String, Object> map) {
        return  RetrofitHttp.getInstance().UpdatePass(map);
    }

    @Override
    public Observable<BaseResult> Code_m(HashMap<String, Object> map) {
        return  RetrofitHttp.getInstance().GetCode(map);
    }
}
