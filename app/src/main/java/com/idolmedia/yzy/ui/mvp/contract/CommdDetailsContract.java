package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.CommodEntity;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/19 17:00
 * 描述： 宝贝详情
 */

public interface CommdDetailsContract {
    interface Model extends BaseModel {
        Observable<DepositEntity> CommdDetails_m(HashMap<String, Object> map);
        Observable<DepositEntity> DepositCommdDetails_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void CommdDetails_v(DepositEntity t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void CommdDetails_p(HashMap<String,Object> map);
        public abstract void DepositCommdDetails_p(HashMap<String,Object> map);
    }
}
