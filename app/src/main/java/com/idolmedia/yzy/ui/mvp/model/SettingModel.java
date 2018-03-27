package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.UpdateVersionEntity;
import com.idolmedia.yzy.ui.mvp.contract.SettingContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/7 14:06
 * 描述：
 */

public class SettingModel implements SettingContract.Model {

    @Override
    public Observable<UpdateVersionEntity> UpdateVersion_m() {
        return RetrofitHttp.getInstance().UPdateVersion();
    }
}
