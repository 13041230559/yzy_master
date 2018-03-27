package com.idolmedia.yzy.ui.mvp.contract;

import android.view.View;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CartEntity;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 15:51
 * 描述：
 */

public interface CartContract {
    interface Model extends BaseModel {
        Observable<CartEntity> CartList_m(HashMap<String, Object> map);
        Observable<BaseResult> UpdateCart_m(HashMap<String, Object> map);
        Observable<BaseResult> DelCart_m(HashMap<String, Object> map);
        Observable<BaseResult> ClearInvalid_m(HashMap<String, Object> map);
        Observable<CommodityEntity>RecommendCartList_m(HashMap<String, Object> map);


    }
    interface View extends BaseView {
        void CartList_v(CartEntity t);
        void UpdateCart_v(BaseResult t,int groupPosition, int childPosition, android.view.View showCountView,String number,String setNumber);
        void DelCart_v(BaseResult t);
        void ClearInvalid_v(BaseResult t);

        void RecommendCartList_v(CommodityEntity t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void CartList_p(HashMap<String,Object> map);
        public abstract void UpdateCart_p(HashMap<String,Object> map, int groupPosition, int childPosition, android.view.View showCountView,String number,String setNumber);
        public abstract void DelCart_p(HashMap<String,Object> map);
        public abstract void ClearInvalid_p(HashMap<String,Object> map);

        public abstract void RecommendCartList_p(HashMap<String,Object> map);
    }
}
