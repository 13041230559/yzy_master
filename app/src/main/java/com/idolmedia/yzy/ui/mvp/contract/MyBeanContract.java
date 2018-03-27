package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.MyBeanEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/5 16:18
 * 描述：
 */

public interface MyBeanContract {
    interface Model extends BaseModel {
        Observable<MyBeanEntity> MyBeanList_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void MyBeanList_v(MyBeanEntity t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void MyBeanList_p(HashMap<String,Object> map);

    }
}
