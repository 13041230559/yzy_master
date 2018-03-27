package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/26 11:30
 * 描述：
 */

public interface SearchResultContract {

    interface Model extends BaseModel {
        Observable<String> SearchResult_m(HashMap<String, Object> map);
        Observable<BaseResult> FollowIdo_m(HashMap<String, Object> map);
        Observable<BaseResult> FollowThirdParty_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void SearchResult_v(String t,int pageNo,int search_type);
        void FollowIdo_v(BaseResult t,int postion,boolean isfollow,int fanscount);
        void FollowThirdParty_v(BaseResult t,int position,boolean isfollow,int fanscount);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void SearchResult_p(HashMap<String,Object> map,int pageNo,int search_type);
        public abstract void FollowIdo_p(HashMap<String,Object> map,int postion,boolean isfollow,int fanscount);
        public abstract void FollowThirdParty_p(HashMap<String,Object> map,int position,boolean isfollow,int fanscount);

    }
}


