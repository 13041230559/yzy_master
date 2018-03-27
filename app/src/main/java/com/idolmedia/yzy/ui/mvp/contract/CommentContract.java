package com.idolmedia.yzy.ui.mvp.contract;

import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommentTypeEntity;
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
 * 创建时间：2018/1/18 13:38
 * 描述：评论
 */

public interface CommentContract {
    interface Model extends BaseModel {
        Observable<CommentEntity> CommentList_m(HashMap<String, Object> map);
        Observable<CommentTypeEntity> CommentType_m(HashMap<String, Object> map);
        Observable<BaseResult> ReplyComment_m(@Part List<MultipartBody.Part> partList);
        Observable<BaseResult> GoodClick_m(HashMap<String, Object> map);
    }
    interface View extends BaseView {
        void CommentList_v(CommentEntity t);
        void CommentType_v(CommentTypeEntity t);
        void ReplyComment_v(BaseResult t);
        void GoodClick_v(BaseResult t,int position ,int linkcount);
    }
    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void CommentList_p(HashMap<String,Object> map);
        public abstract void CommentType_p(HashMap<String,Object> map);
        public abstract void ReplyComment_p(@Part List<MultipartBody.Part> partList);
        public abstract void GoodClick_p(HashMap<String,Object> map,int position ,int linkcount);
    }
}
