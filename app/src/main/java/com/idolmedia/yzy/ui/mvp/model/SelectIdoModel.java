package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.IDoEntity;
import com.idolmedia.yzy.ui.mvp.contract.SelectIdoContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 15:58
 * 描述：
 */

public class SelectIdoModel implements SelectIdoContract.Model {
    @Override
    public Observable<IDoEntity> SelectStars_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().SelectStars(map);
    }

    @Override
    public Observable<BaseResult> FollowIdo_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().FollowIdo(map);
    }
}
