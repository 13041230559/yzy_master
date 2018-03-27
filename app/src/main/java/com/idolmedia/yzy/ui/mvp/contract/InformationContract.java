package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.InformationEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/30 12:04
 * 描述：资讯
 */

public interface InformationContract {

    interface Model extends BaseModel {
        Observable<BannerEntity> Banner_m(HashMap<String, Object> map);
        Observable<InformationEntity> InformationList_m(HashMap<String, Object> map);
        Observable<CommentEntity> CommentList_m(HashMap<String, Object> map);
        Observable<BaseResult> GoodClick_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void Banner_v(BannerEntity t);
        void InformationList_v(InformationEntity t,int PageNo);
        void CommentList_v(CommentEntity t,int PageNo);
        void GoodClick_v(BaseResult t,int position ,int linkcount);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void Banner_p(HashMap<String,Object> map);
        public abstract void InformationLis_p(HashMap<String,Object> map,int PageNo);
        public abstract void CommentList_p(HashMap<String,Object> map,int PageNo);
        public abstract void GoodClick_p(HashMap<String,Object> map,int position ,int linkcount);
    }
}
