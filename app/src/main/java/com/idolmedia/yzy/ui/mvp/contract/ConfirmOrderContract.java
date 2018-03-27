package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.AddressDefaultEntity;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
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

public interface ConfirmOrderContract {
    interface Model extends BaseModel {
        Observable<PayEntity> SubmitOrder_m(HashMap<String, Object> map);
        Observable<ReturnFreightEntity> Freight_m(HashMap<String, Object> map);
        Observable<AddressDefaultEntity> DefaultAddress_m(HashMap<String, Object> map);
        Observable<ConfirmOrderEntity>ConfirmOrder_m(HashMap<String, Object> map);
        //  Observable<String>BuyAdditional_m(HashMap<String, Object> map);
        // Observable<ReturnFreightEntity>BuyFreight_m(HashMap<String, Object> map);
      //  Observable<String>BuyOrder_m(HashMap<String, Object> map);

    }
    interface View extends BaseView {
        void SubmitOrder_v(PayEntity t);
        void Freight_v(ReturnFreightEntity t);
        void DefaultAddress_v(AddressDefaultEntity t);
        void ConfirmOrder_v(ConfirmOrderEntity t);
        //   void BuyAdditional_v(String t);

        //  void BuyFreight_v(ReturnFreightEntity t);

        //  void BuyOrder_v(String t);

    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void SubmitOrder_p(HashMap<String,Object> map);
        public abstract void Freight_p(HashMap<String,Object> map);
        public abstract void DefaultAddress_p(HashMap<String,Object> map);
        public abstract void ConfirmOrder_p(HashMap<String,Object> map);
        //   public abstract void BuyAdditional_p(HashMap<String,Object> map);
        //  public abstract void BuyFreight_p(HashMap<String,Object> map);
        // public abstract void BuyOrder_p(HashMap<String,Object> map);

    }
}
