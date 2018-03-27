package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.ClassEntity;
import com.idolmedia.yzy.ui.mvp.contract.ClassificationContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/21 14:02
 * 描述：
 */

public class ClassificationModel implements ClassificationContract.Model {
    @Override
    public Observable<ClassEntity> Classification_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().ClassificationList(map);
    }
}
