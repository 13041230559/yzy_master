package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/1 18:35
 * 描述：
 */

public interface SupportDetalisContract {

    interface Model extends BaseModel {
        Observable<UserEntity> AutoLogin_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void AutoLogin_v(UserEntity t);
    }
    abstract  class Presenter extends BasePresenter<MeContract.View, MeContract.Model> {
        public abstract void AutoLogin_p(HashMap<String,Object> map);

    }
}
