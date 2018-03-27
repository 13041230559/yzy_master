package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;
import com.idolmedia.yzy.ui.mvp.contract.ReleaseContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:47
 * 描述：
 */

public class ReleaseModel implements ReleaseContract.Model {

    @Override
    public Observable<BaseResult> ReleaseComment_m(List<MultipartBody.Part> partList) {
        return RetrofitHttp.getInstance().ReplyComment(partList);
    }
}
