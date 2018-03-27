package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.LoginContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/24 18:21
 * 描述：
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<UserEntity> Login_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Login(map);
    }

    @Override
    public Observable<UserEntity> CodeLogin_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CodeLogin(map);
    }

    @Override
    public Observable<BaseResult> Code_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GetCode(map);
    }

    @Override
    public Observable<UserEntity> ThirdPartyLogin_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().ThirdPartyLogin(map);
    }
}
