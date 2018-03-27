package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.UpdateVersionEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/7 14:05
 * 描述：
 */

public interface SettingContract  {

    interface Model extends BaseModel {
        Observable<UpdateVersionEntity> UpdateVersion_m();
    }
    interface View extends BaseView {
        void UpdateVersion_v(UpdateVersionEntity t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void UpdateVersion_p();

    }
}
