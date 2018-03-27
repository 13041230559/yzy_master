package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 11:00
 * 描述：活动应援
 */

public interface SupportActivityListContract {
    interface Model extends BaseModel {
        Observable<SupportEntity> SupportList_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void SupportList_v(SupportEntity t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void SupportList_p(HashMap<String,Object> map);

    }
}
