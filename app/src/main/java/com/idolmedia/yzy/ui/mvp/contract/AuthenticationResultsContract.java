package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.AuthenticationResultEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/12 12:42
 * 描述：
 */

public interface AuthenticationResultsContract {

    interface Model extends BaseModel {
        Observable<AuthenticationResultEntity> resultMsg_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void resultMsg_v(AuthenticationResultEntity t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void resultMsg_p(HashMap<String,Object> map);

    }
}
