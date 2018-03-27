package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MessageClassEntity;
import com.idolmedia.yzy.entity.MessageEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/23 18:22
 * 描述：
 */

public interface MessageContract {
    interface Model extends BaseModel {
        Observable<MessageEntity> MessageList_m(HashMap<String, Object> map);
        Observable<MessageClassEntity> QueryMessageList_m(HashMap<String, Object> map);
        Observable<BaseResult> ToRead_m(HashMap<String, Object> map);

    }
    interface View extends BaseView {
        void  MessageList_v(MessageEntity t);
        void  QueryMessageList_v(MessageClassEntity t);
        void  ToRead_v(BaseResult t,int position ,int have_read);
    }
    abstract  class Presenter extends BasePresenter<View,Model> {
        public abstract void  MessageList_p(HashMap<String,Object> map);
        public abstract void   QueryMessageList_p(HashMap<String,Object> map);
        public abstract void   ToRead_p(HashMap<String,Object> map,int position ,int have_read);
    }
}
