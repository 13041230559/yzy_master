package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.InformationEntity;
import com.idolmedia.yzy.ui.mvp.contract.InformationContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/30 12:15
 * 描述：
 */

public class InformationModel implements InformationContract.Model {
    @Override
    public Observable<BannerEntity> Banner_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().Banner(map);
    }

    @Override
    public Observable<InformationEntity> InformationList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().InformationList(map);
    }

    @Override
    public Observable<CommentEntity> CommentList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CommentList(map);
    }

    @Override
    public Observable<BaseResult> GoodClick_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GoodClick(map);
    }
}
