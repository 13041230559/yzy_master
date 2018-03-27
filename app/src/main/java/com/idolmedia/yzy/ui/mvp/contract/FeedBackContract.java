package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/22 10:38
 * 描述：意见反馈
 */

public interface FeedBackContract {
    interface Model extends BaseModel {
        Observable<BaseResult>  FeedBack_m(List<MultipartBody.Part> partList);
    }
    interface View extends BaseView {
        void  FeedBack_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void FeedBack_p(List<MultipartBody.Part> partList);
    }
}
