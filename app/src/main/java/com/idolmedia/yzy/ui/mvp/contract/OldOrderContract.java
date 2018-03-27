package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.OldOrderEntity;
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
 * 描述：我的页面
 */

public interface OldOrderContract {
    interface Model extends BaseModel {
        Observable<OldOrderEntity> OldOrderList_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void OldOrderList(OldOrderEntity t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void OldOrderList_p(HashMap<String,Object> map);

    }
}
