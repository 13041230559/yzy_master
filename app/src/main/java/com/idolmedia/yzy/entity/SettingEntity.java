package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/17 9:50
 * 描述：
 */

public class SettingEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    private String context;

    public SettingEntity(String name,String s){
         this.name=name;
         this.context=s;
    }
}
