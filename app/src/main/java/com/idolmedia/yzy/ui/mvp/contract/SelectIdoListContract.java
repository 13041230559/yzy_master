package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.IDoEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 15:51
 * 描述：
 */

public interface SelectIdoListContract {
    interface Model extends BaseModel {
       // Observable<String> IdoAll_m(HashMap<String, Object> map);
        Observable<IDoEntity> IdoSearch_m(HashMap<String, Object> map);
        Observable<BaseResult> FollowIdo_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
       // void IdoAll_v(String t);
        void IdoSearch_v(IDoEntity t);
        void FollowIdo_v(BaseResult t,int position,boolean isIsAtted);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
     //   public abstract void IdoAll_p(HashMap<String,Object> map);
        public abstract void IdoSearch_p(HashMap<String,Object> map);
        public abstract void FollowIdo_p(HashMap<String,Object> map,int position,boolean isIsAtted);
    }
}
