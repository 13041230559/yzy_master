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
 * 创建时间：2017/12/4 22:31
 * 描述：
 */

public interface RecommendContract {
    interface Model extends BaseModel {
        Observable<BannerEntity> Banner_m(HashMap<String, Object> map);
        Observable<CommodityEntity> RecommendList_m(HashMap<String, Object> map);
        Observable<String> Unreadmessage_m(HashMap<String, Object> map);

    }
    interface View extends BaseView {
        void Banner_v(BannerEntity t);
        void RecommendList_v(CommodityEntity t);
        void Unreadmessage_v(String t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void Banner_p(HashMap<String,Object> map);
        public abstract void RecommendList_p(HashMap<String,Object> map);
        public abstract void Unreadmessage_p(HashMap<String,Object> map);
    }
}
