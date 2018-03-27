package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MyOrderDetalisEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/18 20:06
 * 描述：订单详情
 */

public interface MyOrderDetalisContract {
    interface Model extends BaseModel {
        Observable<MyOrderDetalisEntity> OrderDetalis_m(HashMap<String, Object> map);
        Observable<BaseResult> CancelOrder_m(HashMap<String, Object> map);
        Observable<BaseResult>ConfirmReceipt_m(HashMap<String, Object> map);
        Observable<BaseResult>DeleteOrder_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void OrderDetalis_v(MyOrderDetalisEntity t);
        void CancelOrder_v(BaseResult t);
        void ConfirmReceipt_v(BaseResult t);
        void DeleteOrder_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void OrderDetalis_p(HashMap<String,Object> map);
        public abstract void CancelOrder_p(HashMap<String,Object> map);
        public abstract void ConfirmReceipt_p(HashMap<String,Object> map);
        public abstract void DeleteOrder_p(HashMap<String,Object> map);
    }
}
