package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/28 10:02
 * 描述：
 */

public class SeckillEntity {
    public  SeckillEntity(String iamge,String title,String price,String context,String source){
        this.iamge=iamge;
        this.title=title;
        this.price=price;
        this.context=context;
        this.source=source;
    }
    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    private  String iamge;
    private  String title;
    private  String price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    private  String context;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private  String source;
}
