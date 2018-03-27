package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.ClassEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/21 13:57
 * 描述：  商品分类
 */

public interface ClassificationContract {
    interface Model extends BaseModel {
        Observable<ClassEntity>Classification_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Classification_v(ClassEntity t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void Classification_p(HashMap<String,Object> map);

    }
}
