package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyFollowIdoEntity;
import com.idolmedia.yzy.entity.MyFollowOfficialEntity;
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

public interface MyIdoContract {
    interface Model extends BaseModel {
        Observable<MyFollowIdoEntity> MyIdoList_m(HashMap<String, Object> map);
        Observable<MyFollowOfficialEntity> MyAuthList_m(HashMap<String, Object> map);
        Observable<BaseResult> FollowIdo_m(HashMap<String, Object> map);
        Observable<BaseResult> FollowThirdParty_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void MyIdoList_v(MyFollowIdoEntity t,int pageno);
        void MyAuthList_v(MyFollowOfficialEntity t, int pageno);
        void FollowIdo_v(BaseResult t,int position,boolean isfollow,int fanscount);
        void FollowThirdParty_v(BaseResult t,int position,boolean isfollow,int fanscount);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void  MyIdoList_p(HashMap<String,Object> map,int pageno);

        public abstract void  MyAuthList_p(HashMap<String,Object> map,int pageno);

        public abstract void FollowIdo_p(HashMap<String,Object> map,int position,boolean isfollow,int fanscount);

        public abstract void FollowThirdParty_p(HashMap<String,Object> map,int position,boolean isfollow,int fanscount);
    }
}
