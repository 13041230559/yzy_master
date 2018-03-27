package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.UserEntity;
import com.mumu.common.base.BaseModel;
import com.mumu.common.base.BasePresenter;
import com.mumu.common.base.BaseView;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.contract
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/5 9:11
 * 描述：
 */

public interface UpdateHeadContract {
    interface Model extends BaseModel {
        Observable<String>UpdateHead_m(@Part List<MultipartBody.Part> partList);
       // Observable<String>  UpdateNickName_m(@FieldMap HashMap<String,Object> map);
    }
    interface View extends BaseView {
        void UpdateHead_v(String t);
       // void UpdateNickName_v(String t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void UpdateHead_p(@Part List<MultipartBody.Part> partList);
       // public abstract void UpdateNickName_p(@FieldMap HashMap<String,Object> map);
    }
}
