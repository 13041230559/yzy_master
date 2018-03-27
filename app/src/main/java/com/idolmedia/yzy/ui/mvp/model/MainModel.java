package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.UpdateVersionEntity;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.mvp.contract.MainContract;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:47
 * 描述：
 */

public class MainModel implements MainContract.Model {

    @Override
    public Observable<UpdateVersionEntity> UpdateVersion_m() {
        return RetrofitHttp.getInstance().UPdateVersion();
    }
}
