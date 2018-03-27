package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.LogisticsEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/21 17:02
 * 描述：  物流信息
 */

public interface LogisticsContract {
    interface Model extends BaseModel {
        Observable<LogisticsEntity> Logistics_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Logistics_v(LogisticsEntity t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void Logistics_p(HashMap<String,Object> map);

    }
}
