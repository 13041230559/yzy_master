package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommunityCommentDetalisEntity;
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
 * 创建时间：2018/2/2 17:56
 * 描述：
 */

public interface CommunityDetalisContract {

    interface Model extends BaseModel {
        Observable<CommunityCommentDetalisEntity> CommunityDetalisList_m(HashMap<String, Object> map);
        Observable<BaseResult> ReplyComment_m(HashMap<String, Object> map);
        Observable<BaseResult> GoodClick_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void CommunityDetalisList_v(CommunityCommentDetalisEntity t);
        void  ReplyComment_v(BaseResult t);
        void GoodClick_v(BaseResult t);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void CommunityDetalisList_p(HashMap<String,Object> map);
        public abstract void ReplyComment_p(HashMap<String, Object> map);
        public abstract void GoodClick_p(HashMap<String,Object> map);
    }
}
