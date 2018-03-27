package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.mvp.contract.FeedBackContract;

import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/22 10:40
 * 描述：
 */

public class FeedBackModel implements FeedBackContract.Model {
    @Override
    public Observable<BaseResult> FeedBack_m(List<MultipartBody.Part> partList) {
        return RetrofitHttp.getInstance().FeedBack(partList);
    }
}
