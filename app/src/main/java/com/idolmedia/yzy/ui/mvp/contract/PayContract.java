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
 * 创建时间：2018/1/12 18:01
 * 描述：支付页面
 */

public interface PayContract {
    interface Model extends BaseModel {
        Observable<String> Pay_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Pay_v(String t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void Pay_p(HashMap<String,Object> map);
    }
}
