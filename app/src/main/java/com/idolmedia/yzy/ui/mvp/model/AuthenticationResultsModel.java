package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.AuthenticationResultEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.AuthenticationResultsContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/12 12:47
 * 描述：
 */

public class AuthenticationResultsModel implements AuthenticationResultsContract.Model {
    @Override
    public Observable<AuthenticationResultEntity> resultMsg_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().QueryAuthenticationResult(map);
    }
}
