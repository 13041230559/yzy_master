package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.ProductclassEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/28 11:25
 * 描述：
 */

public interface CommodityDetailsContract {
    interface Model extends BaseModel {
        Observable<ProductclassEntity> ProductclassList_m(HashMap<String, Object> map);
        Observable<DepositEntity> Deposit_m(HashMap<String, Object> map);
        Observable<BaseResult> AddCart_m(HashMap<String, Object> map);
        Observable<BaseResult> GoodClick_m(HashMap<String, Object> map);

    }
    interface View extends BaseView {
        void ProductclassList_v(ProductclassEntity t);
        void Deposit_v(DepositEntity t);
        void AddCart_v(BaseResult t);
        void GoodClick_v(BaseResult t,int linkcount);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void ProductclassList_p(HashMap<String,Object> map);
        public abstract void AddCart_p(HashMap<String,Object> map);
        public abstract void Deposit_p(HashMap<String,Object> map);
        public abstract void GoodClick_p(HashMap<String,Object> map,int linkcount);
    }
}
