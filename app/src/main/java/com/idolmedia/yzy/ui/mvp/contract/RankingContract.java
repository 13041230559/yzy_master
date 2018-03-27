package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.RankingEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/20 13:46
 * 描述： 排行榜
 */

public interface RankingContract {
    interface Model extends BaseModel {
        Observable<RankingEntity> Ranking_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Ranking_v(RankingEntity t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void Ranking_p(HashMap<String,Object> map);
    }
}
