package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.mvp.contract.AuthenticationContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/22 17:05
 * 描述：
 */

public class AuthenticationModel implements AuthenticationContract.Model {
    @Override
    public Observable<BaseResult> Authentication_m(List<MultipartBody.Part> partList) {
        return RetrofitHttp.getInstance().Authentication(partList);
    }
}
