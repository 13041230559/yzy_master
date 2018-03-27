package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyOrderEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/18 19:57
 * 描述： 我的订单
 */

public interface MyOrderRecordContract {
    interface Model extends BaseModel {
        Observable<MyOrderEntity> MyOrderRecordList_m(HashMap<String, Object> map);
        Observable<BaseResult> CancelOrder_m(HashMap<String, Object> map);
        Observable<BaseResult>ConfirmReceipt_m(HashMap<String, Object> map);
        Observable<BaseResult>DeleteOrder_m(HashMap<String, Object> map);

    }
    interface View extends BaseView {
        void MyOrderRecordList_v(MyOrderEntity t);
        void CancelOrder_v(BaseResult t,int position);
        void ConfirmReceipt_v(BaseResult t,int position);
        void DeleteOrder_v(BaseResult t,int position);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void MyOrderRecordList_p(HashMap<String,Object> map);
        public abstract void CancelOrder_p(HashMap<String,Object> map,int position);
        public abstract void ConfirmReceipt_p(HashMap<String,Object> map,int position);
        public abstract void DeleteOrder_p(HashMap<String,Object> map,int position);
    }
}
