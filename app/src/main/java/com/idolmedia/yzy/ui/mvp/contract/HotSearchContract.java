package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.SearchHotEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 11:11
 * 描述：
 */

public interface HotSearchContract {

    interface Model extends BaseModel {
        Observable<SearchHotEntity> HotSearchList_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void HotSearchList_v(SearchHotEntity t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void HotSearchList_p(HashMap<String,Object> map);

    }
}
