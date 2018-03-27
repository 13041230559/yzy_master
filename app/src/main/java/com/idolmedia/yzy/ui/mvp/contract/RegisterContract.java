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
 * 创建时间：2017/11/17 12:09
 * 描述：
 */

public interface RegisterContract {
    interface Model extends BaseModel {
        Observable<UserEntity> register_m(HashMap<String,Object> map);
        Observable<BaseResult> code_m(HashMap<String,Object> map);
    }
    interface View extends BaseView {
        void register_v(UserEntity t);
        void code_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void register_p(HashMap<String,Object> map);
        public abstract void code_p(HashMap<String,Object> map);
    }
}
