package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.RankingEntity;
import com.idolmedia.yzy.ui.mvp.contract.RankingContract;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/20 13:53
 * 描述：
 */

public class RankingModel implements RankingContract.Model {
    @Override
    public Observable<RankingEntity> Ranking_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().RankingList(map);
    }
}
