package com.idolmedia.yzy.ui.mvp.model;

import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MessageClassEntity;
import com.idolmedia.yzy.entity.MessageEntity;
import com.idolmedia.yzy.ui.mvp.contract.MessageContract;

import java.util.HashMap;

import rx.Observable;

/**
 * 项目名称：com.idolmedia.yzy.ui.mvp.model
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/23 18:28
 * 描述：
 */

public class MessageModel implements MessageContract.Model {
    @Override
    public Observable<MessageEntity> MessageList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().MessageList(map);
    }

    @Override
    public Observable<MessageClassEntity> QueryMessageList_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().QueryMessageList(map);
    }

    @Override
    public Observable<BaseResult> ToRead_m(HashMap<String, Object> map) {
        return RetrofitHttp.getInstance().ToRead(map);
    }
}
