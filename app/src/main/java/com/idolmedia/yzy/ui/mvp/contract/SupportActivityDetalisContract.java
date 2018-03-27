package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.SupportDetalisEntity;
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
 * 创建时间：2018/1/12 16:41
 * 描述：应援详情
 */

public interface SupportActivityDetalisContract {
    interface Model extends BaseModel {
        Observable<SupportDetalisEntity> SupportActivityDetalis_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void SupportActivityDetalis_v(SupportDetalisEntity t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void SupportActivityDetalis_p(HashMap<String,Object> map);

    }
}
