package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/14 11:30
 * 描述：
 */

public class MsgEntity  {
    private String type;
    private String title;
    private String context;
    private String time;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public  MsgEntity(String type,String title,String context,String time){
        this.type=type;
        this.title=title;
        this.context=context;
        this.context=time;

    }

}
