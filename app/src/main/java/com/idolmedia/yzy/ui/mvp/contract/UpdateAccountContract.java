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
 * 创建时间：2018/1/29 10:32
 * 描述： 修改账户安全
 */

public interface UpdateAccountContract {

    interface Model extends BaseModel {
        Observable<BaseResult>  bindPhoneEmail_m(HashMap<String, Object> map);
        Observable<BaseResult>  changePhoneEmail_m(HashMap<String, Object> map);
        Observable<BaseResult>  addPass_m(HashMap<String, Object> map);
        Observable<BaseResult>  updatePass_m(HashMap<String, Object> map);
        Observable<BaseResult> Code_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void baseResult_v(BaseResult t);
        void  updatePass_v(BaseResult t);
        void code_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void changePhoneEmail_p(HashMap<String,Object> map);
        public abstract void bindPhoneEmail_p(HashMap<String,Object> map);
        public abstract void addPass_p(HashMap<String,Object> map);
        public abstract void updatePass_p(HashMap<String,Object> map);
        public abstract void Code_p(HashMap<String,Object> map);
    }
}
