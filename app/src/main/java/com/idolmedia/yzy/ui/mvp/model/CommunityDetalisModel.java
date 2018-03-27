package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommunityCommentDetalisEntity;
import com.idolmedia.yzy.ui.mvp.contract.CommunityDetalisContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/2 17:59
 * 描述：
 */

public class CommunityDetalisModel implements CommunityDetalisContract.Model {
    @Override
    public Observable<CommunityCommentDetalisEntity> CommunityDetalisList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CommunityDetalisList(map);
    }

    @Override
    public Observable<BaseResult> ReplyComment_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CommentUser(map);
    }

    @Override
    public Observable<BaseResult> GoodClick_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().GoodClick(map);
    }
}
