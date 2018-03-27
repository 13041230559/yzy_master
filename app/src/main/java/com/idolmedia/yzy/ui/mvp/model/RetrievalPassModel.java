package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.RetrievalPassContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 14:41
 * 描述：
 */

public class RetrievalPassModel implements RetrievalPassContract.Model {

    @Override
    public Observable<BaseResult> RetrievalPass_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().BackPass(map);
    }

    @Override
    public Observable<BaseResult> Code_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GetCode(map);
    }
}
