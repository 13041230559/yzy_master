package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;
import com.idolmedia.yzy.ui.mvp.contract.NewAddressContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:47
 * 描述：
 */

public class NewAddressModel implements NewAddressContract.Model {

    @Override
    public Observable<BaseResult> NewAddress_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().AddAddress(map);
    }

    @Override
    public Observable<BaseResult> EditAddress_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().EditAddress(map);
    }
}
