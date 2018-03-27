package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/22 17:04
 * 描述：
 */

public interface AuthenticationContract {
    interface Model extends BaseModel {
        Observable<BaseResult> Authentication_m(@Part List<MultipartBody.Part> partList);
    }
    interface View extends BaseView {
        void Authentication_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void Authentication_p(@Part List<MultipartBody.Part> partList);

    }
}
