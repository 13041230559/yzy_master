package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.ui.mvp.contract.SupportActivityListContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 11:02
 * 描述：
 */

public class SupportActivityListModel implements SupportActivityListContract.Model {
    @Override
    public Observable<SupportEntity> SupportList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().SupportActivitylList(map);
    }
}
