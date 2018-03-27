package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/2 19:12
 * 描述：
 */

public interface AdvertisementContract {

    interface Model extends BaseModel {
        Observable<BannerEntity> Banner_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Banner_v(BannerEntity t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void Banner_p(HashMap<String,Object> map);

    }
}
