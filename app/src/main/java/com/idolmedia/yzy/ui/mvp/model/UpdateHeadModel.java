package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.ui.mvp.contract.UpdateHeadContract;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/5 9:12
 * 描述：
 */

public class UpdateHeadModel implements UpdateHeadContract.Model {
    @Override
    public Observable<String> UpdateHead_m(@Part List<MultipartBody.Part> partList) {
        return RetrofitHttp.getInstance().UpdateHead(partList);
    }
    /*@Override
    public Observable<String> UpdateNickName_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().UpateNickName(map);
    }*/

}
