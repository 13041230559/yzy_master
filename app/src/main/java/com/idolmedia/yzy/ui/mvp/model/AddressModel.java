package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.AddressEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.AddressContract;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:47
 * 描述：
 */

public class AddressModel implements AddressContract.Model {
    @Override
    public Observable<AddressEntity> AddressList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().AddressList(map);
    }

    @Override
    public Observable<BaseResult> DelAddress_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().DelAddress(map);
    }
}
