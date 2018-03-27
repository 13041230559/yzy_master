package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.MyReleaseInfoEntity;
import com.idolmedia.yzy.ui.mvp.contract.MyReleaseListContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/27 17:28
 * 描述：
 */

public class MyReleaseListModel implements MyReleaseListContract.Model {
    @Override
    public Observable<String> MyReleaseList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().MyReleaseList(map);
    }

    @Override
    public Observable<MyReleaseInfoEntity> MyReleaseInfo_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().MyReleaseInfo(map);
    }
}
