package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.BindingMobileContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/4 17:28
 * 描述：
 */

public class BindingMobileModel  implements BindingMobileContract.Model {
    @Override
    public Observable<UserEntity> BindLogin_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BingPhone(map);
    }

    @Override
    public Observable<BaseResult> Code_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GetCode(map);
    }
}
