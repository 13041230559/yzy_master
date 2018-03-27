package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.AddressDefaultEntity;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.PayBuyEntity;
import com.idolmedia.yzy.entity.PayEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/27 17:11
 * 描述： 订单列表
 */

public interface ConfirmOrderBuyContract {
    interface Model extends BaseModel {
    //    Observable<String> SubmitOrder_m(HashMap<String, Object> map);
    //    Observable<ReturnFreightEntity> Freight_m(HashMap<String, Object> map);
        Observable<AddressDefaultEntity> DefaultAddress_m(HashMap<String, Object> map);
       // Observable<ConfirmOrderEntity>ConfirmOrder_m(HashMap<String, Object> map);
        Observable<String>BuyAddition_m(HashMap<String, Object> map);
        Observable<String>BuyFreight_m(HashMap<String, Object> map);
        Observable<PayBuyEntity>BuyOrder_m(HashMap<String, Object> map);
        Observable<PayBuyEntity>RetainageOrder_m(HashMap<String, Object> map);

    }
    interface View extends BaseView {
      //  void SubmitOrder_v(String t);
     //   void Freight_v(ReturnFreightEntity t);
        void DefaultAddress_v(AddressDefaultEntity t);
     //   void ConfirmOrder_v(ConfirmOrderEntity t);
        void BuyAddition_v(String t);

        void BuyFreight_v(String t);

        void BuyOrder_v(PayBuyEntity t);
        void RetainageOrder_v(PayBuyEntity t);

    }
    abstract  class Presenter extends BasePresenter<View,Model> {
     //   public abstract void SubmitOrder_p(HashMap<String,Object> map);
    //    public abstract void Freight_p(HashMap<String,Object> map);
        public abstract void DefaultAddress_p(HashMap<String,Object> map);
    //    public abstract void ConfirmOrder_p(HashMap<String,Object> map);
        public abstract void BuyAddition_p(HashMap<String,Object> map);
        public abstract void BuyFreight_p(HashMap<String,Object> map);
        public abstract void BuyOrder_p(HashMap<String,Object> map);
        public abstract void RetainageOrder_p(HashMap<String,Object> map);
    }
}
