package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommentTypeEntity;
import com.idolmedia.yzy.ui.mvp.contract.CommentContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/18 13:56
 * 描述：
 */

public class CommentModel implements CommentContract.Model {
    @Override
    public Observable<CommentEntity> CommentList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CommentList(map);
    }

    @Override
    public Observable<CommentTypeEntity> CommentType_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CommentType(map);
    }

    @Override
    public Observable<BaseResult> ReplyComment_m(List<MultipartBody.Part> partList) {
        return RetrofitHttp.getInstance().ReplyComment(partList);
    }

    @Override
    public Observable<BaseResult> GoodClick_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GoodClick(map);
    }
}
