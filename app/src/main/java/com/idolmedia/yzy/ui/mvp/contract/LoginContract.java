package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/17 12:09
 * 描述：
 */

public interface LoginContract {
    interface Model extends BaseModel {
        Observable<UserEntity> Login_m(HashMap<String, Object> map);
        Observable<UserEntity>CodeLogin_m(HashMap<String, Object> map);
        Observable<BaseResult> Code_m(HashMap<String, Object> map);
        Observable<UserEntity> ThirdPartyLogin_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Login_v(UserEntity t);
        void code_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void Login_p(HashMap<String,Object> map);
        public abstract void Code_p(HashMap<String,Object> map);
        public abstract void CodeLogin_p(HashMap<String,Object> map);
        public abstract void ThirdPartyLogin_p(HashMap<String,Object> map);
    }
}
