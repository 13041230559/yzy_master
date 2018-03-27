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
 * 创建时间：2017/12/22 15:25
 * 描述：
 */

public interface SavePhoneContract  {
    interface Model extends BaseModel {
        Observable<String>CodeLogin_m(HashMap<String, Object> map);
        Observable<BaseResult>GetCode_m(HashMap<String, Object> map);

    }
    interface View extends BaseView {
        void code_v(String t);
        void GetCode_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void Code_p(HashMap<String,Object> map);
        public abstract void GetCode_p(HashMap<String,Object> map);
    }
}