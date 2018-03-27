package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.AccountEntity;
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
 * 创建时间：2018/1/17 15:13
 * 描述：
 */

public interface AccountContract {
    interface Model extends BaseModel {
        Observable<AccountEntity> Account_m(HashMap<String, Object> map);
        Observable<BaseResult>BindThird_m(HashMap<String, Object> map);
        Observable<BaseResult>BindPhoneEmail_m(HashMap<String, Object> map);
        Observable<BaseResult>UBindThird_m(HashMap<String, Object> map);
        Observable<BaseResult>ChangeMain_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Account_v(AccountEntity t);
        void BaseResultMsg_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void Account_p(HashMap<String,Object> map);
        public abstract void BindThird_p(HashMap<String,Object> map);
        public abstract void BindPhoneEmail_p(HashMap<String,Object> map);
        public abstract void UBindThird_p(HashMap<String,Object> map);
        public abstract void ChangeMain_p(HashMap<String,Object> map);

    }

}
