package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/18 14:25
 * 描述： 娱
 */

public interface EntertainmentContract {
    interface Model extends BaseModel {
        Observable<CommodityEntity> Entertainment_m(HashMap<String, Object> map);
        Observable<BannerEntity> Banner_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Entertainment_v(CommodityEntity entertainmentEntity);
        void Banner_v(BannerEntity bannerEntity,int type);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void Entertainment_p(HashMap<String,Object> map);
        public abstract void Banner_p(HashMap<String,Object> map,int type);

    }
}
