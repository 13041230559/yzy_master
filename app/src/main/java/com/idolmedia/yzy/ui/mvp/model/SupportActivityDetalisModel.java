package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.SupportDetalisEntity;
import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.ui.mvp.contract.SupportActivityDetalisContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/12 16:43
 * 描述：
 */

public class SupportActivityDetalisModel implements SupportActivityDetalisContract.Model {
    @Override
    public Observable<SupportDetalisEntity> SupportActivityDetalis_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().SupportDetails(map);
    }

}
