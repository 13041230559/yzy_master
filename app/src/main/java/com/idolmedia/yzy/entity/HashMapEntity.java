package com.idolmedia.yzy.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/4 17:06
 * 描述：
 */

public class HashMapEntity implements Serializable {
    public HashMap<String, Object> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Object> hashMap) {
        this.hashMap = hashMap;
    }

    private HashMap<String,Object> hashMap;

}
