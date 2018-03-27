package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.RegisterContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/17 12:15
 * 描述：  注册
 */

public class RegisterModel implements RegisterContract.Model {
    @Override
    public Observable<UserEntity> register_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Register(map);
    }

    @Override
    public Observable<BaseResult> code_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GetCode(map);
    }
}
