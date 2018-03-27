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
 * 创建时间：2017/11/27 11:46
 * 描述：添加地址/修改地址
 */

public interface NewAddressContract {
    interface Model extends BaseModel {
        Observable<BaseResult> NewAddress_m(HashMap<String, Object> map);
        Observable<BaseResult> EditAddress_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void NewAddress_v(BaseResult t);
        //void  UpdateAddress_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void NewAddress_p(HashMap<String,Object> map);
        public abstract void EditAddress_p(HashMap<String,Object> map);
    }
}
