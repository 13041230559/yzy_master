package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/12 11:49
 * 描述：
 */

public class StarDetailEntity {
    private  String iamge;
    private  String title;
    private  String time;
    private  String type;
    private  String number;
    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public StarDetailEntity(String iamge,String title,String time,String type,String number){
        this.iamge=iamge;
        this.title=title;
        this.time=time;
        this.type=type;
        this.number=number;

    }

}
