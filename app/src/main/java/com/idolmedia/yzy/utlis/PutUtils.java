package com.idolmedia.yzy.utlis;

import com.mumu.common.security.Md5Security;
import com.mumu.common.utils.FormatUtil;
import com.umeng.message.PushAgent;

import java.util.HashMap;

import static com.mumu.common.base.BaseApplication.getAppContext;

/**
 * 项目名称：com.idolmedia.yzy.utlis
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 9:53
 * 描述：
 */

public class PutUtils {

    public  static HashMap Put(HashMap map){
        map.putAll(map);
        map.put("device_token", PushAgent.getInstance(getAppContext()).getRegistrationId());
        map.put("device_type","1");
        map.put("FKEY", Md5Security.getMD5("api"+ FormatUtil.dateToStrLong1(new java.util.Date())+"yizhiyu"));
        return map;
    }

    public static String FKEY(){
        return Md5Security.getMD5("api"+ FormatUtil.dateToStrLong1(new java.util.Date())+"yizhiyu");
    }
}
