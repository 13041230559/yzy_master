package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.CommodityEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 11:30
 * 描述：
 */

public interface ClassSearchResultContract {
    interface Model extends BaseModel {
        Observable<CommodityEntity> ClassSearchResult_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void ClassSearchResult_v(CommodityEntity t,int pageNo);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void ClassSearchResult_p(HashMap<String,Object> map,int pageNo);

    }
}
