package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.SearchHotEntity;
import com.idolmedia.yzy.ui.mvp.contract.HotSearchContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 11:14
 * 描述：
 */

public class HotSearchModel implements HotSearchContract.Model {
    @Override
    public Observable<SearchHotEntity> HotSearchList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().HotSearchList(map);
    }
}
