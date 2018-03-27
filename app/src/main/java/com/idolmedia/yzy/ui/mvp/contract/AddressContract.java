package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.AddressEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 12:23
 * 描述：地址
 */

public interface AddressContract {

    interface Model extends BaseModel {
        Observable<AddressEntity> AddressList_m(HashMap<String, Object> map);
        Observable<BaseResult> DelAddress_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void AddressList_v(AddressEntity t);
        void DelAddress_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void AddressList_p(HashMap<String,Object> map);
        public abstract void DelAddress_p(HashMap<String,Object> map);
    }
}
