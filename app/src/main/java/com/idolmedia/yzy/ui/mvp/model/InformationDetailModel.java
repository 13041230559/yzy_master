package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.InformationDetalisEntity;
import com.idolmedia.yzy.ui.mvp.contract.InformationDetailContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/30 15:38
 * 描述：
 */

 public class InformationDetailModel  implements InformationDetailContract.Model{

    @Override
    public Observable<InformationDetalisEntity> InformationDetail_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().InformationDetail(map);
    }

    @Override
    public Observable<CommentEntity> CommentList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().CommentList(map);
    }

    @Override
    public Observable<BaseResult> GoodClick_m(HashMap<String, Object> map) {
        return  RetrofitHttp.getInstance().GoodClick(map);
    }

    @Override
    public Observable<BaseResult> ReplyComment_m(@Part List<MultipartBody.Part> partList) {
        return RetrofitHttp.getInstance().ReplyComment(partList);
    }
}
