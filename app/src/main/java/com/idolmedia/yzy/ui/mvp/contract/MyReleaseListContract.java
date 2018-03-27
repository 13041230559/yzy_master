package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.MyReleaseInfoEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/27 17:25
 * 描述：
 */

public interface MyReleaseListContract {

    interface Model extends BaseModel {
        Observable<String> MyReleaseList_m(HashMap<String, Object> map);
        Observable<MyReleaseInfoEntity> MyReleaseInfo_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void MyReleaseList_v(String t);
        void MyReleaseInfo_v(MyReleaseInfoEntity t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void MyReleaseList_p(HashMap<String,Object> map);
        public abstract void MyReleaseInfo_p(HashMap<String,Object> map);
    }
}
