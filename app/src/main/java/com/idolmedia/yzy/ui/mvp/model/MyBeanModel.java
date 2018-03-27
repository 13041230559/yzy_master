package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.MyBeanEntity;
import com.idolmedia.yzy.ui.mvp.contract.MyBeanContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/5 16:17
 * 描述：
 */

public class MyBeanModel implements MyBeanContract.Model {
    @Override
    public Observable<MyBeanEntity> MyBeanList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().MyYDMoneyList(map);
    }
}
