package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.PreferentiaEntity;
import com.idolmedia.yzy.entity.PreferentialHotEntity;
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

public interface PreferentialContract {
    interface Model extends BaseModel {
        Observable<PreferentiaEntity> Preferential_m(HashMap<String, Object> map);
        Observable<PreferentialHotEntity> Preferentialhot_m(HashMap<String, Object> map);
        Observable<BannerEntity> Banner_m(HashMap<String, Object> map);
        Observable<BannerEntity> Banner1_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Preferential_v(PreferentiaEntity t);
        void Preferentialhot_v(PreferentialHotEntity t);
        void Banner_v(BannerEntity bannerEntity);
        void Banner_v1(BannerEntity bannerEntity);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void Preferential_p(HashMap<String,Object> map);
        public abstract void Preferentialhot_p(HashMap<String,Object> map);
        public abstract void Banner_p(HashMap<String,Object> map);
        public abstract void Banner1_p(HashMap<String,Object> map);
    }
}
